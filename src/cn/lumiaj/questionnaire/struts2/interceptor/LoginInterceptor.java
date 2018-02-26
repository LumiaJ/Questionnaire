package cn.lumiaj.questionnaire.struts2.interceptor;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import cn.lumiaj.questionnaire.model.User;
import cn.lumiaj.questionnaire.struts2.UserAware;
import cn.lumiaj.questionnaire.struts2.action.BaseAction;
import cn.lumiaj.questionnaire.struts2.action.LoginAction;
import cn.lumiaj.questionnaire.struts2.action.RegAction;

@Controller
public class LoginInterceptor implements Interceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction action = (BaseAction) arg0.getAction();
		if(action instanceof LoginAction || action instanceof RegAction) {
			return arg0.invoke();
		}else {
			User user = (User) arg0.getInvocationContext().getSession().get("user");
			if(user == null) {
				return "login";
			}
			if(action instanceof UserAware) {
				((UserAware) action).setUser(user);
			}
			return arg0.invoke();
		}
	}

}
