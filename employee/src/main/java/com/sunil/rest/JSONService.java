package com.sunil.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sunil.DAO.EmployeeDAO;
import com.sunil.Entities.Employee;

@Path("/json/employee")
public class JSONService {
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeInJSON() {
		
		Employee emp = new Employee(1,"Sunil","BCA",23,"Alwar");
		return Response.status(200).entity(emp).build();
	
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployeeInJSON(Employee employee) {
		return Response.status(201).entity(employee).build(); 
	}
	
	@GET
	@Path("/getEmp")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getEmployee() {
		List<Employee> empList = EmployeeDAO.getEmployee();
		String str = empList.toString();
		return Response.status(201).entity(str).build();
	}

}
