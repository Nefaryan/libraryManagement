package co.develhope.libraryManagement.controller.old;


import co.develhope.libraryManagement.model.entities.Warehouse;
import co.develhope.libraryManagement.service.inventory.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @PostMapping("/createWarehouse")
    public ResponseEntity<?> insertWarehouse(@RequestBody Warehouse warehouse){
        try{
            logger.info("Create Warehouse");
            return ResponseEntity.status(HttpStatus.OK).body(warehouseService.create(warehouse));
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/updateWarehouse/{id}")
    public ResponseEntity<?> updateWarehouse(@RequestBody Warehouse warehouse, @PathVariable Long id){
        try{
            logger.info("Update a warehouse");
            return ResponseEntity.status(HttpStatus.OK).body(warehouseService.update(id, warehouse));
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllWarehouse(){
        try{
            logger.info("Get al warehouse");
            return ResponseEntity.status(HttpStatus.OK).body(warehouseService.getAll());
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getWarehouseById(@RequestParam Long id){
        try {
            logger.info("Find warehouse by id");
            return ResponseEntity.status(HttpStatus.OK).body(warehouseService.getSingle(id));
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllWarehouse(){
        try{
            logger.info("Delete all warehouse");
            return ResponseEntity.status(HttpStatus.OK).body(warehouseService.deleteAll());
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteWareHouseById(@RequestParam Long id){
        try {
            logger.info("Delete warehouse by Id");
            return ResponseEntity.status(HttpStatus.OK).body(warehouseService.deleteSingle(id));
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




}
