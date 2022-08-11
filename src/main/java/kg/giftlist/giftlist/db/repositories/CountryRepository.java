package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}