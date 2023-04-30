package com.example.demo.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Route("Window Send Email")
@PermitAll
public class DialogSendEmail extends Div {

    private static TextField emailFrom = new TextField("Email from");
    private static TextField title = new TextField("tile");
    private static TextField messageContent = new TextField("Whishes content");
    private static TextField emailTo = new TextField("email to");
    private JavaMailSender sender;


    @Autowired
    public DialogSendEmail(JavaMailSender sender) {
        var dialog = new Dialog();
        dialog.setHeaderTitle("Send email with best birthdayswishes");


        VerticalLayout dialogLayout = new VerticalLayout(emailFrom, title, messageContent, emailTo);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        dialog.add(dialogLayout);

        var send = new Button("Wyslij");

        send.addClickListener(buttonClickEvent -> {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(emailFrom.getValue());
            msg.setText(messageContent.getValue());
            msg.setSubject(title.getValue());
            msg.setTo(emailTo.getValue());
            sender.send(msg);


        });
        var cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton);
        dialog.getFooter().add(send);

        var button = new Button("Show dialog", e -> dialog.open());

        add(dialog, button);

        dialog.open();


    }


}
