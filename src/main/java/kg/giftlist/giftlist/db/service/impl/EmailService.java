package kg.giftlist.giftlist.db.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void send(String to, String htmlMessage, String subject) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper( mimeMessage, true, "UTF-8" );
            helper.setSubject( subject );
            helper.setFrom( "giftlist16@gmail.com" );
            helper.setTo( to );
            helper.setText( htmlMessage, true );
            mailSender.send( mimeMessage );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}