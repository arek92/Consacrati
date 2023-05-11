package com.example.demo.frontend;
import com.example.demo.entity.Konsekrowany;
import com.example.demo.repository.Repo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route("find")
@RolesAllowed("User")
public class ListaKonsekrowanych extends Div {



    @Autowired
    public ListaKonsekrowanych(Repo repository) {


        var homeMenu = new Button("Home");
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

        Grid.Column<Konsekrowany> deleteColumn = grid.addComponentColumn(konsekrowany -> {
            Button deleteButton = new Button("Delete", new Icon(VaadinIcon.TRASH));
            deleteButton.addClickListener(event -> {
                repository.delete(konsekrowany);
                konsekrowanyList.remove(konsekrowany);
                Notification notification = new Notification("Konsekrowany has been removed successfully",3000);
                notification.open();
                //dataView.refreshAll();
            });
            return deleteButton;
        });
        deleteColumn.setHeader("Actions");

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
            boolean matchesDate = matchesTerm(String.valueOf(konsekrowany.getBirthDay()), searchTerm);

            return matchesFullName || matchesEmail || matchesProfession || matchesDate;
        });


        add(grid, findField, homeMenu);

    }

        private boolean matchesTerm(String value, String searchTerm) {
        return value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
