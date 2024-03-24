package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;

public class UserRepository {

    private List<User> userList = new ArrayList<>();
    private List<Application> applicationList = new ArrayList<>();

    private List<Campaign> campaignList = new ArrayList<>();

    private List<CreditCard> creditCardList = new ArrayList<>();

    public void save(User user) {
        userList.add(user);
    }

    public void saveCampaign(Campaign campaign) {
        campaignList.add(campaign);
    }

    public void save_app(User user, BigDecimal amount, Integer installment, Double interestRate, LoanType  loanType) {
        Loan loan;

        switch (loanType) {
            case IHTIYAC_KREDISI:
                loan = new ConsumerLoan(amount, installment, interestRate);
                break;
            case KONUT_KREDISI:
                loan = new HouseLoan(amount, installment, interestRate);
                break;
            case ARAC_KREDISI:
                loan = new VechileLoan(amount, installment, interestRate);
                break;
            default:
                throw new IllegalArgumentException("Invalid loan type");
        }

        Application application = new Application(loan, user, LocalDateTime.now());
        applicationList.add(application);
    }

    public List<Application> getUserApplications(User user) {

        List<Application> userApplications = new ArrayList<>();
        for (Application application : applicationList) {
            User userInTheAppList = application.getUser();

            if (userInTheAppList != null && userInTheAppList.equals(user)) {
                userApplications.add(application);
            }
        }
        return userApplications;
    }
    public List<User> getAll() {

        return userList;
    }

    public CreditCard saveCreditCard(BigDecimal fee, List<Campaign> campaign) {

        CreditCard creditCard = new CreditCard(fee, campaign);
        creditCardList.add(creditCard);
        return creditCard;
    }

    public List<CreditCard> getAllCreditCards() {

        return creditCardList;
    }

    public List<Campaign> getAllCampaignsByOrder() {

        Collections.sort(campaignList, new Comparator<Campaign>() {
            @Override
            public int compare(Campaign campaign1, Campaign campaign2) {
                return campaign2.getCreateDate().compareTo(campaign1.getCreateDate());
            }
        });

        return campaignList;
    }






    public Optional<Campaign> findByCampaignName(String campaingTitle) {
        return campaignList.stream()
                .filter(campaign -> campaign.getTitle().equals(campaingTitle))
                .findFirst();
    }

    public Optional<User> findByEmail(String email) {
        return userList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public void delete(User user) {
        userList.remove(user);
    }
}
