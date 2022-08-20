package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query("select h from Complaint h" )
    List<Complaint> getAllComplaint();
}