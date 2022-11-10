package co.develhope.libraryManagement.model.entities;

import co.develhope.libraryManagement.utils.PlaceOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "warehouse")
public class Warehouse extends PlaceOfWork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany(mappedBy = "warehouse")
    private List<Stocktaking> stocktaking;
    @OneToMany(mappedBy = "warehouse")
    private Set<Store> stores;

    public Warehouse(){}

    public Warehouse(Long id, List<Stocktaking> stocktaking, Set<Store> stores) {
        this.id = id;
        this.stocktaking = stocktaking;
        this.stores = stores;
    }

    public Warehouse(String name, String city, String address, LocalTime openingTime, LocalTime closingTime,
                     Long id, List<Stocktaking> stocktaking, Set<Store> stores) {
        super(name, city, address, openingTime, closingTime);
        this.id = id;
        this.stocktaking = stocktaking;
        this.stores = stores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Stocktaking> getStocktaking() {
        return stocktaking;
    }

    public void setStocktaking(List<Stocktaking> stocktaking) {
        this.stocktaking = stocktaking;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    @Deprecated
    public Stocktaking getStocktackingByBookId(Long bookId){
        Stocktaking stocktakings = null;
        for (Stocktaking stock: stocktaking) {
            if(stock.getBook().getId().equals(bookId)){
                stocktakings = stock;
            }
        }
        return stocktakings;
    }

}
