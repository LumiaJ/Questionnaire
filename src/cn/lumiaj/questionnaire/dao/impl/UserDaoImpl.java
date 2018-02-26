package cn.lumiaj.questionnaire.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import cn.lumiaj.questionnaire.model.Survey;

@Repository
@Scope("prototype")
public class UserDaoImpl extends BaseDaoImpl<Survey>{
	
}
