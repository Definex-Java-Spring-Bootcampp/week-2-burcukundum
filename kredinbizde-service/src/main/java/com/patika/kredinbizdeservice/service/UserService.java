package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.repository.UserRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserService implements IUserService {

    private UserRepository userRepository = new UserRepository();

    @Override
    public User save(User user) {
        System.out.println("userRepository: " + userRepository.hashCode());

        userRepository.save(user);

        return user;
    }
    @Override
    public Campaign saveCampaign(Campaign campaign) {
        System.out.println("userRepository: " + userRepository.hashCode());

        userRepository.saveCampaign(campaign);
        return campaign;
    }

    @Override
    public CreditCard saveCreditCard(String campaingTitle, BigDecimal fee) {
        List<Campaign> cardCampaignList = new ArrayList<>();
        String[] campaignTitlesArray = campaingTitle.split(",");

        for (String title : campaignTitlesArray) {
            String trimmedTitle = title.trim();

            Optional<Campaign> foundCampaign = userRepository.findByCampaignName(trimmedTitle);
            cardCampaignList.add(foundCampaign.get());
        }
        userRepository.saveCreditCard(fee, cardCampaignList);

        return null;
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return userRepository.getAllCreditCards();
    }

    @Override
    public List<Campaign> getAllCampaignsByOrder() {
        return userRepository.getAllCampaignsByOrder();
    }

    @Override
    public Application saveApp(String email, BigDecimal amount, Integer installment, Double interestRate, LoanType loanType) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()) {
            userRepository.save_app(foundUser.get(), amount, installment, interestRate, loanType);
        }
        return null;
    }



    @Override
    public List<User> getAll() {
        System.out.println("userRepository: " + userRepository.hashCode());
        return userRepository.getAll();
    }

    @Override
    public List<Application> getLoanAppsByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()) {
            return userRepository.getUserApplications(foundUser.get());
        }

        return null;
    }

    @Override
    public User getByEmail(String email) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        User user = null;

        if (foundUser.isPresent()) {
            user = foundUser.get();
        }

        return user;

    }

    @Override
    public User update(String email, User user) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        foundUser.get().setName(user.getName());

        foundUser.get().setSurname(user.getSurname());

        userRepository.delete(user);

        userRepository.save(foundUser.get());

        return foundUser.get();
    }
}
