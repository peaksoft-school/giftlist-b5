package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.Complaint;
import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.models.Wish;
import kg.giftlist.giftlist.db.repositories.ComplaintRepository;
import kg.giftlist.giftlist.db.repositories.GiftRepository;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.repositories.WishRepository;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintResponse;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintViewMapper;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.exception.WishNotFoundException;
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
    public SimpleResponse sendComplaintToWish(Long wishId, String text) {
        User user = getAuthenticatedUser();
        Complaint complaint = new Complaint();
        Wish wish = wishRepository.findById(wishId).orElseThrow(() ->
                new NotFoundException("Wish with id: " + wishId + "not found"));
        complaint.setText(text);
        complaint.setWish(wish);
        complaint.setFromUser(user);
        complaintRepository.save(complaint);

        return new SimpleResponse("Отправлено! Спасибо, что сообщили нам об этом", "Ваши отзывы помогают нам сделать сообщество GIFT LIST безопасной средой для всех");

    }


    public List<ComplaintResponse> getComplaints() {
        List<ComplaintResponse> complaintResponses = new ArrayList<>();
        for (Complaint complaint : complaintRepository.getAllComplaint()) {
            complaintResponses.add(complaintViewMapper.viewComplaints(complaint));
        }

        return complaintResponses;
    }

    @Transactional
    public SimpleResponse sendComplaintToGift(Long giftId, String text) {
        User user = getAuthenticatedUser();
        Complaint complaint = new Complaint();
        Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
                new NotFoundException("Gift with id: " + giftId + "not found"));
        complaint.setText(text);
        complaint.setGift(gift);
        complaint.setFromUser(user);
        complaintRepository.save(complaint);

        return new SimpleResponse("Отправлено!Спасибо, что сообщили нам об этом", "Ваши отзывы помогают нам сделать сообщество GIFT LIST безопасной средой для всех");

    }

    public SimpleResponse deleteComplaintById(Long id) {
        boolean exists = complaintRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("Complaint with id = " + id + " not found!");
        }
        complaintRepository.deleteById(id);
        return new SimpleResponse("Deleted!", "Complaint successfully deleted!");
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepo.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }
}
