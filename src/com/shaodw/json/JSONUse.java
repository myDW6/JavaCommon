package com.shaodw.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
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
		//使用相对路径
		InputStream in = super.getClass().getClassLoader().getResourceAsStream("com/shaodw/json/per.json");
		byte[] buf = new byte[1024];
		int len = -1;
		StringBuffer sb = new StringBuffer();
		while ((len = in.read(buf)) != -1){
			//byte[] -> String
			sb.append(new String(buf, 0, len));//将读到的1024字节转为字符串然后拼接
		}
		//StringBuffer -> String -> JSON
		String str = sb.toString();
		JSONObject json = new JSONObject(str);
		System.out.println(json);
	}
	
	@Test
	public void demo5() throws IOException {
		//使用commons-io的方法将文件变为字符串
		String s = FileUtils.readFileToString(new File("D:\\eclipse-workspace\\Common\\src\\com\\shaodw\\json\\per.json"), "UTF-8");
		JSONObject json = new JSONObject(s);
		System.out.println(json);
	}
	
	@Test
	public void demo6() throws JSONException, IOException {
		//生成json文件
		//准备map 通过map生产json
		Map<String, Person> map = new HashMap<>();
		Person p1 = new Person("张三", 14, new Address("北京", "上海"));
		Person p2 = new Person("李四", 25, new Address("重庆", "湖南"));
		Person p3 = new Person("王五", 41, new Address("青岛", "天津"));
		map.put("zs", p1);
		map.put("ls", p2);
		map.put("ww", p3);
		JSONObject json = new JSONObject(map);
		Writer writer = new FileWriter("D:\\a.json");
		json.write(writer);
		writer.close();
	}
	
	@Test
	public void demo07() {
		//JSONArray的使用
		String str = "[{\"name\":\"zs\", \"age\":12}, {\"className\":\"jike1\", \"classNo\":2},{\"sAddress\":\"chonging\"}]";
		//String -> JSON数组
		JSONArray jsonArray = new JSONArray(str);
		System.out.println(jsonArray);
	}
	
	@Test
	public void demo08() {
		//Map -> JSONArray
		Map<String, String> map = new HashMap<>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		map.put("k3", "v3");
		//使用额外的json lib库
		//注意这里使用的JSONArray不是上面的json.jar中的JSONArray,而是JSON Lib库中(json lib 官网 )
		net.sf.json.JSONArray jsonArray = new net.sf.json.JSONArray().fromObject(map);
		System.out.println(jsonArray);
	}
	
	@Test
	public void demo09() {
		//JSONArray -> Map
		net.sf.json.JSONArray jsonArray = new net.sf.json.JSONArray().fromObject("[{\"name\":\"zs\", \"age\":12}, {\"className\":\"jike1\", \"classNo\":2},{\"sAddress\":\"chonging\"}]");
		//遍历jsonarray获取每一个json,都是key - value形式  —> map
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			net.sf.json.JSONObject jsonObj = (net.sf.json.JSONObject)jsonArray.get(i);
			Set<String> set = jsonObj.keySet();
			for (String s : set) {
				map.put(s, jsonObj.get(s));
			}
		}
		System.out.println(map);
	}
}

