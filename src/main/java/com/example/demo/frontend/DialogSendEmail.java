package com.example.demo.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Route("Window Send Email")
@RolesAllowed("User")
public class DialogSendEmail extends Div {

//    private static final TextField emailFrom = new TextField("Email from");
    private static final TextField title = new TextField("tile");
    private static final TextField emailTo = new TextField("email to");
    private static final TextArea textArea = new TextArea();
    private static final VerticalLayout dialogLayout = new VerticalLayout( title, textArea, emailTo);
    private static final Dialog dialog = new Dialog();

    private JavaMailSender sender;


    @Autowired
    public DialogSendEmail(JavaMailSender sender) {

        dialog.setHeaderTitle("Send email with best birthdayswishes");
        int charLimit = 300;

        var homeMenu = new Button("Home");
        homeMenu.setIcon(new Icon(VaadinIcon.HOME));

        homeMenu.addClickListener(buttonClickEvent -> {
            homeMenu.getUI().ifPresent(ui -> ui.navigate(""));

        });



        textArea.setLabel("Comment");
        textArea.setMaxLength(charLimit);
        textArea.setValueChangeMode(ValueChangeMode.EAGER);
        textArea.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + charLimit);
        });
        textArea.setValue("Put here your message ");


        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        dialog.add(dialogLayout);

        var send = new Button("Wyslij");


        send.addClickListener(buttonClickEvent -> {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("arkadiuszgalus85@gmail.com");
            msg.setText(textArea.getValue());
            msg.setSubject(title.getValue());
            msg.setTo(emailTo.getValue());
            sender.send(msg);
            Notification notification = new Notification("Email has been sent successfully", 3000);
            notification.open();
            dialog.close();


        });

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
