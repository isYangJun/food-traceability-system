package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.QueryPara;
import com.yj.foodtracesystem.repository.FiledInfoRepository;
import com.yj.foodtracesystem.repository.FiledOperationTypeRepository;
import com.yj.foodtracesystem.service.CoopService;
import com.yj.foodtracesystem.service.FarmerService;
import com.yj.foodtracesystem.service.PublicService;
import com.yj.foodtracesystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 19:27 2018/5/16
 */
@Controller
public class CooperatorController {
    @Autowired
    private UserService userService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private CoopService coopService;
    @Autowired
    private FarmerService farmerService;
    @Autowired
    private FiledInfoRepository filedInfoRepository;

    @Autowired
    private FiledOperationTypeRepository filedOperationTypeRepository;

    private static final Logger logger=LoggerFactory.getLogger(CooperatorController.class);
    @RequestMapping(value = "/cooperator/hom", method = RequestMethod.GET)
    @ResponseBody
    public String coophome() {
        logger.info("coophome home");
        return "coophome home";
    }

    /*生产订单管理*/
    @GetMapping("/cooperator/orderMan")
    public ModelAndView orderMan() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");

        modelAndView.addObject("opeartionOrderInfo", new OperationOrderInfo());

        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("filedInfoList", filedInfoList);

        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList", seedInfoList);

        List<FiledOperationType> operationTypeList = farmerService.findAllFiledOperationType();
        modelAndView.addObject("operationTypeList", operationTypeList);

        List<User> userInfoList = coopService.findByUserCompAndRole(user.getUserComp(), 2);
        modelAndView.addObject("userInfoList", userInfoList);

