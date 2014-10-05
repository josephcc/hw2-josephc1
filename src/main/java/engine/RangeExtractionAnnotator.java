package engine;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import model.Gene;
import model.Sentence;

/**
 * 
 * Extract Range of different information based on a strategy.
 * 
 * @author josephcc
 * 
 */

abstract public class RangeExtractionAnnotator extends JCasAnnotator_ImplBase {

  /**
   * Extractor implements RangeExtractor interface is the algorithm (strategy) used, by default
   * extracts genes
   */
  abstract public RangeExtractor getExtractor();
  
  abstract public String getName();

  /**
   * Process Sentence CAS
   * 
   * Extract Sentence type annotations from CAS and process them by extract gene sequences
   */
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {

//    FSIterator<Annotation> iter = aJCas.getAnnotationIndex(Sentence.type).iterator();

//    while (iter.hasNext()) {
//      Sentence sentence = (Sentence) iter.next();

      // System.out.println(sentence.getId());
      // System.out.println(sentence.getText());
      Map<Integer, Integer> genes = getExtractor().getSpans(aJCas.getDocumentText());
      for (Entry<Integer, Integer> range : genes.entrySet()) {
        Integer begin = range.getKey();
        Integer end = range.getValue();
        String name = aJCas.getDocumentText().substring(begin, end);
        // System.out.println(begin + "/" + end + " -> " + name);

        String sentId = ((Sentence) aJCas.getJFSIndexRepository().getAllIndexedFS(Sentence.type).next()).getId();
        if (sentId == null) {
          System.out.println("noooooo " + getName());
        }
        
        Gene gene = new Gene(aJCas);
        gene.setBegin(begin);
        gene.setEnd(end);
        gene.setGene(name);
//        gene.setId(sentence.getId());
//        gene.setText(aJCas.getDocumentText());
        gene.setProcessor(getName());
        gene.addToIndexes();

//      }
      // System.out.println("-----");
    }

  }
}
