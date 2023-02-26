package pl.britenet.springbootstore.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.britenet.springbootstore.model.LoginCredentials;
import pl.britenet.springbootstore.model.LoginResponse;
import pl.britenet.store.model.Basket;
import pl.britenet.store.model.Customer;
import pl.britenet.store.model.builder.BasketBuilder;
import pl.britenet.store.service.BasketService;
import pl.britenet.store.service.CustomerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final Map<String,Integer> loggedInTokens;
    private final CustomerService customerService;
    private final BasketService basketService;

    @Autowired
    public AuthService(CustomerService customerService, BasketService basketService) {
        this.customerService = customerService;
        this.basketService = basketService;
        this.loggedInTokens = new HashMap<>();
    }

    public LoginResponse login(LoginCredentials loginCredentials){
        Optional<Customer> customer;
        loginCredentials.setPassword(DigestUtils.sha256Hex(loginCredentials.getPassword()));
        //System.out.println(loginCredentials.getUsername()+"-"+loginCredentials.getPassword());
        customer=this.customerService.getCustomer(loginCredentials.getUsername(),loginCredentials.getPassword());
        if(customer.isPresent()){
            String token= UUID.randomUUID().toString();
            int userId= customer.get().getCustomer_id();
            this.loggedInTokens.put(token,userId);
            return new LoginResponse(token,userId);
        }
        throw new IllegalStateException("Invalid credentials");
    }

    public boolean isLoggedIn(String token){
        return  this.loggedInTokens.containsKey(token);
    }

    public void register(Customer customer){
        customer.setPassword(DigestUtils.sha256Hex(customer.getPassword()));
        customerService.insert(customer);

        //create basket during registration
        Basket basket = new Basket();
        basket.setBasket_id(customerService.getMaxId().get());//może jednak powinno dodawac o jeden wiecej???
        basketService.insert(basket);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-[max-customer_id]"+customerService.getMaxId().get());
    }
}
