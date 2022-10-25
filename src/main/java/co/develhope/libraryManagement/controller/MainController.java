package co.develhope.libraryManagement.controller;

import co.develhope.libraryManagement.model.entities.Invoice;
import co.develhope.libraryManagement.service.inventory.InvoiceService;
import co.develhope.libraryManagement.service.inventory.StocktakingService;
import co.develhope.libraryManagement.service.inventory.WarehouseService;
import co.develhope.libraryManagement.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('"+ Roles.CUSTOMER + "') OR hasRole('"+Roles.ADMIN+"')")
public class MainController {

    @Autowired
    StocktakingService stocktakingService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    InvoiceService invoiceService;

    /*
    Funzione per effetuare una vendita.
    Al momento della vendita:
    - viene generata una fattura
    - viene aggiornato l'inventario scalando il numero di copie
     */
    @PostMapping("/sellBook")
    public ResponseEntity sellBook(@RequestParam long bookId,
                                   @RequestParam long userId,
                                   @RequestParam long warehouseId) {

        try {
            // create an invoice
            Invoice invoice = invoiceService.create(bookId, userId);

            // update the inventory
            stocktakingService.updateNumberOfCopies(bookId, warehouseId, -1);

            return ResponseEntity.status(HttpStatus.OK).body(invoice);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    // TODO funzione per aggiornare il magazzino
    /*
    Arrivano 10 nuove copie di un libro --> aggiorno l'inventario
    Questa funzione riceve in ingresso l'id del magazzino e l'id del libro e il numero di copie arrivate
    Se esiste un inventario per quel libro e quel magazzino lo aggiorno, altrimenti lo creo e lo aggiorno
     */
    @PostMapping("/loadBooksIntoWarehouse")
    public ResponseEntity loadBooksIntoWarehouse(@RequestParam long bookId,
                                                 @RequestParam long warehouseId,
                                                 @RequestParam int numOfCopies) {
        try {
            stocktakingService.updateNumberOfCopies(bookId, warehouseId, numOfCopies);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
