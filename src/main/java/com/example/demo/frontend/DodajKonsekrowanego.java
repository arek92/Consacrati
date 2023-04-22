package com.example.demo.frontend;

import com.example.demo.entity.Konsekrowany;
import com.example.demo.repository.Repo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

@Route("Dodaj_Konsekrowanego")
@PermitAll
public class DodajKonsekrowanego extends VerticalLayout {

    @Autowired
    public DodajKonsekrowanego(Repo repository){
        TextField textFieldImie = new TextField("imie");
        TextField textFieldNazwisko = new TextField("Nazwisko");
        TextField textFielOaza = new TextField("Oaza");
        DatePicker picker = new DatePicker("Podaj date urodzenia");
        picker.setWeekNumbersVisible(true);
        Button buttonDodaj = new Button("Dodaj Konsekrowanego");

        buttonDodaj.addClickListener(buttonClickEvent -> {
            Konsekrowany konsekrowany = new Konsekrowany();
            konsekrowany.setName(textFieldImie.getValue());
            konsekrowany.setLastname(textFieldNazwisko.getValue());
            konsekrowany.setOasis(textFielOaza.getValue());
            konsekrowany.setBirthDay(picker.getValue());
            repository.save(konsekrowany);
            Notification notification = new Notification("Konsekrowany został dodany do bazy danych ",3000);
            notification.open();


        });

        add(textFieldImie,textFieldNazwisko,textFielOaza,picker,buttonDodaj);





    }
}
