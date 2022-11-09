package co.develhope.libraryManagement.controller.old;


import co.develhope.libraryManagement.model.entities.Store;
import co.develhope.libraryManagement.service.inventory.StoreService;
import co.develhope.libraryManagement.utils.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@PreAuthorize("hasRole('"+ Roles.CUSTOMER + "') OR hasRole('"+Roles.ADMIN+"')")
public class StoreController {

    @Autowired
    private StoreService storeService;

    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @PostMapping("/create")
    public ResponseEntity<?> createStore(@RequestBody Store store, @RequestParam Long id){
        try{
            logger.info("Create stroe");
            return ResponseEntity.status(HttpStatus.OK).body(storeService.create(store,id));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStore(){
        try {
            logger.info("Get all store");
            return ResponseEntity.status(HttpStatus.OK).body(storeService.getAllStore());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getSingle")
    public ResponseEntity<?> getStoreByID(@RequestParam Long id){
        try {
            logger.info("Get store by Id");
            return ResponseEntity.status(HttpStatus.OK).body(storeService.findStoreById(id));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStore(@RequestBody Store store, @PathVariable Long id){
        try{
            logger.info("Update Store");
            return ResponseEntity.status(HttpStatus.OK).body(storeService.update(id,store));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllStore(){
        try {
            logger.info("Deleting all Store");
            return ResponseEntity.status(HttpStatus.OK).body(storeService.deleteAll());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteBYId/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            logger.info("Deleting Store by Id");
            return ResponseEntity.status(HttpStatus.OK).body(storeService.deleteSingle(id));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
