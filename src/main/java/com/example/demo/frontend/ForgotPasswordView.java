package com.example.demo.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Route("ForgotPassword")
@PermitAll
public class ForgotPasswordView extends Div {


    private static final TextField emailTo = new TextField("email to");
    private static final TextField title = new TextField("title");
    //private static final TextArea textArea = new TextArea();
    private static final VerticalLayout dialogLayout = new VerticalLayout(emailTo);
    private static final Dialog dialog = new Dialog();


    private JavaMailSender sender;



    @Autowired
    public ForgotPasswordView(JavaMailSender sender){


        dialog.setHeaderTitle("Password recovery where should we send new email?");
        int charLimit = 300;


        var send = new Button("Wyslij");

        var homeMenu = new Button("Menu Główne");
        homeMenu.setIcon(new Icon(VaadinIcon.HOME));

        homeMenu.addClickListener(buttonClickEvent -> {
            homeMenu.getUI().ifPresent(ui -> ui.navigate(""));

        });

        send.addClickListener(buttonClickEvent -> {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("arkadiuszgalus85@gmail.com");
            msg.setSubject(title.getValue());
            msg.setTo(emailTo.getValue());
            sender.send(msg);
            Notification notification = new Notification("Email has been sent successfully", 3000);
            notification.open();
            dialog.close();


        });





        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        dialog.add(dialogLayout);



        var cancelButton = new Button("Cancel");
        cancelButton.addClickListener(buttonClickEvent -> {
            dialog.close();
        });
        dialog.getFooter().add(cancelButton);
        dialog.getFooter().add(send);

        var button = new Button("Show dialog", e -> dialog.open());

        add(dialog, button, homeMenu);

        dialog.open();








    }
}
