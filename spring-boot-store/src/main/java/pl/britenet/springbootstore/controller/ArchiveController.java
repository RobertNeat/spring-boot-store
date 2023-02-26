package pl.britenet.springbootstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.store.model.Archive;
import pl.britenet.store.service.ArchiveService;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://127.0.0.1:5500")
@RestController
@RequestMapping("api/v2/archive")
public class ArchiveController {
    public final ArchiveService archiveService;

    @Autowired
    public ArchiveController(ArchiveService archiveService){
        this.archiveService=archiveService;
    }

    @GetMapping("/{commodity_id}")
    public Optional<Archive> getArchive(@PathVariable("commodity_id") int id){
        return this.archiveService.getArchive(id);
    }

    @GetMapping
    public List<Archive> getAllArchive(){
        return this.archiveService.getAllArchive();
    }

    @GetMapping("/group/{customer_id}")
    public List<Archive> getAllArchiveWhereCustomerId(@PathVariable("customer_id") int id){
        return this.archiveService.getAllArchiveWhereCustomerId(id);
    }

    @GetMapping("/max")
    public Optional<Integer> getMaxId(){
        return this.archiveService.getMaxId();
    }

    @PostMapping
    public void insert(@RequestBody Archive archive){
        this.archiveService.insert(archive);
    }

    @PutMapping
    public void update(@RequestBody Archive archive){
        this.archiveService.update(archive);
    }

    @DeleteMapping("/{commodity_id}")
    public void delete(@PathVariable("commodity_id") int id){
        this.archiveService.delete(id);
    }
}
