package com.sunil.test;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.sunil.Entities.Employee;


public class Test {
	public static void main(String []args) {
		ObjectMapper om = new ObjectMapper();
		Employee e = new Employee(1, "Sunil", "MCA", 23, "F-128 Goving Bhawan");
		try {
			System.out.println(om.writeValueAsString(e));
			om.writeValue(new File("employee.json"), e);
			e= null;
			e = om.readValue(new File("employee.json"), Employee.class);
			System.out.println("OBJ:  "+ e.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
