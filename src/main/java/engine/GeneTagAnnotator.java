package engine;

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
