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
    Map<Integer, Integer> genes = getExtractor().getSpans(aJCas.getDocumentText());
    for (Entry<Integer, Integer> range : genes.entrySet()) {
  	Integer begin = range.getKey();
  	Integer end = range.getValue();
  	String name = aJCas.getDocumentText().substring(begin, end);
  	
  	Gene gene = new Gene(aJCas);
  	gene.setBegin(begin);
  	gene.setEnd(end);
  	gene.setGene(name);
  	gene.setProcessor(getName());
  	gene.addToIndexes();

    }

  }
}
