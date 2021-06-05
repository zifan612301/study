package com.hq.secondhand_book.controller;

import com.hq.secondhand_book.service.FileService;
import com.hq.secondhand_book.service.UserService;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileController {

    @Autowired
    UserService userService;
    @Autowired
    FileService fileService;

    @PostMapping(value = "/singlefile")
    public ResultResp headImg(@RequestParam(value="file",required=false) MultipartFile file, String userName) throws Exception {
        fileService.singleFile(file,userName);
        return Response.ok();
    }

    //上传图书
    @PostMapping("/morefile")
    public ResultResp moreFile(@RequestParam(value = "file", required = false) MultipartFile[] files,String userName,String bookName,
        String bookSynopsis, Double bookPrice,int bookCategoryId){
        if(files.length == 0){
            return Response.dataErr("未上传图片！");
        }else{
            fileService.moreFile(files, userName,bookName,bookSynopsis,bookPrice,bookCategoryId);
            return Response.ok();
        }
    }
}