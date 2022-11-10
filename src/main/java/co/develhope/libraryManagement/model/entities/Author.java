package co.develhope.libraryManagement.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name ="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private Integer numberOfBookWrite;

    @OneToMany(mappedBy = "author", orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Book> books = new LinkedHashSet<>();

    public Author() {}

    public Author(Long id, String name, String surname, LocalDate dateOfBirth, Integer numberOfBookWrite, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.numberOfBookWrite = numberOfBookWrite;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getNumberOfBookWrite() {
        return numberOfBookWrite;
    }

    public void setNumberOfBookWrite(Integer numberOfBookWrite) {
        this.numberOfBookWrite = numberOfBookWrite;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "surname = " + surname + ", " +
                "dateOfBirth = " + dateOfBirth + ", " +
                "numberOfBookWrite = " + numberOfBookWrite + ")";
    }
}
