package cn.lumiaj.questionnaire.struts2.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.lumiaj.questionnaire.model.Page;
import cn.lumiaj.questionnaire.model.Question;
import cn.lumiaj.questionnaire.service.SurveyService;

@Namespace("/")
@ParentPackage("que")
@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SurveyService ss;
	private int sid;
	private int pid;
	private int qid;

	@Action(className = "questionAction", value = "QuestionAction_editQuestion",
			results = {
					@Result(name="0", location = "/nonMatrixWithOtherQuestionDesign.jsp"),
					@Result(name="1", location = "/nonMatrixWithOtherQuestionDesign.jsp"),
					@Result(name="2", location = "/nonMatrixWithOtherQuestionDesign.jsp"),
					@Result(name="3", location = "/nonMatrixWithOtherQuestionDesign.jsp"),
					
					@Result(name="4", location = "/nonMatrixSelectQuestionDesign.jsp"),
					@Result(name="5", location = "/nonMatrixTextQuestionDesign.jsp"),
					
					@Result(name="6", location = "/matrixNormalQuestionDesign.jsp"),
					@Result(name="7", location = "/matrixNormalQuestionDesign.jsp"),
					@Result(name="8", location = "/matrixSelectQuestionDesign.jsp")
				})
	public String editQuestion() {
		this.model = ss.getQuestion(qid);
		return ""+model.getQuestionType();
	}
	
	@Action(className = "questionAction", value = "QuestionAction_deleteQuestion")
	public String deleteQuestion() {
		ss.deleteQuestion(qid);
		return "designSurveyAction";
	}
	
	@Action(className = "questionAction", value = "QuestionAction_saveOrUpdateQuestion")
	public String saveOrUpdateQuestion() {
		Page page = new Page();
		page.setId(pid);
		this.model.setPage(page);
		ss.saveOrUpdatePage(this.model);
		return "designSurveyAction";
	}
	
	@Action(className = "questionAction", value = "QuestionAction_toDesignQuestionPage",
			results = {
				@Result(name="0", location = "/nonMatrixWithOtherQuestionDesign.jsp"),
				@Result(name="1", location = "/nonMatrixWithOtherQuestionDesign.jsp"),
				@Result(name="2", location = "/nonMatrixWithOtherQuestionDesign.jsp"),
				@Result(name="3", location = "/nonMatrixWithOtherQuestionDesign.jsp"),
				
				@Result(name="4", location = "/nonMatrixSelectQuestionDesign.jsp"),
				@Result(name="5", location = "/nonMatrixTextQuestionDesign.jsp"),
				
				@Result(name="6", location = "/matrixNormalQuestionDesign.jsp"),
				@Result(name="7", location = "/matrixNormalQuestionDesign.jsp"),
				@Result(name="8", location = "/matrixSelectQuestionDesign.jsp")
			})
	public String toDesignQuestionPage() {
		return this.model.getQuestionType()+"";
	}
	
	@Action(className = "questionAction", value = "QuestionAction_toSelectQuestionType",
			results = {@Result(location = "/selectQuestionType.jsp") })
	public String toSelectQuestionType() {
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

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

}
