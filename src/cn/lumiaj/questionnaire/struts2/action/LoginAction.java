package cn.lumiaj.questionnaire.struts2.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.lumiaj.questionnaire.model.User;
import cn.lumiaj.questionnaire.service.UserService;
import cn.lumiaj.questionnaire.util.MyUtil;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("que")
public class LoginAction extends BaseAction<User> implements SessionAware {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService us;
	private Map<String,Object> sessionMap;

	@Action(className = "loginAction", value = "LoginAction_doLogin", results = {
			@Result(name = "input", location = "/index.jsp") })
	public String doLogin() {
		return LOGIN;
	}

	@Action(className = "loginAction", value = "LoginAction_toLoginPage", results = {
			@Result(name = "success", location = "/index.jsp") })
	public String toLoginPage() {
		return SUCCESS;
	}

	public void validateDoLogin() {
		User user = us.loginValidate(model.getEmail(), MyUtil.passwordHandle(model.getPwd()));
		if(user == null) {
			addActionError("email or password error");
		}else {
			sessionMap.put("user", user);
		}
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

}
