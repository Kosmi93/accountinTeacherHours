package ru.biponline.accountinteacherhours.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeekDay {
    @Id
    private Long id;
    private String day_name;

    @Override
    public String toString() {
        return  day_name ;
    }
}
