package co.develhope.libraryManagement.controller.old;

import co.develhope.libraryManagement.model.entities.Invoice;
import co.develhope.libraryManagement.service.inventory.InvoiceService;
import co.develhope.libraryManagement.utils.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("invoice")
@PreAuthorize("hasRole('"+ Roles.CUSTOMER + "') OR hasRole('"+Roles.ADMIN+"')")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

  /*  @PostMapping("/create")
    public ResponseEntity<?> createInvoice(@RequestParam Long bookId,
                                           @RequestParam Long userId){
        try {
            logger.info("Create Invoice");
            return ResponseEntity.status(HttpStatus.OK).body(invoiceService.create(bookId,userId));

        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }*/

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllInvoice(){
        try {
            logger.info("Get all Invoice");
            return ResponseEntity.status(HttpStatus.OK).body(invoiceService.getAllInvoice());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getSingle/{id}")
    public ResponseEntity<?> getInvoiceByID(@PathVariable Long id){
        try {
            logger.info("Get invoice by Id");
            return ResponseEntity.status(HttpStatus.OK).body(invoiceService.getSingle(id));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInvoice(@RequestBody Invoice invoice, @PathVariable Long id){
        try{
            logger.info("Update Invoice");
            return ResponseEntity.status(HttpStatus.OK).body(invoiceService.update(id,invoice));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllInvoice(){
        try {
            logger.info("Deleting all invoices");
            return ResponseEntity.status(HttpStatus.OK).body(invoiceService.deleteAll());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteBYId/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            logger.info("Deleting invoice by Id");
            return ResponseEntity.status(HttpStatus.OK).body(invoiceService.deleteSingle(id));
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
