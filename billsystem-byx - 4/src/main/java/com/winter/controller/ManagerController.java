package com.winter.controller;

import com.winter.model.Bill;
import com.winter.model.Manager;
import com.winter.service.BillService;
import com.winter.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private ModelAndView mv2 = new ModelAndView();
    private ModelAndView mv3 = new ModelAndView();
    private ModelAndView mv4 = new ModelAndView();

    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value = "/register", produces = {"application/json;charset=UTF-8"})
    public String register() {
        return "register";
    }

    /**
     * 添加用户
     * @param manager
     * @return
     */
    //@ResponseBody
    @RequestMapping(value = "/addmanager", produces = {"application/json;charset=UTF-8"})
    public String addManager(Manager manager) {
        managerService.addManager(manager);
        return "redirect:/manager/login";
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value="/login", produces = {"application/json;charset=UTF-8"})
    public String login(Model model) {
//        Manager manager = new Manager();
//        model.addAttribute("manager", manager);
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

    /**
     * 经理 *审批* bill
     * @param bnum
     * @return 更新数据后，重新加载manager页
     */
    @RequestMapping(value = "/shenpi", produces = {"application/json;charset=UTF-8"})
    public String shenpi(@RequestParam("billNum") String bnum) {
        bill = billService.selectByPrimaryKey(bnum);
        Date edate = bill.getExpiryDate();
        Date now = new Date(System.currentTimeMillis()); //获取当前时间
        BigDecimal lr = bill.getPayeeLoanRatio();
        SimpleDateFormat formatyyyy = new SimpleDateFormat("yyyyMMdd");
        bill.setBillState("已提交审批");
        if(lr.compareTo(new BigDecimal("0.10"))<0){
            bill.setBillState("已审批");
            bill.setBillApprovalState("通过风险校验");
            if(edate.after(now)){
                bill.setBillState("已审批");
                bill.setBillApprovalState("通过流程审批");
                bill.setBatchNum("BN" + bill.getBillNum() + formatyyyy.format(now));
                billService.updateBill(bill);
                return "redirect:manager";
            }
            else{
                bill.setBillState("已审批");
                bill.setBillApprovalState("过期！");
                bill.setBatchNum("BN" + bill.getBillNum() + formatyyyy.format(now));
                billService.updateBill(bill);
                return "redirect:manager";
            }
        }
        else{
            bill.setBillState("已审批");
            bill.setBillApprovalState("有风险！");
            bill.setBatchNum("BN" + bill.getBillNum() + formatyyyy.format(now));
            billService.updateBill(bill);
            return "redirect:manager";
        }
    }

    /**
     * 经理 撤销
     * @param bnum
     * @return 更新数据后，重新加载manager页
     */
    @RequestMapping(value = "/repeal", produces = {"application/json;charset=UTF-8"})
    public String repeal(@RequestParam("billNum") String bnum) {
        bill = billService.selectByPrimaryKey(bnum);
        bill.setBillState("未提交审批");
        bill.setBillApprovalState("");
        //关于batchNum: 只要审批了，无论是否撤回都会存在
        billService.updateBill(bill);
        return "redirect:manager";
    }

    /**
     * 经理 *查看票据详细信息*
     * @param bnum
     * @return detail页
     */
    @RequestMapping(value = "/detail", produces = {"application/json;charset=UTF-8"})
    public ModelAndView detail(@RequestParam("billNum") String bnum) {
//        Bill bl;
        bill = billService.selectByPrimaryKey(bnum);
        mv2.setViewName("detail");
        mv2.addObject("name",manager.getManagerName());
        mv2.addObject("billnum",bill.getBillNum());
        mv2.addObject("bill", bill);
        return mv2;
    }

    /**
     * 经理通过搜索框，搜索票据号查看单个票据详细
     * @para bnum
     * @return detail页
     */
    @RequestMapping(value = "/search", produces = {"application/json;charset=UTF-8"})
    public ModelAndView search(@RequestParam("billNum") String bnum) {
//        Bill bl;
        bill = billService.selectByPrimaryKey(bnum);
        mv3.setViewName("search");
        mv3.addObject("name",manager.getManagerName());
        mv3.addObject("billnum",bnum);
        mv3.addObject("bill", bill);
        return mv3;
    }

    @RequestMapping(value = "/ssearch", produces = {"application/json;charset=UTF-8"})
    public ModelAndView ssearch() {
        mv4.setViewName("ssearch");
        mv4.addObject("name",manager.getManagerName());
        mv4.addObject("billnum",bill.getBillNum());
        mv4.addObject("bill", bill);
        return mv4;
    }


    /**
     * search页 *审批* single-bill
     * @param bnum
     * @return 更新数据后，重新加载search页
     */
    @RequestMapping(value = "/seshenpi", produces = {"application/json;charset=UTF-8"})
    public String seshenpi(@RequestParam("billNum") String bnum) {
        bill = billService.selectByPrimaryKey(bnum);
        Date edate = bill.getExpiryDate();
        Date now = new Date(System.currentTimeMillis()); //获取当前时间
        BigDecimal lr = bill.getPayeeLoanRatio();
        SimpleDateFormat formatyyyy = new SimpleDateFormat("yyyyMMdd");
        bill.setBillState("已提交审批");
        if(lr.compareTo(new BigDecimal("0.10"))<0){
            bill.setBillState("已审批");
            bill.setBillApprovalState("通过风险校验");
            if(edate.after(now)){
                bill.setBillState("已审批");
                bill.setBillApprovalState("通过流程审批");
                bill.setBatchNum("BN" + bill.getBillNum() + formatyyyy.format(now));
                billService.updateBill(bill);
                return "redirect:ssearch";
            }
            else{
                bill.setBillState("已审批");
                bill.setBillApprovalState("过期！");
                bill.setBatchNum("BN" + bill.getBillNum() + formatyyyy.format(now));
                billService.updateBill(bill);
                return "redirect:ssearch";
            }
        }
        else{
            bill.setBillState("已审批");
            bill.setBillApprovalState("有风险！");
            bill.setBatchNum("BN" + bill.getBillNum() + formatyyyy.format(now));
            billService.updateBill(bill);
            return "redirect:ssearch";
        }
    }

    /**
     * single页 撤销
     * @param bnum
     * @return 更新数据后，重新加载search页
     */
    @RequestMapping(value = "/serepeal", produces = {"application/json;charset=UTF-8"})
    public String serepeal(@RequestParam("billNum") String bnum) {
        bill = billService.selectByPrimaryKey(bnum);
        bill.setBillState("未提交审批");
        bill.setBillApprovalState("");
        //关于batchNum: 只要审批了，无论是否撤回都会存在
        billService.updateBill(bill);
        return "redirect:ssearch";
    }

}
