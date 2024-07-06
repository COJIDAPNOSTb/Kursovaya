package org.example.spring;


import org.example.parcer.Parcer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws SQLException, InterruptedException {
        SpringApplication.run(DemoApplication.class, args);
        //Parcer.runParsing();


    }
}