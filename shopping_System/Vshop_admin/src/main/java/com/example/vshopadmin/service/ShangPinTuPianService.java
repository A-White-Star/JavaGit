package com.example.vshopadmin.service;

import com.example.vshopadmin.common.Base64Util;
import com.example.vshopadmin.common.RespBean;
import com.example.vshopadmin.dao.ShangPinDao;
import com.example.vshopadmin.dao.ShangPinTuPianDao;
import com.example.vshopadmin.model.ShangPin;
import com.example.vshopadmin.model.ShangPinTuPian;
import org.apache.tomcat.jni.Buffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShangPinTuPianService {
    @Autowired
    ShangPinTuPianDao shangPinTuPianDao;

    public RespBean add(ShangPinTuPian item){
        return shangPinTuPianDao.add(item)>0?RespBean.success(item):RespBean.fail();
    }

    public RespBean deleteByShangPinId(Long shangPinId){
        return shangPinTuPianDao.deleteByShangPinId(shangPinId)>0?RespBean.success():RespBean.fail();
    }

    public RespBean deleteById(Long id){
        return shangPinTuPianDao.deleteById(id)>0?RespBean.success():RespBean.fail();
    }

    public List<String> getShangPinTuPianListByShangPinId(Long shangPinId) {
        List<String> tpList=new ArrayList<>();
        List<ShangPinTuPian> list = shangPinTuPianDao.getTuPianListByShangPinId(shangPinId);

        for(ShangPinTuPian item:list){
           String base64String = Base64Util.imgToBase64String(item.getImgUrl());
           if(base64String !=null)
               tpList.add(base64String);
        }
        return tpList;
    }
}
