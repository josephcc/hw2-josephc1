package engine;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Gene;
import model.Sentence;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.resource.ResourceSpecifier;

/**
 * Output to file
 * 
 * GeneConsumer process model.Gene objects by fixing the space offsets and writes them to the output
 * file
 * 
 * @author josephcc
 * 
 */
public class GeneConsumer extends CasConsumer_ImplBase {

  /**
   * Handle to the final output file
   */
  private PrintWriter outFile;

  /**
   * Path to the final output file
   */
  private final String filename = "hw1-joseph1.out";

  /**
   * Initializer
   * 
   * Initialize output file handle
   * 
   * @see org.apache.uima.collection.CasConsumer_ImplBase#initialize(org.apache.uima.resource.ResourceSpecifier,
   *      java.util.Map)
   */
  @Override
  public boolean initialize(ResourceSpecifier aSpecifier, Map<String, Object> aAdditionalParams)
          throws ResourceInitializationException {
    boolean out = super.initialize(aSpecifier, aAdditionalParams);
    try {
      outFile = new PrintWriter(filename, "UTF-8");
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return out;
  }

  /**
   * Release resources
   * 
   * Release output file handle before class is destroyed
   * 
   * @see org.apache.uima.collection.CasConsumer_ImplBase#destroy()
   */
  @Override
  public void destroy() {
    outFile.close();
    super.destroy();
  }

  private ArrayList<String> extractFeatureMap(FSIterator<Annotation> it, String text) {
    ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>(text.length());
    for (int i=0; i<text.length(); i++) {
      ArrayList<String> features = new ArrayList<String>();
      char c = text.charAt(i);
      String type = "others";
      if (c >= 'A' && c <= 'Z') {
        type = "upper";
      } else if (c >= 'a' && c <= 'z') {
        type = "lower";
      } else if (c >= '0' && c <= '9') {
        type = "number";
      } else if (c == ' ') {
        type = "space";
      }
      features.add("CHAR_" + type);
      map.add(features);
    }
    while (it.hasNext()) {
      Gene gene = (Gene) it.next();
      String type = gene.getCategory();
      String processor = gene.getProcessor();
      int begin = gene.getBegin();
      int end = gene.getEnd();
      for(int i=begin; i<end; i++) {
        map.get(i).add(processor + "_" + type);
      }
    }
    ArrayList<String> words = new ArrayList<String>(text.length());
    for(ArrayList<String> fs : map) {
      String word = fs.get(0);
      for(int i=1; i<fs.size(); ++i) {
        word = word + ":::" + fs.get(i);
      }
      words.add(word);
    }
    return words;
  }
  
  /**
   * Gather data for output
   * 
   * This function is called multiple-times during runtime, each time with a CAS and potentially
   * multiple annotations.
   * 
   * 
   * @see org.apache.uima.collection.base_cpm.CasObjectProcessor#processCas(org.apache.uima.cas.CAS)
   */
  public void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    
    FSIterator<Annotation> it;
    
    String sentId = ((Sentence) jcas.getAnnotationIndex(Sentence.type).iterator().next()).getId();
    try{
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("features.txt", true)));
      out.println(sentId + ":::" + extractFeatureMap(jcas.getAnnotationIndex(Gene.type).iterator(), jcas.getDocumentText()));
      out.close();
    }catch (IOException e) {
      //exception handling left as an exercise for the reader
    }
    it = jcas.getAnnotationIndex(Gene.type).iterator();
    while (it.hasNext()) {
      Gene gene = (Gene) it.next();
      String preText = jcas.getDocumentText().substring(0, gene.getBegin());
      String name = gene.getGene();
      int preSpace = preText.length() - preText.replaceAll(" ", "").length();
      int inSpace = name.length() - name.replaceAll(" ", "").length();
      preSpace = 0;
      inSpace = 0;

      String out = sentId + "|" + (gene.getBegin() - preSpace) + " "
              + (gene.getEnd() - preSpace - inSpace - 1) + "|" + gene.getGene() + "|" + gene.getCategory() + "|" + gene.getScore() + "|" + gene.getProcessor();

      outFile.println(out);
//      System.out.println(out);
    }
  }
}
