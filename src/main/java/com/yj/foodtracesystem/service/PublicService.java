package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.TempModel.OperationHisResult;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 18:59 2018/5/12
 */
@Service("publicService")
public class PublicService {

    public List<OperationHisResult> convertToType( List<Object[]> list) {
        List<OperationHisResult> operationHisResultList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            OperationHisResult operationHisResult=new OperationHisResult();
            operationHisResult.filedId=list.get(i)[0].toString();
            operationHisResult.filedName=list.get(i)[1].toString();
            operationHisResult.userNum=list.get(i)[2].toString();
            operationHisResult.userName=list.get(i)[3].toString();
            operationHisResult.operaInfo=list.get(i)[4].toString();
            operationHisResult.seedName=list.get(i)[5].toString();
            operationHisResult.operationTime=list.get(i)[6].toString().replace(".0","");
            operationHisResult.memo=list.get(i)[7].toString();
            operationHisResultList.add(operationHisResult);
        }
        return operationHisResultList;
    }
/**
 * @Author:yangjun
 * @Description:@return返回字符串格式 yyyy-MM-dd HH:mm:ss
 * @Date: Created in 15:51 2018/5/13
 */
    public  String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
