package com.example.demo.frontend;

import com.example.demo.entity.Konsekrowany;
import com.example.demo.repository.Repo;
import com.fasterxml.jackson.core.format.DataFormatDetector;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Route("ListaKonsekrowanych")
@RolesAllowed("User")
public class ListaKonsekrowanych extends VerticalLayout {

    @Autowired
    public ListaKonsekrowanych(Repo repository) {

        com.vaadin.flow.component.button.Button findButton = new Button("Wyszukaj ");
        TextField findField = new TextField("Wyszukaj");


        List<Konsekrowany> konsekrowanyList = repository.findAll(); // baza danych


        Grid<Konsekrowany> grid = new Grid<>(Konsekrowany.class);
        grid.setItems(konsekrowanyList);

        findButton.addClickListener(buttonClickEvent -> {

            List<Konsekrowany> collect = repository.findAll()
                    .stream()
                    .filter(konsekrowany -> konsekrowany.getName().equals(findField.getValue()) || konsekrowany.getLastname().equals(findField.getValue())
                            || konsekrowany.getOasis().equals(findField.getValue())
                            || konsekrowany.getBirthDay().toString().equals((findField.getValue())))
                    .collect(Collectors.toList());
            grid.setItems(collect);
        });



        add(grid,findButton,findField);


    }
}
