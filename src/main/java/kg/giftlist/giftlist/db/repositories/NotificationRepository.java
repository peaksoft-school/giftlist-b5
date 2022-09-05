package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.*;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.user.UserInfoResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select u from Notification n join n.user u where upper(u.firstName) like %?1% or upper(u.lastName) like %?1%")
    Optional<UserInfoResponse> findUserByUsername(String username);

    @Query("select u from Notification n join n.user u where u.id=?1")
    Optional<UserInfoResponse> findUserById(Long userId);

    @Query("select g from Notification n join n.gift g where g.id=?1")
    Optional<GiftResponse> findGiftById(Long giftId);

    @Query("select w from Notification n join n.wish w where w.id=?1")
    Optional<WishResponse> findWishById(Long wishId);

    @Query("select h from Notification n join n.holiday h where h.id=?1")
    Optional<HolidayResponse> findHolidayById(Long holidayId);

    @Query("select n from Notification n where n.recipientId=?1")
    List<Notification> getAllNotifications(Long userId);

}
