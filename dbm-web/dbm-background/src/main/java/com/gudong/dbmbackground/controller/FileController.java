package com.gudong.dbmbackground.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.gudong.dbm.pojo.MultiResultBean;
import com.gudong.dbm.pojo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author gudong
 * @description
 * @date 2021-05-28 13:54
 */
@Controller
@RequestMapping("file")
public class FileController {
    @Value("${file.upload-url}")
    private String uploadUrl;

    @Autowired
    FastFileStorageClient fastFileStorageClient;

    @PostMapping("upload")
    @ResponseBody
    public ResultBean upload(MultipartFile file) throws IOException {
        System.out.println(file);
        String name = file.getOriginalFilename();
        StorePath storePath = null;
        try {
            storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), name.substring(name.lastIndexOf(".") + 1), null);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBean().error(e.getMessage());
        }
        return new ResultBean().ok(uploadUrl + storePath.getFullPath());
    }

    @PostMapping("multiUpload")
    @ResponseBody
    public MultiResultBean multiUpload(List<MultipartFile> files) throws IOException {
        MultiResultBean resultBean = null;
        List<String> data = new ArrayList<>(files.size());
        for (MultipartFile file : files) {
            String name = file.getOriginalFilename();
            StorePath storePath = null;
            try {
                storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), name.substring(name.lastIndexOf(".") + 1), null);
                data.add(uploadUrl + storePath.getFullPath());
            } catch (IOException e) {
                e.printStackTrace();
                resultBean = new MultiResultBean(-1);
                return resultBean;
            }
        }
        resultBean  = new MultiResultBean(0,data);
        return resultBean;
    }
}

