package com.joseyustiz.msvcdojo.configserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigServerApplicationTests {

	@Test
	public void contextLoads() {
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(ConfigServerApplication.class)
                .properties("spring.config.name=configserver").run("--server.port=0")) {
        }

    }

}
