package cn.nickdlk.patchspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PatchSpringApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(PatchSpringApplication.class, args);
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to instantiate factory class for EnvironmentPostProcessor: " + e.getMessage());
        }
    }

}
