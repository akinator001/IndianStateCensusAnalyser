package com.cp.indianstatecensusanalyser;

import org.junit.Assert;
import org.junit.Test;

public class IndianCensusAnalyserTest {
	private static String CSV_CENSUS_FILE  = "./IndianStateCensusData.csv";

	@Test
	public void givenNumberOfEntriesInACSVFile_ShouldReturnExactlytheSameWhileReading() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		int entries = obj.readData();
		Assert.assertEquals(29, entries);
	}
}
