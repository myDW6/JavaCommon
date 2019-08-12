package com.shaodw.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ���л��ͷ����л�
 * ��һ������д��Ӳ��
 * �ٽ����Ӳ�̶�����
 * @author shaodw
 *		private static final long serialVersionUID = 1L;���Լ��ƶ����Զ���������ô
 *		���л��ͷ����л���ǿ�������ֵ����ֵ������أ�һ���������ı䣬���л�֮���޷��ٴη����л�
 */
public class SeriSample {
	  	private static class Person implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		private String name;
		private int age;
		
		public Person() {
			
		}
		
		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
	  
	  	public static void write() throws IOException {
	  		Person per1 = new Person("ss", 32);
	  		Person per2 = new Person("ms", 23);
	  		Person per3 = new Person("zs", 13);
	  		List<Person> list = new ArrayList<>();
	  		list.add(per1);
	  		list.add(per2);
	  		list.add(per3);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\per.obj"));
			oos.writeObject(list); 
			oos.close();
			System.out.println("д��ɹ�");
	  	}
	  	
	  	public static void read() throws ClassNotFoundException, IOException {
	  		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\per.obj"));
			List<Person> list =(List<Person>) ois.readObject();
			for (Person per : list) {
				System.out.println(per.getName() + "," + per.getAge());
			}
			ois.close();
	  	}
	  	
	  public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
			//write();
			read();
		}
		
}

