package ru.biponline.accountinteacherhours.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
public class Group{
    @Id
    private Long id;
    private String title;
    @ManyToOne
    private Speciality speciality;
    @OneToMany (cascade =CascadeType.ALL, mappedBy = "group",fetch = FetchType.EAGER)
    private List<Report> reports;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private List<GroupDiscipline> groups;

    public Group(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return  title ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (!Objects.equals(id, group.id)) return false;
        if (!Objects.equals(title, group.title)) return false;
        return Objects.equals(speciality, group.speciality);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        return result;
    }


}

