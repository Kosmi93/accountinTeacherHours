package ru.biponline.accountinteacherhours;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import ru.biponline.accountinteacherhours.entity.*;
import ru.biponline.accountinteacherhours.loadingData.InitialDB;
import ru.biponline.accountinteacherhours.repo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StartController {

    //Выбор специальности для связи дисциплин
    @FXML
    private ComboBox<Speciality> choosingSpecialtyToConnectDisciplines;
    @FXML
    private ComboBox<Group> choosingGroupToConnectDisciplines;
    @FXML
    private TableView<GroupDiscipline> tableHouse;
    @FXML
    private TableColumn<GroupDiscipline, Discipline> title;
    @FXML
    private TableColumn<GroupDiscipline, Integer> oneSemestr;
    @FXML
    private TableColumn<GroupDiscipline, Integer> twoSemesre;

    //Главная страница
    @FXML
    private DatePicker dateOneDayDatePicker;
    @FXML
    private DatePicker dateSaveDayToDBDatePicker;
    @FXML
    private DatePicker dateStartDatePicker;
    @FXML
    private DatePicker dateStopDatePicker;
    @FXML
    private ComboBox<WeekDay> dayList;
    //нагрузка
    @FXML
    private TextField textAddSpecialty;
    @FXML
    private ComboBox<Speciality> specialityList;
    @FXML
    private ComboBox<Speciality> groupAddSpeciality;
    @FXML
    private ListView<Group> allGroupListV;
    @FXML
    private ListView<Group> groupSpecialityListV;

    //списки
    private ObservableList<Speciality> specialities = FXCollections.observableArrayList();
    private ObservableList<WeekDay> weekDays = FXCollections.observableArrayList();
    private ObservableList<Group> groupsNoSpeciality = FXCollections.observableArrayList();
    private ObservableList<Group> groupsInSpeciality = FXCollections.observableArrayList();
    private ObservableList<Group> allGroups = FXCollections.observableArrayList();
    private ObservableList<GroupDiscipline> disciplines = FXCollections.observableArrayList();
    //репозитории
    private final DisciplineRepo disciplineRepo = new DisciplineRepo();
    private final TeacherRepo teacherRepo = new TeacherRepo();
    private final GroupRepo groupRepo = new GroupRepo();
    private final DataRepo dataRepo = new DataRepo();
    private final GroupDisciplineRepo groupDisciplineRepo = new GroupDisciplineRepo();
    private final WeekDayRepo weekDayRepo = new WeekDayRepo();
    private final SpecialityRepo specialityRepo = new SpecialityRepo();

    @FXML
    private void initialize() {
        //TODO замедляет загрузку приложения, может вынести в отдельный поток
        // или нужна анимация загрузки
        HibernateUtil.getSessionFactory();
        //FIXME  метод get возвращает Optional, нужно организовать проверку
        //установка полей с датой
        LocalDate date = java.time.LocalDate.now();
        dateStartDatePicker.setValue(java.time.LocalDate.of(date.getYear(),date.getMonth(),1));
        dateStopDatePicker.setValue(date);
        dateOneDayDatePicker.setValue(date);
        dateSaveDayToDBDatePicker.setValue(date);
        //заполнение списков
        specialities.addAll(specialityRepo.getAll().get().stream().sorted(Comparator.comparing(Speciality::getTitle)).toList());
        specialityList.setItems(specialities);
        groupAddSpeciality.setItems(specialities);
        weekDays.addAll(weekDayRepo.getAll().get());
        dayList.setItems(weekDays);
        allGroupListV.setItems(groupsNoSpeciality);
        groupSpecialityListV.setItems(groupsInSpeciality);
        choosingSpecialtyToConnectDisciplines.setItems(specialities);
        allGroups.addAll(groupRepo.getAll().get().stream().sorted(Comparator.comparing(Group::getTitle)).toList());
        choosingGroupToConnectDisciplines.setItems(allGroups);

        title.setCellValueFactory(new PropertyValueFactory<GroupDiscipline, Discipline>("discipline"));
        oneSemestr.setCellValueFactory(new PropertyValueFactory<GroupDiscipline, Integer>("semesterOne"));
        twoSemesre.setCellFactory(TextFieldTableCell.<GroupDiscipline, Integer>forTableColumn(new IntegerStringConverter()));
        oneSemestr.setCellFactory(TextFieldTableCell.<GroupDiscipline, Integer>forTableColumn(new IntegerStringConverter()));
        twoSemesre.setCellValueFactory(new PropertyValueFactory<GroupDiscipline, Integer>("semesterTwo"));
        tableHouse.setItems(disciplines);
        tableHouse.setEditable(true);
        //отслеживание редактирования таблицы
        oneSemestr.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<GroupDiscipline, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<GroupDiscipline, Integer> event) {
                GroupDiscipline temp = tableHouse.getSelectionModel().getSelectedItem();
                temp.setSemesterOne(event.getNewValue());
                saveHouse(temp);
            }
        });
        twoSemesre.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<GroupDiscipline, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<GroupDiscipline, Integer> event) {
                GroupDiscipline temp = tableHouse.getSelectionModel().getSelectedItem();
                temp.setSemesterTwo(event.getNewValue());
                saveHouse(temp);
            }
        });
    }

    private void saveHouse(GroupDiscipline temp) {
        groupDisciplineRepo.update(temp);

    }

    @FXML
    void actionAddSpeciality(ActionEvent event) {
        specialityRepo.save(new Speciality(textAddSpecialty.getText()));
        textAddSpecialty.clear();
        specialities.clear();
        specialities.addAll(specialityRepo.getAll().get().stream().sorted(Comparator.comparing(Speciality::getTitle)).toList());
    }

    @FXML
    void actionGetOneDay(ActionEvent event) {
        Speciality s = specialityRepo.getById(1L).get().get(0);
        System.out.println(s.getGroups());
    }

    @FXML
    void actionInitializationDB(ActionEvent event) throws IOException {
        InitialDB.initDB();
    }

    @FXML
    void actionAddGroupToSpeciality(ActionEvent event) {
        if (groupAddSpeciality.getValue() != null) {
            List<Group> groups = groupRepo.getAll().get().stream().sorted(Comparator.comparing(Group::getTitle)).toList();
            groupsNoSpeciality.clear();
            groupsNoSpeciality.addAll(groups.stream().filter(group -> group.getSpeciality() == null).toList());
            groupsInSpeciality.clear();
            groupsInSpeciality.addAll(groups.stream().filter(group -> group.getSpeciality() != null && group.getSpeciality().getId() == groupAddSpeciality.getValue().getId()).toList());
        } else System.out.println("Нужна группа");
    }

    @FXML
    void actionSaveGroutToSpeciality(ActionEvent event) {

    }

    // Привязка/отвязка группы к/от специальности
    @FXML
    void onMouseClickAllGroupList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                Group tempGroup = allGroupListV.getSelectionModel().getSelectedItem();
                tempGroup.setSpeciality(groupAddSpeciality.getSelectionModel().getSelectedItem());
                groupsInSpeciality.add(allGroupListV.getSelectionModel().getSelectedItem());
                System.out.println(tempGroup.getSpeciality());
                groupRepo.update(tempGroup);
                groupsNoSpeciality.remove(allGroupListV.getSelectionModel().getSelectedItem());
            }
        }
    }

    @FXML
    void onMouseClickGroupToSpecialityList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                Group tempGroup = groupSpecialityListV.getSelectionModel().getSelectedItem();
                tempGroup.setSpeciality(null);
                groupsNoSpeciality.add(tempGroup);
                groupRepo.update(tempGroup);
                groupsInSpeciality.remove(tempGroup);
            }
        }
    }

    //Сопоставление дисциплин
    //выбор специальности
    @FXML
    void actionChoosingSpecialtyToConnectDisciplines(ActionEvent event) {
        if (choosingSpecialtyToConnectDisciplines.getValue() != null) {
            Speciality speciality = choosingSpecialtyToConnectDisciplines.getSelectionModel().getSelectedItem();
            disciplines.clear();
            long id = speciality.getGroups().get(0).getId();
            disciplines.addAll(groupDisciplineRepo.getAll().get().stream()
                    .filter(q -> q.getGroup().getId() == id)
                    .toList());
        }
    }

    @FXML
    void actionChoosingGroupToConnectDisciplines(ActionEvent event) {
        if (choosingGroupToConnectDisciplines.getValue() != null) {
            Group group = choosingGroupToConnectDisciplines.getSelectionModel().getSelectedItem();
            disciplines.clear();
            long id = group.getId();
            disciplines.addAll(groupDisciplineRepo.getAll().get().stream()
                    .filter(q -> q.getGroup().getId() == id)
                    .toList());
        }
    }

    @FXML
    void actionDeleteSpeciality(ActionEvent event) {
    }
    //TODO добавить вывод на экран, а не в консоль
    @FXML
    void actionCopyHours(ActionEvent event) {
        specialities.clear();
        specialities.addAll(specialityRepo.getAll().get());
        for (Speciality s : specialities) {
            List<Group> groups = new ArrayList<>();
            groups.addAll(s.getGroups());
            Group groupDefault = groups.get(0);
            if (groups.size()>1){
                System.out.println(groupDefault.getGroups());
                for (int i = 1; i < groups.size(); i++) {
                    //перебираем все дисциплины из нулевой группы
                    for (GroupDiscipline g : groupDefault.getGroups()) {
                        for (int j = 0; j < groups.get(i).getGroups().size(); j++) {
                            GroupDiscipline d= groups.get(i).getGroups().get(j);
                            if(d.getDiscipline().getId()==g.getDiscipline().getId()){
                                d.setSemesterOne(g.getSemesterOne());
                                d.setSemesterTwo(g.getSemesterTwo());
                                groupDisciplineRepo.update(d);
                                break;
                            }
                        }

                    }
                }
            }
        }
        System.out.println("Готовенько");
    }
    // сохранить день в базу
    @FXML
    void actionSaveDayToDB(ActionEvent event) {

        LocalDate date = dateSaveDayToDBDatePicker.getValue();
        dataRepo.save(new DateTime(date));
        System.out.println(date);
    }
}