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
        UserInfo userInfo = userInfoEditMapper.create(userInfoRequest);
        user.setUserInfo(userInfo);
        userInfo.setUser(user);
        userInfoRepository.save(userInfo);
        return userInfoViewMapper.viewUserInfo(userInfo);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("Username not found "));
    }
}
