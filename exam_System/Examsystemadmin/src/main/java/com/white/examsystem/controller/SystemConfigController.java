package com.white.examsystem.controller;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.model.Menu;
import com.white.examsystem.model.Character;
import com.white.examsystem.service.MenuService;
import com.white.examsystem.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    @Autowired
    MenuService menuService;

    @Autowired
    CharacterService characterService;

    @GetMapping("/menu")
     public RespBean getMenu(){
        List<Menu> data =menuService.getMenuListUserId();
        return RespBean.success(data);
    }

    @GetMapping("/character")
    public RespBean getAllCharacterList(){
        List<Character> allCharacterList = characterService.getAllJueSeList();
        return RespBean.success(allCharacterList);
    }

    @PostMapping("/juese")
//    public RespBean addJueSe(@RequestBody Character item){
//        if(item==null||item.getCharacterNameCN()==null||item.getCharacterNameEN().trim().length()==0)
//            return RespBean.fail(-6,"角色名称不能为空");
//
//        if(item.getCharacterNameCN()==null||item.getCharacterNameCN().trim().length()==0){
//            item.setXianShiMingCheng(item.getJueSeMingCheng());
//        }
//
//        return characterService.saveJueSe(item);
//    }

    @PutMapping("/juese")
//    public RespBean editJueSe(@RequestBody Character item){
//        if(item==null||item.getId()==null||item.getJueSeMingCheng()==null||item.getJueSeMingCheng().trim().length()==0){
//            return RespBean.fail(-6,"角色名称不能为空");
//        }
//
//        if (item.getXianShiMingCheng()==null||item.getXianShiMingCheng().trim().length()==0){
//            item.setXianShiMingCheng(item.getJueSeMingCheng());
//        }
//        return characterService.updateJueSe(item);
//    }
//    @DeleteMapping("/juese/{id}")
//    public  RespBean deleteJueSe(@PathVariable("id") Integer id){
//        if(id==null){ return RespBean.fail(-6,"缺少关键参数"); }
//        return characterService.deleteJueSeById(id);
//    }
//
//    @DeleteMapping("/juese")
//    public  RespBean deleteJueSe(@RequestBody Integer[] ids){
//        if(ids==null){ return RespBean.fail(-6,"缺少关键参数"); }
//        return characterService.deleteJueSeByIds(ids);
//    }
//
//    @GetMapping("/juese/{id}")
//    public RespBean getJueSeByIs(@PathVariable("id") Integer id){
//        if(id == null){ return RespBean.fail(-6,"缺少关键参数");}
//        return  RespBean.success(characterService.getJueSeById(id));
//    }

    @GetMapping("/menulist")
    public RespBean getCaiDanList(){
        return RespBean.success(menuService.getList());
    }

//    @GetMapping("/menulist/{key}")
//    public RespBean getMenuListByKey(@PathVariable("key") String key){
//        key="%"+key+"%";
//        return RespBean.success(menuService.getListByKey(key));
//    }

//    @GetMapping("/caidanlistid/{id}")
//    public RespBean getId(@PathVariable("id") Integer id){
//        if(id == null){
//            return RespBean.fail(-6,"缺少关键参数");
//        }
//        return RespBean.success(caiDanService.getById(id));
//    }

    @GetMapping("/sub")
    public RespBean getSub(){
        return RespBean.success(menuService.getSub());
    }

//    @PostMapping("/caidan")
//    public RespBean addCaiDan(@RequestBody Menu item){
//        if(item == null||item.getMingCheng()==null||item.getMingCheng().trim().length()==0){
//            return RespBean.fail(-6,"缺少关键参数");
//        }
//
//        if(item.getUrl()==null||!item.getUrl().startsWith("/")||item.getPath()==null||!item.getPath().startsWith("/")){
//            return RespBean.fail(-6,"缺少关键参数");
//        }
//        return caiDanService.addCaiDan(item);
//    }

//    @PutMapping("/caidan")
//    public RespBean editCaiDan(@RequestBody Menu item){
//        if(item == null||item.getMingCheng()==null||item.getMingCheng().trim().length()==0){
//            return RespBean.fail(-6,"缺少关键参数");
//        }
//
//        if(item.getUrl()==null||!item.getUrl().startsWith("/")||item.getPath()==null||!item.getPath().startsWith("/")){
//            return RespBean.fail(-6,"缺少关键参数");
//        }
//        return caiDanService.updateCaiDan(item);
//    }
//
//    @DeleteMapping("/caidan/{id}")
//    public RespBean deleteById(@PathVariable("id") Integer id){
//        if(id == null){
//            return RespBean.fail(-6,"缺少关键参数");
//        }
//        return caiDanService.deleteById(id);
//    }
//
//    @DeleteMapping("/caidan")
//    public RespBean deleteByIds(@RequestBody  Integer[] ids){
//        if(ids == null){
//            return RespBean.fail(-6,"缺少关键参数");
//        }
//        return caiDanService.deleteByIds(ids);
//    }
//
//    @GetMapping("/caidantree")
//    public RespBean getAllCaiDanAndZiCaiDan(){
//        return RespBean.success(caiDanService.getAllCaiDanAndZiCaiDan());
//    }

    @GetMapping("/juesecaidan/{jueSeId}")
    public RespBean getJueSeCaiDanByJueSeId(@PathVariable("jueSeId") Integer jueSeId){
        if(jueSeId==null||jueSeId==0){
            return RespBean.fail(-6,"缺少关键参数");
        }
        return RespBean.success(menuService.getMenuByCharacterId(jueSeId));
    }

//    @PostMapping("/juesecaidan")
//    public RespBean updateJueSeCaiDan(@RequestParam("jueSeId") Integer jueSeId, @RequestParam("caiDanIds") Integer[] caiDanIds){
//        if(jueSeId==null||jueSeId==0){
//            return  RespBean.fail(-6,"缺少关键参数");
//        }
//        return characterService.updateJueSeCaiDan(jueSeId,caiDanIds);
//    }
}
