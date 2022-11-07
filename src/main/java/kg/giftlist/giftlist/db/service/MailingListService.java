package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.db.models.MailingList;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.mailingList.SendMailingRequest;
import org.springframework.http.ResponseEntity;

public interface MailingListService {

    void save(MailingList mailingList);

    ResponseEntity<?> send(SendMailingRequest sendMailingRequest);

    SimpleResponse sentLink(String to, String linkForNewPassword) throws Exception;

}
