package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.user.UserInfoRequest;
import kg.giftlist.giftlist.dto.user.UserInfoResponse;

public interface UserInfoService {

    UserInfoResponse create(UserInfoRequest userInfoRequest);

    UserInfoResponse update(Long userInfoId, UserInfoRequest userInfoRequest);


}
