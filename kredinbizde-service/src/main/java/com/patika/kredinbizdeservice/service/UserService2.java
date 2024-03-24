
package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.model.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserService2 implements IUserService {
    @Override
    public User save(User user) {
        return null;
    }

    public Campaign saveCampaign(Campaign campaign) {
        return null;
    }

    public CreditCard saveCreditCard(String campaingTitle, BigDecimal fee) {
        return null;
    }

    @Override
    public Application saveApp(String email, BigDecimal amount, Integer installment, Double interestRate, LoanType loanType) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return null;
    }

    @Override
    public List<Campaign> getAllCampaignsByOrder() {
        return null;
    }

    @Override
    public List<Application> getLoanAppsByEmail(String email) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User update(String email, User user) {
        return null;
    }
}
