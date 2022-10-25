package co.develhope.libraryManagement.repositories.invetory;

import co.develhope.libraryManagement.model.entities.Stocktaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StocktakingRepository extends JpaRepository<Stocktaking,Long> {
    Optional<Stocktaking> findByBook_IdAndWarehouse_Id(Long bookId, Long warehouseId);

}
