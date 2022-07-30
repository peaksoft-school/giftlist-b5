package kg.giftlist.giftlist.repositories;

import kg.giftlist.giftlist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

     @Query("select case when count(u)>0 then true else false end from User u where u.email like :email")
     boolean existsByEmail(@Param(value = "email") String email);


     @Query("select u from User u where upper(concat(u.email,u.lastName,u.firstName)) like %?1% order by concat(u.email,u.firstName,u.lastName) asc ")
     List<User> searchBy(String keyword);

}