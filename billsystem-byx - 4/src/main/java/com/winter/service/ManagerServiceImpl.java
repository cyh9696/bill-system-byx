package com.winter.service;

import com.winter.mapper.ManagerMapper;
import com.winter.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "managerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;//这里会报错，但是并不会影响

    @Override
    public Manager selectManager(String managerId) {
        return managerMapper.selectManagerById(managerId);
    }

}