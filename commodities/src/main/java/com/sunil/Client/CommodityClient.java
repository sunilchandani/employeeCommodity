package com.sunil.Client;

import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sunil.entities.Commodity;
import com.sunil.common.CommodityConstants;

public class CommodityClient {

	/*
	 * @Sunil Kumar
	 * utility method to get the WebResource instance for a URI.
	 *  
	 */
	private static WebResource getWebResource(String uri) {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		Client client = Client.create(clientConfig);
		return client.resource(uri);
	}

	/**
	 * @author Sunil Kumar
	 * Client method for storing commodity.
	 * 
	 * @param commodity : Commodity
	 * @return boolean 
	 */
	public static boolean storeCommodity(Commodity commodity) {
		try {
			WebResource webResource = getWebResource(CommodityConstants.COMMODITY_SERVICE_COMPLETE_URI
					+ CommodityConstants.STORE_COMMODITY_URI);
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, commodity);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @author Sunil Kumar
	 * Client Side method for fetching commodity details by commodity ID.
	 * 
	 * @param id : Long
	 * @return Commodity
	 */
	public static Commodity getCommodity(Long id) {

		Commodity output = null;
		try {
			WebResource webResource = getWebResource(CommodityConstants.COMMODITY_SERVICE_COMPLETE_URI
					+ CommodityConstants.GET_COMMODITY_URI + "/" + id);
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			System.out.println("Output from Server .... \n");
			output = response.getEntity(Commodity.class);
			if (output != null)
				System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * @author Sunil Kumar
	 * Client side method for fetching commodity details by commodity name.
	 * 
	 * @param name : String
	 * @return List<Commodity>
	 */
	public static List<Commodity> getCommodity(String name) {

		List<Commodity> outputList = null;
		try {
			WebResource webResource = getWebResource(CommodityConstants.COMMODITY_SERVICE_COMPLETE_URI
					+ CommodityConstants.GET_COMMODITY_URI + "/" + name);
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			System.out.println("Output from Server .... \n");
			outputList = (List<Commodity>) response
					.getEntity(new GenericType<List<Commodity>>() {
					});
			if (outputList != null)
				System.out.println(outputList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputList;
	}

	/**
	 * @author Sunil Kumar
	 * Client Side method for fetching all commodities.
	 * 
	 * @return List<Commodity>
	 */
	public static List<Commodity> getCommodity() {

		List<Commodity> outputList = null;
		try {
			WebResource webResource = getWebResource(CommodityConstants.COMMODITY_SERVICE_COMPLETE_URI
					+ CommodityConstants.GET_COMMODITY_URI);
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			System.out.println("Output from Server .... \n");
			outputList = (List<Commodity>) response
					.getEntity(new GenericType<List<Commodity>>() {
					});
			if (outputList != null)
				System.out.println(outputList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputList;
	}

	/**
	 * @author Sunil Kumar
	 * Client Side method for updating commodity details.
	 * 
	 * @param commodity : Commodity
	 * @return boolean
	 */
	public static boolean updateCommodity(Commodity commodity) {
		try {
			WebResource webResource = getWebResource(CommodityConstants.COMMODITY_SERVICE_COMPLETE_URI
					+ CommodityConstants.UPDATE_COMMODITY_URI);
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, commodity);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed: HTTP error code: "
						+ response.getStatus());
			}
			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @author Sunil Kumar
	 * Client Side method for deleting commodity details from database.
	 * 
	 * @param id : Long
	 * @return boolean
	 */
	public static boolean deleteCommodity(Long id) {
		try {
			WebResource webResource = getWebResource(CommodityConstants.COMMODITY_SERVICE_COMPLETE_URI
					+ CommodityConstants.DELETE_COMMODITY_URI + "/" + id);
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed: HTTP error code: "
						+ response.getStatus());
			}
			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}