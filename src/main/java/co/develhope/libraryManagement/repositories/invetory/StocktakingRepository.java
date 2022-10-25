package co.develhope.libraryManagement.repositories.invetory;

import co.develhope.libraryManagement.model.entities.Stocktaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocktakingRepository extends JpaRepository<Stocktaking,Long> {
}
