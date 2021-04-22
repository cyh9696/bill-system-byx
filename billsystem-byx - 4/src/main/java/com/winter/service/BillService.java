package com.winter.service;

import com.winter.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> selectAll();

    Bill selectByPrimaryKey(String billNum);

    void updateBill(Bill bill);

    //void deleteBill(Integer billNumber);
}
