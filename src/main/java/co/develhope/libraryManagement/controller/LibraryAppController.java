package co.develhope.libraryManagement.controller;

import co.develhope.libraryManagement.model.entities.Invoice;
import co.develhope.libraryManagement.service.inventory.InvoiceService;
import co.develhope.libraryManagement.service.inventory.StocktakingService;
import co.develhope.libraryManagement.service.inventory.WarehouseService;
import co.develhope.libraryManagement.utils.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
@PreAuthorize("hasRole('"+ Roles.CUSTOMER + "') OR hasRole('"+Roles.ADMIN+"')")
public class LibraryAppController {

    @Autowired
    StocktakingService stocktakingService;

    @Autowired
    WarehouseService warehouseService;
    @Autowired
    InvoiceService invoiceService;

    private static final Logger logger = LoggerFactory.getLogger(LibraryAppController.class);

    /*
    Funzione per effetuare una vendita.
    Al momento della vendita:
    - viene generata una fattura
    - viene aggiornato l'inventario scalando il numero di copie
     */
    @PostMapping("/sellBook")
    public ResponseEntity<?> sellBook(@RequestParam long bookId,
                                   @RequestParam long userId,
                                   @RequestParam long warehouseId) {

        try {
            logger.info("Selling book");
            // create an invoice
            Invoice invoice = invoiceService.create(bookId, userId);
            // update the inventory
            stocktakingService.updateNumberOfCopies(bookId, warehouseId, -1);
            return ResponseEntity.status(HttpStatus.OK).body(invoice);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/sellMulti")
    public ResponseEntity<?> sellMultiBook(@RequestParam List<Long> books,
                                           @RequestParam Long userId,
                                           @RequestParam Long warehouseId){
        try{
            logger.info("Selling multi book");
            Invoice invoice = invoiceService.sellmutipleBook(books,userId);
            //update the inventory of all book sold
            for (Long id:books) {
                stocktakingService.updateNumberOfCopies(id,warehouseId,-1);
            }
            return ResponseEntity.status(HttpStatus.OK).body(invoice);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/loadBooksIntoWarehouse")
    public ResponseEntity<?> loadBooksIntoWarehouse(@RequestParam long bookId,
                                                 @RequestParam long warehouseId,
                                                 @RequestParam int numOfCopies) {
        try {
            stocktakingService.updateNumberOfCopies(bookId, warehouseId, numOfCopies);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
