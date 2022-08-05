package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.dto.mapper.UserInfoEditMapper;
import kg.giftlist.giftlist.dto.mapper.UserInfoViewMapper;
import kg.giftlist.giftlist.dto.user.UserInfoRequest;
import kg.giftlist.giftlist.dto.user.UserInfoResponse;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.models.UserInfo;
import kg.giftlist.giftlist.repositories.UserInfoRepository;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoEditMapper userInfoEditMapper;
    private final UserInfoViewMapper userInfoViewMapper;
    private final UserRepository userRepository;


    @Override
    public UserInfoResponse create(UserInfoRequest userInfoRequest) {

        User user = getAuthenticatedUser();
        user.setFirstName(userInfoRequest.getFirstName());
        user.setLastName(userInfoRequest.getLastName());
        user.setEmail(userInfoRequest.getEmail());
        userRepository.save(user);
        UserInfo userInfo = new UserInfo(userInfoRequest);
        userInfo.setUser(user);
        user.setUserInfo(userInfo);
        userInfoRepository.save(userInfo);
        return userInfoViewMapper.viewUserInfo(userInfo, user);
    }

    @Override
    @Transactional
    public UserInfoResponse update(Long userInfoId, UserInfoRequest userInfoRequest) {
        User user = getAuthenticatedUser();
        String currentUserFirstName = user.getFirstName();
        String newUserFirstName = userInfoRequest.getFirstName();
        if (!currentUserFirstName.equals(newUserFirstName)) {
            user.setFirstName(newUserFirstName);
        }
        String currentUserLastName = user.getFirstName();
        String newUserLastName = userInfoRequest.getFirstName();
        if (!currentUserLastName.equals(newUserLastName)) {
            user.setFirstName(newUserLastName);
        }
        UserInfo userInfo = userInfoRepository.findById(userInfoId).get();
        userInfoEditMapper.update(userInfo, userInfoRequest);
        return userInfoViewMapper.viewUserInfo(userInfoRepository.save(userInfo),user);
    }


    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("Username not found "));
    }
}
