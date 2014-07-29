package com.sunil.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sunil.DAO.EmployeeDAO;
import com.sunil.Entities.Employee;

public class EmployeeDaoTest {
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice;
		String line;
		boolean flag;
		
		try {
			do {
				flag = true;
				System.out.println("-----------------------------------------------");
				System.out.println("---------------Welcome to Menu-----------------");
				System.out.println("-----------------------------------------------");
				System.out.println("1.  Store a Employee.");
				System.out.println("2.  Store Multiple Employees.");
				System.out.println("3.  Get a Employee.");
				System.out.println("4.  Get All Employee.");
				System.out.println("5.  Update Employee Details.");
				System.out.println("6.  Update Multiple Employees Details.");
				System.out.println("7.  Delete a Employee.");
				System.out.println("8.  Delete Multiple Employees.");
				System.out.println("9.  Delete All Employees.");
				System.out.println("10. Search Options.");
				System.out.println("11. Exit.");
				System.out.print("Enter your choice: ");
				line = br.readLine();
				choice = Integer.parseInt(line);
				System.out.println("-----------------------------------------------");
				switch (choice) {
				case 1: {
					Employee emp = new Employee();
					System.out.print("Enter Employee ID: ");
					emp.setEmpId(Integer.parseInt(br.readLine()));
					System.out.print("Enter Employee Name: ");
					emp.setName(br.readLine());
					System.out.print("Enter Employee Department: ");
					emp.setDepartment(br.readLine());
					System.out.print("Enter Employee Age: ");
					emp.setAge(Integer.parseInt(br.readLine()));
					System.out.print("Enter Employee Address: ");
					emp.setAddress(br.readLine());

					if (EmployeeDAO.storeEmployee(emp)) {
						System.out.println("Added Employee details.");
					} else {
						System.out.println("Error occured in adding Employee details.");
					}
					break;
				}
				case 2: {
					List<Employee> empList = new ArrayList<Employee>();
					System.out.print("Enter Number of employeed to be added: ");
					int counter = Integer.parseInt(br.readLine());
					while (counter-- != 0) {
						Employee emp = new Employee();
						System.out.print("Enter Employee ID: ");
						emp.setEmpId(Integer.parseInt(br.readLine()));
						System.out.print("Enter Employee Name: ");
						emp.setName(br.readLine());
						System.out.print("Enter Employee Department: ");
						emp.setDepartment(br.readLine());
						System.out.print("Enter Employee Age: ");
						emp.setAge(Integer.parseInt(br.readLine()));
						System.out.print("Enter Employee Address: ");
						emp.setAddress(br.readLine());

						empList.add(emp);
					}
					if (EmployeeDAO.storeEmployee(empList)) {
						System.out.println("Added All Employee details.");
					} else {
						System.out
								.println("Error occured in adding Employee details.");
					}
					break;
				}
				case 3: {
					System.out.print("Enter Employee ID: ");
					Employee emp = EmployeeDAO.getEmployee(Integer.parseInt(br
							.readLine()));
					if (emp != null) {
						System.out.println(emp.toString());
					}
					break;
				}
				case 4: {
					List<Employee> empList = EmployeeDAO.getEmployee();
					for (Employee emp : empList) {
						System.out.println(emp.toString());
					}
					break;
				}
				case 5: {
					Employee emp = new Employee();
					System.out.print("Enter Employee ID for which details has to be updated: ");
					emp.setEmpId(Integer.parseInt(br.readLine()));
					System.out.print("Enter Updated Name: ");
					emp.setName(br.readLine());
					System.out.print("Enter Updated Department: ");
					emp.setDepartment(br.readLine());
					System.out.print("Enter Updated Age: ");
					emp.setAge(Integer.parseInt(br.readLine()));
					System.out.print("Enter Updated Address: ");
					emp.setAddress(br.readLine());

					if (EmployeeDAO.updateEmployee(emp)) {
						System.out.println("Employee details updated.");
					} else {
						System.out.println("Error occured in updating Employee details.");
					}
					break;
				}
				case 6: {
					List<Employee> empList = new ArrayList<Employee>();
					System.out.print("Enter Number of employeed to be Updated: ");
					int counter = Integer.parseInt(br.readLine());
					while (counter-- != 0) {
						Employee emp = new Employee();
						System.out.print("Enter Employee ID for which details has to be updated: ");
						emp.setEmpId(Integer.parseInt(br.readLine()));
						System.out.print("Enter Updated Name: ");
						emp.setName(br.readLine());
						System.out.print("Enter Updated Department: ");
						emp.setDepartment(br.readLine());
						System.out.print("Enter Updated Age: ");
						emp.setAge(Integer.parseInt(br.readLine()));
						System.out.print("Enter Updated Address: ");
						emp.setAddress(br.readLine());
						empList.add(emp);
					}
					if(EmployeeDAO.updateEmployee(empList)) {
						System.out.println("Employee details updated.");
					} else {
						System.out.println("Error occured in updating Employee details.");
					}
					break;
				}
				case 7: {
					System.out.print("Enter Employee ID for deleting: ");
					if(EmployeeDAO.deleteEmployee(Integer.parseInt(br.readLine())))
						System.out.println("Employee Details Deleted.");
					else 
						System.out.println("Error occured in deleting Employee.");
					break;
				}
				case 8: {
					List<Integer> idList = new ArrayList<Integer>();
					System.out.print("Number of Employees to be deleted: ");
					int counter = Integer.parseInt(br.readLine());
					System.out.print("Enter Employee ID's for deleting: ");
					while(counter != 0) {
						line = br.readLine();
						Scanner scanner = new Scanner(line);
						while(scanner.hasNextInt()) {
							counter--;
							idList.add(scanner.nextInt());
						}
						scanner.close();
					}
					if(EmployeeDAO.deleteEmployee(idList))
						System.out.println("Employee details Deleted.");
					else 
						System.out.println("Error occured in deleting Employees.");
					break;
				}
				case 9: {
					if(EmployeeDAO.deleteEmployee())
						System.out.println("All Employee Deleted.");
					else
						System.out.println("Error occured in deleting Employee.");
					break;
				}
				case 10: {
					System.out.println("1. Search by Employee ID.");
					System.out.println("2. Search by Employee Name.");
					System.out.println("3. Search by Employee Department.");
					System.out.println("4. Search by Employee Age.");
					System.out.println("5. Search by Employee Address.");
					System.out.print("Enter your choice:");
					int ch = Integer.parseInt(br.readLine());
					List<Employee> emList = new ArrayList<Employee>();
					if(ch == 1) {
						System.out.print("Enter Employee ID: ");
						int id = Integer.parseInt(br.readLine());
						emList = EmployeeDAO.searchEmployee(new Employee(id,null,null,0,null));
					}
					else if(ch == 2) {
						System.out.print("Enter Employee Name: ");
						//String name = br.readLine();
						emList = EmployeeDAO.searchEmployee(new Employee(0,"Naveen",null,25,null));
					} 
					else if(ch == 3) {
						System.out.print("Enter Employee Department: ");
						String department = br.readLine();
						emList = EmployeeDAO.searchEmployee(new Employee(0,null,department,0,null));
					}
					else if(ch == 4) {
						System.out.print("Enter Employee Age: ");
						int age  = Integer.parseInt(br.readLine());
						emList = EmployeeDAO.searchEmployee(new Employee(0,null,null,age,null));
					}
					else if(ch == 5) {
						System.out.print("Enter Employee Address: ");
						String address = br.readLine();
						emList = EmployeeDAO.searchEmployee(new Employee(0,null,null,0,address));
					}
					System.out.println(emList);
					break;
				}
				case 11: {
					flag = false;
					break;
				}
				default: {
					System.out.println("Invalid choice.");
				}
				}
			} while (flag == true);
		} catch (IOException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
}
