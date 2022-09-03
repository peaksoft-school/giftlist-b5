package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.MailingList;
import kg.giftlist.giftlist.db.repositories.MailingListRepository;
import kg.giftlist.giftlist.db.service.MailingListService;
import kg.giftlist.giftlist.dto.mailing_list.SendMailingRequest;
import kg.giftlist.giftlist.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailingListServiceImpl implements MailingListService {

    private final MailingListRepository repository;
    private final EmailService emailService;
    private final Logger logger = LogManager.getLogger(AdminServiceImpl.class);

    @Override
    public void save(MailingList mailingList) {
        repository.save(mailingList);
        logger.info("Email successfully saved in db");
    }

    @Override
    public ResponseEntity<?> send(SendMailingRequest sendMailingRequest) {
        String htmlMessage = sendMailingRequest.createHtmlMessage();
        List<MailingList> mailingLists = repository.findAll();
        mailingLists.forEach( mailingList -> {
                    emailService.send( mailingList.getEmail(), htmlMessage, sendMailingRequest.getTitle() );
                }
        );
        logger.info("Mailing list successfully send");
        return ResponseEntity.ok( HttpStatus.OK );
    }
}