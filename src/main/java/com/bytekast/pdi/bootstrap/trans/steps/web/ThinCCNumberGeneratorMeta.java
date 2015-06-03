package com.bytekast.pdi.bootstrap.trans.steps.web;

import com.bytekast.pdi.bootstrap.trans.steps.web.model.ThinCCGeneratorModel;
import com.bytekast.pdi.bootstrap.trans.steps.web.model.ThinCreditCardEntry;
import org.pentaho.di.core.annotations.Step;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.steps.randomccnumber.RandomCCNumberGeneratorMeta;
import org.pentaho.di.trans.steps.randomccnumber.RandomCreditCardNumberGenerator;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author Rowell Belen
 */
@Step( id = "ThinCCNumberGenerator", image = "PaloCellInput.png",
   i18nPackageName = "com.bytekast.pdi.bootstrap.trans.steps.web", name = "ThinCCNumberGenerator.TransName",
   description = "ThinCCNumberGenerator.TransDescription",
   categoryDescription = "i18n:org.pentaho.di.trans.step:BaseStep.Category.Experimental" )
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class ThinCCNumberGeneratorMeta extends RandomCCNumberGeneratorMeta implements StepMetaInterface {

  public ThinCCGeneratorModel getThinCCGeneratorModel() {

    // map models
    ThinCCGeneratorModel thinCCGeneratorModel = new ThinCCGeneratorModel();
    thinCCGeneratorModel.setLengthFieldName(getCardLengthFieldName());
    thinCCGeneratorModel.setNumberFieldName(getCardNumberFieldName());
    thinCCGeneratorModel.setTypeFieldName(getCardTypeFieldName());

    if(super.getFieldCCType() != null && super.getFieldCCType().length > 0){

      ThinCreditCardEntry[] entries = new ThinCreditCardEntry[super.getFieldCCType().length];
      for(int i = 0; i < super.getFieldCCType().length; i++){
        entries[i] = new ThinCreditCardEntry();
        try {
          int type = Integer.parseInt(super.getFieldCCType()[i]);
          entries[i].setType(RandomCreditCardNumberGenerator.getCardName(type));
        }
        catch (Exception e){
          entries[i].setType(super.getFieldCCType()[i]);
        }
        entries[i].setCount(Integer.parseInt(super.getFieldCCSize()[i]));
        entries[i].setLength(Integer.parseInt(super.getFieldCCLength()[i]));
      }
      thinCCGeneratorModel.setCardEntries(entries);
    }

    return thinCCGeneratorModel;
  }

  public void setThinCCGeneratorModel(ThinCCGeneratorModel thinCCGeneratorModel) {

    // map models
    setCardLengthFieldName(thinCCGeneratorModel.getLengthFieldName());
    setCardNumberFieldName(thinCCGeneratorModel.getNumberFieldName());
    setCardTypeFieldName(thinCCGeneratorModel.getTypeFieldName());

    super.allocate( 0 ); // clear entries
    ThinCreditCardEntry[] entries = thinCCGeneratorModel.getCardEntries();
    if(entries != null && entries.length > 0 ){
      int count = entries.length;
      super.allocate( count );

      for ( int i = 0; i < count; i++ ) {
        super.getFieldCCType()[i] = entries[i].getType();
        super.getFieldCCLength()[i] = entries[i].getLength() + "";
        super.getFieldCCSize()[i] = entries[i].getCount() + "";
      }
    }
  }
}
