package com.shaodw.xmlparse;

import java.util.List;

public class Sample {
	public static void main(String[] args) throws Exception{
		//����xml���ļ�·�� ʵ�ֽ��� ���List����
		String path = "src//com//shaodw//xmlparse//dogs.xml";
		
		List<Dog> dogs = XMLUtil.parseXmlToList(path);
		for (Dog dog : dogs) {
			System.out.println(dog.getId() + " "+ dog.getName() + " " + dog.getScore() + " " + dog.getLevel());
		}
	}
	
	
}
