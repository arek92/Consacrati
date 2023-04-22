package com.example.demo.frontend;

import com.example.demo.entity.Konsekrowany;
import com.example.demo.repository.Repo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Route("Czy ktos ma urodziny")
@PermitAll
public class CzyKtosMaUrodziny extends VerticalLayout {

    private Repo repo;



    @Autowired
    public CzyKtosMaUrodziny(Repo repo) {
        Label label = new Label("Wybierz date");
        DatePicker picker = new DatePicker();
        Button findDate = new Button("Wyszukaj");
        Label nieMaUrodzin = new Label("dzisiaj nikt nie ma urodzin");





        findDate.addClickListener(buttonClickEvent -> {


            LocalDate dzisiejszaData;
            dzisiejszaData = picker.getValue();
            String day = String.valueOf(dzisiejszaData.getDayOfMonth());
            String month = String.valueOf(dzisiejszaData.getMonthValue());
            List<Konsekrowany>konsekrowanyList = repo.findAll();
            for (Konsekrowany konsekrowany : repo.findAll()){
                Notification notification;
                if (String.valueOf(konsekrowany.getBirthDay().getDayOfMonth()).equals(day) && String.valueOf(konsekrowany.getBirthDay().getMonthValue()).equals(month)){
                    notification = new Notification("Dzisiaj urodziny ma : " + " " + konsekrowany,3000);
                    notification.open();

                }
                else {
                   notification = new Notification("Dzisiaj nikt nie ma urodzin",3000);
                  // continue;
                }
                notification.open();


            }

        });

        add(label, picker, findDate);
    }
}
