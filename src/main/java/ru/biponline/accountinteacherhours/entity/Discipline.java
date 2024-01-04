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
public class Discipline {
    @Id
    private Long id;
    private String title;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "discipline_id")
    private List<GroupDiscipline> disciplines;


    public Discipline(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return  title ;
    }
}

