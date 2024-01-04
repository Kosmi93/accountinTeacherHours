package ru.biponline.accountinteacherhours.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Class{
    private Long id;
    private String class_name;



    @Override
    public String toString() {
        return  id+class_name;
    }
}

