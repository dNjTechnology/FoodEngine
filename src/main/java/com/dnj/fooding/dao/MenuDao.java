/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.dao;

import com.dnj.fooding.model.Menu;
import com.dnj.fooding.model.TablesDineIn;
import com.dnj.fooding.support.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

/**
 *
 * @author Animesh Samanta
 */
public class MenuDao {
     private static MenuDao menuDaoInstance=null;

    
    private MenuDao(){
        
    }
    public static MenuDao getInstance(){
        if(menuDaoInstance==null){
            menuDaoInstance=new MenuDao();
            return menuDaoInstance;
        }
        return menuDaoInstance;
    }
    public List<Menu> getAllMenu(){
        Session session = HibernateUtil.getSessionFactory().openSession();
          CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        List<Menu> menu=null;
        CriteriaQuery<Menu> tableQuery = criteriaBuilder.createQuery(Menu.class);
        tableQuery.from(Menu.class);
        menu = session.createQuery(tableQuery).list();
        return menu;
    }
}
