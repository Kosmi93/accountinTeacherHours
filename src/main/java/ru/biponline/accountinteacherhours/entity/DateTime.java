package ru.biponline.accountinteacherhours.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_time")
    private LocalDate date;
    @OneToMany (cascade =CascadeType.ALL, mappedBy = "date", fetch = FetchType.EAGER)
    private List<Report> reports;



    public DateTime(LocalDate date){
        this.date=date;
    }

    @Override
    public String toString() {
        return  date.toString() ;
    }
}
