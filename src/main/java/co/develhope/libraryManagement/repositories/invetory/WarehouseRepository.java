package co.develhope.libraryManagement.repositories.invetory;

import co.develhope.libraryManagement.model.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {
}
