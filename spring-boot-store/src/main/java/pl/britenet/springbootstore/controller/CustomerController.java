package pl.britenet.springbootstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.store.model.Customer;
import pl.britenet.store.service.CustomerService;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://127.0.0.1:5500")
@RestController
@RequestMapping("api/v2/customer")
public class CustomerController {
    public final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @GetMapping("/{customer_id}")
    public Optional<Customer> getCustomer(@PathVariable("customer_id") int id){
        return this.customerService.getCustomer(id);
    }

    @GetMapping
    public List<Customer> getAllCustomer(){
        return this.customerService.getAllCustomer();
    }

    @GetMapping("/credentials/{username}/{password}")
    public Optional<Customer> getCustomer(@PathVariable("username") String username,@PathVariable("password") String password){
        return this.customerService.getCustomer(username,password);
    }

    @GetMapping("/max")
    public Optional<Integer> getMaxId(){
        return this.customerService.getMaxId();
    }


    @PostMapping
    public void insert(@RequestBody Customer customer){
        this.customerService.insert(customer);
    }

    @PutMapping
    public void update(@RequestBody Customer customer){
        this.customerService.update(customer);
    }

    @DeleteMapping("/{customer_id}")
    public void delete(@PathVariable("customer_id") int id){
        this.customerService.delete(id);
    }
}
