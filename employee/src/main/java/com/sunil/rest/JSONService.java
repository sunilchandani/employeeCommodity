package com.sunil.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunil.DAO.EmployeeDAO;
import com.sunil.Entities.Employee;
import com.sunil.common.EmployeeConstants;

/**
 * 
 * @author sunil
 * This is the service class for Employee.
 */
@Path(EmployeeConstants.EMPLOYEE_SERVICE_START_URL)
public class JSONService {
	
	Logger logger = LoggerFactory.getLogger(JSONService.class);
	
	/**
	 * produces employee information by employee ID.
	 */
	@GET
	@Path(EmployeeConstants.GET_EMPLOYEE_BY_PARAM_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee(@PathParam("param") String id) {
		Integer empId = Integer.parseInt(id);
		Employee emp = EmployeeDAO.getEmployee(empId);
		return Response.status(HttpServletResponse.SC_OK).entity(emp).build();
	}

	/**
	 * produces list of all employees.
	 */
	@GET
	@Path(EmployeeConstants.GET_ALL_EMOPLOYEE_URL)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee() {
		List<Employee> empList = EmployeeDAO.getEmployee();
		GenericEntity<List<Employee>> genericEntity = new GenericEntity<List<Employee>>(
				empList) {};
		return Response.status(HttpServletResponse.SC_OK).entity(genericEntity).build();
	}

	/**
	 * @author sunil
	 * @param employee : {@link Employee}
	 * 
	 * Stores single employee information in the database.
	 */
	@POST
	@Path(EmployeeConstants.STORE_EMPLOYEE_URL)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response storeEmployee(Employee employee) {
		boolean isStored = EmployeeDAO.storeEmployee(employee);
		if (isStored) return Response.status(HttpServletResponse.SC_CREATED).entity("Employee Details added.")
					.build();
		else return Response.status(HttpServletResponse.SC_ACCEPTED).entity("Employee Already Exists.")
				.build();
	}

	/**
	 * @author sunil
	 * @param empList : List<Employee>
	 * 
	 * stores list of employess in the database.
	 */
	@POST
	@Path(EmployeeConstants.STORE_MULTIPLE_EMPLOYEE_URL)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response storeMultipleEmployee(List<Employee> empList) {
		boolean isStored = EmployeeDAO.storeEmployee(empList);
		if (isStored)
			return Response.status(HttpServletResponse.SC_CREATED).entity("Employee Details added.")
					.build();
		else
			return Response.status(HttpServletResponse.SC_ACCEPTED)
					.entity("Some of the Employee ID's already exists.")
					.build();
	}

	/**
	 * @author sunil
	 * @param employee : {@link Employee}
	 * 
	 * updates information for one employee in the database.
	 */
	@POST
	@Path(EmployeeConstants.UPDATE_EMPLOYEE_URL)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(Employee employee) {
		boolean isUpdated = EmployeeDAO.updateEmployee(employee);
		if (isUpdated)
			return Response.status(HttpServletResponse.SC_CREATED)
					.entity("Updated:\n" + employee.toString()).build();
		else
			return Response
					.status(HttpServletResponse.SC_ACCEPTED)
					.entity("Error in Updation: No Employee with ID="
							+ employee.getEmpId() + " exist.").build();
	}

	/**
	 * @author sunil
	 * @param empList : List<Employee>
	 * 
	 * Updates mutiple employee information in the database.
	 */
	@POST
	@Path(EmployeeConstants.UPDATE_MULTIPLE_EMPLOYEE_URL)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(List<Employee> empList) {
		boolean state;
		state = EmployeeDAO.updateEmployee(empList);
		if (state)
			return Response.status(HttpServletResponse.SC_CREATED)
					.entity("Updated:\n" + empList.toString()).build();
		else
			return Response.status(HttpServletResponse.SC_ACCEPTED).entity("Error in Updation").build();
	}

	/**
	 * @author sunil
	 * @param id : Integer
	 * 
	 * Deletes employee by employee ID from the databases.
	 */
	@POST
	@Path(EmployeeConstants.DELETE_EMPLOYEE_BY_PARAM_URL)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEmployee(@PathParam("param") String id) {
		boolean isDeleted = EmployeeDAO.deleteEmployee(Integer.parseInt(id));
		if (isDeleted)
			return Response.status(HttpServletResponse.SC_CREATED).entity("Employee details Deleted\n")
					.build();
		else
			return Response.status(HttpServletResponse.SC_ACCEPTED)
					.entity("No Employee with Employee ID:" + id + "\n")
					.build();
	}

	/**
	 * @author sunil
	 *
	 * Deletes All employee information from the database.
	 */
	@POST
	@Path(EmployeeConstants.DELETE_ALL_EMPLOYEE_URL)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEmployee() {
		boolean isDeleted = EmployeeDAO.deleteEmployee();
		if (isDeleted)
			return Response.status(HttpServletResponse.SC_CREATED)
					.entity("All employee details deleted:\n").build();
		else
			return Response.status(HttpServletResponse.SC_ACCEPTED).entity("Error in Deletion.\n").build();
	}
	
	/**
	 * @author sunil
	 * @param empId : Integer
	 * @param name : String
	 * @param department : String
	 * @param age : Integer
	 * @param address : String
	 * 
	 * It searches for employees in the database based on the given input parameters.
	 */
	@GET
	@Path(EmployeeConstants.SEARCH_EMPLOYEE_URL)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchEmployee(@DefaultValue("0") @QueryParam("empId") Integer empId, 
									@DefaultValue("") @QueryParam("name") String name,
									@DefaultValue("") @QueryParam("department") String department, 
									@DefaultValue("0") @QueryParam("age") Integer age, 
									@DefaultValue("") @QueryParam("address") String address) {
		Employee emp = new Employee(empId, name, department, age, address);
		System.out.println(emp);
		
		List<Employee> empList = EmployeeDAO.searchEmployee(emp);
		
		if(empList != null && !empList.isEmpty()) {
			return Response.status(HttpServletResponse.SC_OK)
					.entity(empList).build();
		}
		else { 
			return Response.status(HttpServletResponse.SC_NO_CONTENT)
					.entity(empList).build();
		}
		
	}

}