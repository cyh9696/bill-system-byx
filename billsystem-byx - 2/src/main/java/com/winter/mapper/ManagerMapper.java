package com.winter.mapper;

import com.winter.model.Manager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {
    List<Manager> selectAllManager();

    Manager selectManagerById(@Param("managerId") String ManagerId);

    int addManager(Manager manager);

    int deleteManager(String managerId);

}