package ru.biponline.accountinteacherhours.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoJoGroup {
    private Long id;
    private int shift;
    private String group_name;

    @Override
    public String toString() {
        return   group_name;
    }
}