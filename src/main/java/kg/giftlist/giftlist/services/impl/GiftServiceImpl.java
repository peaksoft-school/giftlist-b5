package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.gift.mapper.GiftEditMapper;
import kg.giftlist.giftlist.dto.gift.mapper.GiftViewMapper;
import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.GiftRepository;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.services.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {
    private final GiftRepository  giftRepository;
    private final GiftViewMapper giftViewMapper;
    private final GiftEditMapper giftEditMapper;

    private final UserRepository  userRepository;


    @Override
    public GiftResponse create(GiftRequest request) {
        Gift gift = new Gift(request);
        giftRepository.save(gift);
        return giftViewMapper.viewGift(gift);
    }

    @Override
    public GiftResponse update(Long id, GiftRequest request) {
        Gift gift = giftRepository.findById(id).get();
        giftEditMapper.update(gift,request);
        return giftViewMapper.viewGift(giftRepository.save(gift));
    }

    @Override
    public GiftResponse findById(Long id) {
        Gift gift = giftRepository.findById(id).get();
        return giftViewMapper.viewGift(gift);
    }

    @Override
    public GiftResponse deleteById(Long id) {
        Gift gift = giftRepository.getById(id);
        giftRepository.delete(gift);
        return giftViewMapper.viewGift(gift);
    }

    @Override
    public List<GiftResponse> getAll() {
        return giftViewMapper.view(giftRepository.findAll());
    }
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("Username not found "));
    }

}
