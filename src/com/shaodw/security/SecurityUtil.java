package com.shaodw.security;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtil {

	//ʹ�����ʵ�ּ��ܣ����� һ�������ܣ��ٴ�������
	//����String �õ����ܺ��String
	public static String xor(String input) {
		char[] chs = input.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			chs[i] = (char)(chs[i] ^ 3000);
		}
		return new String(chs);
	}
	
	//ʹ��md5���� byte[] -> String
	public static String md5Encode(byte[] input) {
		return DigestUtils.md5Hex(input);
	}
	
	//ʹ��sha256����
	public static String sha256Encode(byte[] input) {
		return DigestUtils.sha256Hex(input);
	}
	
	//Base64��ʹ��
	//����
	public String base64Encode(byte[] input){
		String res = null;
		try {
			Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method = clazz.getMethod("encode", byte[].class);
			res = (String)method.invoke(null, input);//encode�����Ǿ�̬�Ĳ���Ҫ���� ���Կ�����null
		}
		  catch (ClassNotFoundException e) {
			// TODO: handle exception
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	
	//����
	public byte[] base64Decode(String input) {
		byte[] by = null;
		try {
			Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method = clazz.getMethod("decode", String.class);
			by = (byte[]) method.invoke(null, input);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return by;
	}
}
