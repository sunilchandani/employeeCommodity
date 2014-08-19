package commodities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sunil.DAO.CommodityDAO;
import com.sunil.common.CommodityConstants;
import com.sunil.entities.Commodity;

public class CommodityDaoTest {
public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int choice;
		String line;
		boolean flag;
		
		try {
			do {
				flag = true;
				System.out.println("-----------------------------------------------");
				System.out.println("---------------Welcome to Commodity Menu-----------------");
				System.out.println("-----------------------------------------------");
				System.out.println("1.  Store Commodity.");
				System.out.println("2.  Store Multiple Commodities.");
				System.out.println("3.  Get All Commodities.");
				System.out.println("4.  Update Commodity Details.");
				System.out.println("5.  Delete a Commodity.");
				System.out.println("6.  Delete All Commodities.");
				System.out.println("7. Search Commodity by ID.");
				System.out.println("8. Search Commodity by Name.");
				System.out.println("9. Exit.");
				System.out.print("Enter your choice: ");
				line = br.readLine();
				choice = Integer.parseInt(line);
				System.out.println("-----------------------------------------------");
				switch (choice) {
				case 1: {
					Commodity commodity = new Commodity();
					System.out.print("Enter Commodity Name: ");
					commodity.setName(br.readLine());
					System.out.print("Enter Commodity Price: ");
					commodity.setPrice(Double.parseDouble(br.readLine()));
					System.out.println("Enter Commodity Type: ");
					System.out.println("0 => General Type (Default)");
					System.out.println("1 => Food Item");
					System.out.println("2 => Electronics Item");
					int type = Integer.parseInt(br.readLine());
					if(type == 1){
						commodity.setType(CommodityConstants.COMMODITY_TYPE_FOOD_ITEM);
						System.out.print("Enter Expiry Date for Food Commodity: (dd/mm/yyyy): ");
						commodity.setExpiryDate(sdf.parse(br.readLine()));
					} else if(type == 2) {
						commodity.setType(CommodityConstants.COMMODITY_TYPE_ELECTRINOC);
						commodity.setExpiryDate(null);
					} else {  // Default
						commodity.setType(CommodityConstants.COMMODITY_TYPE_GENERAL);
						commodity.setExpiryDate(null);
					}
					
					if (CommodityDAO.storeCommodity(commodity)) {
						System.out.println("Added Commodity details.");
					} else {
						System.out.println("Error Occured in adding commodity details.");
					}
					break;
				}
				case 2: {
					List<Commodity> commodityList = new ArrayList<Commodity>();
					System.out.print("Enter Number of commodities to be added: ");
					int counter = Integer.parseInt(br.readLine());
					while (counter-- != 0) {
						Commodity commodity = new Commodity();
						System.out.print("Enter Commodity Name: ");
						commodity.setName(br.readLine());
						System.out.print("Enter Commodity Price: ");
						commodity.setPrice(Double.parseDouble(br.readLine()));
						System.out.println("Enter Commodity Type: ");
						System.out.println("0 => General Type (Default)");
						System.out.println("1 => Food Item");
						System.out.println("2 => Electronics Item");
						int type = Integer.parseInt(br.readLine());
						if(type == 1){
							commodity.setType(CommodityConstants.COMMODITY_TYPE_FOOD_ITEM);
							System.out.print("Enter Expiry Date for Food Commodity: (dd/mm/yyyy): ");
							commodity.setExpiryDate(sdf.parse(br.readLine()));
						} else if(type == 2) {
							commodity.setType(CommodityConstants.COMMODITY_TYPE_ELECTRINOC);
							commodity.setExpiryDate(null);
						} else {  // Default
							commodity.setType(CommodityConstants.COMMODITY_TYPE_GENERAL);
							commodity.setExpiryDate(null);
						}

						commodityList.add(commodity);
					}
					if (CommodityDAO.storeCommodity(commodityList)) {
						System.out.println("Added All Commodities.");
					} else {
						System.out
								.println("Error occured in adding Commodity details.");
					}
					break;
				}
				case 3: {
					List<Commodity> commodityList = CommodityDAO.getCommodity();
					for (Commodity commodity : commodityList) {
						System.out.println(commodity.toString());
					}
					break;
				}
				case 4: {
					Commodity commodity = new Commodity();
					System.out.print("Enter Commodity ID for which details has to be updated: ");
					commodity.setId(Long.parseLong(br.readLine()));
					System.out.print("Enter Commodity Name: ");
					commodity.setName(br.readLine());
					System.out.print("Enter Commodity Price: ");
					commodity.setPrice(Double.parseDouble(br.readLine()));
					System.out.println("Enter Commodity Type: ");
					System.out.println("0 => General Type (Default)");
					System.out.println("1 => Food Item");
					System.out.println("2 => Electronics Item");
					int type = Integer.parseInt(br.readLine());
					if(type == 1){
						commodity.setType(CommodityConstants.COMMODITY_TYPE_FOOD_ITEM);
						System.out.print("Enter Expiry Date for Food Commodity: (dd/mm/yyyy): ");
						commodity.setExpiryDate(sdf.parse(br.readLine()));
					} else if(type == 2) {
						commodity.setType(CommodityConstants.COMMODITY_TYPE_ELECTRINOC);
						commodity.setExpiryDate(null);
					} else {  // Default
						commodity.setType(CommodityConstants.COMMODITY_TYPE_GENERAL);
						commodity.setExpiryDate(null);
					}

					if (CommodityDAO.updateCommodity(commodity)) {
						System.out.println("Commodity details updated.");
					} else {
						System.out.println("Error occured in updating Commodity details.");
					}
					break;
				}

				case 5: {
					System.out.print("Enter Commodity ID for deleting: ");
					if(CommodityDAO.deleteCommodity(Long.parseLong(br.readLine())))
						System.out.println("Commodity Details Deleted.");
					else 
						System.out.println("Error occured in deleting Commodity.");
					break;
				}
				case 6: {
					if(CommodityDAO.deleteCommodity())
						System.out.println("All Commodities Deleted.");
					else
						System.out.println("Error occured in deleting Commodities.");
					break;
				}
				case 7: {
					System.out.print("Enter Commodity ID for search: ");
					Commodity commodity = CommodityDAO.search(Long.parseLong(br.readLine()));
					if(commodity != null) System.out.println("Success: "+commodity.toString());
					else System.out.println("No such Commodity Exists.");
					break;
				}
				case 8: {
					System.out.print("Enter Commodity Name for search: ");
					List<Commodity> commodities = CommodityDAO.search(br.readLine());
					if(commodities != null) System.out.println("Success: "+commodities.toString());
					else System.out.println("Error in search. Please Try Again.");
					break;
				}
				case 9: {
					flag = false;
					break;
				}
				default: {
					System.out.println("Invalid choice.");
				}
				}
			} while (flag == true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
}
