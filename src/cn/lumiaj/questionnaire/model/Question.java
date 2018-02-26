package cn.lumiaj.questionnaire.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import cn.lumiaj.questionnaire.util.MyUtil;

@Entity
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String RN = "\r\n";
	private int id;
	private int questionType;
	private String title;
	private String options;
	private String[] optionArr;
	private boolean other;
	private int otherStyle;
	private String otherSelectOptions;
	private String[] otherSelectOptionArr;
	private String matrixRowTitles;
	private String[] matrixRowTitleArr;
	private String matrixColTitles;
	private String[] matrixColTitleArr;
	private String matrixSelectOptions;
	private String[] matrixSelectOptionArr;

	private Page page;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
		this.optionArr = MyUtil.stringToArr(options, RN);
	}

	public boolean isOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}

	public int getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(int otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
		this.matrixRowTitleArr = MyUtil.stringToArr(matrixRowTitles, RN);
	}

	public String getMatrixColTitles() {
		return matrixColTitles;
	}

	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = matrixColTitles;
		this.matrixColTitleArr = MyUtil.stringToArr(matrixColTitles, RN);
	}

	public String getMatrixSelectOptions() {
		return matrixSelectOptions;
	}

	public void setMatrixSelectOptions(String matrixSelectOptions) {
		this.matrixSelectOptions = matrixSelectOptions;
		this.matrixSelectOptionArr = MyUtil.stringToArr(matrixSelectOptions, RN);
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
		this.otherSelectOptionArr = MyUtil.stringToArr(otherSelectOptions, RN);
	}

	@ManyToOne
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String[] getOptionArr() {
		return optionArr;
	}

	public String[] getOtherSelectOptionArr() {
		return otherSelectOptionArr;
	}

	public String[] getMatrixRowTitleArr() {
		return matrixRowTitleArr;
	}

	public String[] getMatrixColTitleArr() {
		return matrixColTitleArr;
	}

	public String[] getMatrixSelectOptionArr() {
		return matrixSelectOptionArr;
	}

	public void setOptionArr(String[] optionArr) {
		this.optionArr = optionArr;
	}

	public void setOtherSelectOptionArr(String[] otherSelectOptionArr) {
		this.otherSelectOptionArr = otherSelectOptionArr;
	}

	public void setMatrixRowTitleArr(String[] matrixRowTitleArr) {
		this.matrixRowTitleArr = matrixRowTitleArr;
	}

	public void setMatrixColTitleArr(String[] matrixColTitleArr) {
		this.matrixColTitleArr = matrixColTitleArr;
	}

	public void setMatrixSelectOptionArr(String[] matrixSelectOptionArr) {
		this.matrixSelectOptionArr = matrixSelectOptionArr;
	}

}
