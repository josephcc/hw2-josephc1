

/* First created by JCasGen Thu Oct 09 19:52:25 EDT 2014 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Thu Oct 09 19:52:25 EDT 2014
 * XML source: /Users/josephcc/git/courses/software_engineering/hw2-josephc1/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class Annotation extends org.apache.uima.jcas.tcas.Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Annotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Annotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Annotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Annotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Annotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: casProcessorId

  /** getter for casProcessorId - gets The unique identifier of the CAS processor that added this annotation to the CAS
   * @generated
   * @return value of the feature 
   */
  public String getCasProcessorId() {
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_casProcessorId == null)
      jcasType.jcas.throwFeatMissing("casProcessorId", "edu.cmu.deiis.types.Annotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Annotation_Type)jcasType).casFeatCode_casProcessorId);}
    
  /** setter for casProcessorId - sets The unique identifier of the CAS processor that added this annotation to the CAS 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCasProcessorId(String v) {
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_casProcessorId == null)
      jcasType.jcas.throwFeatMissing("casProcessorId", "edu.cmu.deiis.types.Annotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((Annotation_Type)jcasType).casFeatCode_casProcessorId, v);}    
   
    
  //*--------------*
  //* Feature: confidence

  /** getter for confidence - gets The confidence level assigned to the Annotation by the CAS processor that created it.
   * @generated
   * @return value of the feature 
   */
  public double getConfidence() {
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "edu.cmu.deiis.types.Annotation");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Annotation_Type)jcasType).casFeatCode_confidence);}
    
  /** setter for confidence - sets The confidence level assigned to the Annotation by the CAS processor that created it. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setConfidence(double v) {
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "edu.cmu.deiis.types.Annotation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Annotation_Type)jcasType).casFeatCode_confidence, v);}    
  }

    