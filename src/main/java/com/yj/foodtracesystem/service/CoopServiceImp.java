package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    private SeedInfoRepository seedInfoRepository;

    @Autowired
    private ProductInfoRepository productIndoRepository;

    @Autowired
    private ProductBatchInfoRepository productBatchInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationOrderInfoRepository operationOrderInfoRepository;

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
    public List<ProductInfo> findByFiledNumOrSeedName(int id, String seedName) {
        List<ProductInfo> productInfoList = productIndoRepository.findBySeedNameInAndFiledNumIn(seedName, id);
        return productInfoList;
    }

    @Override
    public List<ProductInfo> findByHarvTime(String startTime, String endTime) {
        return productIndoRepository.findByHarvTimeBetween(startTime, endTime);
    }

    @Override
    public ProductInfo findByProductNum(String productNum) {
        return productIndoRepository.findByProNum(productNum);
    }

    @Override
    public List<ProductInfo> findByProductNumIn(String productNum) {
        return productIndoRepository.findByProNumIn(productNum);
    }

    @Override
    public int findSeedIdByProductNum(String productNum) {
        ProductInfo productInfo = productIndoRepository.findByProNum(productNum);
        return productInfo.getSeedNum();
    }

    @Override
    public String findSeedNameBySeedNum(int seedId) {
        SeedInfo seedInfo = seedInfoRepository.findById(seedId);
        return seedInfo.getSeedName();
    }

    @Override
    public void saveproBachInfo(ProductBatchInfo productBatchInfo) {
        productBatchInfoRepository.save(productBatchInfo);
    }

    @Override
    public List<ProductBatchInfo> findProBachInfoByBachNum(String proBachNum) {
        return productBatchInfoRepository.findByProBatchNum(proBachNum);
    }

    @Override
    public List<ProductBatchInfo> findBySeedNum(int seedId) {
        return productBatchInfoRepository.findBySeedNum(seedId);
    }

    @Override
    public List<ProductBatchInfo> findProBatchInfoByTime(String startTime, String endTime) {
        return productBatchInfoRepository.findByHarvTimeBetween(startTime, endTime);
    }

    @Override
    public List<ProductBatchInfo> findAllProBatchInfo() {
        return productBatchInfoRepository.findAll();
    }

    @Override
    public String findProBatchNumByProNum(String proNum) {
        ProductInfo productInfo = productIndoRepository.findByProNum(proNum);
        String proBatchNum = productInfo.getProBatchNum();
        return proBatchNum;
    }

    @Override
    public int findProBatchWeightByBatchNum(String batchNum) {
        List<ProductBatchInfo> productBatchInfoList =productBatchInfoRepository.findByProBatchNum(batchNum);
        ProductBatchInfo productBatchInfo=productBatchInfoList.get(0);
        return productBatchInfo.getProWeight();
    }

    @Override
    public int findCompanyRoleByComNum(String comNum) {
        List<ComInfo> comInfoList=findComInfoByComNum(comNum);
        int comRole=comInfoList.get(0).getComRole();
        return comRole;
    }

    @Override
    public int findProWeightByProNum(String proNum) {
        ProductInfo productInfo=productIndoRepository.findByProNum(proNum);
        return productInfo.getProWeight();
    }

    @Override
    public List<User> findByUserCompAndRole(String userComp, int userRole) {
        return userRepository.findByUserCompAndUserRole(userComp,userRole);
    }

    @Override
    public void saveOperationOrderInfo(OperationOrderInfo operationOrderInfo) {
        operationOrderInfoRepository.save(operationOrderInfo);

    }

    @Override
    public List<OperationOrderInfo> findAllOperationOrderInfo() {
        return operationOrderInfoRepository.findAll();
    }

    @Override
    public List<OperationOrderInfo> findByUserIdAndIsDone(String UserId, int isDone) {
        return operationOrderInfoRepository.findByUserIdAndIsDone(UserId,isDone);
    }
}
