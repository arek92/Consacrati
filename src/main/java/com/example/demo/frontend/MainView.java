package com.example.demo.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@Route("")
@PermitAll
public class MainView extends Div {


     public MainView(){

         Tab dodajKonsekrowanego = new Tab("Dodaj konsekrowanego");
         Tab wyszukajKonsekrowanegoLubInformacje = new Tab("znajdz konsekrowanego");
         Tab czyKtosMaUrodziny = new Tab("komu zyczyc dzisiaj 100 lat");
         Tab aktualizujInformacje = new Tab("Assetti- zmienmy dane");


         dodajKonsekrowanego.getElement().addEventListener("click",attachEvent ->
                 dodajKonsekrowanego.getUI().ifPresent(ui -> ui.navigate("Dodaj_Konsekrowanego")));

         wyszukajKonsekrowanegoLubInformacje.getElement().addEventListener("click",attachEvent ->
                 wyszukajKonsekrowanegoLubInformacje.getUI().ifPresent(ui -> ui.navigate("Znajdz konsekrowanego")));

         czyKtosMaUrodziny.getElement().addEventListener("click",attachEvent ->
                 czyKtosMaUrodziny.getUI().ifPresent(ui -> ui.navigate("Czy ktos ma urodziny")));

         aktualizujInformacje.getElement().addEventListener("click",attachEvent ->
                 aktualizujInformacje.getUI().ifPresent(ui -> ui.navigate("zaktualizuj dane")));







         Tabs tabs = new Tabs(dodajKonsekrowanego,wyszukajKonsekrowanegoLubInformacje,czyKtosMaUrodziny,aktualizujInformacje);
         add(tabs);



    }
}
