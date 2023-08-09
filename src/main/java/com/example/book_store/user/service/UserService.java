package com.example.book_store.user.service;

import com.example.book_store.auth.repository.AuthorityRepository;
import com.example.book_store.common.UserNotFoundException;
import com.example.book_store.user.common.*;
import com.example.book_store.auth.domain.Authority;
import com.example.book_store.user.domain.Address;
import com.example.book_store.user.domain.CreditCard;
import com.example.book_store.user.domain.User;
import com.example.book_store.user.repository.AddressRepository;
import com.example.book_store.user.repository.CreditCardRepository;
import com.example.book_store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CreditCardRepository creditCardRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final UserDetailsServiceImpl userDetailsService;

    @Transactional
    public void processRegistration(RegistrationForm form) {
        log.info("UserService -> processRegistration : OK");
        var auth = new Authority();
        auth.setRole(Role.ROLE_USER);

        var newUser = form.toUser(passwordEncoder);
        auth.setUser(newUser);

        var savedUser = userRepository.save(newUser);
        savedUser.getAuthorities().add(auth);

        authorityRepository.save(auth);
    }



    @Transactional
    public void addCardToUser(AddCardForm cardForm, String username) {
        User user = userRepository.findByUsername(username);
        CreditCard creditCard = new CreditCard(
                cardForm.getNumber(),
                cardForm.getValidation(),
                cardForm.getType(),
                user
        );
        creditCardRepository.save(creditCard);
    }

    @Transactional
    public void addAddressToUser(AddAddressForm addressForm, String username) {
        User user = userRepository.findByUsername(username);
        Address address = new Address(
                addressForm.getPostalCode(),
                addressForm.getDefaultAddress(),
                addressForm.getDetailAddress(),
                user
        );
        addressRepository.save(address);
    }


    public UserInformationDto findUserInformationByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return UserInformationDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .grade(user.getGrade())
                .mileage(user.getMileage())
                .createdDate(user.getCreatedDate())
                .build();
    }

    public List<CreditCard> findUserCardListByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return user.getCardList().stream().toList();
    }

    public List<Address> findUserAddressListByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return user.getAddressList().stream().toList();
    }
}

