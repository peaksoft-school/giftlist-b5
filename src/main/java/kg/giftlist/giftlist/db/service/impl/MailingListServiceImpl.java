package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.MailingList;
import kg.giftlist.giftlist.db.repositories.MailingListRepository;
import kg.giftlist.giftlist.db.service.MailingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailingListServiceImpl implements MailingListService {
    private final MailingListRepository repository;
    @Override
    public void save(MailingList mailingList) {
        repository.save(mailingList);
    }
}