package cn.lumiaj.questionnaire.struts2.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.lumiaj.questionnaire.model.User;
import cn.lumiaj.questionnaire.service.UserService;
import cn.lumiaj.questionnaire.util.MyUtil;

@ParentPackage("que")
@Namespace("/")
@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User> {
	private static final long serialVersionUID = 1L;
	private String checkedPassword;
	@Autowired
	private UserService us;

	@Action(className = "regAction", value = "RegAction_toRegPage", results = {
			@Result(name = "regPage", location = "/reg.jsp") })
	public String toRegPage() {
		return "regPage";
	}

	@Action(className = "regAction", value = "RegAction_doReg", results = {
			@Result(name = "success", location = "/index.jsp") ,
			@Result(name = "input", location = "/reg.jsp") })
	public String doReg() {
		model.setPwd(MyUtil.passwordHandle(model.getPwd()));
		us.saveEntity(model);
		return SUCCESS;
	}

	public void validateDoReg() {
		if (MyUtil.checkNotNull(model.getEmail()))
			addFieldError("email", "email can not be null");
		if (MyUtil.checkNotNull(model.getName()))
			addFieldError("name", "Name can not be null");
		if (MyUtil.checkNotNull(model.getPwd()))
			addFieldError("password", "Password can not be null");
		if (hasErrors())
			return;

		if (!model.getPwd().equals(checkedPassword)) 
			addFieldError("password", "password should equal");
		if (hasErrors())
			return;

		if (us.isRegisted(model.getEmail())) 
			addFieldError("email", "email was used");
	}

	public String getCheckedPassword() {
		return checkedPassword;
	}

	public void setCheckedPassword(String checkedPassword) {
		this.checkedPassword = checkedPassword;
	}

}
