package ru.biponline.accountinteacherhours.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teachers")
public class Teacher {
    @Id
    private Long id;
    private String fullName;
    @OneToMany (cascade =CascadeType.ALL, mappedBy = "teachers",fetch = FetchType.EAGER)
    private List<Report> report;





}
