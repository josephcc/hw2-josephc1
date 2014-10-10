package engine;

import org.apache.uima.resource.ResourceInitializationException;

/**
 * A concrete implementation of the abstract RangeExtractionAnnotator class. Uses all the logic in
 * the superclass with the PosRangeExtractor object that uses Stanford NLP as the tagger.
 * 
 * @author josephcc
 * 
 */
public class PosAnnotator extends RangeExtractionAnnotator {

  /**
   * handle to the PosRangeExtractor object that loads the Stanford NLP libs so that its only load
   * once
   */
  private RangeExtractor extractor = null;

  /**
   * Lazy instantiate the extractor and return it 
   */
  @Override
  public RangeExtractor getExtractor() {
    if (extractor == null) {
      try {
        extractor = new PosRangeExrtactor();
      } catch (ResourceInitializationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return extractor;
  }

  @Override
  public String getName() {
    return "StanfordPos";
  }

}
