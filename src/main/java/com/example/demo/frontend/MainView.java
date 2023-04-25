package com.example.demo.frontend;

import com.example.demo.ImageClassResourcess;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@Route("")
@PermitAll
public class MainView extends Div {


     public MainView(){



         Tab dodajKonsekrowanego = new Tab(VaadinIcon.USER.create(), new Span("Dodaj"));
         Tab znajdzInformacje = new Tab(VaadinIcon.SEARCH.create(), new Span("Szukaj"));
         Tab urodziny = new Tab(VaadinIcon.CALENDAR_CLOCK.create(), new Span("Urodziny"));
         Tab aktualizujInformacje = new Tab(VaadinIcon.EDIT.create(), new Span("Zaktualizuj"));

         var ressources = new ImageClassResourcess();

         // Set the icon on top
         for (Tab tab : new Tab[] { dodajKonsekrowanego, znajdzInformacje, urodziny,aktualizujInformacje}) {
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


         Tabs tabs = new Tabs(dodajKonsekrowanego,znajdzInformacje,urodziny,aktualizujInformacje);
         add(tabs,ressources);



    }
}
