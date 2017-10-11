package com.test.web.service;

import com.test.web.Util.DateUtil;
import com.test.web.Util.HttpClientUtil;
import com.test.web.comm.ReturnValue;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * Created by zhaochuncheng on 2017/10/11.
 */
@Service
public class SampleService {

    public ReturnValue fileupload(MultipartFile file){
        ReturnValue returnValue=new ReturnValue();

        // 文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        //文件名称
        String realFileName = file.getOriginalFilename();
        //年月
        String serialNo = DateUtil.getNowDateString(DateUtil.FORMAT_STYLE_4);

        //新的名字
        String fileNewName = serialNo + UUID.randomUUID().toString().replaceAll("-", "")+"."+suffix;

        //保存路径
        String path = "D://";//CommonConstant.imgdir+serialNo;
        // 没有文件夹先创建
        File existfile = new File(path);
        if (!existfile.exists()) {// 判断文件是否存在
            existfile.mkdirs(); // 创建文件夹
        }
        String srcImgPath =  path+File.separator+fileNewName;
        File record = new File(srcImgPath);
        try {
            if(!record.exists()){
                record.createNewFile();
            }
            boolean flag =  HttpClientUtil.doUploadClientforfile(file.getBytes(), record);
            if(!flag){
                returnValue.setErrMsg("上传失败");
                returnValue.setStatus(101);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return  returnValue;
    }
}
