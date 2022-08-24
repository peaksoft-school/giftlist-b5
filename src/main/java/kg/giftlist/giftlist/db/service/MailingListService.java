package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.db.models.MailingList;
import kg.giftlist.giftlist.dto.mailing_list.SendMailingRequest;
import org.springframework.http.ResponseEntity;


public interface MailingListService {
    void save(MailingList mailingList);

    ResponseEntity<?> send(SendMailingRequest sendMailingRequest);
}
