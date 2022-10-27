package co.develhope.libraryManagement.controller.old;

import co.develhope.libraryManagement.model.entities.Book;
import co.develhope.libraryManagement.service.library.AuthorService;
import co.develhope.libraryManagement.service.library.BookService;
import co.develhope.libraryManagement.utils.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@PreAuthorize("hasRole('"+ Roles.CUSTOMER + "') OR hasRole('"+Roles.ADMIN+"')")
public class BookController {

    @Autowired
    private BookService bookService;



    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/create")
    public ResponseEntity<?> insertNewBook(@RequestBody Book book, @RequestParam Long authorId) throws Exception {
        try{
            logger.info("Create a book");
            return ResponseEntity.status(HttpStatus.OK).body(bookService.create(book,authorId));
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
   @GetMapping("/getAllBook")
    public ResponseEntity<?> getAllBook(){
        try {
            logger.info("Getting all book");
            return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
   }

   @GetMapping("/getBookById")
   public ResponseEntity<?> getBookById(@RequestParam Long id){
       try {
           logger.info("Get book by id");
           return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
       } catch (Exception e) {
           logger.error(e.toString());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
   }

    @GetMapping("/getByISBN")
    public ResponseEntity<?> getBookById(@RequestParam String ISBN){
        try {
            logger.info("Get book by id");
            return ResponseEntity.status(HttpStatus.OK).body(bookService.findByISBN(ISBN));
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @RequestParam Long id) {
       try{
           logger.info("Update a book");
           return ResponseEntity.status(HttpStatus.OK).body(bookService.update(book, id));
       } catch (Exception e) {
           logger.error(e.toString());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllBook(){
     try {
         logger.info("Delete all book");
         return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteAll());
     } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
     }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteAuthorById(@RequestParam Long id){
       try {
           logger.info("delete author by id");
           return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteById(id));
       } catch (Exception e) {
         logger.error(e.toString());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

















}
