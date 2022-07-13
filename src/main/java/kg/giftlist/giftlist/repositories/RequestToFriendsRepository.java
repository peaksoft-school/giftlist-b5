package kg.giftlist.giftlist.repositories;

import kg.giftlist.giftlist.models.RequestToFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestToFriendsRepository extends JpaRepository<RequestToFriends, Long> {
}