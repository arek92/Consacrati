package com.example.demo.frontend;

import com.example.demo.pictureResources.RisenImageClass;
import com.helger.commons.state.EContinue;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Route(value = "")
@PermitAll
public class MainView extends Div {

    public MainView() {


        var dodajKonsekrowanego = new Tab(VaadinIcon.USER.create(), new Span("Dodaj"));
        var znajdzInformacje = new Tab(VaadinIcon.SEARCH.create(), new Span("Szukaj"));
        var urodziny = new Tab(VaadinIcon.CALENDAR_CLOCK.create(), new Span("Urodziny"));
        var aktualizujInformacje = new Tab(VaadinIcon.EDIT.create(), new Span("Zaktualizuj"));
        var buttonLogout = new Tab(VaadinIcon.EXIT.create(), new Span("Wyloguj"));
        var ressources = new RisenImageClass();


        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var tabCurrentUser = new Tab(VaadinIcon.USER.create(), new Span("Zalogowany jako " + " " + " " + currentUser.getUsername()));


        VaadinSession session = UI.getCurrent().getInternals().getSession();
        Calendar startTime = Calendar.getInstance();
        startTime.setTimeInMillis(session.getSession().getCreationTime());
        Instant startTimeRight = startTime.toInstant();
        Duration elapsedDuration = Duration.between(startTimeRight, Instant.now());
        long elapsedSeconds = elapsedDuration.getSeconds();
        long sessionDurationMinutes = 20;
       // long sessionDurationSeconds = session.getSession().getMaxInactiveInterval();
        long sessionDurationSeconds = sessionDurationMinutes * 60;
        long remainingSeconds = sessionDurationSeconds - elapsedSeconds;

        long minutes = remainingSeconds / 60;
        long seconds = remainingSeconds % 60;
        String formated = String.format("%02d:%02d", minutes, seconds);

        Tab sessionDurationTab = new Tab(VaadinIcon.TIMER.create(), new Span("do konca sesji pozostało : " + formated));

        if (remainingSeconds <= 0) {
            // Session has expired
            session.getSession().invalidate();
            session.close();

            getUI().ifPresent(ui -> ui.navigate("/logout"));

        }


        // Set the icon on top
        for (Tab tab : new Tab[]{dodajKonsekrowanego, znajdzInformacje, urodziny, aktualizujInformacje, buttonLogout, tabCurrentUser}) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }

        dodajKonsekrowanego.getElement().addEventListener("click", attachEvent ->
                dodajKonsekrowanego.getUI().ifPresent(ui -> ui.navigate("Dodaj_Konsekrowanego")));

        znajdzInformacje.getElement().addEventListener("click", attachEvent ->
                znajdzInformacje.getUI().ifPresent(ui -> ui.navigate("Znajdz konsekrowanego")));

        urodziny.getElement().addEventListener("click", attachEvent ->
                urodziny.getUI().ifPresent(ui -> ui.navigate("Czy ktos ma urodziny")));

        aktualizujInformacje.getElement().addEventListener("click", attachEvent ->
                aktualizujInformacje.getUI().ifPresent(ui -> ui.navigate("zaktualizuj dane")));


        buttonLogout.getElement().addEventListener("click", attachEvent ->
                buttonLogout.getUI().ifPresent(ui -> ui.navigate("login")));

        Tabs tabs = new Tabs(dodajKonsekrowanego, znajdzInformacje, urodziny, aktualizujInformacje, tabCurrentUser, sessionDurationTab, buttonLogout);


        add(tabs, ressources);


    }

}



