package com.example.vshopadmin.controller;

import com.example.vshopadmin.common.RespBean;
import com.example.vshopadmin.model.CaiDan;
import com.example.vshopadmin.model.JueSe;
import com.example.vshopadmin.service.CaiDanService;
import com.example.vshopadmin.service.JueSeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    @Autowired
    CaiDanService caiDanService;

    @Autowired
    JueSeService jueSeService;

    @GetMapping("/caidan")
     public RespBean getMenu(){
        List<CaiDan> data =caiDanService.getCaiDanListYuanGongId();
        return RespBean.success(data);
    }

    @GetMapping("/juese")
    public RespBean getAllJueSeList(){
        List<JueSe> allJueSeList= jueSeService.getAllJueSeList();
        return RespBean.success(allJueSeList);
    }

    @PostMapping("/juese")
    public RespBean addJueSe(@RequestBody JueSe item){
        if(item==null||item.getJueSeMingCheng()==null||item.getJueSeMingCheng().trim().length()==0)
            return RespBean.fail(-6,"角色名称不能为空");

        if(item.getXianShiMingCheng()==null||item.getXianShiMingCheng().trim().length()==0){
            item.setXianShiMingCheng(item.getJueSeMingCheng());
        }

        return jueSeService.saveJueSe(item);
    }

    @PutMapping("/juese")
    public RespBean editJueSe(@RequestBody JueSe item){
        if(item==null||item.getId()==null||item.getJueSeMingCheng()==null||item.getJueSeMingCheng().trim().length()==0){
            return RespBean.fail(-6,"角色名称不能为空");
        }

        if (item.getXianShiMingCheng()==null||item.getXianShiMingCheng().trim().length()==0){
            item.setXianShiMingCheng(item.getJueSeMingCheng());
        }
        return jueSeService.updateJueSe(item);
    }
    @DeleteMapping("/juese/{id}")
    public  RespBean deleteJueSe(@PathVariable("id") Integer id){
        if(id==null){ return RespBean.fail(-6,"缺少关键参数"); }
        return jueSeService.deleteJueSeById(id);
    }

    @DeleteMapping("/juese")
    public  RespBean deleteJueSe(@RequestBody Integer[] ids){
        if(ids==null){ return RespBean.fail(-6,"缺少关键参数"); }
        return jueSeService.deleteJueSeByIds(ids);
    }

    @GetMapping("/juese/{id}")
    public RespBean getJueSeByIs(@PathVariable("id") Integer id){
        if(id == null){ return RespBean.fail(-6,"缺少关键参数");}
        return  RespBean.success(jueSeService.getJueSeById(id));
    }

    @GetMapping("/caidanlist")
    public RespBean getCaiDanList(){
        return RespBean.success(caiDanService.getList());
    }

    @GetMapping("/caidanlist/{key}")
    public RespBean getCaiDanListByKey(@PathVariable("key") String key){
        key="%"+key+"%";
        return RespBean.success(caiDanService.getListByKey(key));
    }

    @GetMapping("/caidanlistid/{id}")
    public RespBean getId(@PathVariable("id") Integer id){
        if(id == null){
            return RespBean.fail(-6,"缺少关键参数");
        }
        return RespBean.success(caiDanService.getById(id));
    }

    @GetMapping("/yiji")
    public RespBean getYiJi(){
        return RespBean.success(caiDanService.getYiJi());
    }

    @PostMapping("/caidan")
    public RespBean addCaiDan(@RequestBody CaiDan item){
        if(item == null||item.getMingCheng()==null||item.getMingCheng().trim().length()==0){
            return RespBean.fail(-6,"缺少关键参数");
        }

        if(item.getUrl()==null||!item.getUrl().startsWith("/")||item.getPath()==null||!item.getPath().startsWith("/")){
            return RespBean.fail(-6,"缺少关键参数");
        }
        return caiDanService.addCaiDan(item);
    }

    @PutMapping("/caidan")
    public RespBean editCaiDan(@RequestBody CaiDan item){
        if(item == null||item.getMingCheng()==null||item.getMingCheng().trim().length()==0){
            return RespBean.fail(-6,"缺少关键参数");
        }

        if(item.getUrl()==null||!item.getUrl().startsWith("/")||item.getPath()==null||!item.getPath().startsWith("/")){
            return RespBean.fail(-6,"缺少关键参数");
        }
        return caiDanService.updateCaiDan(item);
    }

    @DeleteMapping("/caidan/{id}")
    public RespBean deleteById(@PathVariable("id") Integer id){
        if(id == null){
            return RespBean.fail(-6,"缺少关键参数");
        }
        return caiDanService.deleteById(id);
    }

    @DeleteMapping("/caidan")
    public RespBean deleteByIds(@RequestBody  Integer[] ids){
        if(ids == null){
            return RespBean.fail(-6,"缺少关键参数");
        }
        return caiDanService.deleteByIds(ids);
    }

    @GetMapping("/caidantree")
    public RespBean getAllCaiDanAndZiCaiDan(){
        return RespBean.success(caiDanService.getAllCaiDanAndZiCaiDan());
    }

    @GetMapping("/juesecaidan/{jueSeId}")
    public RespBean getJueSeCaiDanByJueSeId(@PathVariable("jueSeId") Integer jueSeId){
        if(jueSeId==null||jueSeId==0){
            return RespBean.fail(-6,"缺少关键参数");
        }
        return RespBean.success(caiDanService.getCaiDanByJueSeId(jueSeId));
    }

    @PostMapping("/juesecaidan")
    public RespBean updateJueSeCaiDan(@RequestParam("jueSeId") Integer jueSeId, @RequestParam("caiDanIds") Integer[] caiDanIds){
        if(jueSeId==null||jueSeId==0){
            return  RespBean.fail(-6,"缺少关键参数");
        }
        return jueSeService.updateJueSeCaiDan(jueSeId,caiDanIds);
    }
}
