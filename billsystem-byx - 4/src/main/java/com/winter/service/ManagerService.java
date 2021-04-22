package com.winter.service;

import com.winter.model.Manager;

public interface ManagerService {

    //经理登陆验证
    Manager selectManager(String managerId);

    //注册新经理
    int addManager(Manager manager);

}
