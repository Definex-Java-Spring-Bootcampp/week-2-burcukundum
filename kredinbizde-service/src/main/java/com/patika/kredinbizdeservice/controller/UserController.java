package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        System.out.println("userService: " + userService.hashCode());
        return  userService.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> update(@PathVariable String email, @RequestBody User user) {

        User user1 = userService.update(email, user);

        if (user1 != null){
            return ResponseEntity.ok().body(user1);
        }

        return ResponseEntity.notFound().build();
    }
    private final ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("/appLoan")
    @ResponseStatus(HttpStatus.CREATED)
    public Application create(@RequestBody String request, @RequestParam LoanType loanType) {
        System.out.println("userService: " + userService.hashCode());
        try {
            JsonNode rootNode = objectMapper.readTree(request);
            String email = rootNode.get("email").asText();
            BigDecimal amount = new BigDecimal(rootNode.get("amount").asText());
            int installment = rootNode.get("installment").asInt();
            double interestRate = rootNode.get("interestRate").asDouble();

            return userService.saveApp(email, amount, installment, interestRate, loanType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/campaigns")
    @ResponseStatus(HttpStatus.CREATED)
    public Campaign create(@RequestBody Campaign campaign) {
        return  userService.saveCampaign(campaign);
        }

    @GetMapping("/campaignsByOrder")
    public List<Campaign> getAllCampaignsByOrder() {
        return userService.getAllCampaignsByOrder();
    }


    private final ObjectMapper objectMapper2 = new ObjectMapper();
    @PostMapping("/creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard create(@RequestBody String request) {
        try {
            JsonNode rootNode = objectMapper2.readTree(request);
            String campaingTitle = rootNode.get("campaingTitle").asText();
            BigDecimal fee = new BigDecimal(rootNode.get("fee").asText());

            return userService.saveCreditCard(campaingTitle, fee);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/creditCard")
    public List<CreditCard> getAllCreditCards() {
        return userService.getAllCreditCards();
    }

    @GetMapping("/{loanAppByEmail}/{email}")
    public List<Application> getLoanAppsByEmail(@PathVariable String email) {
        List<Application> loanApps = userService.getLoanAppsByEmail(email);
        System.out.println(loanApps);
        return loanApps;
    }


   /* @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }*/
}
