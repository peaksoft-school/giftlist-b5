package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.MailingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingListRepository extends JpaRepository<MailingList, Long> {

}