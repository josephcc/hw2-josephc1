package engine;

/**
 * A concrete implementation of the abstract RangeExtractionAnnotator class. Uses all the logic in
 * the superclass with the GeneRangeExtractor singleton object that loads
 * ne-en-bio-genia-2.TokenShapeChunker as the tagger.
 * 
 * @author josephcc
 * 
 */
public class GeniaAnnotator extends RangeExtractionAnnotator {

  @Override
  public RangeExtractor getExtractor() {
    return GeneRangeExtractor.getSingletonInstance("ne-en-bio-genia-2.TokenShapeChunker");
  }

  @Override
  public String getName() {
    return "Genia";
  }

}
