package kg.giftlist.giftlist.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;


@Service
@Log4j2
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void send(String to, String htmlMessage, String subject) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper( mimeMessage, true, "UTF-8" );
            helper.setSubject( "[GIFT LIST] " + subject );
            helper.setFrom( "giftlist16@gmail.com" );
            helper.setTo( to );
            helper.setText( htmlMessage, true );
            mailSender.send( mimeMessage );
            log.info( "Mailing sent success" );
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error( "Mailing not sent" );
        }
    }

    public void getApiKey(String to, String linkForNewPassword) throws IOException {
        Email from = new Email( "giftlist16@gmail.com" );
        Email toUser = new Email( to );
        Content content = new Content( "text/plain", linkForNewPassword );
        Mail mail = new Mail( from, "[GIFT LIST] password reset link ", toUser, content );
        SendGrid sg = new SendGrid( "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" );
        Request request = new Request();
        try {
            request.setMethod( Method.POST );
            request.setEndpoint( "mail/send" );
            request.setBody( mail.build() );
            Response response = sg.api( request );
            System.out.println( response.getStatusCode() );
            System.out.println( response.getBody() );
            System.out.println( response.getHeaders() );
        } catch (IOException ex) {
            throw ex;
        }
    }
}