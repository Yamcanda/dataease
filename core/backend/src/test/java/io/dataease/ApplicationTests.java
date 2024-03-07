package io.dataease;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationTests {

    public static void main(String[] args) throws Exception {
        System.setProperty("spring.profiles.active", "dev");
        Application.main(args);
    }

}
