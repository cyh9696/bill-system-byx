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

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);

    List<Bill> selectAll();
}