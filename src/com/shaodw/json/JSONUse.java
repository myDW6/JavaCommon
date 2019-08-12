package com.shaodw.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Test;

/**
 * Map JavaBean String --> JSON
 * @author shaodw
 *
 */
public class JSONUse {
	
	@Test
	public void demo1() {
		//Map -> Json
		Map<String, String> map = new HashMap<>();
		map.put("s001", "zs");
		map.put("s002", "ls");
		map.put("s003", "ww");
		JSONObject json = new JSONObject(map);
		System.out.println(json);
	}
	
	@Test
	public void demo2() {
		//JavaBean -> JSON
		Person per = new Person();
		per.setName("zs");
		per.setAge(12);
		Address address = new Address("beijing" , "xian");
		per.setAddress(address); 
		JSONObject json = new JSONObject(per);
		System.out.println(json);
	}
	
	@Test
	public void demo3() {
		//String -> JSON
		String str = "{\"name\":\"zs\", \"age\":12}";
		JSONObject json = new JSONObject(str);
		System.out.println(json);
	}
	
	@Test
	public void demo4() throws IOException {
		//File -> JSON ==== File -> String -> JSON
		//ʹ�����·��
		InputStream in = super.getClass().getClassLoader().getResourceAsStream("com/shaodw/json/per.json");
		byte[] buf = new byte[1024];
		int len = -1;
		StringBuffer sb = new StringBuffer();
		while ((len = in.read(buf)) != -1){
			//byte[] -> String
			sb.append(new String(buf, 0, len));//��������1024�ֽ�תΪ�ַ���Ȼ��ƴ��
		}
		//StringBuffer -> String -> JSON
		String str = sb.toString();
		JSONObject json = new JSONObject(str);
		System.out.println(json);
	}
	
	@Test
	public void demo5() throws IOException {
		//ʹ��commons-io�ķ������ļ���Ϊ�ַ���
		String s = FileUtils.readFileToString(new File("D:\\eclipse-workspace\\Common\\src\\com\\shaodw\\json\\per.json"), "UTF-8");
		JSONObject json = new JSONObject(s);
		System.out.println(json);
	}
}
