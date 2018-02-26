package cn.lumiaj.questionnaire.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lumiaj.questionnaire.model.User;
import cn.lumiaj.questionnaire.service.UserService;
import cn.lumiaj.questionnaire.util.MyUtil;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Override
	public boolean isRegisted(String email) {
		String hql = "from User u where u.email = ?";
		List<User> list = this.findEntityByHQL(hql, email);
		return !MyUtil.isEmpty(list);
	}

	@Override
	public User loginValidate(String email, String pwd) {
		String hql = "from User u where u.email = ? and u.pwd = ?";
		List<User> list = findEntityByHQL(hql, email, pwd);
		return MyUtil.isEmpty(list) ? null : list.get(0);
	}

}
