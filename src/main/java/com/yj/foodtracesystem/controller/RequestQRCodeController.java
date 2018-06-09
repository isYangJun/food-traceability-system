package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 19:53 2018/6/6
 */
@Controller
public class RequestQRCodeController {
    @Autowired
    private FarmerService farmerService;
    @Autowired
    private CoopService coopService;
    @Autowired
    private TransporterService transporterService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private ReposService reposService;

    @GetMapping("/QRCodeRes")
    public ModelAndView qRCoqdeRes(@RequestParam(value = "proNum") String proNum) {
        ModelAndView modelAndView = new ModelAndView();
        List<SeedInfo> seedInfoResList = farmerService.findSeedInfoById(coopService.findSeedIdByProductNum(proNum));
        modelAndView.addObject("seedInfoResList", seedInfoResList);

        int seedId = coopService.findSeedIdByProductNum(proNum);
        List<FiledOperation> operationHisResList = farmerService.findFiledOperationBySeedId(seedId);
        modelAndView.addObject("operationHisResList", operationHisResList);

        List<ProductInfo> proInfoResList = coopService.findByProductNumIn(proNum);
        modelAndView.addObject("proInfoResList", proInfoResList);

        List<TransportInfo> transInfoResList = transporterService.findTransInfoByProNum(proNum);
        modelAndView.addObject("transInfoResList", transInfoResList);

        List<RepositoryInfo> repositoryInfoList = reposService.findRepositoryInfoByProNum(proNum);
        modelAndView.addObject("repositoryInfoList", repositoryInfoList);

        List<SaleInfo> saleInfoResList = saleService.findSaleInfoByProNum(proNum);
        modelAndView.addObject("saleInfoResList", saleInfoResList);

        modelAndView.setViewName("QRCodeRes");
        return modelAndView;
    }
}
