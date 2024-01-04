package ru.biponline.accountinteacherhours.loadingData;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.biponline.accountinteacherhours.entity.*;
import ru.biponline.accountinteacherhours.pojo.GforD;
import ru.biponline.accountinteacherhours.pojo.PoJoGroup;
import ru.biponline.accountinteacherhours.pojo.Class;
import ru.biponline.accountinteacherhours.http_client.HTTPutils;
import ru.biponline.accountinteacherhours.http_client.Setting;
import ru.biponline.accountinteacherhours.repo.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

//загружаем данные с сервера для формирования базы
// нужно получить
    //учителей
    //дисциплины
    //группы
    //группы-дисциплины
//To-do обработать все исключения
public class InitialDB {
    private static final TeacherRepo teacherRepo = new TeacherRepo();
    private static final DisciplineRepo disciplineRepo = new DisciplineRepo();
    private static final WeekDayRepo weekDayRepo = new WeekDayRepo();
    private static final GroupRepo groupRepo = new GroupRepo();
    private static final GroupDisciplineRepo groupDisciplineRepo = new GroupDisciplineRepo();

    private static final HTTPutils client = new HTTPutils();
    private static final Gson gson = new Gson();
    private static final JavaPropsMapper propsMapper = new JavaPropsMapper();
    private static final Setting config;

    static {
        try {
            config = propsMapper.readValue(new File("./src/main/resources/settings.properties"),Setting.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //даже работает
    public static void initDB() throws IOException {

        getTeachers();
        getDiscipline();
        getGroups();
        getDay();
        getGroupDiscipline();
    }

    private static void getGroupDiscipline() throws IOException {
        String res = client.get(config.getUri()+config.getClassesForGroups());
        System.out.println(res);
        List<GforD> list = gson.fromJson(res, new TypeToken<List<GforD>>(){}.getType());
        list.forEach(System.out::println);
        for (GforD element:list){
            groupDisciplineRepo.save(new GroupDiscipline(element.getDiscipline(), element.getGroup()));
        }
    }
    private static void getGroups() throws IOException {
        String res = client.get(config.getUri()+config.getGroups());
        List<PoJoGroup> list = gson.fromJson(res, new TypeToken<List<PoJoGroup>>(){}.getType());
        for (PoJoGroup element:list){
            groupRepo.save(new Group(element.getId(),element.getGroup_name()));
        }
    }
    private static void getDay() throws IOException {
        String res = client.get(config.getUri()+config.getWeekDays());
        List<WeekDay> list = gson.fromJson(res, new TypeToken<List<WeekDay>>(){}.getType());

        for (WeekDay element:list){
            weekDayRepo.save(element);
        }
    }
    private static void getTeachers() throws IOException {
        //получаем преподавателей
        String res = client.get(config.getUri()+config.getTeachers());
        // dataRepo.getTable(DateTime.class);
        List<Teacher> teachers = gson.fromJson(res, new TypeToken<List<Teacher>>(){}.getType());
        for (Teacher t: teachers){
            teacherRepo.save(t);
        }
    }
    private static void getDiscipline() throws IOException {


        String res = client.get(config.getUri()+config.getDiscipline());
        List<Class> disciplines = gson.fromJson(res, new TypeToken<List<Class>>(){}.getType());
        for (Class d: disciplines){
            disciplineRepo.save(new Discipline(d.getId(),d.getClass_name()));
        }

    }



}
