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
@Data
@AllArgsConstructor
@NoArgsConstructor
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
