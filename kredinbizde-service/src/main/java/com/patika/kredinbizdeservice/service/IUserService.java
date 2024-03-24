package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface IUserService {
    User save(User user);

    Application saveApp(String email, BigDecimal amount, Integer installment, Double interestRate, LoanType loanType);

    Campaign saveCampaign(Campaign campaign);

    CreditCard saveCreditCard(String campaingTitle, BigDecimal fee);

    List<Application> getLoanAppsByEmail(String email);

    List<User> getAll();

    List<CreditCard> getAllCreditCards();

    List<Campaign> getAllCampaignsByOrder();

    User getByEmail(String email);

    User update(String email, User user);
}
