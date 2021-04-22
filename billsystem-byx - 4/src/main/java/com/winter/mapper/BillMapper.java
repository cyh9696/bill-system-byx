package com.winter.mapper;

import com.winter.model.Bill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BillMapper {
    int deleteByPrimaryKey(String billNum);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(String billNum);

    //int 返回更新的行数 不需要
    int updateByPrimaryKeySelective(Bill record); //updateByPrimaryKeySelective会对字段进行判断再更新(如果为Null就忽略更新)，如果你只想更新某一字段，可以用这个方法。

    int updateByPrimaryKey(Bill record); //updateByPrimaryKey对你注入的字段全部更新

    List<Bill> selectAll();

//    void updateBill(String billNum, String result);
}