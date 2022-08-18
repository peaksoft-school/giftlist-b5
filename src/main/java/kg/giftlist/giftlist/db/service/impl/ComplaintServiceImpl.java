package kg.giftlist.giftlist.db.service.impl;


import kg.giftlist.giftlist.db.models.Complaint;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.models.Wish;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.repositories.WishRepository;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintRequest;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintResponse;
import kg.giftlist.giftlist.dto.mapper.wish.WishViewMapper;
import kg.giftlist.giftlist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.ForbiddenException;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl {
    private WishRepository wishRepository;
    private UserServiceImpl userService;
    private ComplaintResponse complaintResponse;
    private WishViewMapper wishViewMapper;
    private ComplaintRequest complaintRequest;
    private UserRepository userRepo;


    public ComplaintResponse sendComplaint(Long wishId){


        User user = getAuthenticatedUser();

        Complaint complaint = new Complaint();

        Wish wish = wishRepository.findById(wishId).orElseThrow(() ->
                new NotFoundException("Gift with id: " + wishId + "not found"));

        //Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
          //      new NotFoundException("Gift with id: " + giftId + "not found"));

        complaint.setId(complaintResponse.getId());
        complaint.setText(complaintRequest.getText());
        complaint.setWish(wish);
        complaint.setFromUser(user);

        return wishViewMapper.viewComplaint(complaint,user);
    }
    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepo.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }
}
