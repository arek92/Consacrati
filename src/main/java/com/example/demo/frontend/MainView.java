package com.example.demo.frontend;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.example.demo.pictureResources.RisenImageClass;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;


@Route("")
@RolesAllowed("User")
public class MainView extends Div {


     public MainView(){



         var dodajKonsekrowanego = new Tab(VaadinIcon.USER.create(), new Span("Dodaj"));
         var znajdzInformacje = new Tab(VaadinIcon.SEARCH.create(), new Span("Szukaj"));
         var urodziny = new Tab(VaadinIcon.CALENDAR_CLOCK.create(), new Span("Urodziny"));
         var aktualizujInformacje = new Tab(VaadinIcon.EDIT.create(), new Span("Zaktualizuj"));
         var buttonLogout = new Tab(VaadinIcon.EXIT.create(),new Span("Wyloguj"));
         var ressources = new RisenImageClass();






         // Set the icon on top
         for (Tab tab : new Tab[] { dodajKonsekrowanego, znajdzInformacje, urodziny,aktualizujInformacje,buttonLogout}) {
             tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
         }

         dodajKonsekrowanego.getElement().addEventListener("click",attachEvent ->
                 dodajKonsekrowanego.getUI().ifPresent(ui -> ui.navigate("Dodaj_Konsekrowanego")));

         znajdzInformacje.getElement().addEventListener("click",attachEvent ->
                 znajdzInformacje.getUI().ifPresent(ui -> ui.navigate("Znajdz konsekrowanego")));

         urodziny.getElement().addEventListener("click",attachEvent ->
                 urodziny.getUI().ifPresent(ui -> ui.navigate("Czy ktos ma urodziny")));

         aktualizujInformacje.getElement().addEventListener("click",attachEvent ->
                 aktualizujInformacje.getUI().ifPresent(ui -> ui.navigate("zaktualizuj dane")));

         buttonLogout.getElement().addEventListener("click",attachEvent ->
                 buttonLogout.getUI().ifPresent(ui -> ui.navigate("login")));


         Tabs tabs = new Tabs(dodajKonsekrowanego,znajdzInformacje,urodziny,aktualizujInformacje,buttonLogout);
         add(tabs,ressources);


    }

}
