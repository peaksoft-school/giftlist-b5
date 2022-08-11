package kg.giftlist.giftlist.db.service.impl;
import kg.giftlist.giftlist.db.service.UserInfoService;
import kg.giftlist.giftlist.dto.mapper.UserInfoEditMapper;
import kg.giftlist.giftlist.dto.mapper.UserInfoViewMapper;
import kg.giftlist.giftlist.dto.user.UserInfoRequest;
import kg.giftlist.giftlist.dto.user.UserInfoResponse;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.models.UserInfo;
import kg.giftlist.giftlist.db.repositories.UserInfoRepository;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        String currentUserLastName = user.getLastName();
        String newUserLastName = userInfoRequest.getLastName();
        if (!currentUserLastName.equals(newUserLastName)) {
            user.setLastName(newUserLastName);
        }
        UserInfo userInfo = findByUserInfoId(userInfoId);
        userInfoEditMapper.update(userInfo, userInfoRequest);
        return userInfoViewMapper.viewUserInfo(userInfoRepository.save(userInfo),user);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("Username not found "));
    }

    public UserInfo findByUserInfoId(Long userInfoId) {
        return userInfoRepository.findById(userInfoId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("userInfo with id = %s does not exists", userInfoId)
                ));
    }

}
