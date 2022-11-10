package co.develhope.libraryManagement.model.entities;

import co.develhope.libraryManagement.utils.PlaceOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name = "stores")
public class Store extends PlaceOfWork {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Store(){}
    public Store(String name, String city, String address, LocalTime openingTime, LocalTime closingTime, Long id, Warehouse warehouse) {
        super(name, city, address, openingTime, closingTime);
        this.id = id;
        this.warehouse = warehouse;
    }

    public Store(Long id, Warehouse warehouse) {
        this.id = id;
        this.warehouse = warehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
