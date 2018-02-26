package cn.lumiaj.questionnaire.service;

import cn.lumiaj.questionnaire.model.User;

public interface UserService extends BaseService<User>{

	public boolean isRegisted(String email);
	
	public User loginValidate(String email, String pwd);
}
