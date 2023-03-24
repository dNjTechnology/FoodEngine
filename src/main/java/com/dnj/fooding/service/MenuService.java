/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.service;

import com.dnj.fooding.dao.MenuDao;
import com.dnj.fooding.model.Menu;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Animesh Samanta
 */
public class MenuService {
    private static MenuService instance;
     private void MenuService(){
        
    }
    public static MenuService getInstance(){
        if(instance==null){
            instance=new MenuService();
            return instance;
        }
        return instance;
    }
    public List<Menu> getMenu(){
       List<Menu> menuList = MenuDao.getInstance().getAllMenu();
       return menuList;
    }
    public List<Menu> filterByCategory(String category){
        List<Menu> menuList = MenuDao.getInstance().getAllMenu();
        if(category.equalsIgnoreCase("All")){
            return menuList;
        }
        List<Menu> menuListFiltered = new ArrayList<>();
        for(Menu menu:menuList){
            if(menu.getCategory().equalsIgnoreCase(category)){
                menuListFiltered.add(menu);
            }
        }
        return menuListFiltered;
    }
    public List<String> getAllCategory(){
        List<Menu> menuList = MenuDao.getInstance().getAllMenu();
        Set<String> category=new HashSet<>();
        for(Menu menu:menuList){
            category.add(menu.getCategory());
        }
        return new ArrayList<String>(category);
    }

    public List<Menu> filterByCategory(String category, String item) {
        List<Menu> menuList = MenuDao.getInstance().getAllMenu();
        if(category!=null&&!category.isEmpty()&&item!=null&&!item.isEmpty()){
            List<Menu> menuReturn=new ArrayList<>();
            for(Menu menu:menuList){
                if(menu.getCategory().equalsIgnoreCase(category)&&menu.getItemName().equalsIgnoreCase(item)){
                    menuReturn.add(menu);
                }
                else if(menu.getCategory().equalsIgnoreCase(category)&&item.equalsIgnoreCase("All"))
                {
                    menuReturn.add(menu);
                }
                else if(category.equalsIgnoreCase("All")&&item.equalsIgnoreCase("All")){
                   menuReturn.add(menu); 
                }
                else if(category.equalsIgnoreCase("All")&&item.equalsIgnoreCase(menu.getItemName())){
                   menuReturn.add(menu); 
                   return menuReturn;
                }
                
            }
            return menuReturn;
        }
        else if(category!=null&&!category.isEmpty()){
           List<Menu> menuReturn=new ArrayList<>();
           if(category.equalsIgnoreCase("All")){
                   return menuList; 
                }
            for(Menu menu:menuList){
                if(menu.getCategory().equalsIgnoreCase(category)){
                    menuReturn.add(menu);
                }
                
            }
            return menuReturn;
        }
        else if(item!=null&&!item.isEmpty()){
            List<Menu> menuReturn=new ArrayList<>();
            for(Menu menu:menuList){
                if(menu.getItemName().equalsIgnoreCase(item)){
                    menuReturn.add(menu);
                }
            }
            return menuReturn;
        }
        else{
            return menuList;
        }
         }
}
