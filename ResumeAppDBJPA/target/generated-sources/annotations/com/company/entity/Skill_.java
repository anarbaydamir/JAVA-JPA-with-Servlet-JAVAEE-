package com.company.entity;

import com.company.entity.UserSkill;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:25:38")
@StaticMetamodel(Skill.class)
public class Skill_ { 

    public static volatile SingularAttribute<Skill, String> name;
    public static volatile SingularAttribute<Skill, Integer> id;
    public static volatile ListAttribute<Skill, UserSkill> userSkillList;

}