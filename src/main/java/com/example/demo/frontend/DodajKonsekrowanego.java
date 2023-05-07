package com.example.demo.frontend;

import com.example.demo.pictureResources.ImageClassResourcess;
import com.example.demo.entity.Konsekrowany;
import com.example.demo.repository.Repo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route("add_new")
@RolesAllowed("User")
public class DodajKonsekrowanego extends VerticalLayout {

    @Autowired
    public DodajKonsekrowanego(Repo repository){



        var homeMenu = new Button("Home");
        homeMenu.setIcon(new Icon(VaadinIcon.HOME));

        homeMenu.addClickListener(buttonClickEvent -> {
            homeMenu.getUI().ifPresent(ui -> ui.navigate(""));

        });

        var ressources = new ImageClassResourcess();
        TextField textFieldImie = new TextField("name");
        TextField textFieldNazwisko = new TextField("lastname");
        TextField textFielOaza = new TextField("oasis");
        DatePicker picker = new DatePicker("BirthDay");
        picker.setWeekNumbersVisible(true);
        Button buttonDodaj = new Button("Dodaj Konsekrowanego");


        buttonDodaj.addClickListener(buttonClickEvent -> {
            Konsekrowany konsekrowany = new Konsekrowany();
            konsekrowany.setName(textFieldImie.getValue());
            konsekrowany.setLastname(textFieldNazwisko.getValue());
            konsekrowany.setOasis(textFielOaza.getValue());
            konsekrowany.setBirthDay(picker.getValue());
            repository.save(konsekrowany);
            Notification notification = new Notification("new consacrated added successfully ",3000);
            notification.open();


        });




        add(textFieldImie,textFieldNazwisko,textFielOaza,picker,buttonDodaj,homeMenu,ressources);





    }
}
