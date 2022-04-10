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
        List<Menu> data = menuService.getMenuListByUserId();
        return RespBean.success(data);
    }

    @GetMapping("/character")
    public RespBean getAllJueSeList(){
        List<Character> allCharacterList= characterService.getAllCharacterList();
        return RespBean.success(allCharacterList);
    }
//————————————————character
//    @PostMapping("/juese")
//    public RespBean addJueSe(@RequestBody Character item){
//        if(item==null||item.getJueSeMingCheng()==null||item.getJueSeMingCheng().trim().length()==0)
//            return RespBean.fail(-6,"角色名称不能为空");
//
//        if(item.getXianShiMingCheng()==null||item.getXianShiMingCheng().trim().length()==0){
//            item.setXianShiMingCheng(item.getJueSeMingCheng());
//        }
//
//        return jueSeService.saveJueSe(item);
//    }
//
//    @PutMapping("/juese")
//    public RespBean editJueSe(@RequestBody Character item){
//        if(item==null||item.getId()==null||item.getJueSeMingCheng()==null||item.getJueSeMingCheng().trim().length()==0){
//            return RespBean.fail(-6,"角色名称不能为空");
//        }
//
//        if (item.getXianShiMingCheng()==null||item.getXianShiMingCheng().trim().length()==0){
//            item.setXianShiMingCheng(item.getJueSeMingCheng());
//        }
//        return jueSeService.updateJueSe(item);
//    }

//    @DeleteMapping("/juese/{id}")
//    public  RespBean deleteJueSe(@PathVariable("id") Integer id){
//        if(id==null){ return RespBean.fail(-6,"缺少关键参数"); }
//        return jueSeService.deleteJueSeById(id);
//    }
//
//    @DeleteMapping("/juese")
//    public  RespBean deleteJueSe(@RequestBody Integer[] ids){
//        if(ids==null){ return RespBean.fail(-6,"缺少关键参数"); }
//        return jueSeService.deleteJueSeByIds(ids);
//    }
//
//    @GetMapping("/juese/{id}")
//    public RespBean getJueSeByIs(@PathVariable("id") Integer id){
//        if(id == null){ return RespBean.fail(-6,"缺少关键参数");}
//        return  RespBean.success(jueSeService.getJueSeById(id));
//    }
//_____________menu
//    @GetMapping("/caidanlist")
//    public RespBean getCaiDanList(){
//        return RespBean.success(menuService.getList());
//    }
//
//    @GetMapping("/caidanlist/{key}")
//    public RespBean getCaiDanListByKey(@PathVariable("key") String key){
//        key="%"+key+"%";
//        return RespBean.success(menuService.getListByKey(key));
//    }
//
//    @GetMapping("/caidanlistid/{id}")
//    public RespBean getId(@PathVariable("id") Integer id){
//        if(id == null){
//            return RespBean.fail(-6,"缺少关键参数");
//        }
//        return RespBean.success(menuService.getById(id));
//    }
//
    @GetMapping("/firstmenu")
    public RespBean getFirstMenu(){
        return RespBean.success(menuService.getFirstMenu());
    }

//    @GetMapping("/caidantree")
//    public RespBean getAllCaiDanAndZiCaiDan(){
//        return RespBean.success(menuService.getAllCaiDanAndZiCaiDan());
//    }

    @GetMapping("/charactermenu/{characterId}")
    public RespBean getMenuListCharacterId(@PathVariable("characterId") Integer characterId){
        if(characterId==null||characterId==0){
            return RespBean.fail(-6,"缺少关键参数");
        }
        return RespBean.success(menuService.getMenuListCharacterId(characterId));
    }
//
//    @PostMapping("/charactermenu")
//    public RespBean updateCharacterMenu(@RequestParam("characterId") Integer characterId, @RequestParam("menuIds") Integer[] menuIds){
//        if(characterId==characterId||characterId==0){
//            return  RespBean.fail(-6,"缺少关键参数");
//        }
//        return jueSeService.updateJueSeCaiDan(jueSeId,caiDanIds);
//    }
}
