package com.winter.service;

import com.winter.mapper.BillMapper;
import com.winter.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;


    @Override
    public List<Bill> selectAll() {

        return billMapper.selectAll();
    }

    @Override
    public Bill selectByPrimaryKey(String billNum) {

        return billMapper.selectByPrimaryKey(billNum);
    }

    @Override
    public void updateBill(Bill bill) {

        billMapper.updateByPrimaryKeySelective(bill);
    }

    /*@Override
    public void deleteBill(String billNum) {

        BillMapper.deleteBill(billNum);
    }*/
}
