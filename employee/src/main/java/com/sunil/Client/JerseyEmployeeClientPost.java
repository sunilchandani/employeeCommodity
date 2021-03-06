package com.sunil.Client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sunil.Entities.Employee;

public class JerseyEmployeeClientPost {
	public static void main(String[] args) {
		 
		try {
			
			ClientConfig clientConfig = new DefaultClientConfig();
	        clientConfig.getFeatures().put(
	        		JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);

			Employee emp = new Employee(23,"Preeti Rawat", "Lolwa", 25, "Delhi");
			
			WebResource webResource = client
			   .resource("http://localhost:8080/employee/rest/json/employee/postEmp");
	 
			ClientResponse response = webResource.type("application/json")
			   .post(ClientResponse.class,emp);
	 
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
	 
			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
	 
		  } catch (Exception e) {
			e.printStackTrace();
		  }
	 
		}
}
