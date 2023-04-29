package com.example.demo.frontend;

import com.example.demo.entity.Konsekrowany;
import com.example.demo.repository.Repo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route("Znajdz konsekrowanego")
@RolesAllowed("User")
public class ListaKonsekrowanych extends Div {



    @Autowired
    public ListaKonsekrowanych(Repo repository) {


        var homeMenu = new Button("Menu Główne");
        homeMenu.setIcon(new Icon(VaadinIcon.HOME));

        homeMenu.addClickListener(buttonClickEvent -> {
            homeMenu.getUI().ifPresent(ui -> ui.navigate(""));

        });





        List<Konsekrowany> konsekrowanyList = repository.findAll();
        Grid<Konsekrowany> grid = new Grid<>();

        grid.addColumn(Konsekrowany::getName).setHeader("First name");
        grid.addColumn(Konsekrowany::getLastname).setHeader("Last name");
        grid.addColumn(Konsekrowany::getOasis).setHeader("Oasis");
        grid.addColumn(Konsekrowany::getBirthDay).setHeader("BirthDay");

        grid.setItems(konsekrowanyList);
        GridListDataView<Konsekrowany> dataView = grid.setItems(konsekrowanyList);

        var findField = new TextField();
        findField.setPlaceholder("Search");
        findField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        findField.setValueChangeMode(ValueChangeMode.EAGER);
        findField.addValueChangeListener(e -> dataView
                .refreshAll());

        dataView.addFilter(konsekrowany -> {
            String searchTerm = findField.getValue().trim();

            boolean matchesFullName = matchesTerm(konsekrowany.getName(),
                    searchTerm);
            boolean matchesEmail = matchesTerm(konsekrowany.getLastname(), searchTerm);
            boolean matchesProfession = matchesTerm(konsekrowany.getOasis(), searchTerm);
            boolean matchesDate = matchesTerm(konsekrowany.getBirthDay().toString(), searchTerm);

            return matchesFullName || matchesEmail || matchesProfession || matchesDate;
        });


        add(grid, findField,homeMenu);

    }

    private boolean matchesTerm(String value, String searchTerm) {
        return value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
