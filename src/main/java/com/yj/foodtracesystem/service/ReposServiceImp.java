package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.ProductInfo;
import com.yj.foodtracesystem.model.RepositoryInfo;
import com.yj.foodtracesystem.model.RepositoryStationInfo;
import com.yj.foodtracesystem.model.TransportInfo;
import com.yj.foodtracesystem.repository.ProductInfoRepository;
import com.yj.foodtracesystem.repository.ReposInfoRepository;
import com.yj.foodtracesystem.repository.ReposStationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:25 2018/5/30
 */
@Service("reposService")
public class ReposServiceImp implements ReposService {
    @Autowired
    private ReposInfoRepository reposInfoRepository;
    @Autowired
    private ReposStationInfoRepository reposStationInfoRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PublicService publicService;

    @Override
    public List<RepositoryInfo> findRepositoryInfoByProNum(String proNum) {
        return reposInfoRepository.findByProNum(proNum);
    }

    @Override
    public List<RepositoryInfo> findRepositoryInfoByTime(String startTime, String endTime) {
        return reposInfoRepository.findByRecordedTimeBetween(startTime, endTime);
    }

    @Override
    public List<TransportInfo> findOwnedTransportInfo(String comNum) {
        String sql = "SELECT ti_product_num,ti_product_name FROM tbl_transport_info WHERE ti_destination_num='" + comNum + "'";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object[]> list = nativeQuery.getResultList();
        List<TransportInfo> transportInfoList = publicService.convertToType(list, TransportInfo.class.getName());
        return transportInfoList;
    }

    @Override
    public void saveRepositoryInfo(RepositoryInfo repositoryInfo) {
        reposInfoRepository.save(repositoryInfo);
    }

    @Override
    public List<RepositoryStationInfo> findAllRepositoryStationInfo() {
        return reposStationInfoRepository.findAll();
    }

    @Override
    public String findWareNameByWarehouseNum(String warehouseNum) {
        RepositoryStationInfo repositoryStationInfo=reposStationInfoRepository.findByWarehouseNum(warehouseNum);
        return repositoryStationInfo.getRepositoryName();
    }

    @Override
    public String findByProNum(String proNum) {
        ProductInfo productInfo= productInfoRepository.findByProNum(proNum);
        return productInfo.getProName();
    }

    @Override
    public String findSaveTempByWarehouseNum(String warehouseNum) {
        RepositoryStationInfo repositoryStationInfo=reposStationInfoRepository.findByWarehouseNum(warehouseNum);
        return repositoryStationInfo.getSaveTemp();
    }

    @Override
    public List<RepositoryStationInfo> findReposStationInfoByReposNum(String comNum) {
        return reposStationInfoRepository.findByRepositoryNumIn(comNum);
    }
}
