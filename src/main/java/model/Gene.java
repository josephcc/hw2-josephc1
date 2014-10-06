

/* First created by JCasGen Tue Sep 23 17:53:20 EDT 2014 */
package model;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Oct 06 10:40:56 EDT 2014
 * XML source: /Users/josephcc/git/courses/software_engineering/hw2-josephc1/src/main/resources/descriptors/typeSystemDescriptor.xml
 * @generated */
public class Gene extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Gene.class);
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
  protected Gene() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Gene(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Gene(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Gene(JCas jcas, int begin, int end) {
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
  //* Feature: gene

  /** getter for gene - gets 
   * @generated
   * @return value of the feature 
   */
  public String getGene() {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_gene == null)
      jcasType.jcas.throwFeatMissing("gene", "model.Gene");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Gene_Type)jcasType).casFeatCode_gene);}
    
  /** setter for gene - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGene(String v) {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_gene == null)
      jcasType.jcas.throwFeatMissing("gene", "model.Gene");
    jcasType.ll_cas.ll_setStringValue(addr, ((Gene_Type)jcasType).casFeatCode_gene, v);}    
   
    
  //*--------------*
  //* Feature: processor

  /** getter for processor - gets 
   * @generated
   * @return value of the feature 
   */
  public String getProcessor() {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_processor == null)
      jcasType.jcas.throwFeatMissing("processor", "model.Gene");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Gene_Type)jcasType).casFeatCode_processor);}
    
  /** setter for processor - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setProcessor(String v) {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_processor == null)
      jcasType.jcas.throwFeatMissing("processor", "model.Gene");
    jcasType.ll_cas.ll_setStringValue(addr, ((Gene_Type)jcasType).casFeatCode_processor, v);}    
   
    
  //*--------------*
  //* Feature: score

  /** getter for score - gets 
   * @generated
   * @return value of the feature 
   */
  public double getScore() {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "model.Gene");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Gene_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore(double v) {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "model.Gene");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Gene_Type)jcasType).casFeatCode_score, v);}    
   
    
  //*--------------*
  //* Feature: category

  /** getter for category - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCategory() {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_category == null)
      jcasType.jcas.throwFeatMissing("category", "model.Gene");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Gene_Type)jcasType).casFeatCode_category);}
    
  /** setter for category - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCategory(String v) {
    if (Gene_Type.featOkTst && ((Gene_Type)jcasType).casFeat_category == null)
      jcasType.jcas.throwFeatMissing("category", "model.Gene");
    jcasType.ll_cas.ll_setStringValue(addr, ((Gene_Type)jcasType).casFeatCode_category, v);}    
  }

    