package ru.biponline.accountinteacherhours.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeRange{
    private Long id;
    private String range;
    private int class_number;
    private int week_day_number;

    @Override
    public String toString() {
        return  range
                + " " + class_number
                + " " + week_day_number;
    }
}
