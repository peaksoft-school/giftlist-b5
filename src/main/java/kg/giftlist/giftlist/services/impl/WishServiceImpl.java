package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.mapper.wish.WishEditMapper;
import kg.giftlist.giftlist.dto.mapper.wish.WishViewMapper;
import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.exception.WishNotFoundException;
import kg.giftlist.giftlist.models.Wish;
import kg.giftlist.giftlist.repositories.WishRepository;
import kg.giftlist.giftlist.services.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;

    private final WishEditMapper editMapper;

    private final WishViewMapper viewMapper;

    @Override
    public WishResponse create (WishRequest wishRequest) {

//        Wish wish = wishRepository.findById(wishRequest.getHolidayId()).orElseThrow(() ->
//
//                new CompanyNotFoundException(
//
//                        "Company with id " + courseRequest.getCompanyId() + " not found!"
//
//                ));

        Wish wish = editMapper.create(wishRequest);

        wishRepository.save(wish);

        return viewMapper.viewWish(wish);

    }

    @Override
    public WishResponse update(Long id,
                                 WishRequest wishRequest){

        Wish wish = wishRepository.findById(id)

                .orElseThrow(()-> new WishNotFoundException(

                                "Wish with id "+ id  +" not found!"

                        )
                );

        editMapper.update(wish, wishRequest);

        return viewMapper.viewWish(wishRepository.save(wish));

    }

    @Override
    public WishResponse findById(Long id){

        Wish wish = getWishById(id);

        return viewMapper.viewWish(wish);

    }

    private Wish getWishById(Long wishId) {

        return wishRepository.findById(wishId).

                orElseThrow(() -> new WishNotFoundException(

                        "Wish with id = " + wishId + " not found!"

                ));
    }

    public SimpleResponse deleteById(Long id){

        boolean exists = wishRepository.existsById(id);

        if (!exists) {

            throw new WishNotFoundException(

                    "Wish with id = " + id + " not found!"

            );
        }

        wishRepository.deleteById(id);

        return new SimpleResponse("Deleted!",
                "Wish successfully deleted!");

    }

    public List<WishResponse> getAllWishes(){

        return viewMapper.view(wishRepository.findAll());

    }
}
