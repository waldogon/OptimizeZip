package com.brainbrass.wstest.domain.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.brainbrass.wstest.domain.Range;

/**
 * This is a utility class with static helpers to manage Range lists.
 * 
 * @author walter
 */
public class RangeUtil {
	final static Logger logger = Logger.getLogger(RangeUtil.class);

	/**
	 * Helper to print a list of Range(s) consistently.
	 * 
	 * @param message
	 * @param list
	 */
	public static void printList(String message, final List<Range> list) {

		StringBuilder sbuilder = new StringBuilder(message);

		for (Iterator<Range> iterator = list.iterator(); iterator.hasNext();) {
			Range range = iterator.next();
			sbuilder.append("[" + range.getLow() + "," + range.getHigh() + "] ");
		}

		logger.info(sbuilder);
	}

	/**
	 * Helper method to compare two lists of Range(s).
	 * Equality means the same values are in each range, as we iterate through.
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static boolean compareLists(final List<Range> list1, final List<Range> list2) {

		// We can bail early if the two lists aren't the same size
		if (list1.size() != list2.size())
			return false;
		
		// We'll iterate over both lists, comparing each item in sequence.
		Iterator<Range> it1 = list1.iterator();
		Iterator<Range> it2 = list2.iterator();

		while (it1.hasNext()) {
			Range t1 = it1.next();
			Range t2 = it2.next();
			if (!t1.isEqual(t2)) {
				// as soon as a difference is found, stop looping
				return false;
			}
		}
		return true;
	}
	
}
