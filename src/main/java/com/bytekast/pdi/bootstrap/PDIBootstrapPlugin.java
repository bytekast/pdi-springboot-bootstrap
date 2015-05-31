package com.bytekast.pdi.bootstrap;

import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.PluginTypeInterface;
import org.pentaho.di.ui.spoon.*;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulException;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpoonPlugin(id = "pdi-bootstrap-plugin", image = "")
@SpoonPluginCategories({"spoon"})
@SpringBootApplication
public class PDIBootstrapPlugin implements SpoonPluginInterface {

  private ApplicationContext applicationContext;

  public static PluginInterface getPluginObject(String pluginId) {
    for (Class<? extends PluginTypeInterface> pluginType : PluginRegistry.getInstance().getPluginTypes()) {
      if (PluginRegistry.getInstance().findPluginWithId(pluginType, pluginId) != null) {
        return PluginRegistry.getInstance().findPluginWithId(pluginType, pluginId);
      }
    }
    return null;
  }

  public static String buildPluginFolderPath() {
    PluginInterface plugin = getPluginObject( "pdi-bootstrap-plugin" );
    if ( plugin != null && plugin.getPluginDirectory() != null ) {
      return plugin.getPluginDirectory().getFile();
    }
    return null;
  }

  @Override
  public void applyToContainer(String s, XulDomContainer xulDomContainer) throws XulException {
    // TODO
  }

  @Override
  public SpoonPerspective getPerspective() {
    return null;
  }

  @Override
  public SpoonLifecycleListener getLifecycleListener() {
    return new SpoonLifecycleListener() {
      @Override
      public void onEvent(SpoonLifeCycleEvent spoonLifeCycleEvent) {
        if (spoonLifeCycleEvent.equals(SpoonLifeCycleEvent.STARTUP)) {
          start();
        }
        if (spoonLifeCycleEvent.equals(SpoonLifeCycleEvent.SHUTDOWN)) {
          stop();
        }
      }
    };
  }

  private void start() {
    RunInPluginClassLoader.run(new Runnable() {
      @Override
      public void run() {
        PDIBootstrap.main(new String[]{});
      }
    });
  }

  private void stop() {
    RunInPluginClassLoader.run(new Runnable() {
      @Override
      public void run() {
        PDIBootstrap.stop();
      }
    });
  }
}
