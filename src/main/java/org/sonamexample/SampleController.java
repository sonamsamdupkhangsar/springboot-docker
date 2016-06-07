package org.sonamexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SampleController {

    private Logger logger = LoggerFactory.getLogger(getClass().toString());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    private String home() {
	logger.info("home controller called");
	return "Hello World!";
    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    @ResponseBody
    private String throwException() {
	logger.info("throwing exception");

	if (true)
	    throw new RuntimeException("test throwing exception");
	return "exception generator!";
    }

    /*
     * public static void main(String[] args) throws Exception {
     * SpringApplication.run(SampleController.class, args); }
     */
}