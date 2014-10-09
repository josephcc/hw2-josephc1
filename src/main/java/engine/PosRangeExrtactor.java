package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.uima.resource.ResourceInitializationException;

import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
//import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class PosRangeExrtactor  implements RangeExtractor  {

  private StanfordCoreNLP pipeline;

  public PosRangeExrtactor() throws ResourceInitializationException {
    Properties props = new Properties();
    props.put("annotators", "tokenize, ssplit, pos");
    pipeline = new StanfordCoreNLP(props);
  }


  public List<Map<String, Object>> getSpans(String text) {
    
    ArrayList<Map<String, Object>> out = new ArrayList<Map<String, Object>>();

    Annotation document = new Annotation(text);
    pipeline.annotate(document);

    for (CoreLabel token : document.get(TokensAnnotation.class)) {
      String pos = token.get(PartOfSpeechAnnotation.class);
//      String word = token.get(TextAnnotation.class);
      int begin = token.beginPosition();
      int end = token.endPosition();

      Map<String,Object> gene = new HashMap<String, Object>();

      gene.put("begin", begin);
      gene.put("end", end);
      gene.put("score", -1.0);
      gene.put("type", pos);

      out.add(gene);
      
    }
    return out;
  }

}
