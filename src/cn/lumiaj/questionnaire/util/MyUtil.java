package cn.lumiaj.questionnaire.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

public class MyUtil {
	
	public static Serializable deepCopy(Serializable src) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(src);
			byte[] bytes = baos.toByteArray();
			oos.close();
			baos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Serializable copy = (Serializable) ois.readObject();
			ois.close();
			bais.close();
			return copy;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String[] stringToArr(String str, String tag) {
		if(!checkNotNull(str))
			return str.split(tag);
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection c) {
		return (c==null || c.isEmpty());
	}
	
	public static boolean checkNotNull(String str) {
		return (str == null || str.trim().equals(""));
	}

	public static String passwordHandle(String pwd) {
		char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buffer = new StringBuffer();
		byte[] bytes = pwd.getBytes();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
		}
		byte[] pwds = md.digest(bytes);
		for(byte b : pwds) {
			buffer.append(chars[(b >> 4) & 0x0F]);
			buffer.append(chars[b & 0x0F]);
		}
		return buffer.toString();
	}

}
