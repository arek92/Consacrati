package com.example.demo.frontend;

import com.example.demo.entity.Konsekrowany;
import com.example.demo.repository.Repo;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@Route("zaktualizuj dane")
@PermitAll
public class AssettiClass extends Div {

    private Optional<Grid.Column<Konsekrowany>> currentColumn = Optional.empty();
    private Optional<Konsekrowany> currentItem = Optional.empty();

    @Autowired
    public AssettiClass(Repo repo) {

        var homeMenu = new Button("Menu Główne");
        homeMenu.setIcon(new Icon(VaadinIcon.HOME));

        homeMenu.addClickListener(buttonClickEvent -> {
            homeMenu.getUI().ifPresent(ui -> ui.navigate(""));

        });



        List<Konsekrowany> konsekrowanyList = repo.findAll();
        Grid<Konsekrowany> grid = new Grid<>(Konsekrowany.class,false);
        grid.setAllRowsVisible(true);
       grid.setItems(konsekrowanyList);
        Notification notification = new Notification("Aby zachowac zmiany odswiez strone",4000);
        notification.open();


        var editor = grid.getEditor();
        var binder = new BeanValidationBinder<>(Konsekrowany.class);

        editor.setBinder(binder);
        editor.setBuffered(true);

        // Save Listener to save the changed SamplePerson
        editor.addSaveListener(event -> {
            Konsekrowany item = event.getItem();
            update(item,repo);
        });

        var colFirstName = grid.addColumn("name");
        var txtFirstName = new TextField();
        binder.forField(txtFirstName).bind("name");
        colFirstName.setEditorComponent(txtFirstName);

        var colLastName = grid.addColumn("lastname");
        var txtLastName = new TextField();
        binder.forField(txtLastName).bind("lastname");
        colLastName.setEditorComponent(txtLastName);


        var colOasis = grid.addColumn("oasis");
        var txtOasis = new TextField();
        binder.forField(txtOasis).bind("oasis");
        colOasis.setEditorComponent(txtOasis);


        var dpDateOfBirth = new DatePicker();
        dpDateOfBirth.setWidthFull();
        binder.forField(dpDateOfBirth).bind("birthDay");
        grid.addColumn("birthDay").setEditorComponent(dpDateOfBirth).setAutoWidth(true);




        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(konsekrowany -> {
            editor.save();
            if (!editor.isOpen()) {
                grid.getEditor().editItem(konsekrowany);
                currentColumn.ifPresent(column -> {
                    if (column.getEditorComponent() instanceof Focusable<?> focusable) {
                        focusable.focus();
                    }
                });



            }

        }));

        grid.addCellFocusListener(event -> {
            // Store the item on cell focus. Used in the ENTER ShortcutListener
            currentItem = event.getItem();
            // Store the current column. Used in the SelectionListener to focus the editor component
            currentColumn = event.getColumn();
        });

        Shortcuts.addShortcutListener(grid, event -> currentItem.ifPresent(grid::select), Key.ENTER).listenOn(grid);


        Shortcuts.addShortcutListener(grid, () -> {
            if (editor.isOpen()) {
                editor.cancel();
            }
        }, Key.ESCAPE).listenOn(grid);


        add(grid,homeMenu);

    }

    public Konsekrowany update(Konsekrowany entity,Repo repo) {
        return repo.save(entity);
    }
}




