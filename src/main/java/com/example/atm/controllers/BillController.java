package com.example.atm.controllers;

import com.example.atm.models.Bill;
import com.example.atm.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/atm")
public class BillController {

    @Autowired
    private BillRepository billRepository;

    // Endpoint to withdraw a specific amount
    @GetMapping(path = "/withdraw/{amount}")
    public String withdraw(@PathVariable int amount) {

        int originalAmount = amount;
        // Creating the bill objects here so I can use them at more places
        Bill bill_1000 = billRepository.findBillByValue(1000);
        Bill bill_500 = billRepository.findBillByValue(500);
        Bill bill_100 = billRepository.findBillByValue(100);

        // Checking if it should withdraw 1000, 500 or 100
        // Does this until the amount is zero or until there are no bills left
        while (amount != 0) {
            if (amount >= 1000 && amount/1000 <= bill_1000.getQuantity()) {
                amount -= 1000;
                bill_1000.setQuantity(bill_1000.getQuantity()-1);
                billRepository.save(bill_1000);
            }
            else if (amount >= 500 && amount/500 <= bill_500.getQuantity()) {
                amount -= 500;
                bill_500.setQuantity(bill_500.getQuantity()-1);
                billRepository.save(bill_500);
            }
            else if (amount >= 100 && amount/100 <= bill_100.getQuantity()) {
                amount -= 100;
                bill_100.setQuantity(bill_100.getQuantity()-1);
                billRepository.save(bill_100);
            }
            else {
                return "Can't withdraw that." + printWithdrawal();
            }
        }
        return "Your amount to withdraw was: " + originalAmount + "." + printWithdrawal();
    }

    // Endpoint to print all bills in JSON format
    @GetMapping(path = "/all")
    public Iterable<Bill> allBills() {
        return billRepository.findAll();
    }

    public String printWithdrawal() {
        StringBuilder message = new StringBuilder();
        for (Bill b : billRepository.findAll()) {
            message.append(" There are ").append(b.getQuantity()).append(" ").append(b.getValue()).append("-bills left.");
        }
        return message.toString();
    }
}
