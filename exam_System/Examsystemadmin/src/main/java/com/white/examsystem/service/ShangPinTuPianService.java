package com.white.examsystem.service;

import com.white.examsystem.common.Base64Util;
import com.white.examsystem.common.RespBean;
import com.white.examsystem.dao.ShangPinTuPianDao;
import com.white.examsystem.model.ShangPinTuPian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
