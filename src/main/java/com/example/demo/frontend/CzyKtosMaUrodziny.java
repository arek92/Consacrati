package com.example.demo.frontend;

import com.example.demo.entity.Konsekrowany;
import com.example.demo.repository.Repo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Route("Someone celebrates his birthday?")
@RolesAllowed("User")
public class CzyKtosMaUrodziny extends VerticalLayout {


    //private Repo repo;



    @Autowired
    public CzyKtosMaUrodziny(Repo repo) {


        var homeMenu = new Button("Home");
        homeMenu.setIcon(new Icon(VaadinIcon.HOME));

        homeMenu.addClickListener(buttonClickEvent -> {
            homeMenu.getUI().ifPresent(ui -> ui.navigate(""));

        });
        Label label = new Label("provide data");
        DatePicker picker = new DatePicker();
        Button findDate = new Button("search");
        Grid<Konsekrowany> konsekrowanyGrid = new Grid<>(Konsekrowany.class);
        Button wyslijEmail = new Button("send email with best wishes");


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
                Notification notification = new Notification("today we dont have to sent email with birthdaywishes",5000);
                notification.open();
            }


        });

        wyslijEmail.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate("/Window Send Email");

        });




        add(label, picker, findDate,konsekrowanyGrid,homeMenu,wyslijEmail);
    }


}

