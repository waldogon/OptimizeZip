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
	 * Helper method to compare two lists of Range(s)
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static boolean compareLists(final List<Range> list1, final List<Range> list2) {

		Comparator<Range> myComparator = new Comparator<Range>() {
			@Override
			public int compare(Range range1, Range range2) {
				return (range1.isEqual(range2)) ? 0 : -1;
			}
		};

		// iterate through the elements and compare them one by one using
		// the provided comparator.
		Iterator<Range> it1 = list1.iterator();
		Iterator<Range> it2 = list2.iterator();
		while (it1.hasNext()) {
			Range t1 = it1.next();
			Range t2 = it2.next();
			if (myComparator.compare(t1, t2) != 0) {
				// as soon as a difference is found, stop looping
				return false;
			}
		}
		return true;
	}
}
