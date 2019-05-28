package com.brainbrass.wstest.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;

import com.brainbrass.wstest.domain.Range;
import com.brainbrass.wstest.domain.util.RangeUtil;

/**
 * ZipcodeService Encapsulates logic to optimize a list of Zipcode Range(s).
 * 
 * @author walter
 */
public class ZipcodeService {

	final static Logger logger = Logger.getLogger(ZipcodeService.class);

	/**
	 * Returns a new optimized list, leaving the original unmodified.
	 * 
	 * @param inputList
	 * @return
	 * @throws Exception
	 */
	public static List<Range> optimize(final List<Range> inputList) throws Exception {

		// Works with at least one
		if (inputList.size() < 1) {
			String msg = "Input must have at least one Range.";
			logger.error(msg);
			throw new Exception(msg);
		}

		// Make a list from input array
		List<Range> list = new LinkedList<Range>(inputList);
		if (logger.isDebugEnabled())
			RangeUtil.printList("Debug Unsorted:", list);

		sortList(list);
		if (logger.isDebugEnabled()) {
			RangeUtil.printList("Debug Sorted:", list);
		}

		minimizeList(list);
		if (logger.isDebugEnabled()) {
			RangeUtil.printList("Debug Minimized:", list);
		}

		return list;
	}

	private static void sortList(List<Range> list) {
		Collections.sort(list, new Comparator<Range>() {
			public int compare(Range o1, Range o2) {
				return o1.getLow() - o2.getLow();
			}
		});
	}

	// Iterate through list coalescing ranges as needed

	private static void minimizeList(List<Range> list) {

		ListIterator<Range> it = list.listIterator();

		// Skip the first item
		Range previous = it.next();

		while (it.hasNext()) {

			Range current = it.next();

			// is there overlap?
			if (previous.hasOverlap(current)) {
				previous.coalesce(current);
				// remove current from list
				it.remove();
			} else {
				// set previous to current and continue iterating
				previous = current;
			}

		}
	}

}
