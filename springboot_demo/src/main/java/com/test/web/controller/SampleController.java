package com.test.web.controller;

import com.test.web.comm.ReturnValue;
import com.test.web.service.SampleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhaochuncheng on 2017/10/11.
 */
@RestController
public class SampleController {

    @Autowired
    SampleService sampleService;

    @RequestMapping("/upload")
    @ApiOperation(value = "上传文件",httpMethod = "POST",notes = "上传文件测试")
    public ReturnValue fileupload(@RequestParam(value="file",required=false) MultipartFile file){
        ReturnValue returnValue = sampleService.fileupload(file);
        return returnValue;
    }
}
