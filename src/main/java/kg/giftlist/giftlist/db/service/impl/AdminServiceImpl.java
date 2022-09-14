package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.models.Wish;
import kg.giftlist.giftlist.db.repositories.GiftRepository;
import kg.giftlist.giftlist.db.repositories.HolidayRepository;
import kg.giftlist.giftlist.db.repositories.WishRepository;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.mapper.UserEditMapper;
import kg.giftlist.giftlist.dto.mapper.wish.WishViewMapper;
import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Log4j2
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private UserEditMapper userEditMapper;
    private UserRepository userRepository;
    private WishRepository wishRepository;
    private GiftRepository giftRepository;

    @Override
    public List<AdminPageUserGetAllResponse> getAllUsers() {
        List<User> users = userRepository.getAll();
        List<AdminPageUserGetAllResponse> userList = new ArrayList<>();
        for (User i : users) {
            userList.add(userEditMapper.createUser(i));
        }
        return userList;
    }

    @Override
    @Transactional
    public SimpleResponse blockUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("user with id = %s not found", id)));
        user.setIsBlock(true);
        log.info("Successfully blocked user with id: {}", user.getId());
        return new SimpleResponse("BLOCK","user with id = "+String.valueOf(id)+" blocked");
    }

    @Override
    @Transactional
    public SimpleResponse unBlockUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("user with id = %s not found", id)));
        user.setIsBlock(false);
        log.info("Successfully unblocked user with id: {}", user.getId());
        return new SimpleResponse("UNBLOCK","user with id = "+String.valueOf(id)+" unblocked");
    }

    @Override
    @Transactional
    public SimpleResponse blockWish(Long wishId) {
        Wish wish = wishRepository.findById(wishId).orElseThrow(() ->
                new NotFoundException(
                        String.format("Wish with id = %s not found", wishId)));
        wish.setIsBlock(true);
        log.info("Successfully blocked Wish with id: {}", wish.getId());
        return new SimpleResponse("BLOCK","Wish with id = "+String.valueOf(wishId)+" blocked");
    }

    @Override
    @Transactional
    public SimpleResponse unBlockWish(Long wishId) {
        Wish wish = wishRepository.findById(wishId).orElseThrow(() ->
                new NotFoundException(
                        String.format("Wish with id = %s not found", wishId)));
        wish.setIsBlock(false);
        log.info("Successfully unblocked Wish with id: {}", wish.getId());
        return new SimpleResponse("UNBLOCK","Wish with id = "+String.valueOf(wishId)+" unblocked");
    }

    @Override
    @Transactional
    public SimpleResponse blockGift(Long giftId) {
        Gift gift = giftRepository.findById(giftId).orElseThrow(()->
                new NotFoundException(
                        String.format("Gift with id = %s not found", giftId)));
        gift.setIsBlock(true);
        log.info("Successfully blocked Gift with id: {}", gift.getId());
        return new SimpleResponse("BLOCK","Gift with id = "+String.valueOf(giftId)+" blocked");

    }

    @Override
    @Transactional
    public SimpleResponse unBlockGift(Long giftId) {
        Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
                new NotFoundException(
                        String.format("Gift with id = %s not found", giftId)));
        gift.setIsBlock(false);
        log.info("Successfully unblocked Gift with id: {}", gift.getId());
        return new SimpleResponse("UNBLOCK","Gift with id = "+String.valueOf(giftId)+" unblocked");
    }

}
