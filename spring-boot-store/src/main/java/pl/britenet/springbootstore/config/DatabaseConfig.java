package pl.britenet.springbootstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.britenet.store.database.DatabaseService;

@Configuration
public class DatabaseConfig {
    @Bean
    public DatabaseService getDatabaseService(){
        return new DatabaseService();
    }
}
