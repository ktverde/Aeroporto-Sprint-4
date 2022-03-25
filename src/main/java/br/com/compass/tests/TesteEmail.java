package br.com.compass.tests;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

public class TesteEmail {
    private final static String key ="ad012412463ebe6448f914102da56b4a-0677517f-26205401";
    private final static String sendgridKey="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";


   /* public static void main(String[] args) throws UnirestException{
        HttpResponse request  = Unirest.post("https://api.mailgun.net/v3/"+"localhost:8080/"+"/messages")
                    .basicAuth("api", key)
                    .field("from", "sup.apairlines@gmail.com")
                    .field("to", "andre.m.f.lima@outlook.com")
                    .field("subject", "hello")
                    .field("text", "testing")
                    .asString();
        System.out.println(request.getBody());
    }
*/
    /*public static void main(String[] args) throws MailjetException, MailjetSocketTimeoutException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(System.getenv("ad722e19cae2dc3c0efb390a15d66e78"), System.getenv("072c275f76fb7bf6b7e104f8b40b3903"), new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "andre.f.lima@edu.ufes.br")
                                        .put("Name", "André"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", "andre.m.f.lima1@gmail.com")
                                                .put("Name", "André")))
                                .put(Emailv31.Message.SUBJECT, "Greetings from Mailjet.")
                                .put(Emailv31.Message.TEXTPART, "My first Mailjet email")
                                .put(Emailv31.Message.HTMLPART, "<h3>Dear passenger 1, welcome to <a href='https://www.mailjet.com/'>Mailjet</a>!</h3><br />May the delivery force be with you!")
                                .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }*/

    public static void main(String[] args) throws IOException {
        Email from = new Email("sup.apairlines@gmail.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("andre.m.f.lima@outlook.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
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