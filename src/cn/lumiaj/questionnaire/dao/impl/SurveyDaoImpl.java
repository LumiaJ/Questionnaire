package cn.lumiaj.questionnaire.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import cn.lumiaj.questionnaire.model.User;

@Repository
@Scope("prototype")
public class SurveyDaoImpl extends BaseDaoImpl<User>{
	
}
