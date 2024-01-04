package ru.biponline.accountinteacherhours.pojo;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.biponline.accountinteacherhours.entity.Discipline;
import ru.biponline.accountinteacherhours.entity.Group;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GforD {
    @SerializedName("class_id")
    private Long discipline;
    @SerializedName("group_id")
    private Long group;
    @Override
    public String toString() {
        return "discipline=" + discipline +
                ", group=" + group;
    }
}
