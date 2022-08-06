package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.dto.mapper.UserEditMapper;
import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.services.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
