package cn.lumiaj.questionnaire.struts2.action;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.lumiaj.questionnaire.model.Survey;
import cn.lumiaj.questionnaire.model.User;
import cn.lumiaj.questionnaire.service.SurveyService;
import cn.lumiaj.questionnaire.struts2.UserAware;
import cn.lumiaj.questionnaire.util.MyUtil;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("que")
public class SurveyAction extends BaseAction<Survey> implements UserAware,ServletContextAware {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SurveyService ss;
	private List<Survey> list;
	private User user;
	private int sid;
	private File logoPhoto;
	private String logoPhotoFileName;
	private ServletContext sc;
	
	
	@Action(className = "surveyAction", value = "SurveyAction_doAddLogo", results = {
			@Result(name="input", location = "/addLogo.jsp")})
	public String doAddLogo() {
		if(!MyUtil.checkNotNull(logoPhotoFileName)) {
			String dir = sc.getRealPath("/upload");
			String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			long l = System.nanoTime();
			File newFile = new File(dir, l+ext);
			logoPhoto.renameTo(newFile);
			ss.updateLogoPhotoPath(sid, "/upload/"+l+ext);
		}
		return "designSurveyAction";
	}
	
	@Action(className = "surveyAction", value = "SurveyAction_toAddLogoPage", results = {
			@Result(location="/addLogo.jsp")})
	public String toAddLogoPage() {
		return SUCCESS;
	}
	
	@Action(className = "surveyAction", value = "SurveyAction_toggleStatus")
	public String toggleStatus() {
		ss.toggleSurveyStatus(sid);
		return "mySurveysAction";
	}
	
	@Action(className = "surveyAction", value = "SurveyAction_clearAnswer")
	public String clearAnswer() {
		ss.clearAnswer(sid);
		return "mySurveysAction";
	}
	
	@Action(className = "surveyAction", value = "SurveyAction_deleteSurvey")
	public String deleteSurvey() {
		ss.deleteSurvey(sid);
		return "mySurveysAction";
	}
	
	@Action(className = "surveyAction", value = "SurveyAction_updateSurvey")
	public String updateSurvey() {
		sid = model.getId();
		model.setUser(user);
		Survey srcSurvey = ss.getSurvey(sid);
		model.setClosed(srcSurvey.isClosed());
		model.setLogoPhotoPath(srcSurvey.getLogoPhotoPath());
		ss.updateSurvey(this.model);
		return "designSurveyAction";
	}
	
	@Action(className = "surveyAction", value = "SurveyAction_editSurvey", results = {
			@Result(location = "/editSurvey.jsp") })
	public String editSurvey() {
		this.model = ss.getSurvey(sid);
		return SUCCESS;
	}
	
	@Action(className = "surveyAction", value = "SurveyAction_designSurvey", results = {
			@Result(location = "/designSurvey.jsp") })
	public String designSurvey() {
		this.model = ss.getSurveyWithChildren(sid);
		return SUCCESS;
	}
	
	@Action(className = "surveyAction", value = "SurveyAction_newSurvey", results = {
			@Result(location = "/designSurvey.jsp") })
	public String newSurvey() {
		this.model = ss.newSurvey(user);
		return SUCCESS;
	}

	@Action(className = "surveyAction", value = "SurveyAction_mySurveys", results = {
			@Result(location = "/mySurveys.jsp") })
	public String mySurveys() {
		list = ss.findMySurveys(user);
		return SUCCESS;
	}
	
	public boolean logoExist() {
		String path = this.model.getLogoPhotoPath();
		if(!MyUtil.checkNotNull(path)) {
			String absPath = sc.getRealPath(path);
			File file = new File(absPath);
			return file.exists();
		}
		return false;
	}

	public List<Survey> getList() {
		return list;
	}

	public void setList(List<Survey> list) {
		this.list = list;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public File getLogoPhoto() {
		return logoPhoto;
	}

	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}

}
