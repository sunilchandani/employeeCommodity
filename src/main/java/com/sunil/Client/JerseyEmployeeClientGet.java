package com.sunil.Client;

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sunil.Entities.Employee;


public class JerseyEmployeeClientGet {
	public static void main(String[] args) {
		 
		try {
			
			ClientConfig clientConfig = new DefaultClientConfig();
	        clientConfig.getFeatures().put(
	                JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);
	 
			WebResource webResource = client
			   .resource("http://localhost:8080/employee/rest/json/employee/getEmp");
	 
			ClientResponse response = webResource.type("application/json")
			   .get(ClientResponse.class);
	 
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			List<Employee> genericEntity = (List<Employee>)response.
					getEntity(new GenericType<List<Employee>>(){} );

			System.out.println(genericEntity);

		} catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
	 
		}
}
