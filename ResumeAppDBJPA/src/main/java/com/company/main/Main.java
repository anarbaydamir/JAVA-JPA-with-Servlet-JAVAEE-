package com.company.main;

import com.company.dao.inter.*;
import com.company.entity.Country;
import com.company.entity.EmploymentHistory;
import com.company.entity.Skill;
import com.company.entity.User;

import java.sql.Connection;
import java.util.List;

//DAO-data access object
//JDBC--JAVA DATABASE CONNECTIVITY

public class Main {
    public static void main (String[] args) throws Exception {
      //  UserDaoInter userDao = Context.instanceUserDao(); // loosely coupling --- yeni men bashqa class yaratdim o userDaoImpl-i return edir. Biz onu gormuruk burda. Arxada nese deyishsek buna tesir etmeyecek
        //tightly coupling -- bu ise eger userDaoImpl-i birbasha istifade etseydik olardi.

        UserDaoInter userDaoInter = Context.instanceUserDao();

//        System.out.println(userDaoInter.getAll("Rana",null,null));
//        Country country = new Country(1,null,null);
//
//        User u = new User(0,"test","test","+9945555555","test@mail.com",null,null,null,country,country);
//        u.setPassword("12345");
//
//        userDaoInter.addUser(u);
//
//        User u = userDaoInter.getById(5);
//        
//        System.out.println(u.getEmail());

//          CountryDaoInter countryDaoInter = Context.instanceCountryDao();
//          
//          System.out.println(countryDaoInter.addCountry(new Country(null,"scotish","Scotland")));
          
          EmploymentHistoryDaoInter emDaoInter = Context.instanceEmploymentHistoryDao();
          
          List<EmploymentHistory> em = emDaoInter.getAll();
          
          for (EmploymentHistory eHistory : em) 
          {
              System.out.println(eHistory);
          }
    }
}
