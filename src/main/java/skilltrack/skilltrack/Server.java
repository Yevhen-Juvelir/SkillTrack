package skilltrack.skilltrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("skilltrack.skilltrack.entity") // Указываем где искать Entity
@EnableJpaRepositories("skilltrack.skilltrack.repository") // Указываем где искать Repository
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
        System.out.println("server started on port 8080");
    }
}