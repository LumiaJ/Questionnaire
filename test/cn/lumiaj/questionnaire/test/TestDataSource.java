package cn.lumiaj.questionnaire.test;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.lumiaj.questionnaire.model.User;
import cn.lumiaj.questionnaire.service.SurveyService;
import cn.lumiaj.questionnaire.service.UserService;
import cn.lumiaj.questionnaire.struts2.action.RegAction;

public class TestDataSource {
	private static UserService us;
	private static ApplicationContext ctx;
	private static SurveyService ss;
	
	@BeforeClass
	public static void beforeClass() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		us = ctx.getBean(UserService.class);
		ss = ctx.getBean(SurveyService.class);
	}
	
	@Test
	public void test5() {
		ss.getSurvey(1);
	}
	
	@Test
	public void test4() {
		User u = new User();
		u.setId(19);
		ss.newSurvey(u);
	}
	
	@Test
	public void test3() {
		RegAction regAction = (RegAction) ctx.getBean("regAction");
		String str = regAction.toRegPage();
		System.out.println(str);
	}
	
	@Test
	public void test2() {
		User u = new User();
		u.setEmail("493502758@qq.com");
		u.setName("lumiaj");
		u.setNickName("hwh");
		u.setPwd("123456");
		us.saveEntity(u);
	}

	@Test
	public void test1() throws SQLException {
		SessionFactory sf = ctx.getBean(SessionFactory.class);
		Session s = sf.openSession();
		s.beginTransaction();
		s.getTransaction().commit();
	}

}
