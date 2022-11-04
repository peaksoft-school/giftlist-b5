package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}