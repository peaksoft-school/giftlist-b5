package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {

    @Query("select g from User u join u.gifts g where u.id=?1")
    List<Gift> getAllUserGifts(Long userId);

    @Query("select g from Gift g where g.isBlock=false and (upper(g.name) like upper(concat('%',:name,'%')) " +
            "or :name = 'all') and (g.status = :status or :status is null ) " +
            "and (:categoryId is null or :categoryId = g.category.id) " +
            "and (:subCategoryId is null or :subCategoryId = g.subCategory.id) order by g.createdAt desc")
    List<Gift> filterGift(String name, Status status, Long categoryId, Long subCategoryId);

    @Query("select g from Gift g where g.complaints.size>0")
    List<Gift> getAllComplaintGifts();

    @Query("select g from User u join u.gifts g where g.isBlock=false")
    List<Gift> getAllGifts();

    @Query("select g from Gift g where (upper(g.name) like upper(concat('%',:name,'%')) " +
            "or :name = 'all') and (g.status = :status or :status is null ) " +
            "and (:categoryId is null or :categoryId = g.category.id) " +
            "and (:subCategoryId is null or :subCategoryId = g.subCategory.id) order by g.createdAt desc ")
    List<Gift> filterGiftForAdmin(String name, Status status, Long categoryId, Long subCategoryId);

}