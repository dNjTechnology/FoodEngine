/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.model.support;

import com.dnj.fooding.model.Preference;
import com.dnj.fooding.support.HibernateUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author Animesh Samanta
 */
public class CollectPreference {
    public static Map<String,String> collectPref(){
        Map<String,String> prefVal=new TreeMap<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
CriteriaQuery<Preference> query = builder.createQuery(Preference.class);
Root<Preference> root = query.from(Preference.class);
query.select(root);

List<Preference> preferences = session.createQuery(query).getResultList();
for(Preference pref:preferences){
    prefVal.put(pref.getContext(),pref.getValue());
}
session.close();
return prefVal;
    }
}
