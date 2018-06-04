package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.SaleInfo;
import com.yj.foodtracesystem.model.TempModel.ProductPara;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 20:05 2018/6/3
 */
public interface SaleService {
    List<SaleInfo> findSaleInfoByProNum(String proNum);
    List<SaleInfo> findSaleInfoByTime(String startTime,String endTime);
    String findComNameByComNum(String comNum);
    String findProNameByProNum(String proNum);
    void saveSaleInfo(SaleInfo saleInfo);
}
