package ru.biponline.accountinteacherhours.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeekDay{
    private Long id;
    private String day_name;

    @Override
    public String toString() {
        return  day_name ;
    }
}
