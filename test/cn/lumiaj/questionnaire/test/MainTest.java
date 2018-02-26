package cn.lumiaj.questionnaire.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainTest {
	public static void main(String[] args) {
		long t1, t2;
		List<Object> list1 = new ArrayList<Object>();
		List<Object> list3 = new LinkedList<Object>();
		Object o = new Object();
		int n = 500000;
		
		t1 = System.currentTimeMillis();
		for(int i=0;i<n;i++) {
			list1.add(0,o);
		}
		t2 = System.currentTimeMillis();
		System.out.println(t2-t1);
		
		t1 = System.currentTimeMillis();
		for(int i=0;i<n;i++) {
			list3.add(0,o);
		}
		t2 = System.currentTimeMillis();
		System.out.println(t2-t1);
		
	}
	
}
