package pl.britenet.springbootstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.store.model.Basket;
import pl.britenet.store.service.BasketService;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://127.0.0.1:5500")
@RestController
@RequestMapping("api/v2/basket")
public class BasketController {
    public final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService){
        this.basketService=basketService;
    }

    @GetMapping("/{basket_id}")
    public Optional<Basket> getBasket(@PathVariable("basket_id") int id){
        return this.basketService.getBasket(id);
    }

    @GetMapping
    public List<Basket> getAllBasket(){
        return this.basketService.getAllBasket();
    }

    @GetMapping("/max")
    public Optional<Integer> getMaxId(){
        return this.basketService.getMaxId();
    }

    @PostMapping
    public void insert(@RequestBody Basket basket){
        this.basketService.insert(basket);
    }

    @PutMapping
    public void update(@RequestBody Basket basket){
        this.basketService.update(basket);
    }

    @DeleteMapping("/{basket_id}")
    public void delete(@PathVariable("basket_id") int id){
        this.basketService.delete(id);
    }
}
