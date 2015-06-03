package com.bytekast.pdi.bootstrap;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
@EnableSpringConfigured
@ComponentScan(basePackages = {"com.bytekast.pdi.bootstrap"})
public class PDIBootstrap {

  private ApplicationContext applicationContext;

  public void start() {
    SpringApplication app = new SpringApplication(PDIBootstrap.class);
    app.setWebEnvironment(false);
    applicationContext = app.run(new String[]{});
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

  public static void main(String[] args) {
    (new PDIBootstrap()).start();
  }
}
