package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.ComInfo;
import com.yj.foodtracesystem.model.ProductInfo;
import com.yj.foodtracesystem.model.TempModel.OperationHisResult;
import com.yj.foodtracesystem.model.TransStationInfo;
import com.yj.foodtracesystem.model.TransportInfo;
import com.yj.foodtracesystem.repository.TransStationRepository;
import com.yj.foodtracesystem.repository.TransporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 7:39 2018/5/27
 */
@Service("transporterService")
public class TransporterServiceImp implements TransporterService {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PublicService publicService;

    @Autowired
    private TransStationRepository transStationRepository;

    @Autowired
    private TransporterRepository transporterRepository;

    @Override
    public List<ProductInfo> findTranstedProductInfo() {
        String sql = "SELECT pi_product_num,pi_product_name FROM tbl_product_info WHERE pi_is_transed=1";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object[]> list = nativeQuery.getResultList();
        List<ProductInfo> proNumNameList = publicService.convertToType(list,ProductInfo.class.getName());
        return proNumNameList;
    }

    @Override
    public List<TransStationInfo> findTransStationInfo() {
        return transStationRepository.findAll();
    }

    @Override
    public List<ComInfo> findTransOrReposInfo() {
        String sql = "SELECT CI_COMPANY_NUM,CI_COMPANY_NAME from tbl_company_info WHERE ci_company_role in(2,3,4) ";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object[]> list = nativeQuery.getResultList();
        List<ComInfo> comInfoList = publicService.convertToType(list,ComInfo.class.getName());
        return comInfoList;
    }

    @Override
    public List<TransportInfo> findTransInfoByProNum(String proNum) {
        return transporterRepository.findByProNum(proNum);
    }

    @Override
    public List<TransportInfo> findTransInfoByTime(String startTime, String endTime) {
        return transporterRepository.findByRecordedTimeBetween(startTime,endTime);
    }

    @Override
    public void saveTransInfo(TransportInfo transportInfo) {
        transporterRepository.save(transportInfo);
    }
}
