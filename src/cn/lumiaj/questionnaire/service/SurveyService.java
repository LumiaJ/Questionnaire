package cn.lumiaj.questionnaire.service;

import java.util.List;

import cn.lumiaj.questionnaire.model.Page;
import cn.lumiaj.questionnaire.model.Question;
import cn.lumiaj.questionnaire.model.Survey;
import cn.lumiaj.questionnaire.model.User;

public interface SurveyService {
	
	public List<Survey> findMySurveys(User user);
	
	public Survey newSurvey(User user);
	
	public Survey getSurvey(int id);
	
	public Survey getSurveyWithChildren(int id);

	public void updateSurvey(Survey survey);

	public void saveOrUpdatePage(Page page);

	public Page getPage(int pid);

	public void saveOrUpdatePage(Question question);

	public void deleteQuestion(int qid);

	public void deletePage(int pid);

	public void deleteSurvey(int sid);

	public Question getQuestion(int qid);

	public void clearAnswer(int sid);

	public void toggleSurveyStatus(int sid);

	public void updateLogoPhotoPath(int sid, String path);

	public List<Survey> getAllPages(User user);

	public void doMoveOrCopyPage(int srcPid, int tarPid, int pos);

	public List<Survey> findAllAvailableSurvey();

	public Page getFirstPage(int sid);

	public Page getPrePage(int currPid);

	public Page getNextPage(int currPid);

}
