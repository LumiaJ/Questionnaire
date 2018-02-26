package cn.lumiaj.questionnaire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lumiaj.questionnaire.dao.BaseDao;
import cn.lumiaj.questionnaire.model.Answer;
import cn.lumiaj.questionnaire.model.Page;
import cn.lumiaj.questionnaire.model.Question;
import cn.lumiaj.questionnaire.model.Survey;
import cn.lumiaj.questionnaire.model.User;
import cn.lumiaj.questionnaire.service.SurveyService;
import cn.lumiaj.questionnaire.util.MyUtil;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService{
	@Autowired
	private BaseDao<Survey> surveyDao;
	@Autowired
	private BaseDao<Page> pageDao;
	@Autowired
	private BaseDao<Question> questionDao;
	@Autowired
	private BaseDao<Answer> answerDao;

	@Override
	public List<Survey> findMySurveys(User user) {
		String hql = "from Survey s where s.user.id = ?";
		List<Survey> list = surveyDao.findEntityByHQL(hql, user.getId());
		return list;
	}

	@Override
	public Survey newSurvey(User user) {
		Survey s = new Survey();
		Page p = new Page();
		s.setUser(user);
		s.getPages().add(p);
		p.setSurvey(s);
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s;
	}

	@Override
	public Survey getSurvey(int id) {
		return surveyDao.getEntity(id);
	}

	@Override
	public Survey getSurveyWithChildren(int id) {
		Survey s = getSurvey(id);
		for(Page p : s.getPages()) {
			p.getQuestions().size();
		}
		return s;
	}

	@Override
	public void updateSurvey(Survey survey) {
		surveyDao.updateEntity(survey);
	}

	@Override
	public void saveOrUpdatePage(Page page) {
		pageDao.saveOrUpdateEntity(page);
	}

	@Override
	public Page getPage(int pid) {
		return pageDao.getEntity(pid);
	}

	@Override
	public void saveOrUpdatePage(Question question) {
		questionDao.saveOrUpdateEntity(question);
	}

	@Override
	public void deleteQuestion(int qid) {
		String hql = "delete from Answer a where a.qid = ?";
		answerDao.batchEntityByHQL(hql, qid);
		hql = "delete from Question q where q.id = ?";
		questionDao.batchEntityByHQL(hql, qid);
	}

	@Override
	public void deletePage(int pid) {
		Page page = pageDao.getEntity(pid);
		Survey survey = page.getSurvey();
		for(Question q : page.getQuestions()) {
			deleteQuestion(q.getId());
		}
		String hql = "delete from Page p where p.id = ?";
		pageDao.batchEntityByHQL(hql, pid);
		if(survey.getPages().isEmpty()) {
			Page newPage = new Page();
			newPage.setSurvey(survey);
			saveOrUpdatePage(newPage);
		}
	}

	@Override
	public void deleteSurvey(int sid) {
		Survey s = surveyDao.getEntity(sid);
		for(Page p : s.getPages()) {
			deletePage(p.getId());
		}
		String hql = "delete from Survey s where s.id = ?";
		surveyDao.batchEntityByHQL(hql, sid);
	}

	@Override
	public Question getQuestion(int qid) {
		return questionDao.getEntity(qid);
	}

	@Override
	public void clearAnswer(int sid) {
		String hql = "delete from Answer a where a.sid = ?";
		answerDao.batchEntityByHQL(hql, sid);
	}

	@Override
	public void toggleSurveyStatus(int sid) {
		String hql = "update Survey s set s.closed = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql, !getSurvey(sid).isClosed(),sid);
	}

	@Override
	public void updateLogoPhotoPath(int sid, String path) {
		String hql = "update Survey s set s.logoPhotoPath = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql, path, sid);
	}

	@Override
	public List<Survey> getAllPages(User user) {
		String hql = "from Survey s where s.user.id = ?";
		List<Survey> list = surveyDao.findEntityByHQL(hql, user.getId());
		for(Survey s : list) {
			s.getPages().size();
		}
		return list;
	}

	@Override
	public void doMoveOrCopyPage(int srcPid, int tarPid, int pos) {
		PageUtil pu = new PageUtil();
		Page srcPage = this.getPage(srcPid);
		Survey srcSurvey = srcPage.getSurvey();
		Page tarPage = this.getPage(tarPid);
		Survey tarSurvey = tarPage.getSurvey();
		if(srcSurvey.getId() == tarSurvey.getId()) {
			pu.setOrderNum(srcPage, tarPage, pos);
		}else {
			srcPage.getQuestions().size();
			Page copyPage = (Page) MyUtil.deepCopy(srcPage);
			copyPage.setSurvey(tarSurvey);
			pageDao.saveEntity(copyPage);
			for(Question q : copyPage.getQuestions()) {
				questionDao.saveEntity(q);
			}
			pu.setOrderNum(copyPage, tarPage, pos);
		}
	}

	private class PageUtil {
		
		public void setOrderNum(Page srcPage, Page tarPage, int pos) {
			if(pos == 0) {
				if(isFirstPage(tarPage)) 
					srcPage.setOrderNum(tarPage.getOrderNum() - 0.01);
				else 
					srcPage.setOrderNum((getPrePage(tarPage).getOrderNum() 
							+ tarPage.getOrderNum()) / 2);
			}else {
				if(isLastPage(tarPage)) 
					srcPage.setOrderNum(tarPage.getOrderNum() + 0.01);
				else 
					srcPage.setOrderNum((getNextPage(tarPage).getOrderNum() 
							+ tarPage.getOrderNum()) / 2);
			}
		}
		
		private Page getNextPage(Page tarPage) {
			String hql = "from Page p where p.survey.id = ? and p.orderNum > ? order by p.orderNum asc";
			List<Page> pages = pageDao.findEntityByHQL(hql, tarPage.getSurvey().getId(), tarPage.getOrderNum());
			return pages.get(0);
		}
		
		private Page getPrePage(Page tarPage) {
			String hql = "from Page p where p.survey.id = ? and p.orderNum < ? order by p.orderNum desc";
			List<Page> pages = pageDao.findEntityByHQL(hql, tarPage.getSurvey().getId(), tarPage.getOrderNum());
			return pages.get(0);
		}
		
		private boolean isLastPage(Page tarPage) {
			String hql = "select count(*) from Page p where p.survey.id = ? and p.orderNum > ?";
			long count = (long) pageDao.uniqueResult(hql, tarPage.getSurvey().getId(), tarPage.getOrderNum());
			return count==0;
		}
		
		private boolean isFirstPage(Page tarPage) {
			String hql = "select count(*) from Page p where p.survey.id = ? and p.orderNum < ?";
			long count = (long) pageDao.uniqueResult(hql, tarPage.getSurvey().getId(), tarPage.getOrderNum());
			return count==0;
		}
	}

	@Override
	public List<Survey> findAllAvailableSurvey() {
		String hql = "from Survey s where s.closed = ?";
		return surveyDao.findEntityByHQL(hql, false);
	}

	@Override
	public Page getFirstPage(int sid) {
		String hql = "from Page p where p.survey.id = ? order by p.orderNum asc";
		Page currPage = pageDao.findEntityByHQL(hql, sid).get(0);
		currPage.getQuestions().size();
		return currPage;
	}

	@Override
	public Page getPrePage(int currPid) {
		PageUtil pu = new PageUtil();
		Page p = pu.getPrePage(getPage(currPid));
		p.getQuestions().size();
		return p;
	}

	@Override
	public Page getNextPage(int currPid) {
		PageUtil pu = new PageUtil();
		Page p = pu.getNextPage(getPage(currPid));
		p.getQuestions().size();
		return p;
	}
	
}
