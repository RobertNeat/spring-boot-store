package pl.britenet.springbootstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.service.*;

@Configuration
public class ServiceConfig {
    private final DatabaseService databaseService;
    @Autowired
    public ServiceConfig(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    @Bean
    public ArchiveService getArchiveService(){
        return new ArchiveService(this.databaseService);
    }

    @Bean
    public BasketService getBasketService(){
        return new BasketService(this.databaseService);
    }

    @Bean
    public BookService getBookService(){
        return new BookService(this.databaseService);
    }

    @Bean
    public CommodityService getCommodityService(){
        return new CommodityService(this.databaseService);
    }

    @Bean
    public CustomerService getCustomerService(){
        return new CustomerService(this.databaseService);
    }

}
