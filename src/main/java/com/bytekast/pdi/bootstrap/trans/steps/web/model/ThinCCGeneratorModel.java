package com.bytekast.pdi.bootstrap.trans.steps.web.model;

/**
 * @author Rowell Belen
 */
public class ThinCCGeneratorModel {

  private String stepName;

  private String numberFieldName;

  private String typeFieldName;

  private String lengthFieldName;

  private ThinCreditCardEntry[] cardEntries;

  public String getStepName() {
    return stepName;
  }

  public void setStepName(String stepName) {
    this.stepName = stepName;
  }

  public String getNumberFieldName() {
    return numberFieldName;
  }

  public void setNumberFieldName(String numberFieldName) {
    this.numberFieldName = numberFieldName;
  }

  public String getTypeFieldName() {
    return typeFieldName;
  }

  public void setTypeFieldName(String typeFieldName) {
    this.typeFieldName = typeFieldName;
  }

  public String getLengthFieldName() {
    return lengthFieldName;
  }

  public void setLengthFieldName(String lengthFieldName) {
    this.lengthFieldName = lengthFieldName;
  }

  public ThinCreditCardEntry[] getCardEntries() {
    return cardEntries;
  }

  public void setCardEntries(ThinCreditCardEntry[] cardEntries) {
    this.cardEntries = cardEntries;
  }
}
