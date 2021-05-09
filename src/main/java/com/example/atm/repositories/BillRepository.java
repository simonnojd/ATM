package com.example.atm.repositories;

import com.example.atm.models.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
    Bill findBillByValue(int value);
}
