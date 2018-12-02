package com.learnx.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class DemoApplication {

    //private static final Logger logger = LogManager.getLogger();

    @Autowired
    DataWarehouse dataWarehouse;

    private static boolean INIT_DATABASE = true;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            //logger.log(Level.INFO, "Spring has been started.");
            //Run code to run after initialization
            if (INIT_DATABASE) {
                dataWarehouse.generate();
            }
            //logger.log(Level.INFO, "Initial data has been loaded.");
        };
    }

}
