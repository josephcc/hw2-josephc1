import com.aliasi.crf.ChainCrfFeatureExtractor;
import com.aliasi.crf.ChainCrfFeatures;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class SimpleChainCrfFeatureExtractor
    implements ChainCrfFeatureExtractor<String>,
               Serializable {

    static final long serialVersionUID = -744679625659290324L;

    public ChainCrfFeatures<String> extract(List<String> tokens,
                                            List<String> tags) {
        return new SimpleChainCrfFeatures(tokens,tags);
    }

    static class SimpleChainCrfFeatures
        extends ChainCrfFeatures<String> {

        public SimpleChainCrfFeatures(List<String> tokens,
                                      List<String> tags) {
            super(tokens,tags);



        }
        public Map<String,Integer> nodeFeatures(int n) {
			String[] _features = token(n).split(":::");
			String[] _features2;
			String[] _features3;

			if (n > 0) {
				_features2 = token(n-1).split(":::");
			} else {
				_features2 = new String[1];
				_features2[0] = "SENT_START";
			}
			if (n + 1 < numTokens()) {
				_features3 = token(n+1).split(":::");
			} else {
				_features3 = new String[1];
				_features3[0] = "SENT_END";
			}
			Arrays.sort(_features);
			Arrays.sort(_features2);
			Arrays.sort(_features3);
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(String _feature : _features) {
				map.put(_feature, Integer.valueOf(1));
			}

			for(int i=0; i<_features.length -1; ++i){
				for(int j=i+1; j<_features.length; ++j) {
					map.put(_features[i] + ":" + _features[j], Integer.valueOf(1));
					for(int k=j+1; k<_features.length; ++k) {
						map.put(_features[i] + ":" + _features[j] + ":" + _features[k],  Integer.valueOf(1));
					}
				}
			}

			// bigrams
			for(String curr : _features) {
				for(String prev : _features2) {
					map.put("Bigram_prev_" + prev + ":" + curr, Integer.valueOf(1));
				}
				for(String next : _features3) {
					map.put("Bigram_next_" + curr + ":" + next, Integer.valueOf(1));
				}
			}

			for(String prev : _features2) {
				for(String curr : _features) {
					for(String next : _features3) {
						map.put("Trigram_" + prev + ":" + curr + ":" + next, Integer.valueOf(1));
					}
				}
			}


			return map;
        }
        public Map<String,Integer> edgeFeatures(int n, int k) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("PREV_TAG_" + tag(k), Integer.valueOf(1));
			String[] _features = token(n).split(":::");
			for(String _feature : _features) {
				map.put("CURRENT_TOK_PREV_TAG" + _feature + ":" + tag(k), Integer.valueOf(1));
			}
			return map;
        }
    }
}
