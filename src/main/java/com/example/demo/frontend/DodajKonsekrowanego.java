package com.example.demo.frontend;

import com.example.demo.entity.Konsekrowany;
import com.example.demo.pictureResources.ImageClassResourcess;
import com.example.demo.repository.Repo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route("add_new")
@RolesAllowed("User")
public class DodajKonsekrowanego extends VerticalLayout {

    private final TextField textFieldImie = new TextField("name");
    private final TextField textFieldNazwisko = new TextField("lastname");
    private final TextField textFielOaza = new TextField("oasis");
    private final DatePicker picker = new DatePicker("BirthDay");
    private final Button buttonDodaj = new Button("Add new consacrated");
    private final Binder<Konsekrowany> binder = new Binder<>();

    @Autowired
    public DodajKonsekrowanego(Repo repository) {

        picker.setWeekNumbersVisible(true);

        var homeMenu = new Button("Home");
        homeMenu.setIcon(new Icon(VaadinIcon.HOME));

        homeMenu.addClickListener(buttonClickEvent -> {
            homeMenu.getUI().ifPresent(ui -> ui.navigate(""));
        });

//        textFieldImie.setPattern("\\D*");
//        textFieldImie.setErrorMessage("Please enter a valid name name can not contain numbers");
//
//        textFieldNazwisko.setPattern("\\D*");
//        textFieldNazwisko.setErrorMessage("Please enter a valid lastName lastname can not contain numbers");
//
//        textFielOaza.setPattern("\\D*");
//        textFielOaza.setErrorMessage("Please enter a valid oasis name , oasis name can not contain numbers");

        binder.forField(textFieldImie)
                .withValidator(name -> name.matches("\\D*"), "Please enter a valid name name can not contain numbers")
                .bind(Konsekrowany::getName, Konsekrowany::setName);

        binder.forField(textFieldNazwisko)
                .withValidator(lastname -> lastname.matches("\\D*"), "Please enter a valid lastName lastname can not contain numbers")
                .bind(Konsekrowany::getLastname, Konsekrowany::setLastname);

        binder.forField(textFielOaza)
                .withValidator(oasis -> oasis.matches("\\D*"), "Please enter a valid oasis name, oasis name can not contain numbers")
                .bind(Konsekrowany::getOasis, Konsekrowany::setOasis);

        binder.forField(picker)
                .bind(Konsekrowany::getBirthDay, Konsekrowany::setBirthDay);

        binder.setBean(new Konsekrowany());

        buttonDodaj.setEnabled(false);

        binder.addValueChangeListener(event -> {
            buttonDodaj.setEnabled(binder.isValid());
        });

        buttonDodaj.addClickListener(event -> {
            Konsekrowany konsekrowany = binder.getBean();
            repository.save(konsekrowany);
            Notification notification = new Notification("New consacrated added successfully", 3000);
            notification.open();
            binder.setBean(new Konsekrowany());
        });

        add(textFieldImie, textFieldNazwisko, textFielOaza, picker, buttonDodaj,homeMenu);
    }
}
