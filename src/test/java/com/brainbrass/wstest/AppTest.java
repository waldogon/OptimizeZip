package com.brainbrass.wstest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.brainbrass.wstest.domain.Range;
import com.brainbrass.wstest.domain.util.RangeUtil;
import com.brainbrass.wstest.service.ZipcodeService;



/**
 * Unit test Zipcode Service 
 */
public class AppTest {

	final static Logger logger = Logger.getLogger(AppTest.class);

	/**
	 * Test Happy Cases first
	 */
	@Test
	public void testInput1() {

		List<Range> input = Arrays.asList(new Range(94133, 94133), new Range(94200, 94299), new Range(94600, 94699));
		List<Range> expected = Arrays.asList(new Range(94133, 94133), new Range(94200, 94299), new Range(94600, 94699));

		successfullTestHelper("First Test", input, expected);
	}

	@Test
	public void testInput2() {

		List<Range> input = Arrays.asList(new Range(94133, 94133), new Range(94200, 94299), new Range(94226, 94399));
		List<Range> expected = Arrays.asList(new Range(94133, 94133), new Range(94200, 94399));

		successfullTestHelper("Second Test", input, expected);
	}

	@Test
	public void testInput3() {

		List<Range> input = Arrays.asList(new Range(94133, 94133), new Range(94250, 94300), new Range(94200, 94299),
				new Range(94226, 94399), new Range(94226, 94380));
		List<Range> expected = Arrays.asList(new Range(94133, 94133), new Range(94200, 94399));

		successfullTestHelper("Third Test", input, expected);
	}
	
	

	
	/**
	 * Test proper error handling for empty list case
	 */
	@Test
	public void testEmptyList() {
		logger.info( "Empty list test" );

		List<Range> empty = new LinkedList<Range>();

		// Test empty list handling
		try {
			List<Range> actual = ZipcodeService.optimize(empty);
			RangeUtil.printList("Actual  :", actual);
			Assert.fail("Empty list shouldn't return.");

		} catch (Exception e) {
			logger.info("Success" );
		}

	}
	
	
	/**
	 * A method to help test the happy case flows. 
	 * 
	 * @param message
	 * @param input
	 * @param expected
	 */
	private void successfullTestHelper(String message, List<Range> input, List<Range> expected )
	{
		logger.info( message );
		
		RangeUtil.printList("Input:", input);
		RangeUtil.printList("Expected:", expected);

		try {
			List<Range> actual = ZipcodeService.optimize(input);
			RangeUtil.printList("Actual  :", actual);
			
			Assert.assertTrue( RangeUtil.compareLists( actual,  expected ));

		} catch (Exception e) {
			Assert.fail("Test failed : " + e.getMessage());
		}
		
		logger.info("Success" );
	}
	
}
