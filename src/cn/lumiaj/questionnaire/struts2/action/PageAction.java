package cn.lumiaj.questionnaire.struts2.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.lumiaj.questionnaire.model.Page;
import cn.lumiaj.questionnaire.model.Survey;
import cn.lumiaj.questionnaire.service.SurveyService;

@Namespace("/")
@ParentPackage("que")
@Controller
@Scope("prototype")
public class PageAction extends BaseAction<Page>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private SurveyService ss;
	private int sid;
	private int pid;
	private double orderNum;
	
	@Action(className = "pageAction", value = "PageAction_deletePage")
	public String deletePage() {
		ss.deletePage(pid);
		return "designSurveyAction";
	}

	@Action(className = "pageAction", value = "PageAction_editPage", results = {
			@Result(location = "/editPage.jsp") })
	public String editPage() {
		this.model = ss.getPage(pid);
		return SUCCESS;
	}
	
	@Action(className = "pageAction", value = "PageAction_saveOrUpdatePage")
	public String saveOrUpdatePage() {
		Survey s = new Survey();
		s.setId(sid);
		this.model.setSurvey(s);
		ss.saveOrUpdatePage(model);
		return "designSurveyAction";
	}
	
	@Action(className = "pageAction", value = "PageAction_toAddPage", results = {
			@Result(location = "/editPage.jsp") })
	public String toAddPage() {
		return SUCCESS;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public double getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(double orderNum) {
		this.orderNum = orderNum;
	}
	
}


