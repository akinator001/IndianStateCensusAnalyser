package com.cp.indianstatecensusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class IndianCensusAnalyserTest {
	private static final String CSV_FILE = "./Indian.csv";
	private static final String CSV_CENSUS_FILE_INVALID_DELIMITER = "./IndianStateCensusInvalidDelimeter.csv";
	private static final String CSV_CENSUS_FILE_INVALID_HEADER =  "./IndianStateCensusInvalidHeader.csv";
	private static final String CSV_STATE_CODE_FILE = "./IndianStateCode.csv";;
	private static String CSV_CENSUS_FILE  = "./IndianStateCensusData.csv";

	@Test
	public void givenNumberOfEntriesInACSVFile_ShouldReturnExactlytheSameWhileReading() throws StateCensusAnalyserException {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		int entries = obj.readData(CSV_CENSUS_FILE);
		Assert.assertEquals(29, entries);
	}

	@Test
	public void givenWrongFileLocationsthrowsCustomeException_ForInvalidFilePath() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readData(CSV_FILE);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
		
	}
	
	@Test
	public void givenInvalidDelimiter_ShouldThrowCustomException() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readData(CSV_CENSUS_FILE_INVALID_DELIMITER);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
		}
	}	
	
	@Test
	public void givenInvalidHeader_ShouldThrowCustomException() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readData(CSV_CENSUS_FILE_INVALID_HEADER);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_HEADER, e.type);
		}
	}
	
	@Test
	public void givenNumberOfEntriesInAStateCodeCSVFile_ShouldReturnExactlytheSameWhileReading()
			throws StateCensusAnalyserException {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		int entries = obj.readCodeData(CSV_STATE_CODE_FILE);
		Assert.assertEquals(37, entries);
	}	

	@Test
	public void givenWrongFileLocationOfStateCodeCVSFilethrowsCustomeException_ForInvalidFilePath() {
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			obj.readCodeData(CSV_FILE);
		} catch (StateCensusAnalyserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}		
}
