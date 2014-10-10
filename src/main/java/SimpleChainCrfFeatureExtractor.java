import com.aliasi.crf.ChainCrfFeatureExtractor;
import com.aliasi.crf.ChainCrfFeatures;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Arrays;

/**
 * Feature extractor for pre-trained LingPipe CRF tagger The purpose of this class is similar to the
 * "template configuration" in other CRF libs, e.g., CRF++. The topology of the probability
 * dependency network, i.e., Bigram, Trigram, combining feature types, edge features, is defined by
 * this file. This is deliverately placed under the default package to work with the LingPipe sample
 * codes.
 * 
 * @author josephcc
 * 
 */
public class SimpleChainCrfFeatureExtractor implements ChainCrfFeatureExtractor<String>,
        Serializable {

  /**
   * This Unique ID is for mapping pre-trained model to the correct feature extractor
   */
  static final long serialVersionUID = -744679625659290324L;

  /**
   * return a ChainCrfFeatures<String> class to the LingPipe framework as call back
   */
  public ChainCrfFeatures<String> extract(List<String> tokens, List<String> tags) {
    return new SimpleChainCrfFeatures(tokens, tags);
  }

  /**
   * The actual feature extractor call back class.
   * 
   * @author josephcc
   * 
   */
  static class SimpleChainCrfFeatures extends ChainCrfFeatures<String> {

    /**
     * The constructor is called by the LingPipe framework and context for the sequence is given.
     * 
     * @param tokens used to represent the observables for assigning dependency 
     * @param tags used to represent the outcomes for assigning edge dependency
     */
    public SimpleChainCrfFeatures(List<String> tokens, List<String> tags) {
      super(tokens, tags);

    }

    /**
     * Given a index to a node, return its node dependencies (bigrams, trigrams)
     */
    public Map<String, Integer> nodeFeatures(int n) {
      String[] _features = token(n).split(":::");
      String[] _features2;
      String[] _features3;

      if (n > 0) {
        _features2 = token(n - 1).split(":::");
      } else {
        _features2 = new String[1];
        _features2[0] = "SENT_START";
      }
      if (n + 1 < numTokens()) {
        _features3 = token(n + 1).split(":::");
      } else {
        _features3 = new String[1];
        _features3[0] = "SENT_END";
      }
      Arrays.sort(_features);
      Arrays.sort(_features2);
      Arrays.sort(_features3);
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      // unigrams
      for (String _feature : _features) {
        map.put(_feature, Integer.valueOf(1));
      }

      for (int i = 0; i < _features.length - 1; ++i) {
        for (int j = i + 1; j < _features.length; ++j) {
          map.put(_features[i] + ":" + _features[j], Integer.valueOf(1));
          for (int k = j + 1; k < _features.length; ++k) {
            map.put(_features[i] + ":" + _features[j] + ":" + _features[k], Integer.valueOf(1));
          }
        }
      }

      // unigrams
      for (String curr : _features) {
        for (String prev : _features2) {
          map.put("Unigram_prev_" + prev, Integer.valueOf(1));
        }
        for (String next : _features3) {
          map.put("Unigram_next_" + next, Integer.valueOf(1));
        }
      }

      // bigrams
      for (String curr : _features) {
        for (String prev : _features2) {
          map.put("Bigram_prev_" + prev + ":" + curr, Integer.valueOf(1));
        }
        for (String next : _features3) {
          map.put("Bigram_next_" + curr + ":" + next, Integer.valueOf(1));
        }
      }

      for (String prev : _features2) {
        for (String curr : _features) {
          for (String next : _features3) {
            map.put("Trigram_" + prev + ":" + curr + ":" + next, Integer.valueOf(1));
          }
        }
      }

      return map;
    }

    /**
     * Defined dependencies from node to outcome, i.e., edge dependency
     */
    public Map<String, Integer> edgeFeatures(int n, int k) {
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      map.put("PREV_TAG_" + tag(k), Integer.valueOf(1));
      String[] _features = token(n).split(":::");
      for (String _feature : _features) {
        map.put("CURRENT_TOK_PREV_TAG" + _feature + ":" + tag(k), Integer.valueOf(1));
      }
      return map;
    }
  }
}
