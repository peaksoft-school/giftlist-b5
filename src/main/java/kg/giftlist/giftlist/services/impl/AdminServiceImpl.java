package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.dto.mapper.UserEditMapper;
import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;
import kg.giftlist.giftlist.exception.NotFoundException;

import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.Holiday;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.models.Wish;
import kg.giftlist.giftlist.repositories.GiftRepository;
import kg.giftlist.giftlist.repositories.HolidayRepository;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.repositories.WishRepository;
import kg.giftlist.giftlist.services.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private UserEditMapper userEditMapper;
    private UserRepository userRepository;

    @Override
    public List<AdminPageUserGetAllResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<AdminPageUserGetAllResponse> userList = new ArrayList<>();
        for (User i : users) {
            userList.add(userEditMapper.createUser(i));
        }
        log.info("Find all Users works");
        return userList;
    }

    @Override
    @Transactional
    public ResponseEntity<?> blockUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("user with id = %s not found", id)));
        user.setIsBlock(true);
        return ResponseEntity.ok("user is blocked");
    }

    @Override
    @Transactional
    public ResponseEntity<?> unBlockUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("user with id = %s not found", id)));
        user.setIsBlock(false);
        return ResponseEntity.ok("user is unBlocked");
    }
}
