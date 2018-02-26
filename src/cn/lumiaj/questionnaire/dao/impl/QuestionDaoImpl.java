package cn.lumiaj.questionnaire.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import cn.lumiaj.questionnaire.model.Question;

@Repository
@Scope("prototype")
public class QuestionDaoImpl extends BaseDaoImpl<Question>{
	
}
