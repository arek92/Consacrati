package com.example.demo.frontend;

import com.example.demo.entity.Konsekrowany;
import com.example.demo.repository.Repo;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route("Czy ktos ma urodziny")
@PermitAll
public class CzyKtosMaUrodziny extends VerticalLayout {

    private Repo repo;


    @Autowired
    public CzyKtosMaUrodziny(Repo repo) {


        var homeMenu = new Button("Menu Główne");
        homeMenu.setIcon(new Icon(VaadinIcon.HOME));

        homeMenu.addClickListener(buttonClickEvent -> {
            homeMenu.getUI().ifPresent(ui -> ui.navigate(""));

        });
        Label label = new Label("Wybierz date");
        DatePicker picker = new DatePicker();
        Button findDate = new Button("Wyszukaj");
        Grid<Konsekrowany> konsekrowanyGrid = new Grid<>(Konsekrowany.class);


        findDate.addClickListener(buttonClickEvent -> {



            LocalDate dzisiejszaData;
            dzisiejszaData = picker.getValue();
            int day = dzisiejszaData.getDayOfMonth();
            int month =dzisiejszaData.getMonthValue();
            List<Konsekrowany> konsekrowany = repo.findAll();
            List<Konsekrowany> collect = konsekrowany
                    .stream()
                    .filter(kon -> kon.getBirthDay().getDayOfMonth() == day && kon.getBirthDay().getMonthValue() == month).toList();
            konsekrowanyGrid.setItems(collect);
            if (collect.isEmpty()){
                Notification notification = new Notification("Dzisiaj nikt nie ma urodzin",5000);
                notification.open();
            }


        });


        add(label, picker, findDate,konsekrowanyGrid,homeMenu);
    }


}

