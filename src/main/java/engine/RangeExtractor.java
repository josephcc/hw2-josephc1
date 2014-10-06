package engine;

import java.util.List;
import java.util.Map;

/**
 * Generic range extractor, implement this interface as a strategy for annotators
 * 
 * @author josephcc
 * 
 */
public interface RangeExtractor {
  public List<Map<String, Object>> getSpans(String text);
}
