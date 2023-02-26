package pl.britenet.springbootstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.store.model.Commodity;
import pl.britenet.store.service.CommodityService;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://127.0.0.1:5500")
@RestController
@RequestMapping("api/v2/commodity")
public class CommodityController {
    public final CommodityService commodityService;

    @Autowired
    public CommodityController(CommodityService commodityService){
        this.commodityService=commodityService;
    }

    @GetMapping("/{commodity_id}")
    public Optional<Commodity> getCommodity(@PathVariable("commodity_id") int id){
        return this.commodityService.getCommodity(id);
    }

    @GetMapping
    public List<Commodity> getAllCommodity(){
        return this.commodityService.getAllCommodity();
    }

    @GetMapping("/group/{basket_id}")
    public List<Commodity> getAllCommodityWhereBasketId(@PathVariable("basket_id") int basket_id){
        return this.commodityService.getAllCommodityWhereBasketId(basket_id);
    }

    @GetMapping("/max")
    public Optional<Integer> getMaxId(){
        return this.commodityService.getMaxId();
    }

    @PostMapping
    public void insert(@RequestBody Commodity commodity){
        this.commodityService.insert(commodity);
    }

    @PutMapping
    public void update(@RequestBody Commodity commodity){
        this.commodityService.update(commodity);
    }

    @DeleteMapping("/{commodity_id}")
    public void delete(@PathVariable("commodity_id") int id){
        this.commodityService.delete(id);
    }

}
