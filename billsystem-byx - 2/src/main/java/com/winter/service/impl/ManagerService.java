package com.winter.service.impl;

import com.winter.model.Manager;

public interface ManagerService {

    //经理登陆验证
    Manager selectManager(String managerId);

}