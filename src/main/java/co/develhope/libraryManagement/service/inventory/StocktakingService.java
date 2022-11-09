package co.develhope.libraryManagement.service.inventory;


import co.develhope.libraryManagement.model.entities.Book;
import co.develhope.libraryManagement.model.entities.Stocktaking;
import co.develhope.libraryManagement.model.entities.Warehouse;
import co.develhope.libraryManagement.repositories.invetory.StocktakingRepository;
import co.develhope.libraryManagement.service.library.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StocktakingService {

    @Autowired
    private StocktakingRepository stocktakingRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private WarehouseService warehouseService;

    public Stocktaking createStock(Stocktaking stocktaking, Long bookId, Long warehouseId) throws Exception {
        try {
            Optional<Book> book = bookService.findById(bookId);
            Optional<Warehouse> warehouse = warehouseService.getSingle(warehouseId);
            if(book.isPresent()&& warehouse.isPresent()){
                stocktaking.setBook(book.get());
                stocktaking.setWarehouse(warehouse.get());
                stocktakingRepository.save(stocktaking);
            }
            return stocktaking;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Incorrect input");
        }
    }

    public List<Stocktaking> findAllStocktaking() throws Exception {
        List<Stocktaking> allStock = stocktakingRepository.findAll();
        if(allStock.isEmpty()){
            throw new Exception("Not stocktaking found");
        }
        return allStock;
    }

    public Optional<Stocktaking> findStocktakingById(Long id) throws Exception {
        try{
            return stocktakingRepository.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Id not found");
    }
    }

    public Stocktaking updateStocktaking(Long id,Stocktaking stocktaking) throws Exception {
        if(!stocktakingRepository.existsById(id)){
            throw new Exception("Id doesn't exist");
        }
        stocktaking.setId(id);
        return stocktakingRepository.save(stocktaking);
    }

    public String deleteAllStocktaking() throws Exception {
        try{
            stocktakingRepository.deleteAll();
            return "All stocktaking are deleted";
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Can't delete all stock from db");
        }

    }

    public String deleteSingleStock(Long id) throws Exception {
        try {
            stocktakingRepository.deleteById(id);
            return String.format("Stocktaking with id %d as delete", id);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Stocktaking id not found");
        }
    }

    @Deprecated
    public Stocktaking addCopy(Long stockId,Long bookId,Long warehouseId,int copyToAdd) throws Exception {
        Optional<Stocktaking> stocktakingToBeUpdate = stocktakingRepository.findById(stockId);
        if(stocktakingToBeUpdate.isEmpty()) throw new Exception("Stocktaking not found");
        warehouseService.getSingle(warehouseId).get().getStocktackingByBookId(bookId)
               .setNumberOfCopies(stocktakingToBeUpdate.get().getNumberOfCopies() + copyToAdd);
        return stocktakingToBeUpdate.get();
    }

    public void updateNumberOfCopies(long bookId, long warehouseId, int numOfCopies) throws Exception {
        Optional<Stocktaking> optionalStocktaking = stocktakingRepository.findByBook_IdAndWarehouse_Id(bookId, warehouseId);
        if (optionalStocktaking.isPresent()) {
            Stocktaking stocktaking = optionalStocktaking.get();
            stocktaking.setNumberOfCopies(stocktaking.getNumberOfCopies()+numOfCopies);
            stocktakingRepository.save(stocktaking);
        }
        else {
            // stavo cercando di decrementare il numero di copie (numOfCopies minore di zero) di un inventario
            // che non esiste
            if (numOfCopies < 0) {
                throw new Exception(String.format("Cannot find stocktaking for book: %d and warehouse: %d", bookId, warehouseId));
            }
            // se invece il numero di copie è maggiore di zero allora se l'inventario non esiste non è un errore:
            // è che deve ancora essere creato
            else {
                Stocktaking newStocktaking = new Stocktaking();
                newStocktaking.setBook(bookService.findById(bookId).get());
                newStocktaking.setWarehouse(warehouseService.getSingle(warehouseId).get());
                newStocktaking.setNumberOfCopies(numOfCopies);
                stocktakingRepository.save(newStocktaking);
            }
        }
    }


}
