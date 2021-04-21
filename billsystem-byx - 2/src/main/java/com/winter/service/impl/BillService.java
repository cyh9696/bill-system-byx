package com.winter.service.impl;

import com.winter.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> selectAll();

    Bill selectByPrimaryKey(String billNum);

    //void updateBill(String billNumber, String result);

    //void deleteBill(Integer billNumber);
}
