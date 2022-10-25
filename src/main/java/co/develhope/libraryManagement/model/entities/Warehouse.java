package co.develhope.libraryManagement.model.entities;

import co.develhope.libraryManagement.utils.PlaceOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
