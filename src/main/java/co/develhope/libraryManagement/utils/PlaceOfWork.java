package co.develhope.libraryManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.time.LocalTime;


@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOfWork {

    private String name;
    private String city;
    private String address;
    private LocalTime openingTime;
    private LocalTime closingTime;


}

