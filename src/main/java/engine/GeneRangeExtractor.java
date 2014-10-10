package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
 * GENETAG and Genia TokenShapeChunker from LingPipe
 * 
 * This use a slightly extended singleton design pattern that stores a set of "singletons" in a Map
 * that can be used to retrieve a "singletons" of different models, to make sure each unique model
 * is only loaded once
 * 
 * @author josephcc
 * 
 */

public class GeneRangeExtractor implements RangeExtractor {

  /**
   * Chunker for GENETAG NER tagger
   */
  private Chunker chunker;

  /**
   * A map that hold all the singletons, each with one preload model from LingPipe
   */
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

  /**
   * Global point of entry to the singleton classes with unique models
   * 
   * @param resourceName
   *          the model for the intended singleton object
   * @return
   */
  public static GeneRangeExtractor getSingletonInstance(String resourceName) {
    if (null == singletonInstances) {
      singletonInstances = new HashMap<String, GeneRangeExtractor>();
    }
    if (!singletonInstances.containsKey(resourceName)) {
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
  public List<Map<String, Object>> getSpans(String text) {
    ArrayList<Map<String, Object>> out = new ArrayList<Map<String, Object>>();

    Chunking chunking = chunker.chunk(text);
    Set<Chunk> chunkSet = chunking.chunkSet();
    Iterator<Chunk> it = chunkSet.iterator();
    while (it.hasNext()) {
      Map<String, Object> gene = new HashMap<String, Object>();
      Chunk chunk = it.next();
      gene.put("begin", chunk.start());
      gene.put("end", chunk.end());
      gene.put("score", chunk.score());
      gene.put("type", chunk.type());

      out.add(gene);
    }
    return out;
  }
}
