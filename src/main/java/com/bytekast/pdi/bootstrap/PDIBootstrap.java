package com.bytekast.pdi.bootstrap;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PDIBootstrap {

  private static ApplicationContext applicationContext;

  public static void main(String[] args) {
    applicationContext = SpringApplication.run(PDIBootstrap.class, args);
  }

  public  static void stop() {
    SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
      @Override
      public int getExitCode() {
        return 0;
      }
    });
  }
}
