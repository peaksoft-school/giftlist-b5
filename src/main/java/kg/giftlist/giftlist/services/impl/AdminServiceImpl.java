package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.dto.mapper.UserEditMapper;
import kg.giftlist.giftlist.dto.mapper.user_info.UserInfoMapper;
import kg.giftlist.giftlist.dto.user.AdminUserGetAllResponse;
import kg.giftlist.giftlist.dto.user_info.UserInfoRequest;
import kg.giftlist.giftlist.dto.user_info.UserInfoResponse;
import kg.giftlist.giftlist.enums.ClothingSize;
import kg.giftlist.giftlist.exception.IsEmptyException;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.models.UserInfo;
import kg.giftlist.giftlist.repositories.UserInfoRepository;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.services.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;
    private UserInfoRepository userInfoRepository;
    private UserEditMapper userEditMapper;
    private UserInfoMapper userInfoMapper;

    @Override
    public List<AdminUserGetAllResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<AdminUserGetAllResponse> userList = new ArrayList<>();
        for (User i : users) {
            userList.add(userEditMapper.createUser(i));
        }
        log.info("Find all Users works");
        return userList;
    }
    @Override
    public UserInfoResponse getUserById(Long id) {
        UserInfo user = userInfoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User with id ={} does not exists", id);
                    throw new IsEmptyException(
                            String.format("user with id %s doesn't exist!", id)
                    );
                });
        log.info("Get user by id works");
        return userInfoMapper.create(user);
    }
    @Override
    public ResponseEntity<?> deleteUserById(Long id) {
        User userById = userRepository.findById(id).orElseThrow(
                () -> new IsEmptyException("User not Found!")
        );
        log.info("Successfully deleter");
        userRepository.deleteById(userById.getId());
        return ResponseEntity.ok("Successfully deleted");
    }
    @Override
    @Transactional
    public ResponseEntity<?> updateByIdInUser(Long id, UserInfoRequest request) {
        UserInfo userInfo = userInfoRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IsEmptyException(String.format("user with id = %s does not exists", id)
                    );
                });
        String photo = userInfo.getPhoto();
        String newPhoto = request.getPhoto();
        if (!Objects.equals(photo, newPhoto)) {
            userInfo.setPhoto(newPhoto);
        }
        String country = userInfo.getCountry();
        String newCountry = request.getCountry();
        if (!Objects.equals(country, newCountry)) {
            userInfo.setCountry(newCountry);
        }
        LocalDate localDate = userInfo.getDateOfBirth();
        LocalDate newLocalDate = request.getDateOfBirth();
        if (!Objects.equals(localDate, newLocalDate)) {
            userInfo.setDateOfBirth(newLocalDate);
        }
        String phoneNumber = userInfo.getPhoneNumber();
        String newPhoneNumber = request.getPhoneNumber();
        if (!Objects.equals(phoneNumber, newPhoneNumber)) {
            userInfo.setPhoneNumber(newPhoneNumber);
        }
        ClothingSize clothingSize = userInfo.getClothingSize();
        ClothingSize newClothingSize = request.getClothingSize();
        if (!Objects.equals(clothingSize, newClothingSize)) {
            userInfo.setClothingSize(newClothingSize);
        }
        int shoeSize = userInfo.getShoeSize();
        int newShoeSize = request.getShoeSize();
        if (!Objects.equals(shoeSize, newShoeSize)) {
            userInfo.setShoeSize(newShoeSize);
        }
        String hobby = userInfo.getHobby();
        String newHobby = request.getHobby();
        if (!Objects.equals(hobby, newHobby)) {
            userInfo.setHobby(newHobby);
        }
        String importantNote = userInfo.getImportantNote();
        String newImportantNote = request.getImportantNote();
        if (!Objects.equals(importantNote, newImportantNote)) {
            userInfo.setImportantNote(newImportantNote);
        }
        String instagramLink = userInfo.getInstagramLink();
        String newInstagramLink = request.getInstagramLink();
        if (!Objects.equals(instagramLink, newInstagramLink)) {
            userInfo.setInstagramLink(newInstagramLink);
        }
        String telegramLink = userInfo.getTelegramLink();
        String newTelegramLink = request.getTelegramLink();
        if (!Objects.equals(telegramLink, newTelegramLink)) {
            userInfo.setTelegramLink(newTelegramLink);
        }
        String facebookLink = userInfo.getFacebookLink();
        String newFacebookLink = request.getFacebookLink();
        if (!Objects.equals(facebookLink, newFacebookLink)) {
            userInfo.setFacebookLink(newFacebookLink);
        }
        String vkLink = userInfo.getVkLink();
        String newVkLink = request.getVkLink();
        if (!Objects.equals(vkLink, newVkLink)) {
            userInfo.setVkLink(newVkLink);
        }
        String firstName = userInfo.getUser().getFirstName();
        String newFirstName = request.getFirst_name();
        if (!Objects.equals(firstName, newFirstName)) {
             userInfo.getUser().setFirstName(newFirstName);
        }
        String lastName = userInfo.getUser().getLastName();
        String newLastName = request.getLast_name();
        if (!Objects.equals(lastName, newLastName)) {
            userInfo.getUser().setLastName(newLastName);
        }
        String email = userInfo.getUser().getEmail();
        String newEmail = request.getEmail();
        if (!Objects.equals(email, newEmail)) {
            userInfo.getUser().setEmail(newEmail);
        }
        return ResponseEntity.ok("successfully updated");
    }

}
