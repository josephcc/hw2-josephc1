package engine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

/**
 * Extract Gene mentions in a given text
 * 
 * Implements the RangeExtractor interface and extract gene sequence in a input text using HMM
 * GENETAG from LingPipe
 * 
 * @author josephcc
 * 
 */

public class GeneRangeExtractor implements RangeExtractor {

  /**
   * Chunker for GENETAG NER tagger
   */
  private Chunker chunker;
  

  // Static member holds only one instance of the
  // SingletonExample class
  private static Map<String, GeneRangeExtractor> singletonInstances;

  // SingletonExample prevents any other class from instantiating
  /**
   * Initializer
   * 
   * Load the pre-trained model once when object is initialized called when the singleton is created
   */
  private GeneRangeExtractor(String resourceName) {
    try {
      chunker = (Chunker) AbstractExternalizable.readResourceObject(resourceName);
    } catch (IOException e1) {
      e1.printStackTrace();
      return;
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
      return;
    }
  }

  // Providing Global point of access
  public static GeneRangeExtractor getSingletonInstance(String resourceName) {
    if (null == singletonInstances) {
      singletonInstances = new HashMap<String, GeneRangeExtractor>();
    }
    if (! singletonInstances.containsKey(resourceName)) {
      singletonInstances.put(resourceName, new GeneRangeExtractor("/" + resourceName));
    }
    return singletonInstances.get(resourceName);
  }



  /**
   * Extract gene sequence from text
   * 
   * Find spans of gene appearance in a given English text
   * 
   * @param text
   *          A English sentence that may or may not contains some gene sequence
   * @return A Map that map begin indexes to end indexes of where gene sequence appear in the input
   *         text
   */
  public Map<Integer, Integer> getSpans(String text) {
    Map<Integer, Integer> begin2end = new HashMap<Integer, Integer>();

    Chunking chunking = chunker.chunk(text);
    Set<Chunk> chunkSet = chunking.chunkSet();
    Iterator<Chunk> it = chunkSet.iterator();
    while (it.hasNext()) {
      Chunk chunk = it.next();
      begin2end.put(chunk.start(), chunk.end());
//      chunk.score();
    }
    return begin2end;
  }
}
