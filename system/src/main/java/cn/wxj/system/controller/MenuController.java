package cn.wxj.system.controller;


import com.baomidou.mybatisplus.plugins.Page;
import cn.wxj.common.annotation.PathRestController;
import cn.wxj.common.controller.BaseController;
import cn.wxj.common.factory.PageFactory;
import cn.wxj.system.common.MenuNode;
import cn.wxj.system.entity.Menu;
import cn.wxj.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-02
 */
@PathRestController("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/list/page")
    public String listPage(@RequestParam(required = false) Integer menuType){
        Page<Menu> page = new PageFactory<Menu>().defaultPage();
        List<Menu> menuList = menuService.listByPage(page, menuType);
        return success(page, menuList);
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) Integer menuType){
        List<Menu> menuList = menuService.list(menuType);
        return success(menuList);
    }

    @GetMapping("/list/func")
    public String listFunc(){
        List<Menu> menuList = menuService.listHasUrl();
        return success(menuList);
    }

    @GetMapping("/children/button")
    public String childrenButton(@RequestParam String url){
        List<Menu> menuList = menuService.childrenButton(url);
        return success(menuList);
    }

    @GetMapping("/tree")
    public String tree(){
        List<MenuNode> menuList = menuService.tree(null);
        return success(menuList);
    }

    @GetMapping("/tree/menu")
    public String menuTree(){
        List<MenuNode> menuList = menuService.menuTree();
        return success(menuList);
    }

    @PostMapping("/add")
    public String add(@RequestBody Menu menu){
        Integer menuId = menuService.addMenu(menu);
        return success(menuId);
    }

    @PostMapping("/update")
    public String update(@RequestBody Menu menu){
        menuService.updateMenu(menu);
        return SUCCESS_RESULT;
    }

    @GetMapping("/delete/{menuId}")
    public String delete(@PathVariable Integer menuId){
        menuService.deleteMenuById(menuId);
        return SUCCESS_RESULT;
    }

    @GetMapping("/detail/{menuId}")
    public String detail(@PathVariable Integer menuId){
        Menu menu = menuService.getMenuById(menuId);
        return success(menu);
    }
}

