package com.codechallenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine, VendingMachineHardwareFunctions {

	private Map<Integer, Product> productsCatalog = null;
	private static VendingMachineImpl vendingMachineImpl = null;
	private String filePath = "C:\\Users\\Ali\\git\\codechallenge\\CodeChallenge\\products\\";
	private String fileName = "products.txt";
	private Integer userMoneyInCents = 0;

	private VendingMachineImpl() {
		productsCatalog = new HashMap<Integer, Product>();
		loadProducts();
	}

	private void loadProducts() {
		File file = null;
		BufferedReader br = null;
		try {
			String productStr = null;
			file = new File(filePath + fileName);
			br = new BufferedReader(new FileReader(file));

			while ((productStr = br.readLine()) != null) {
				String[] productArr = productStr.split(",");
				Product product = new Product();
				product.setProductName(productArr[0]);
				product.setProductPosition(Integer.valueOf(productArr[1]));
				product.setProductPrice(Integer.valueOf(productArr[2]));
				product.setProductCount(Integer.valueOf(productArr[3]));
				product.setProductOOS(Boolean.valueOf(productArr[4]));
				productsCatalog.put(product.getProductPosition(), product);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Since there is only one Vending Machine, create only one Vending Machine
	// object
	public static VendingMachineImpl getInstance() {
		if (vendingMachineImpl == null) {
			vendingMachineImpl = new VendingMachineImpl();
		}

		return vendingMachineImpl;
	}

	// To ensure that there is only one Catalog for the given Vending Machine
	public Map<Integer, Product> getProductsCatalog() {
		return productsCatalog;
	}

	/**
	 * User Function - This is called when a user presses a button for a particular
	 * product. This is used for both price checking and purchasing.
	 */
	@Override
	public void buttonPress(Integer productPosition) {
		Product prod = null;
		if (getProductsCatalog().get(productPosition) != null) {
			prod = getProductsCatalog().get(productPosition);

			if (getUserMoney() >= prod.getProductPrice()) {

				if (!prod.isProductOOS()) {
					prod.setProductCount(prod.getProductCount() - 1);
					if (prod.getProductCount() == 0) {
						prod.setProductOOS(true);
					}
					dispenseProduct(prod.getProductPosition(), prod.getProductName());
					addUserMoney(Integer.valueOf(-prod.getProductPrice()));
					if (getUserMoney() > 0) {
						dispenseChange(getUserMoney());
						addUserMoney((-getUserMoney()));
					}
				} else {
					showMessage("Product Out Of Stock");
				}
			} else {
				showMessage(
						"InSufficient Funds, Please add " + (prod.getProductPrice() - getUserMoney()) + " cents more!");
			}
		} else {
			showMessage("Product Not Found");
		}
	}

	/**
	 * User Function - This is called when the user adds money to the machine. The
	 * cents parameter represent the value of the particular currency added to the
	 * machine. For example, when the user adds a Nickel, this function will be
	 * called with a value of 5.
	 * <p>
	 * Note: Only one coin will be added at a time. Only Nickels, Dimes, and
	 * Quarters will be added.
	 */
	@Override
	public void addUserMoney(Integer cents) {
		userMoneyInCents += cents;
	}

	public Integer getUserMoney() {
		return userMoneyInCents;
	}

}
