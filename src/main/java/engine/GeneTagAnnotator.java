package engine;

/**
 * A concrete implementation of the abstract RangeExtractionAnnotator class. Uses all the logic in
 * the superclass with the GeneRangeExtractor singleton object that loads
 * ne-en-bio-genetag.HmmChunker as the tagger.
 * 
 * @author josephcc
 * 
 */
public class GeneTagAnnotator extends RangeExtractionAnnotator {

  @Override
  public RangeExtractor getExtractor() {
    return GeneRangeExtractor.getSingletonInstance("ne-en-bio-genetag.HmmChunker");
  }

  @Override
  public String getName() {
    return "GeneTag";
  }
}
