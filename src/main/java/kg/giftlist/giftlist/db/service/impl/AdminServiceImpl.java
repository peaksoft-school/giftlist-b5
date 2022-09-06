package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.mapper.UserEditMapper;
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

    @Override
    public List<AdminPageUserGetAllResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
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
        return new SimpleResponse("UN BLOCK","user with id = "+String.valueOf(id)+" un blocked");
    }
}
