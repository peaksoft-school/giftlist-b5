package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.models.MailingList;
import kg.giftlist.giftlist.repositories.MailingListRepository;
import kg.giftlist.giftlist.services.MailingListService;
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