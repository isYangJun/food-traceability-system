package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.ComInfo;
import com.yj.foodtracesystem.model.SaleInfo;
import com.yj.foodtracesystem.repository.ComInfoRepository;
import com.yj.foodtracesystem.repository.ProductInfoRepository;
import com.yj.foodtracesystem.repository.SaleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Autowired
    private CoopService coopService;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

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
    public String countProWeightByProBatchNum(String proBatchNum) {

        String sql="SELECT SUM(ti_product_weight) FROM tbl_transport_info  WHERE ti_probatch_num='"+proBatchNum+"' AND ti_destination_role=4";
        Query nativeQuery = entityManager.createNativeQuery(sql);
       double totalRealProWeight = (double)nativeQuery.getSingleResult();

        double totalPreProWeight=coopService.findProBatchWeightByBatchNum(proBatchNum);
       if((totalRealProWeight>=totalPreProWeight-10)||(totalRealProWeight<=totalPreProWeight+10)){
           return "通过对比计算，该批次产品重量可靠";
       }
        return "通过对比计算，该批次产品重量不可靠，请联系监管部门";
    }
}
