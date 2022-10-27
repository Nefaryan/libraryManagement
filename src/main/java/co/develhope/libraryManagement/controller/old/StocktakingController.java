package co.develhope.libraryManagement.controller.old;

import co.develhope.libraryManagement.model.entities.Stocktaking;
import co.develhope.libraryManagement.service.inventory.StocktakingService;
import co.develhope.libraryManagement.utils.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocktaking")
@PreAuthorize("hasRole('"+ Roles.CUSTOMER + "') OR hasRole('"+Roles.ADMIN+"')")
public class StocktakingController {

    @Autowired
    private StocktakingService stocktakingService;

    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @PostMapping("/create")
    public ResponseEntity<?> createStock(@RequestBody Stocktaking stocktaking,
                                         @RequestParam Long bookId,@RequestParam Long warehouseId){
        try {
            logger.info("Insert a stock");
            return ResponseEntity.status(HttpStatus.OK).body(stocktakingService.createStock(stocktaking,bookId,warehouseId));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStock() {
        try {
            logger.info("Get all Stock");
            return ResponseEntity.status(HttpStatus.OK).body(stocktakingService.findAllStocktaking());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getStockById(@PathVariable Long id){
        try {
            logger.info("Get Stock by Id");
            return ResponseEntity.status(HttpStatus.OK).body(stocktakingService.findStocktakingById(id));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestParam Stocktaking stocktaking) {
        try {
            logger.info("Update Stock");
            return ResponseEntity.status(HttpStatus.OK).body(stocktakingService.updateStocktaking(id, stocktaking));
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/addCopy")
    public ResponseEntity<?> updateCopy(@RequestParam Long stockId,@RequestParam Long bookId,
                                     @RequestParam Long warehouseId,@RequestParam int copyToadd){
        try {
            logger.info("Update number of copy");
            return ResponseEntity.status(HttpStatus.OK).body(stocktakingService.addCopy(stockId,bookId,warehouseId,copyToadd));
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteStockById/{id}")
    public ResponseEntity<?> deleteStockById(@PathVariable Long id) {
        try{
            logger.info("Delete a stock by id");
            return ResponseEntity.status(HttpStatus.OK).body(stocktakingService.deleteSingleStock(id));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteAllStock")
    public ResponseEntity<?> deleteAllStock(){
        try{
            logger.info("Delete all stock ");
            return ResponseEntity.status(HttpStatus.OK).body(stocktakingService.deleteAllStocktaking());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
