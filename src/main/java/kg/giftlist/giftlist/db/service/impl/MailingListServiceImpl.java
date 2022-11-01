package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.MailingList;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.repositories.MailingListRepository;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.service.MailingListService;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.mailing_list.SendMailingRequest;

import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MailingListServiceImpl implements MailingListService {

    private final MailingListRepository repository;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Override
    public void save(MailingList mailingList) {
        repository.save(mailingList);
        log.info("Email successfully saved in db");
    }

    @Override
    public ResponseEntity<?> send(SendMailingRequest sendMailingRequest) {
        String htmlMessage = sendMailingRequest.createHtmlMessage();
        List<MailingList> mailingLists = repository.findAll();
        mailingLists.forEach(mailingList -> {
                    emailService.send(mailingList.getEmail(), htmlMessage, sendMailingRequest.getTitle());
                }
        );
        log.info("Mailing list successfully send");
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @Override
    public SimpleResponse sentLink(String email, String linkForNewPassword) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with " + email + " not found!"));
        emailService.sendLinkToChangeUserPassword(user.getEmail(), linkForNewPassword + "/" + String.valueOf(user.getId()));
        return new SimpleResponse("OK", "Here working");
    }

}