        List<OperationOrderInfo> operationOrderInfoList = coopService.findAllOperationOrderInfo();
        modelAndView.addObject("orderInfoResList", operationOrderInfoList);
        modelAndView.setViewName("cooperator/orderMan");
        return modelAndView;
    }

    @PostMapping(value = "/cooperator/addOrder")
    public ModelAndView addOrder(OperationOrderInfo operationOrderInfo) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        operationOrderInfo.setFiledName(filedInfoRepository.findById(operationOrderInfo.getFiledId()).getFiledName());
        operationOrderInfo.setCreaterId(user.getUserNum());
        operationOrderInfo.setCreaterName(user.getName());
        operationOrderInfo.setIsDone(0);
        operationOrderInfo.setOperationName(filedOperationTypeRepository.findById(operationOrderInfo.getOperateTypeId()).getOperationName())
        ;
        operationOrderInfo.setUserName(userService.findUserByUserNum(operationOrderInfo.getUserId()).getName());
        operationOrderInfo.setOrderTime(publicService.getStringDate());
        operationOrderInfo.setSeedName(farmerService.findBySeedId(operationOrderInfo.getSeedId()).getSeedName());
        coopService.saveOperationOrderInfo(operationOrderInfo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("opeartionOrderInfo", new OperationOrderInfo());

        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("filedInfoList", filedInfoList);

        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList", seedInfoList);

        List<FiledOperationType> operationTypeList = farmerService.findAllFiledOperationType();
        modelAndView.addObject("operationTypeList", operationTypeList);

        List<User> userInfoList = coopService.findByUserCompAndRole(user.getUserComp(), 2);
        modelAndView.addObject("userInfoList", userInfoList);

        List<OperationOrderInfo> operationOrderInfoList = coopService.findAllOperationOrderInfo();
        modelAndView.addObject("orderInfoResList", operationOrderInfoList);

        modelAndView.setViewName("cooperator/orderMan");
        return modelAndView;
    }

    /*
     * **************************生产者管理*************************/
    @GetMapping(value = "/cooperator/farmerMan")
    public ModelAndView home() {
        ModelAndView modelAndView = getModelAndView();
        User farmerInfo = new User();
        User farmerInfoById = new User();
        QueryPara farmerInfoByTime = new QueryPara();
        modelAndView.addObject("farmerInfo", farmerInfo);
        modelAndView.addObject("farmerInfoById", farmerInfoById);
        modelAndView.addObject("farmerInfoByTime", farmerInfoByTime);
        modelAndView.setViewName("cooperator/farmerMan");
        return modelAndView;
    }

    @PostMapping("/cooperator/addFarmer")
    public ModelAndView addFarmer(User farmerInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        farmerInfo.setActive(1);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        farmerInfo.setPassword("123456");
        farmerInfo.setUserCompName(user.getUserCompName());
        farmerInfo.setUserComp(user.getUserComp());
        farmerInfo.setRegTime(publicService.getStringDate());
        Role role = new Role();
        role.setRole("2");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        farmerInfo.setRoles(roles);
        userService.saveUser(farmerInfo);
        modelAndView.addObject("farmerInfo", new User());
        modelAndView.addObject("farmerInfoById", new User());
        modelAndView.addObject("farmerInfoByTime", new QueryPara());
        modelAndView.setViewName("cooperator/farmerMan");
        return modelAndView;
    }

    @PostMapping("/cooperator/queryFarmerById")
    public ModelAndView queryFarmerById(User farmerInfoById) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<User> farmerInfoResList = userService.findByUserNumOrName(farmerInfoById.getName(), farmerInfoById.getUserNum(), user.getUserComp());
        modelAndView.addObject("farmerInfoResList", farmerInfoResList);
        modelAndView.addObject("farmerInfo", new User());
        modelAndView.addObject("farmerInfoById", new User());
        modelAndView.addObject("farmerInfoByTime", new QueryPara());
        modelAndView.setViewName("cooperator/farmerMan");
        return modelAndView;
    }

    @PostMapping("/cooperator/queryFarmerByTime")
    public ModelAndView queryFarmerByTime(QueryPara farmerInfoByTime) {
        ModelAndView modelAndView = getModelAndView();
        String startTime = farmerInfoByTime.getStartTime();
        String endTime = farmerInfoByTime.getEndTime();
        List<User> farmerInfoResList = userService.findByTime(startTime, endTime);
        modelAndView.addObject("farmerInfoResList", farmerInfoResList);
        modelAndView.addObject("farmerInfo", new User());
        modelAndView.addObject("farmerInfoById", new User());
        modelAndView.addObject("farmerInfoByTime", new QueryPara());
        modelAndView.setViewName("cooperator/farmerMan");
        return modelAndView;
    }

    /*
     * **************************合作社管理*************************/
    @GetMapping(value = "/cooperator/coopMan")
    public ModelAndView coopMan() {
        ModelAndView modelAndView = initialCoopMan();
        modelAndView.addObject("comInfoPara", new ComInfo());
        modelAndView.setViewName("cooperator/coopMan");
        return modelAndView;
    }

    @PostMapping("/cooperator/viewComInfo")
    public ModelAndView viewComInfo(ComInfo comInfoView) {
        ModelAndView modelAndView = initialCoopMan();
        List<ComInfo> comInfoList = coopService.findComInfoByNumorAdd(comInfoView.getComNum(), comInfoView.getComAdd());
        modelAndView.addObject("comInfoPara", new ComInfo());
        modelAndView.addObject("comInfoResList", comInfoList);
        modelAndView.setViewName("cooperator/coopMan");
        return modelAndView;
    }

    @PostMapping("/cooperator/queryComInfoById")
    public ModelAndView queryComInfoById(ComInfo comInfoPara) {
        ModelAndView modelAndView = initialCoopMan();
        coopService.updateComInfo(comInfoPara.getComName(), comInfoPara.getComAdd(), comInfoPara.getComNum());
        modelAndView.addObject("comInfoPara", new ComInfo());
        modelAndView.setViewName("cooperator/coopMan");
        return modelAndView;
    }

    private ModelAndView initialCoopMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        modelAndView.addObject("comInfoView", new ComInfo());
        List<ComInfo> comInfoPreList = coopService.findAll();
        List<ComInfo> comAddList = coopService.findDistinctComAdd();
        modelAndView.addObject("comAddList", comAddList);
        modelAndView.addObject("compNum", user.getUserComp());
        modelAndView.addObject("comInfoPreList", comInfoPreList);
        return modelAndView;
    }


    private ModelAndView getModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        return modelAndView;
    }

    /*
     * ***************************生产信息管理************************/
    @GetMapping(value = "/cooperator/productMan")
    public ModelAndView productMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        modelAndView.addObject("productInfo", new ProductInfo());
        modelAndView.addObject("compNum", user.getUserComp());
        modelAndView.addObject("compName", user.getUserCompName());
        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("filedInfoList", filedInfoList);
        modelAndView.addObject("creatQRCode", new ProductInfo());
        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList", seedInfoList);
        modelAndView.addObject("productInfoById", new ProductInfo());
        modelAndView.addObject("proInfoByTime", new QueryPara());
        List<ProductBatchInfo> productBatchInfoList = coopService.findAllProBatchInfo();
        modelAndView.addObject("productBatchInfoList", productBatchInfoList);
        modelAndView.setViewName("cooperator/productMan");
        return modelAndView;
    }

    @PostMapping(value = "/cooperator/addProduct")
    public ModelAndView addProduct(ProductInfo productInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        productInfo.setCoopName(user.getUserCompName());
        String proNum = productInfo.getCoopNum() + String.valueOf(productInfo.getFiledNum()) + String.valueOf(productInfo.getSeedNum());
        proNum += publicService.getTimeStamp();
        productInfo.setProNum(proNum);
        productInfo.setHarvTime(publicService.formatTime(productInfo.getHarvTime()));
        productInfo.setProName(farmerService.findBySeedId(productInfo.getSeedNum()).getSeedName());
        productInfo.setSeedName(farmerService.findBySeedId(productInfo.getSeedNum()).getSeedName());
        productInfo.setIsTransed(1);
        productInfo.setOperatorNum(user.getUserNum());
        coopService.saveProInfo(productInfo);
        modelAndView.addObject("productInfo", new ProductInfo());
        modelAndView.addObject("compNum", user.getUserComp());
        modelAndView.addObject("compName", user.getUserCompName());
        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("filedInfoList", filedInfoList);
        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("creatQRCode", new ProductInfo());
        modelAndView.addObject("seedInfoList", seedInfoList);
        modelAndView.addObject("productInfoById", new ProductInfo());
        modelAndView.addObject("proInfoByTime", new QueryPara());
        List<ProductBatchInfo> productBatchInfoList = coopService.findAllProBatchInfo();
        modelAndView.addObject("productBatchInfoList", productBatchInfoList);
        modelAndView.setViewName("cooperator/productMan");
        return modelAndView;
    }

    @PostMapping(value = "/cooperator/queryProInfoById")
    public ModelAndView queryProInfoById(ProductInfo productInfoById) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        productInfoById.setSeedName(coopService.findSeedNameBySeedNum(productInfoById.getSeedNum()));
        List<ProductInfo> proInfoResList = coopService.findByFiledNumOrSeedName(productInfoById.getFiledNum(), productInfoById.getSeedName());
        modelAndView.addObject("proInfoResList", proInfoResList);
        modelAndView.addObject("productInfo", new ProductInfo());
        modelAndView.addObject("compNum", user.getUserComp());
        modelAndView.addObject("compName", user.getUserCompName());
        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("creatQRCode", new ProductInfo());
        modelAndView.addObject("filedInfoList", filedInfoList);
        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList", seedInfoList);
        modelAndView.addObject("productInfoById", new ProductInfo());
        modelAndView.addObject("proInfoByTime", new QueryPara());
        List<ProductBatchInfo> productBatchInfoList = coopService.findAllProBatchInfo();
        modelAndView.addObject("productBatchInfoList", productBatchInfoList);
        modelAndView.setViewName("cooperator/productMan");
        return modelAndView;
    }

    @PostMapping(value = "/cooperator/proInfoByTime")
    public ModelAndView proInfoByTime(QueryPara proInfoByTime) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<ProductInfo> proInfoResList = coopService.findByHarvTime(proInfoByTime.startTime, proInfoByTime.endTime);
        modelAndView.addObject("proInfoResList", proInfoResList);
        modelAndView.addObject("creatQRCode", new ProductInfo());
        modelAndView.addObject("productInfo", new ProductInfo());
        modelAndView.addObject("compNum", user.getUserComp());
        modelAndView.addObject("compName", user.getUserCompName());
        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("filedInfoList", filedInfoList);
        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList", seedInfoList);
        modelAndView.addObject("productInfoById", new ProductInfo());
        modelAndView.addObject("proInfoByTime", new QueryPara());
        List<ProductBatchInfo> productBatchInfoList = coopService.findAllProBatchInfo();
        modelAndView.addObject("productBatchInfoList", productBatchInfoList);
        modelAndView.setViewName("cooperator/productMan");
        return modelAndView;
    }


    /**************** add product bach info******************/
    @GetMapping(value = "/cooperator/productBachMan")
    public ModelAndView productBachMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");

        modelAndView.addObject("productBatchInfo", new ProductBatchInfo());
        modelAndView.addObject("compNum", user.getUserComp());
        modelAndView.addObject("compName", user.getUserCompName());

        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("filedInfoList", filedInfoList);

        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList", seedInfoList);
        modelAndView.addObject("proBachInfoByTime", new QueryPara());
        modelAndView.setViewName("cooperator/productBachMan");
        return modelAndView;
    }

    @PostMapping(value = "/cooperator/addProductBachInfo")
    public ModelAndView addProductBachInfo(ProductBatchInfo productBatchInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");

        productBatchInfo.setCoopName(user.getUserCompName());
        String proBatchNum = publicService.getTimeStamp() + productBatchInfo.getCoopNum() + String.valueOf(productBatchInfo.getFiledNum()) + String.valueOf(productBatchInfo.getSeedNum()) + productBatchInfo.getProWeight();
        productBatchInfo.setProBatchNum(proBatchNum);

        productBatchInfo.setHarvTime(publicService.formatTime(productBatchInfo.getHarvTime()));

        productBatchInfo.setSeedName(farmerService.findBySeedId(productBatchInfo.getSeedNum()).getSeedName());
        productBatchInfo.setOperatorNum(user.getUserNum());
        coopService.saveproBachInfo(productBatchInfo);

        modelAndView.addObject("productBatchInfo", new ProductBatchInfo());
        modelAndView.addObject("compNum", user.getUserComp());
        modelAndView.addObject("compName", user.getUserCompName());

        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("filedInfoList", filedInfoList);

        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList", seedInfoList);
        modelAndView.addObject("proBachInfoByTime", new QueryPara());
        modelAndView.setViewName("cooperator/productBachMan");
        return modelAndView;
    }

    @PostMapping(value = "/cooperator/proBachInfoByTime")
    public ModelAndView proBachInfoByTime(QueryPara proBachInfoByTime) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");

        modelAndView.addObject("productBatchInfo", new ProductBatchInfo());
        modelAndView.addObject("compNum", user.getUserComp());
        modelAndView.addObject("compName", user.getUserCompName());

        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("filedInfoList", filedInfoList);

        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList", seedInfoList);

        List<ProductBatchInfo> proBachInfoResList = coopService.findProBatchInfoByTime(proBachInfoByTime.startTime, proBachInfoByTime.endTime);
        modelAndView.addObject("proBachInfoResList", proBachInfoResList);

        modelAndView.addObject("proBachInfoByTime", new QueryPara());
        modelAndView.setViewName("cooperator/productBachMan");
        return modelAndView;
    }

    @PostMapping("/cooperator/creatQRCode")
    public ModelAndView testQRCode(ProductInfo creatQRCode) {
        ModelAndView modelAndView = new ModelAndView();
        String url = "http://173919zc58.imwork.net:45167/QRCodeRes?proNum=" + creatQRCode.getProNum();
        modelAndView.addObject("URL", url);
        modelAndView.addObject("proNum", creatQRCode.getProNum());
        modelAndView.setViewName("cooperator/creatQRCode");
        return modelAndView;
    }
}
