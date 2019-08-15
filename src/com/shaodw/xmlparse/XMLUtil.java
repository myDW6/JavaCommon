package com.shaodw.xmlparse;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil {
	public static List<Dog> parseXmlToList(String fileName) throws Exception{
		List<Dog> list = new ArrayList<Dog>();
		//DOM方式解析 : 入口
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//产生一个dom工厂
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream(fileName));
		//可以用java处理Document对象
		Element element = document.getDocumentElement();
		NodeList nodeList = element.getElementsByTagName("dog");
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element2 = (Element)nodeList.item(i);
			int id = Integer.parseInt(element2.getAttribute("id"));
			Dog dog = new Dog();
			dog.setId(id);
			NodeList childNodes = element2.getChildNodes();
			for (int j = 0 ;j < childNodes.getLength(); j++) {
				Node node = childNodes.item(j);//<name> <level> <score> 也有可能是空格 回车 也就是子节点会包括空格 回车 标签
				//只拿<>形式的子节点
				if (node.getNodeType() == Node.ELEMENT_NODE){
					if ("name".equals(node.getNodeName())) {
						dog.setName(node.getFirstChild().getNodeValue());
					}
					else if ("score".equals(node.getNodeName())) {
						dog.setScore((Integer.parseInt(node.getFirstChild().getNodeValue())));
					}
					else {
						dog.setLevel(Integer.parseInt(node.getFirstChild().getNodeValue()));
					}
				}
			}
			list.add(dog);
		}
		return list;
	}
}
