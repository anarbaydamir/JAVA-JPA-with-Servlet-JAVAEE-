package com.company.main;

import com.company.dao.impl.*;
import com.company.dao.inter.*;

public class Context {
    public static UserDaoInter instanceUserDao() { //Factory Pattern
        return new UserDaoImpl();
    }
    
    public static UserSkillDaoInter instanceUserSkillDao () {return  new UserSkillDaoImpl();}
    
    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao () {return  new EmploymentHistoryDaoImpl();}
    
    public static CountryDaoInter instanceCountryDao () {return new CountryDaoImpl();}
    
    public static SkillDaoInter instanceSkillDao () {return new SkillDaoImpl();}
    
}
