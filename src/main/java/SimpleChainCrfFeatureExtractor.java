
import com.aliasi.crf.ChainCrfFeatureExtractor;
import com.aliasi.crf.ChainCrfFeatures;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.HashMap;



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
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(String _feature : _features) {
				map.put(_feature, Integer.valueOf(1));
			}
			return map;
        }
        public Map<String,Integer> edgeFeatures(int n, int k) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("PREV_TAG_" + tag(k), Integer.valueOf(1));
			map.put("PREV_TAG_TOK_" + tag(k) + "_" + token(n-1), Integer.valueOf(1));
			return map;
        }
    }
}
