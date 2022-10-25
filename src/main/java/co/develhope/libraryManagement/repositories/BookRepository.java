package co.develhope.libraryManagement.repositories;

import co.develhope.libraryManagement.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByISBN(String ISBN);
}
