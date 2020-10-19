package com.cp.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	private static String CSV_CENSUS_FILE = "./IndianStateCensusData.csv";

	@SuppressWarnings("unchecked")
	public void readData() {
		int noOfEntries = 0;
		try {
			Reader readFile = Files.newBufferedReader(Paths.get(CSV_CENSUS_FILE));
			CsvToBean<IndianStateCensus> user = new CsvToBeanBuilder(readFile).withType(IndianStateCensus.class)
												.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<IndianStateCensus> userIterator = user.iterator();
			while (userIterator.hasNext()) {
				IndianStateCensus csvuser = userIterator.next();
				System.out.println(csvuser);
				noOfEntries++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public static void main(String[] args) {
		System.out.println("Welcome to indian state census analyser");
		StateCensusAnalyser object = new StateCensusAnalyser();
		object.readData();
	}
}
