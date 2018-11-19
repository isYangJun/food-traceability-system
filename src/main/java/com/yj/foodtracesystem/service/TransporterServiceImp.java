package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.OperationHisResult;
import com.yj.foodtracesystem.repository.CarInfoRepository;
import com.yj.foodtracesystem.repository.TransStationRepository;
import com.yj.foodtracesystem.repository.TransporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Autowired
    private CoopService coopService;

    @Autowired
    private CarInfoRepository carInfoRepository;

    @PersistenceContext
    private EntityManager em;

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

    @Override
    public void saveTransStationInfo(TransStationInfo transStationInfo){transStationRepository.save(transStationInfo);
    }

    @Override
    public List<TransStationInfo> findTransStationInfoByCarNum(String carNum){
        return transStationRepository.findTransStationInfoByCarNum(carNum);
    }
    @Override
    public List<TransStationInfo> findTransStationInfoByTime(String startTime,String endTime){
        return  transStationRepository.findTransStationInfoByCarRegTimeBetween(startTime,endTime);
    }

    @Override
    public List<TransStationInfo> findAllInfo() {
        return transStationRepository.findAll();
    }
    @Override
    public List<Car>  findCarInfo() {
        return carInfoRepository.findAll();
    }

    @Override
    public int findProWeightByProNumAndDestinationNum(String destinationNum, String proNum) {
        TransportInfo transportInfo=transporterRepository.findByDestinationNumAndProNum(destinationNum,proNum);
        return transportInfo.getProWeight();
    }

    @Override
    public int findIdByComNumAndProNum(String comNum,String proNum) {
        TransportInfo transportInfo=transporterRepository.findByComNumAndProNum(comNum,proNum);
        return transportInfo.getId();
    }

    @Override
    public String findDestinationNumByComNumAndProNum(String comNum, String proNum) {
        TransportInfo transportInfo=transporterRepository.findByComNumAndProNum(comNum,proNum);
        return transportInfo.getDestinationNum();
    }

    @Override
    public boolean isNextNodeTransted(String comNum, String proNum) {
        try{
            String desNum=findDestinationNumByComNumAndProNum(comNum,proNum);
            int id =findIdByComNumAndProNum(desNum,proNum);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    @Override
    public void updateTransporterInfo(TransportInfo transportInfo,int proId) {

        TransportInfo newTransportInfo = em.find(TransportInfo.class, proId);
        newTransportInfo.setProWeight(transportInfo.getProWeight());
        newTransportInfo.setGrossLossRate(transportInfo.getGrossLossRate());
        newTransportInfo.setDestinationName(transportInfo.getDestinationName());
        newTransportInfo.setDestinationRole(transportInfo.getDestinationRole());
        newTransportInfo.setOperatorNum(transportInfo.getOperatorNum());
        newTransportInfo.setRecordedTime(transportInfo.getRecordedTime());
        newTransportInfo.setCarNum(transportInfo.getCarNum());
    }
}

