package engine;

import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import model.Gene;

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

  /**
   * Provide the name of the extractor to be used as the CasProcessorId
   * 
   * @return name of the extractor
   */
  abstract public String getName();

  /**
   * Process Sentence CAS
   * 
   * Extract Sentence type annotations from CAS and process them by extract gene sequences
   */
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    List<Map<String, Object>> ranges = getExtractor().getSpans(aJCas.getDocumentText());
    for (Map<String, Object> range : ranges) {
      Integer begin = (Integer) range.get("begin");
      Integer end = (Integer) range.get("end");
      Double score = (Double) range.get("score");
      String category = (String) range.get("type");
      String name = aJCas.getDocumentText().substring(begin, end);

      Gene gene = new Gene(aJCas);
      gene.setBegin(begin);
      gene.setEnd(end);
      gene.setGene(name);
      gene.setConfidence(score);
      gene.setCasProcessorId(getName());
      gene.setCategory(category);
      gene.addToIndexes();
    }

  }
}
