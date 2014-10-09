package engine;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.aliasi.crf.ChainCrf;
import com.aliasi.tag.Tagging;
import com.aliasi.util.AbstractExternalizable;

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
//      String type = "others";
//      if (c >= 'A' && c <= 'Z') {
//        type = "upper";
//      } else if (c >= 'a' && c <= 'z') {
//        type = "lower";
//      } else if (c >= '0' && c <= '9') {
//        type = "number";
//      } else if (c == ' ') {
//        type = "space";
//      }
//      features.add("CHAR_" + type);
      features.add("CHAR_IS_" + c);
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
      if (fs.size() < 1)
        continue;
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
    
    String sentId = ((Sentence) jcas.getAnnotationIndex(Sentence.type).iterator().next()).getId();
    
    try {
      @SuppressWarnings("unchecked")
      ChainCrf<String> crf = (ChainCrf<String>) AbstractExternalizable.readResourceObject("/version3.1000.ChainCrf");
      
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("features.txt", true)));
      ArrayList<String> words = extractFeatureMap(jcas.getAnnotationIndex(Gene.type).iterator(), jcas.getDocumentText());
      String _words = words.get(0);
      if (words.size() > 1) {
        for(int i=1; i<words.size(); ++i) {
          _words = _words + "\t" + words.get(i); 
        }
      }
      out.println(sentId + ":::" + _words);
      out.close();
      
      Tagging<String> tagging = crf.tag(words);
      String pattern = "";
      for(String tag : tagging.tags()) {
        pattern = pattern + tag;
      }

//      System.out.println(pattern);
      
      Pattern bi = Pattern.compile("BI*");
      Matcher matcher = bi.matcher(pattern);
      
      while(matcher.find()) {
        int begin = matcher.start();
        int end = matcher.end();
        String name = jcas.getDocumentText().substring(begin, end);
        String preText = jcas.getDocumentText().substring(0, begin);
        int preSpace = preText.length() - preText.replaceAll(" ", "").length();
        int inSpace = name.length() - name.replaceAll(" ", "").length();
        String format = sentId + "|" + (begin - preSpace) + " "
                + (end - preSpace - inSpace - 1) + "|" + name;

        outFile.println(format);
//        System.out.println(format);
      }
      
//      System.out.println("\n");
      
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch (ClassNotFoundException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
   
  }
}
