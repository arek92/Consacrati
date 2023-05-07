package com.example.demo.frontend;

import com.example.demo.entity.User;
import com.example.demo.pictureResources.ImageClassResourcess;
import com.example.demo.repository.UserRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;


@Route(value = "login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver{



    private LoginForm login = new LoginForm();

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


//    private UserRepository repository;
//
//    @Autowired
//    public LoginView(UserRepository repository) {
//
//
//
//        ImageClassResourcess resourcess = new ImageClassResourcess();
//        TextField usernameField = new TextField("Username");
//        PasswordField passwordField = new PasswordField("Password");
//        Button loginButton = new Button("Login", e -> {
//            String username = usernameField.getValue();
//            String password = passwordField.getValue();
//            Optional<User> userOptional = repository.findByUsername(username);
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
//                    // create authentication token
//                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    // redirect to the main view after successful authentication
//                    UI.getCurrent().navigate("");
//                } else {
//                    Notification notification = new Notification("Invalid password", 3000, Notification.Position.MIDDLE);
//                    notification.open();
//                }
//            } else {
//                Notification notification = new Notification("Invalid username", 3000, Notification.Position.MIDDLE);
//                notification.open();
//            }
//        });
//
//        // add form fields to the layout
//        add(resourcess,usernameField, passwordField,loginButton);






//        add(new H1("Chrystus Zmartwychwstał!"), login);


    }


//    @Override
//    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
//        if(beforeEnterEvent.getLocation()
//                .getQueryParameters()
//                .getParameters()
//                .containsKey("error")) {
//            login.setError(true);
//        }
//
//    }






