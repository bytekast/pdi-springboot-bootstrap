package com.bytekast.pdi.bootstrap.trans.steps.web.handlers;

import org.glassfish.grizzly.http.server.HttpHandler;

/**
 * @author Rowell Belen
 */
public interface RestHandler {
  HttpHandler handler();
  String mapping();
}
