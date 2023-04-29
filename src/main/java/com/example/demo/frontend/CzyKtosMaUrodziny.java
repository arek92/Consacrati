package com.example.demo.frontend;

import com.example.demo.entity.Konsekrowany;
import com.example.demo.repository.Repo;
import com.example.demo.service.Service;
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

@Route("Czy ktos ma urodziny")
@RolesAllowed("User")
public class CzyKtosMaUrodziny extends VerticalLayout {


    @Autowired
    private Repo repo;


    @Autowired
    Service service = new Service();






    public CzyKtosMaUrodziny() {


        var homeMenu = new Button("Menu Główne");
        homeMenu.setIcon(new Icon(VaadinIcon.HOME));

        homeMenu.addClickListener(buttonClickEvent -> {
            homeMenu.getUI().ifPresent(ui -> ui.navigate(""));

        });
        Label label = new Label("Wybierz date");
        DatePicker picker = new DatePicker();
        Button findDate = new Button("Wyszukaj");
        Grid<Konsekrowany> konsekrowanyGrid = new Grid<>(Konsekrowany.class);
        Button wyslijEmail = new Button("Wyslij Email z zyczeniami");


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

        wyslijEmail.addClickListener(buttonClickEvent -> {
            service.sendSimpleEmail("arek<kera0604@o2.pl>","test","Tanti Auguri Arek");
            Notification notification = new Notification("Pomyslnie wyslano maila",5000);
            notification.open();

        });



//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("from@example.com");
//            message.setTo("arkadiuszgalus85@gmail.com");
//            message.setSubject("Test email");
//            message.setText("Tanti Auguri Arkadiusz !!.");
//
//            sender.send(message);
//
//
//        });


        add(label, picker, findDate,konsekrowanyGrid,homeMenu,wyslijEmail);
    }


}

