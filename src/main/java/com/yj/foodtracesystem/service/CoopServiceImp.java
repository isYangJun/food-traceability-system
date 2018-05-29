package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.ComInfo;
import com.yj.foodtracesystem.model.ProductInfo;
import com.yj.foodtracesystem.repository.ComInfoRepository;
import com.yj.foodtracesystem.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 12:46 2018/5/18
 */
@Service("coopService")
public class CoopServiceImp implements CoopService {
    @Autowired
    private ComInfoRepository comInfoRepository;

    @Autowired
    private ProductInfoRepository productIndoRepository;
    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ComInfo> findAll() {
        return comInfoRepository.findAll();
    }

    @Override
    public List<ComInfo> findComInfoByComAdd(String comAdd) {
        return comInfoRepository.findByComAdd(comAdd);
    }

    @Override
    public List<ComInfo> findComInfoByComNum(String comNum) {
        return comInfoRepository.findByComNum(comNum);
    }

    @Override
    public List<ComInfo> findDistinctComAdd() {
        String sql = "SELECT DISTINCT ci_company_add FROM tbl_company_info";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<ComInfo> list = nativeQuery.getResultList();
        return list;
    }

    @Override
    public List<ComInfo> findComInfoByNumorAdd(String comNum, String comAdd) {
        List<ComInfo> comInfoList = comInfoRepository.findByComNum(comNum);
        List<ComInfo> comInfoList1 = comInfoRepository.findByComAdd(comAdd);
        for (ComInfo comInfo : comInfoList1
                ) {
            comInfoList.add(comInfo);
        }
        return comInfoList;
    }

    @Override
    public void updateComInfo(String comName, String comAdd, String comNum) {
        String sql = "UPDATE tbl_company_info SET ci_company_name='" + comName + "',ci_company_add='" + comAdd + "' WHERE ci_company_num='" + comNum + "';";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.executeUpdate();
    }

    @Override
    public void saveProInfo(ProductInfo productInfo) {
        productIndoRepository.save(productInfo);
    }

    @Override
    public List<ProductInfo> findByFiledNum(int id) {
        return productIndoRepository.findByFiledNum(id);
    }

    @Override
    public List<ProductInfo> findBySeedName(String seedName) {
        return productIndoRepository.findBySeedName(seedName);
    }

    @Override
    public List<ProductInfo> findByFiledNumOrSeedName(int id,String seedName) {
        List<ProductInfo> productInfoList= productIndoRepository.findBySeedName(seedName);
        List<ProductInfo>productInfoList1=productIndoRepository.findByFiledNum(id);
        for(ProductInfo productInfo:productInfoList1){
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }

    @Override
    public List<ProductInfo> findByHarvTime(String startTime, String endTime) {
        return productIndoRepository.findByHarvTimeBetween(startTime,endTime);
    }

    @Override
    public ProductInfo findByProductNum(String productNum) {
        return productIndoRepository.findByProNum(productNum);
    }
}
