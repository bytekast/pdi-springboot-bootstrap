package com.bytekast.pdi.bootstrap;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PDIBootstrap {

  private ApplicationContext applicationContext;

  public void start() {
    applicationContext = SpringApplication.run(PDIBootstrap.class, new String[]{});
  }

  public void stop() {
    if (applicationContext != null) {
      SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
        @Override
        public int getExitCode() {
          return 0;
        }
      });
    }
  }

  public boolean isInitialized() {
    return applicationContext != null;
  }

  public static void main(String[] args) {
    (new PDIBootstrap()).start();
  }
}
