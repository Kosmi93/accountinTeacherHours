package ru.biponline.accountinteacherhours.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "speciality",fetch = FetchType.EAGER)
    private List<Group> groups = new ArrayList<>();;

    public Speciality(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return  title;
    }
}
