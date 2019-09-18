package com.revature.testables;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.utilities.MiscUtil;

public class UtilityTests {
	
	/**
	 * DF2 test
	 * df2 method is intended to format the dollar values correctly
	 * this test will check the outputs
	 */
	@Test
	public void testNoDecimal() {
		double value = 5;
		assertTrue(MiscUtil.df2(value).equals(" $5.00"));	//the method adds a space if the output isn't negative
	}														// so they will be aligned correctly with values
															// that ARE negative
	@Test
	public void testNegativeOneDecimalPlace() {
		double value = -5.2;
		assertTrue(MiscUtil.df2(value).equals("-$5.20"));
	}
	

}
