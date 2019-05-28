package com.brainbrass.wstest.domain;

/**
 * Range - Contains the low,high value pair that defines a zipcode range.
 * 
 * @author walter
 */
public class Range {
	private int low;
	private int high;

	public Range(int low_in, int high_in) {
		this.low = low_in;
		this.high = high_in;
	}

	public int getLow() {
		return low;
	}

	public int getHigh() {
		return high;
	}

	// See if the given range has overlap with the instance
	public Boolean hasOverlap(Range compare) {
		return (compare.getLow() <= this.getHigh());
	}

	// Coalesce two Ranges (combining into this)
	public void coalesce(Range combine) {
		this.low = Math.min(this.low, combine.getLow());
		this.high = Math.max(this.high, combine.getHigh());
	}

	// A quick way to see if another Range is equal
	public boolean isEqual(Range test) {
		int testLow = test.getLow();
		int testHigh = test.getHigh();

		return (this.low == testLow && this.high == testHigh);
	}
}
