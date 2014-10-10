package engine;

import org.apache.uima.resource.ResourceInitializationException;

public class PosAnnotator extends RangeExtractionAnnotator {

  private RangeExtractor extractor = null;

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
