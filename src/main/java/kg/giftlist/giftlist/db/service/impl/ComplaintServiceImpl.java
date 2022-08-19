package kg.giftlist.giftlist.db.service.impl;


import kg.giftlist.giftlist.db.models.Complaint;
import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.models.Wish;
import kg.giftlist.giftlist.db.repositories.ComplaintRepository;
import kg.giftlist.giftlist.db.repositories.GiftRepository;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.repositories.WishRepository;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintRequest;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintResponse;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintViewMapper;
import kg.giftlist.giftlist.dto.mapper.wish.WishViewMapper;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.ForbiddenException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl {
    private final WishRepository wishRepository;
    private final GiftRepository giftRepository;
    private final ComplaintViewMapper complaintViewMapper;
    private final UserRepository userRepo;
    private final ComplaintRepository complaintRepository;

    @Transactional
    public ComplaintResponse sendComplaintToWish(Long wishId, String text) {
        User user = getAuthenticatedUser();
        Complaint complaint = new Complaint();
        Wish wish = wishRepository.findById(wishId).orElseThrow(() ->
                new NotFoundException("Gift with id: " + wishId + "not found"));
        complaint.setText(text);
        complaint.setWish(wish);
        complaint.setFromUser(user);
        complaintRepository.save(complaint);

        return complaintViewMapper.viewComplaintWish(complaint, user);
    }

    public List<ComplaintResponse> getAllComplaints(List<Complaint> complaints) {
        List<ComplaintResponse> complaintResponses = new ArrayList<>();
        for (Complaint complaint : complaints) {
            if(complaint.getWish()!=null){
            complaintResponses.add(complaintViewMapper.viewComplaintWish(complaint,getAuthenticatedUser()));}
            else{
            complaintResponses.add(complaintViewMapper.viewComplaintGift(complaint,getAuthenticatedUser()));
        }
        }
        return complaintResponses;
    }

    public List<ComplaintResponse> getComplaints() {

        return getAllComplaints(complaintRepository.getAllComplaint());
    }

    @Transactional
    public ComplaintResponse sendComplaintToGift(Long giftId,String text) {
        User user = getAuthenticatedUser();
        Complaint complaint = new Complaint();
        Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
              new NotFoundException("Gift with id: " + giftId + "not found"));
        complaint.setText(text);
        complaint.setGift(gift);
        complaint.setFromUser(user);
        complaintRepository.save(complaint);

        return complaintViewMapper.viewComplaintGift(complaint, user);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepo.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }
}
