package engine;

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
