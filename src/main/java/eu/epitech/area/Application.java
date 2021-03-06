package eu.epitech.area;

import eu.epitech.area.link.LinkProcessingThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@SpringBootApplication()
@ComponentScan("eu.epitech.area")
@EnableAutoConfiguration()
@ImportResource("classpath:app-servlet.xml")
@EnableJpaRepositories(basePackages = "eu.epitech.area")
public class Application
{
    @Autowired
    LinkProcessingThread actionProcessingThread;

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

}