
/* First created by JCasGen Thu Oct 09 22:33:21 EDT 2014 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Thu Oct 09 22:33:21 EDT 2014
 * @generated */
public class NGram_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NGram_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NGram_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NGram(addr, NGram_Type.this);
  			   NGram_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NGram(addr, NGram_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = NGram.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.deiis.types.NGram");
 
  /** @generated */
  final Feature casFeat_elements;
  /** @generated */
  final int     casFeatCode_elements;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getElements(int addr) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "edu.cmu.deiis.types.NGram");
    return ll_cas.ll_getRefValue(addr, casFeatCode_elements);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setElements(int addr, int v) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "edu.cmu.deiis.types.NGram");
    ll_cas.ll_setRefValue(addr, casFeatCode_elements, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getElements(int addr, int i) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "edu.cmu.deiis.types.NGram");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setElements(int addr, int i, int v) {
        if (featOkTst && casFeat_elements == null)
      jcas.throwFeatMissing("elements", "edu.cmu.deiis.types.NGram");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elements), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_elementType;
  /** @generated */
  final int     casFeatCode_elementType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getElementType(int addr) {
        if (featOkTst && casFeat_elementType == null)
      jcas.throwFeatMissing("elementType", "edu.cmu.deiis.types.NGram");
    return ll_cas.ll_getStringValue(addr, casFeatCode_elementType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setElementType(int addr, String v) {
        if (featOkTst && casFeat_elementType == null)
      jcas.throwFeatMissing("elementType", "edu.cmu.deiis.types.NGram");
    ll_cas.ll_setStringValue(addr, casFeatCode_elementType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public NGram_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_elements = jcas.getRequiredFeatureDE(casType, "elements", "uima.cas.FSArray", featOkTst);
    casFeatCode_elements  = (null == casFeat_elements) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_elements).getCode();

 
    casFeat_elementType = jcas.getRequiredFeatureDE(casType, "elementType", "uima.cas.String", featOkTst);
    casFeatCode_elementType  = (null == casFeat_elementType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_elementType).getCode();

  }
}



    