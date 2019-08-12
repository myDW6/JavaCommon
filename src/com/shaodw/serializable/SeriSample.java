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
 * 序列化和反序列化
 * 将一个对象写入硬盘
 * 再将其从硬盘读回来
 * @author shaodw
 *		private static final long serialVersionUID = 1L;不自己制定会自动生产，那么
 *		序列化和反序列化会强烈需求该值，该值与类相关，一旦类做出改变，序列化之后将无法再次反序列化
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
			System.out.println("写入成功");
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

