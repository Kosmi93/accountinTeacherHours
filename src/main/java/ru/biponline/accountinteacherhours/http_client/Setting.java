package ru.biponline.accountinteacherhours.http_client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
public class Setting {
        private String uri;
        private String schedule;
        private String teachers;
        private String groups;
        @JsonProperty("classes")
        private String discipline;
        private String weekDays;
        private String time;
        private String classrooms;
        @JsonProperty("classes-for-groups")
        private String classesForGroups;

}
