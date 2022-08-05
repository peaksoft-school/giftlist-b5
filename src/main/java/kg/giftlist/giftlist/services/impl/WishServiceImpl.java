package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.mapper.wish.WishEditMapper;
import kg.giftlist.giftlist.dto.mapper.wish.WishViewMapper;
import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.exception.WishNotFoundException;
import kg.giftlist.giftlist.models.Holiday;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.models.Wish;
import kg.giftlist.giftlist.repositories.HolidayRepository;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.repositories.WishRepository;
import kg.giftlist.giftlist.services.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;

    private final WishEditMapper editMapper;

    private final WishViewMapper viewMapper;

    private final UserServiceImpl userService;

    private final HolidayRepository holidayRepository;

    @Override
    public WishResponse create(WishRequest wishRequest) {

        User user = userService.getAuthenticatedUser();
        Wish wish = editMapper.create(wishRequest);
        wish.setUser(user);
        user.setWishes(List.of(wish));

        Holiday holiday = holidayRepository.findByName(wishRequest.getHolidayName()).orElseThrow(() -> new NullPointerException("Holiday not found"));

        wish.setHolidays(holiday);

        //    holidayRepository.save(holiday);

        wishRepository.save(wish);

        return viewMapper.viewWish(wish);

    }

    @Transactional
    @Override
    public WishResponse update(Long id, WishRequest wishRequest) {
        User user = userService.getAuthenticatedUser();
        Wish wish = wishRepository.findById(id).orElseThrow(() -> new WishNotFoundException("Wish with id " + id + " not found!"));
        if (wish.getUser() == user) {
            editMapper.update(wish, wishRequest);
        }
        return viewMapper.viewWish(wish);
    }

    @Override
    public WishResponse findById(Long id) {

        Wish wish = getWishById(id);

        return viewMapper.viewWish(wish);

    }

    private Wish getWishById(Long wishId) {

        return wishRepository.findById(wishId).

                orElseThrow(() -> new WishNotFoundException(

                        "Wish with id = " + wishId + " not found!"

                ));
    }

    public SimpleResponse deleteById(Long id) {

        boolean exists = wishRepository.existsById(id);

        if (!exists) {

            throw new WishNotFoundException(

                    "Wish with id = " + id + " not found!"

            );
        }

        wishRepository.deleteById(id);

        return new SimpleResponse("Deleted!", "Wish successfully deleted!");

    }

    public List<WishResponse> getAllWishes() {

        return viewMapper.view(wishRepository.findAll());

    }
}
