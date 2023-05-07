package com.example.demo.frontend;
import com.example.demo.pictureResources.ImageClassResourcess;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;



@Route(value = "login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver{



    private final LoginForm login = new LoginForm();

    public LoginView() {


        addClassName("login-view");
        setSizeFull();
        var resourcess = new ImageClassResourcess();
        add(resourcess);

        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(FlexComponent.Alignment.CENTER);

        login.setAction("login");



        add(new H1("Chrystus Zmartwychwstał!"), login);


    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }

    }



    }








