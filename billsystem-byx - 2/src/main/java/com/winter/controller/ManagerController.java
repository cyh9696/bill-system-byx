package com.winter.controller;

import com.winter.model.Bill;
import com.winter.model.Manager;
import com.winter.service.impl.BillService;
import com.winter.service.impl.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Manager)表控制层
 *
 * @author makejava
 * @since 2021-04-19 18:52:36
 */
@Controller //@Controller类中的方法可以直接通过返回String跳转到jsp、ftl、html等模版页面 and @RestController类中的所有方法只能返回String、Object、Json等实体对象，不能跳转到模版页面
@RequestMapping("/manager")
public class ManagerController {
    /**
     * 服务对象
     */
    @Resource
    private ManagerService managerService;
    @Resource
    private BillService billService;

    private Manager manager;
    private Bill bill;
    private ModelAndView mv = new ModelAndView();

    /**
     * 用户登录
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model) {
        Manager manager = new Manager();
        model.addAttribute("manager", manager);
        return "login";
    }
    /**
     * 登录验证
     * @param managerId
     * @param managerPassword
     * @return
     */
    @RequestMapping(value = "/verify", produces = {"application/json;charset=UTF-8"})
    public String selectManager(@RequestParam("managerId") String managerId,
                                    @RequestParam("managerPassword") String managerPassword) {
        manager = managerService.selectManager(managerId);
        System.out.println(manager.getManagerPassword().equals(managerPassword));
        if (manager.getManagerPassword().equals(managerPassword)) {
            mv.setViewName("manager");
            return "redirect:manager";
        }
        return "redirect:login";

    }

    /**
     * 经理首页显示票据系统内的票据信息
     * @return
     */
    @RequestMapping(value = "/manager", produces = {"application/json;charset=UTF-8"})
    public ModelAndView manager() {
        List<Bill> list = new ArrayList<>();
        list = billService.selectAll();
        mv.setViewName("manager");
        mv.addObject("name",manager.getManagerName());
        mv.addObject("list", list);
        return mv;
    }

}
