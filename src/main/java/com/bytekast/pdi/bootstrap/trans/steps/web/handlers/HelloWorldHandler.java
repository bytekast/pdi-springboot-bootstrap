package com.bytekast.pdi.bootstrap.trans.steps.web.handlers;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.springframework.stereotype.Component;

/**
 * @author Rowell Belen
 */
@Component
public class HelloWorldHandler implements RestHandler {
  @Override
  public HttpHandler handler() {
    return new HttpHandler() {
      @Override
      public void service(Request request, Response response) throws Exception {
        String s = "Hello, World!";
        response.setContentType("text/plain");
        response.setContentLength(s.length());
        response.getWriter().write(s);
      }
    };
  }

  @Override
  public String mapping() {
    return "/hello";
  }
}
