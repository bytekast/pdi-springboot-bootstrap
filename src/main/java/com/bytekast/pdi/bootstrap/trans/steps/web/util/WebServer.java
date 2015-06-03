package com.bytekast.pdi.bootstrap.trans.steps.web.util;

import com.bytekast.pdi.bootstrap.trans.steps.web.handlers.RestHandler;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.Map;

/**
 * @author Rowell Belen
 */
@Component
@EnableScheduling
public class WebServer {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private RuntimeConfig runtimeConfig;

  private HttpServer server;

  @Scheduled(fixedDelay = 1800000)
  public void heartBeat() {
    // keep server alive
  }

  @PostConstruct
  public void start() throws Exception {

    server = HttpServer.createSimpleServer(".", runtimeConfig.getPort());

    // Auto register Http handlers
    Map<String, RestHandler> map = applicationContext.getBeansOfType(RestHandler.class);
    Collection<RestHandler> handlers = map.values();
    for (RestHandler handler : handlers) {
      if (handler == null || handler.handler() == null || handler.mapping() == null) {
        continue;
      }
      server.getServerConfiguration().addHttpHandler(handler.handler(), handler.mapping());
    }

    // Add the CLStaticHttpHandler to serve static resources from the jar
    server.getServerConfiguration().addHttpHandler(
       new CLStaticHttpHandler(WebServer.class.getClassLoader(), "static/"), "/static");

    try {
      server.start();
    }
    catch (Exception e) {
      System.err.println(e);
    }
  }

  @PreDestroy
  public void stop() {
    server.shutdownNow();
  }
}
