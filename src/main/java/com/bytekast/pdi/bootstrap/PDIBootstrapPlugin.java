package com.bytekast.pdi.bootstrap;

import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.PluginTypeInterface;
import org.pentaho.di.ui.spoon.SpoonLifecycleListener;
import org.pentaho.di.ui.spoon.SpoonPerspective;
import org.pentaho.di.ui.spoon.SpoonPlugin;
import org.pentaho.di.ui.spoon.SpoonPluginCategories;
import org.pentaho.di.ui.spoon.SpoonPluginInterface;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulException;

@SpoonPlugin(id = "pdi-bootstrap-plugin", image = "")
@SpoonPluginCategories({"spoon"})
public class PDIBootstrapPlugin implements SpoonPluginInterface {

  private boolean calledOnce = false;
  private PDIBootstrap application = new PDIBootstrap();

  public static PluginInterface getPluginObject(String pluginId) {
    for (Class<? extends PluginTypeInterface> pluginType : PluginRegistry.getInstance().getPluginTypes()) {
      if (PluginRegistry.getInstance().findPluginWithId(pluginType, pluginId) != null) {
        return PluginRegistry.getInstance().findPluginWithId(pluginType, pluginId);
      }
    }
    return null;
  }

  public static String buildPluginFolderPath() {
    PluginInterface plugin = getPluginObject("pdi-bootstrap-plugin");
    if (plugin != null && plugin.getPluginDirectory() != null) {
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
          if (!calledOnce) {
            calledOnce = true;
            start();
          }
        }
        if (spoonLifeCycleEvent.equals(SpoonLifeCycleEvent.SHUTDOWN)) {
          stop();
        }
      }
    };
  }

  private synchronized void start() {
    RunInPluginClassLoader.run(new Runnable() {
      @Override
      public void run() {
        application.start();
      }
    });
  }

  private synchronized void stop() {
    RunInPluginClassLoader.run(new Runnable() {
      @Override
      public void run() {
        application.stop();
      }
    });
  }
}
