package ru.biponline.accountinteacherhours.pojo;



import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private int number;
    private int shift;
    private boolean changed;
    private WeekDay week_day;
    private PoJoGroup poJoGroup;
    @SerializedName("class")
    private Class myclass;
    private Teacher teacher;
    private Classroom classroom;
    private TimeRange time_range;

    @Override
   public String toString() {
        return  number +
                " " + week_day +
                " " + poJoGroup +
                " " + myclass +
                " " + teacher +
                " " + classroom +
                " " + time_range;
    }
}
