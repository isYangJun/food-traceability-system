package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.ComInfo;
import com.yj.foodtracesystem.model.SaleInfo;
import com.yj.foodtracesystem.repository.ComInfoRepository;
import com.yj.foodtracesystem.repository.ProductInfoRepository;
import com.yj.foodtracesystem.repository.SaleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 20:23 2018/6/3
 */
@Service("saleService")
public class SaleServiceImp implements SaleService {
    @Autowired
    private SaleInfoRepository saleInfoRepository;

    @Autowired
    private ComInfoRepository comInfoRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public List<SaleInfo> findSaleInfoByProNum(String proNum) {
        return saleInfoRepository.findByProNum(proNum);
    }

    @Override
    public List<SaleInfo> findSaleInfoByTime(String startTime, String endTime) {
        return saleInfoRepository.findByRecordedTimeBetween(startTime,endTime);
    }

    @Override
    public String findComNameByComNum(String comNum) {
        List<ComInfo> comInfo=comInfoRepository.findByComNum(comNum);
        return comInfo.get(0).getComName();
    }

    @Override
    public String findProNameByProNum(String proNum) {
        return productInfoRepository.findByProNum(proNum).getProName();
    }

    @Override
    public void saveSaleInfo(SaleInfo saleInfo) {
        saleInfoRepository.save(saleInfo);
    }

    @Override
    public int countProWeight(String proNum) {
        return 0;
    }
}
