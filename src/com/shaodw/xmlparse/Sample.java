package com.shaodw.xmlparse;

import java.util.List;

public class Sample {
	public static void main(String[] args) throws Exception{
		//输入xml的文件路径 实现解析 输出List集合
		String path = "src//com//shaodw//xmlparse//dogs.xml";
		
		List<Dog> dogs = XMLUtil.parseXmlToList(path);
		for (Dog dog : dogs) {
			System.out.println(dog.getId() + " "+ dog.getName() + " " + dog.getScore() + " " + dog.getLevel());
		}
	}
	
	
}
