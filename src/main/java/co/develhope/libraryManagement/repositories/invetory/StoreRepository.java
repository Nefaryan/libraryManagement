package co.develhope.libraryManagement.repositories.invetory;

import co.develhope.libraryManagement.model.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
}
