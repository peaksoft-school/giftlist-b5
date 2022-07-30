package kg.giftlist.giftlist.services.serviceImpl;

import kg.giftlist.giftlist.models.Wish;
import kg.giftlist.giftlist.repositories.WishRepository;
import kg.giftlist.giftlist.services.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishesServiceImpl implements WishService {

    private final WishRepository wishRepository;

    @Override
    public List<Wish> searchWithGiftName(String keyword) {
        return wishRepository.searchByGiftName(keyword.toUpperCase()) ;
    }
}
