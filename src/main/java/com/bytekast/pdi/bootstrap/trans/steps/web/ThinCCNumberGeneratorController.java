package com.bytekast.pdi.bootstrap.trans.steps.web;

import com.bytekast.pdi.bootstrap.trans.steps.web.model.ThinCCGeneratorModel;
import com.bytekast.pdi.bootstrap.trans.steps.web.util.RuntimeConfig;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @author Rowell Belen
 */
@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = true, preConstruction = true)
public class ThinCCNumberGeneratorController {

  @Autowired
  private RuntimeConfig runtimeConfig;

  private ThinCCNumberGeneratorDialog dialog;

  public ThinCCNumberGeneratorController(ThinCCNumberGeneratorDialog dialog) {
    this.dialog = dialog;
  }

  @PostConstruct // called after dependencies are injected
  public void setup() {
  }

  public ThinCCGeneratorModel getModel(String id) {
    return dialog.getModel();
  }

  public void applyModel(String id, ThinCCGeneratorModel remoteModel) {
    if (remoteModel == null) {
      return;
    }

    dialog.setModel(remoteModel);
    this.dialog.close();
  }

  public void help(String id) {
    this.dialog.showHelp();
  }

  public void cancel(String id) {
    // do nothing
    this.dialog.close();
  }

  public String getUrl() {
    return "http://localhost:" + runtimeConfig.getPort() + "/static/thinccgenerator/index.html";
  }

  @PreDestroy
  public void destroy() {
    this.dialog = null; // remove reference for garbage collection
  }
}
