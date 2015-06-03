package com.bytekast.pdi.bootstrap.trans.steps.web.util;

import org.springframework.stereotype.Component;
import org.springframework.util.SocketUtils;

import javax.annotation.PostConstruct;

/**
 * @author Rowell Belen
 */
@Component
public class RuntimeConfig {

  private int port;

  @PostConstruct
  private void init() {
    port = SocketUtils.findAvailableTcpPort();
  }

  public int getPort() {
    return port;
  }
}
