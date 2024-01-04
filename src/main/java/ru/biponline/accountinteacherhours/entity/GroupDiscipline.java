package ru.biponline.accountinteacherhours.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group_discipline")
public class GroupDiscipline {

    private int remainder;
    private Integer semesterOne;
    private Integer semesterTwo;
    @Id
    @JoinColumn(name = "discipline_id")
    @ManyToOne
    private Discipline discipline;
    @Id
    @JoinColumn(name = "group_id")
    @ManyToOne
    private Group group;

    public GroupDiscipline(Long discipline_id,Long group_id) {
        this.discipline = new Discipline(discipline_id,"");
        this.group = new Group(group_id,"");
    }

    @Override
    public String toString() {
        return  discipline +
                " " + group;
    }
}
