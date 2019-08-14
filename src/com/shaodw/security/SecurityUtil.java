package com.shaodw.security;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtil {

	//使用异或实现加密，解密 一次异或加密，再次异或解密
	//传入String 得到加密后的String
	public static String xor(String input) {
		char[] chs = input.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			chs[i] = (char)(chs[i] ^ 3000);
		}
		return new String(chs);
	}
	
	//使用md5加密 byte[] -> String
	public static String md5Encode(byte[] input) {
		return DigestUtils.md5Hex(input);
	}
	
	//使用sha256加密
	public static String sha256Encode(byte[] input) {
		return DigestUtils.sha256Hex(input);
	}
	
	//Base64的使用
	//加密
	public String base64Encode(byte[] input){
		String res = null;
		try {
			Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method = clazz.getMethod("encode", byte[].class);
			res = (String)method.invoke(null, input);//encode方法是静态的不需要对象 所以可以是null
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
	
	
	//解密
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
