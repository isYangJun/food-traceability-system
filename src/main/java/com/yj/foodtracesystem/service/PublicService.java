package com.yj.foodtracesystem.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yj.foodtracesystem.model.ComInfo;
import com.yj.foodtracesystem.model.ProductInfo;
import com.yj.foodtracesystem.model.TempModel.OperationHisResult;
import com.yj.foodtracesystem.model.TempModel.ProductPara;
import com.yj.foodtracesystem.model.TransportInfo;
import com.yj.foodtracesystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 18:59 2018/5/12
 */
@Service("publicService")
public class PublicService {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PublicService publicService;

    public List<ProductPara> findSaleProFromTransAndRepos(String comNum) {
        String sql = "SELECT ti_product_num,ti_product_name FROM tbl_transport_info WHERE ti_destination_num='" + comNum + "'";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object[]> list = nativeQuery.getResultList();
        List<ProductPara> productParaList = publicService.convertToType(list, ProductPara.class.getName());
        return productParaList;
    }

    /**
     * @Author:yangjun
     * @Description:将查询的object值返回为对应类型
     * @Date: Created in 8:24 2018/5/17
     */
    public <T> T convertToType(List<Object[]> list, String typeName) {
        if (typeName.contains("OperationHisResult")) {
            List<OperationHisResult> operationHisResultList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                OperationHisResult operationHisResult = new OperationHisResult();
                operationHisResult.filedId = list.get(i)[0].toString();
                operationHisResult.filedName = list.get(i)[1].toString();
                operationHisResult.userNum = list.get(i)[2].toString();
                operationHisResult.userName = list.get(i)[3].toString();
                operationHisResult.operaInfo = list.get(i)[4].toString();
                operationHisResult.seedName = list.get(i)[5].toString();
                operationHisResult.operationTime = list.get(i)[6].toString().replace(".0", "");
                operationHisResult.memo = list.get(i)[7].toString();
                operationHisResultList.add(operationHisResult);
            }
            return (T) operationHisResultList;
        }
        if (typeName.contains("User")) {
            List<User> userList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                User userREs = new User();
                userREs.setName(list.get(i)[0].toString());
                userREs.setUserNum(list.get(i)[1].toString());
                userREs.setIdNum(list.get(i)[2].toString());
                userREs.setSex(list.get(i)[3].toString());
                userREs.setRegTime(list.get(i)[4].toString().replace(".0", ""));
                userREs.setActive(Integer.parseInt(list.get(i)[5].toString()));
                userList.add(userREs);
            }
            return (T) userList;
        }
        if (typeName.contains("ComInfo")) {
            List<ComInfo> comInfoList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                ComInfo comInfo = new ComInfo();
                comInfo.setComNum(list.get(i)[0].toString());
                comInfo.setComName(list.get(i)[1].toString());
                comInfoList.add(comInfo);
            }
            return (T) comInfoList;
        }
        if (typeName.contains("ProductInfo")) {
            List<ProductInfo> productInfoList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setProNum(list.get(i)[0].toString());
                productInfo.setProName(list.get(i)[1].toString());
                productInfoList.add(productInfo);
            }
            return (T) productInfoList;
        }
        if (typeName.contains("TransportInfo")) {
            List<TransportInfo> transportInfoList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                TransportInfo transportInfo = new TransportInfo();
                transportInfo.setProNum(list.get(i)[0].toString());
                transportInfo.setProName(list.get(i)[1].toString());
                transportInfoList.add(transportInfo);
            }
            return (T) transportInfoList;
        }
        if (typeName.contains("ProductPara")) {
            List<ProductPara> productParaList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                ProductPara productPara = new ProductPara();
                productPara.proNum = list.get(i)[0].toString();
                productPara.proName = list.get(i)[1].toString();
                productParaList.add(productPara);
            }
            return (T) productParaList;
        } else {
            return null;
        }
    }

    /*public List<OperationHisResult> convertToType(List<Object[]> list) {
        List<OperationHisResult> operationHisResultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            OperationHisResult operationHisResult = new OperationHisResult();
            operationHisResult.filedId = list.get(i)[0].toString();
            operationHisResult.filedName = list.get(i)[1].toString();
            operationHisResult.userNum = list.get(i)[2].toString();
            operationHisResult.userName = list.get(i)[3].toString();
            operationHisResult.operaInfo = list.get(i)[4].toString();
            operationHisResult.seedName = list.get(i)[5].toString();
            operationHisResult.operationTime = list.get(i)[6].toString().replace(".0", "");
            operationHisResult.memo = list.get(i)[7].toString();
            operationHisResultList.add(operationHisResult);
        }
        return operationHisResultList;
    }*/

    /**
     * @Author:yangjun
     * @Description:@return返回字符串格式 yyyy-MM-dd HH:mm:ss
     * @Date: Created in 15:51 2018/5/13
     */
    public String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public String formatTime(String initialTime) {
        return initialTime.replaceAll("T", " ");
    }

    public String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }
}
