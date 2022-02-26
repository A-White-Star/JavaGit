package com.example.vshopadmin.service;

import com.example.vshopadmin.common.Base64Util;
import com.example.vshopadmin.common.RespBean;
import com.example.vshopadmin.dao.ShangPinDao;
import com.example.vshopadmin.dao.ShangPinTuPianDao;
import com.example.vshopadmin.model.ShangPin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
import java.util.List;

@Service
public class ShangPinService {
    @Autowired
    ShangPinDao shangPinDao;
    @Autowired
    ShangPinTuPianDao tpDao;

    public RespBean add(ShangPin item) {
        int res = shangPinDao.add(item);
        return res > 0 ? RespBean.success(item) : RespBean.fail();
    }

    public RespBean update(ShangPin item) {
        return shangPinDao.update(item) > 0 ? RespBean.success() : RespBean.fail();
    }

    public RespBean deleteById(Long id) {
        tpDao.deleteByShangPinId(id);
        return shangPinDao.deleteById(id) > 0 ? RespBean.success() : RespBean.fail();
    }

    public RespBean getAll() {
        return RespBean.success(shangPinDao.getAll());
    }

    public RespBean getListByKey(String key) {
        return RespBean.success(shangPinDao.getListByKey(key));
    }

    public RespBean getById(Long id) {
        return RespBean.success(shangPinDao.getById(id));
    }

    public List<ShangPin> getShowList() {
        List<ShangPin> showList = shangPinDao.getShowList();
        for (ShangPin item : showList) {
            if (item.getImgList() != null  && item.getImgList().size() > 0  ) {
                String base64String = Base64Util.imgToBase64String(item.getImgList().get(0).getImgUrl());
                if (base64String != null)
                    item.setZhanShiTuPian(base64String);
                else //加载失败
                    item.setZhanShiTuPian(Base64Util.imgToBase64String("D:\\shixun\\uploading\\fail.jpg"));
            } else {//没有图片
                item.setZhanShiTuPian(Base64Util.imgToBase64String("D:\\shixun\\uploading\\fail.jpg"));
            }
//            item.setZhanShiTuPian(Base64Util.imgToBase64StringSmall("D:\\shixun\\uploading\\fail.jpg"));
        }
        return showList;
    }

}