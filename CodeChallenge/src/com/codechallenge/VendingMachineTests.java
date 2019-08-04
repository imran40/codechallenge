package com.codechallenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class VendingMachineTests {
	static VendingMachineImpl vmi = null;
	
	@BeforeAll
	static void startVendingMachine() {
		vmi = VendingMachineImpl.getInstance();
	}

	@Test
	void testButtonPressExactChange() {
		vmi.addUserMoney(Integer.valueOf(55));
		vmi.buttonPress(Integer.valueOf(3));

		if(vmi.getUserMoney() == 0) {
			assertTrue(true);
		} else {
			vmi.addUserMoney(-vmi.getUserMoney());
			assertTrue(false);
		}
	}

	@Test
	void testButtonPressMoreChange() {
		vmi.addUserMoney(Integer.valueOf(65));
		vmi.buttonPress(Integer.valueOf(3));

		if(vmi.getUserMoney() == 0) {
			assertTrue(true);
		} else {
			vmi.addUserMoney(-vmi.getUserMoney());
			assertTrue(false);
		}
	}

	@Test
	void testButtonPressLessChange() {
		vmi.addUserMoney(Integer.valueOf(45));
		vmi.buttonPress(Integer.valueOf(3));

		vmi.addUserMoney(Integer.valueOf(20));
		vmi.buttonPress(Integer.valueOf(3));

		if(vmi.getUserMoney() == 0) {
			assertTrue(true);
		} else {
			vmi.addUserMoney(-vmi.getUserMoney());
			assertTrue(false);
		}
	}

	@Test
	void testButtonPressOutOfStock() {
		vmi.addUserMoney(Integer.valueOf(300));
		vmi.buttonPress(Integer.valueOf(4));

		vmi.addUserMoney(Integer.valueOf(305));
		vmi.buttonPress(Integer.valueOf(4));

		if(vmi.getProductsCatalog().get(4).isProductOOS()) {
			assertTrue(true);
		} else {
			vmi.addUserMoney(-vmi.getUserMoney());
			assertTrue(false);
		}
	}

	@Test
	void testAddUserMoney() {
		Integer userCurrentBalance = 0;
		vmi.addUserMoney(Integer.valueOf(15));
		userCurrentBalance = 15;
		vmi.addUserMoney(Integer.valueOf(25));
		userCurrentBalance += 25;

		if(vmi.getUserMoney() == userCurrentBalance) {
			vmi.addUserMoney(-vmi.getUserMoney());
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
}
