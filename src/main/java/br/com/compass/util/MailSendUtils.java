package br.com.compass.util;

import br.com.compass.models.Ticket;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;


public class MailSendUtils {

    public static void sendEmail(Ticket ticket) throws IOException {

        Email from = new Email("sup.apairlines@gmail.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email(ticket.getClient().getEmail());
        Content content = new Content("text/plain", ticket.toString());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

}