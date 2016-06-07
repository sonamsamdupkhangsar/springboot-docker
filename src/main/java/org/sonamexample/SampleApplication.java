package org.sonamexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleApplication {
    private static Logger logger = LoggerFactory.getLogger(SampleApplication.class);

    public static void main(String[] args) {
	logger.info("running RestapiApplication");

	SpringApplication.run(SampleApplication.class, args);
    }
}
