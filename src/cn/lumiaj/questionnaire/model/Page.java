package cn.lumiaj.questionnaire.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Page implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String title = "未命名";
	private String description;
	private double orderNum;
	
	private transient Survey survey;
	private Set<Question> questions = new HashSet<Question>();

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		this.orderNum = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne
	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	@OneToMany(mappedBy="page")
	@OrderBy("id")
	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public double getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(double orderNum) {
		this.orderNum = orderNum;
	}

}
