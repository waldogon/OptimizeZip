package com.brainbrass.wstest;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.brainbrass.wstest.domain.Range;
import com.brainbrass.wstest.domain.util.RangeUtil;
import com.brainbrass.wstest.service.ZipcodeService;

/**
 * Small Main program to illustrate how to use the optimize service method.
 *
 */
public class App {

	final static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {

		List<Range> input1 = Arrays.asList(new Range(94133, 94133), new Range(94200, 94299), new Range(94600, 94699));

		try {
			RangeUtil.printList("Input:", input1);
			List<Range> minimized = ZipcodeService.optimize(input1);
			RangeUtil.printList("Minimized:", minimized);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		List<Range> input2 = Arrays.asList(new Range(94133, 94133), new Range(94200, 94299), new Range(94226, 94399));

		try {
			RangeUtil.printList("Input:", input2);
			List<Range> minimized = ZipcodeService.optimize(input2);
			RangeUtil.printList("Minimized:", minimized);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		List<Range> input3 = Arrays.asList(new Range(94133, 94133), new Range(94250, 94300), new Range(94200, 94299),
				new Range(94226, 94399), new Range(94226, 94380));

		try {
			RangeUtil.printList("Input:", input3);
			List<Range> minimized = ZipcodeService.optimize(input3);
			RangeUtil.printList("Minimized:", minimized);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
