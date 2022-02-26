package com.example.vshopadmin.controller;

import com.example.vshopadmin.common.RespBean;
import com.example.vshopadmin.dao.ShangPinTuPianDao;
import com.example.vshopadmin.model.ShangPin;
import com.example.vshopadmin.model.ShangPinTuPian;
import com.example.vshopadmin.service.ShangPinService;
import com.example.vshopadmin.service.ShangPinTuPianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.beans.SimpleBeanInfo;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/houtai")
public class ShangPinController {
    @Autowired
    ShangPinService shangPinService;
    @Autowired
    ShangPinTuPianService tpService;

    @PostMapping("shangpin")
    public RespBean add(@RequestBody ShangPin item){ return shangPinService.add(item);}

    @PutMapping("shangpin")
    public RespBean update(@RequestBody ShangPin item){
        return shangPinService.update(item);
    }

    @DeleteMapping("shangpin/{id}")
    public RespBean deleteById(@PathVariable("id") Long id){
        return shangPinService.deleteById(id);
    }
    @PostMapping("shangpintupian/{shangPinid}")
    public RespBean uploading(@PathVariable("shangPinid") Long shangPinId, MultipartFile file) throws IOException {
        String path="D:\\shixun\\uploading";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String dirName = sdf.format(new Date());
        File saveDir =new File(path,dirName);
        if(!saveDir.exists()) saveDir.mkdirs();

        String extName =file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString()+extName;
        File imgFile= new File(saveDir,fileName);
        file.transferTo(imgFile);

        return tpService.add(new ShangPinTuPian(null,shangPinId,imgFile.getPath(),false));
    }

    @DeleteMapping("shangpintupian/{id}")
    public RespBean deleteTuPianById(@PathVariable("id") Long id){
        return tpService.deleteById(id);
    }
    @GetMapping("shangpin/{key}")
    public RespBean getListByKey(@PathVariable("key") String key){
        return shangPinService.getListByKey(key);
    }
    @GetMapping("shangpinbyid/{id}")
    public RespBean getById(@PathVariable("id") Long id){
        return shangPinService.getById(id);
    }
    @GetMapping("shangpin")
    public RespBean getAll(){
        return shangPinService.getAll();
    }
    @GetMapping("shangpintupian/{shangPinId}")
    public RespBean getTuPianListByShangPinId(@PathVariable("shangPinId") Long id){
        return RespBean.success(tpService.getShangPinTuPianListByShangPinId(id));
    }
}
