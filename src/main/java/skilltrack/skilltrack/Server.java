package skilltrack.skilltrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.swing.*;
import java.util.Collections;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Server {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Server.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
        app.run(args);
        System.out.println("server started on port 8080");
    }

}
