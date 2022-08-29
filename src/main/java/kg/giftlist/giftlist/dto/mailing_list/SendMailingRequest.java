package kg.giftlist.giftlist.dto.mailing_list;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailingRequest {

    private String photo;
    private String title;
    private String text;

    public String createHtmlMessage() {
        return String.format(
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>send email message</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<img src=\"%s\" height=\"300\" width=\"300\"/>\n" +
                        "<br>\n" +
                        "<h1>%s</h1>\n" +
                        "<input type=\"date\"/>\n" +
                        "</body>\n" +
                        "</html>",
                photo, text
        );

    }
}