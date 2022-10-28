package co.develhope.libraryManagement.service.inventory;

import co.develhope.libraryManagement.model.entities.Book;
import co.develhope.libraryManagement.model.entities.Invoice;
import co.develhope.libraryManagement.model.entities.User;
import co.develhope.libraryManagement.repositories.invetory.InvoiceRepository;
import co.develhope.libraryManagement.service.UserService;
import co.develhope.libraryManagement.service.library.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public Invoice create(Long bookId, Long userId) throws Exception {
        try{
            Invoice invoice = new Invoice();
            Optional<Book> book = bookService.findById(bookId);
            Optional<User> user = userService.findUserById(userId);
            if(book.isPresent() && user.isPresent()){
                invoice.setBooks(new ArrayList<>(Arrays.asList(book.get())));
                invoice.setUser(user.get());
                invoice.setTotalPrice(book.get().getPrice());
                invoice.setEmissionDate(LocalDate.now());
                invoiceRepository.save(invoice);
            }
            return invoice;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Incorrect input");
        }
    }

    public Invoice sellmutipleBook(List<Long> bookId,Long userId) throws Exception {
        try {
            Invoice invoice = new Invoice();
            Optional<User> user = userService.findUserById(userId);
            List<Book> books = new ArrayList<>();
            double totalPrice = 0;
            for (Long id: bookId) {
                books.add(bookService.findById(id).get());
            }
            for (Book book : books) {
                totalPrice += book.getPrice();
            }
            if(!books.isEmpty() && user.isPresent()){
                invoice.setBooks(books);
                invoice.setUser(user.get());
                invoice.setEmissionDate(LocalDate.now());
                invoice.setTotalPrice(totalPrice);
                invoiceRepository.save(invoice);
            }
            return invoice;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Incorrect input");
        }

    }

    public Invoice update(Long id, Invoice invoice) throws Exception {
        if(!invoiceRepository.existsById(id)){
            throw new Exception("Id doesn't exist");
        }
        invoice.setId(id);
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoice() throws Exception {
      List<Invoice> invoices = invoiceRepository.findAll();
      if(invoices.isEmpty()){
          throw new Exception("Not invoice found");
      }
      return invoices;
    }

    public Optional<Invoice> getSingle(Long id) throws Exception {
       try{
           return invoiceRepository.findById(id);
       }catch (Exception e){
           e.printStackTrace();
        throw new Exception("Store id not found");
       }
    }

    public String deleteAll() throws Exception {
        try{
            invoiceRepository.deleteAll();
            return "All invoice are deleted";
        }catch (Exception e){
            throw new Exception("Can't delete invoices from db");
        }
    }

    public String deleteSingle(Long id) throws Exception {
        try {
            invoiceRepository.deleteById(id);
            return String.format("Invoice with id %d as delete", id);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Id not found");
        }
    }






}
