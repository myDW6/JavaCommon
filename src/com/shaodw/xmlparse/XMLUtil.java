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
		//DOM��ʽ���� : ���
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//����һ��dom����
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream(fileName));
		//������java����Document����
		Element element = document.getDocumentElement();
		NodeList nodeList = element.getElementsByTagName("dog");
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element2 = (Element)nodeList.item(i);
			int id = Integer.parseInt(element2.getAttribute("id"));
			Dog dog = new Dog();
			dog.setId(id);
			NodeList childNodes = element2.getChildNodes();
			for (int j = 0 ;j < childNodes.getLength(); j++) {
				Node node = childNodes.item(j);//<name> <level> <score> Ҳ�п����ǿո� �س� Ҳ�����ӽڵ������ո� �س� ��ǩ
				//ֻ��<>��ʽ���ӽڵ�
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
