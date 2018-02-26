package cn.lumiaj.questionnaire.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Formula;

@Entity
public class Survey {
	private int id;
	private String title = "未命名";
	private String preText = "上一页";
	private String nextText = "下一页";
	private String exitText = "退出";
	private String doneText = "完成";
	private Date createTime = new Date();
	private boolean closed;
	
	private double minOrderNum;
	private double maxOrderNum;

	private User user;
	private Set<Page> pages = new HashSet<Page>();
	private String logoPhotoPath;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToMany(mappedBy="survey")
	@OrderBy("orderNum")
	public Set<Page> getPages() {
		return pages;
	}

	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreText() {
		return preText;
	}

	public void setPreText(String preText) {
		this.preText = preText;
	}

	public String getNextText() {
		return nextText;
	}

	public void setNextText(String nextText) {
		this.nextText = nextText;
	}

	public String getExitText() {
		return exitText;
	}

	public void setExitText(String exitText) {
		this.exitText = exitText;
	}

	public String getDoneText() {
		return doneText;
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getLogoPhotoPath() {
		return logoPhotoPath;
	}

	public void setLogoPhotoPath(String logoPhotoPath) {
		this.logoPhotoPath = logoPhotoPath;
	}

	@Formula("(select min(p.orderNum) from page p where p.survey_id = id)")
	public double getMinOrderNum() {
		return minOrderNum;
	}

	public void setMinOrderNum(double minOrderNum) {
		this.minOrderNum = minOrderNum;
	}

	@Formula("(select max(p.orderNum) from page p where p.survey_id = id)")
	public double getMaxOrderNum() {
		return maxOrderNum;
	}

	public void setMaxOrderNum(double maxOrderNum) {
		this.maxOrderNum = maxOrderNum;
	}

}
