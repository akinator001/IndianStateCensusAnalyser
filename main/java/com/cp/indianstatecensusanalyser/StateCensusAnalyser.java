package com.cp.indianstatecensusanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;

import com.cp.indianstatecensusanalyser.StateCensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	private static final String CSV_CENSUS_CODE_FILE = "./IndianStateCode.csv";
	private static String CSV_CENSUS_FILE = "./IndianStateCensusData.csv";

	@SuppressWarnings("unchecked")
	public int readData(String DATA_FILE) throws StateCensusAnalyserException {
		int noOfEntries = 0;
		try {			
			Reader readFile = Files.newBufferedReader(Paths.get(DATA_FILE));
			CsvToBeanBuilder<IndianStateCensus> user = new CsvToBeanBuilder<IndianStateCensus>(readFile).withType(IndianStateCensus.class);
			CsvToBean user1 = user.withIgnoreLeadingWhiteSpace(true).build();
			BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));
			String line = "";
			int flag=0;
			while ((line = br.readLine()) != null) {
				if (!line.contains(","))
					throw new StateCensusAnalyserException(ExceptionType.INVALID_DELIMITER,
							"Invalid delimiter in the file ! ");
				if (flag == 0) {
					String[] headerArray = line.split(",");
					if (!(headerArray[0].equals("State") && headerArray[1].equals("Population")
							&& headerArray[2].equals("Area") && headerArray[3].equals("Density")))
						throw new StateCensusAnalyserException(ExceptionType.INVALID_HEADER,
								"Invalid headers in File!!");
					flag++;
				}
			}
			br.close();
			Iterator<IndianStateCensus> userIterator = user1.iterator();

			while (userIterator.hasNext()) {
				IndianStateCensus csvuser = userIterator.next();
				System.out.println(csvuser);
				noOfEntries++;
			}
			
		} catch (IOException e) {

			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid File Location!! ");
		}
		return noOfEntries;
				
	}	
	
	public int readCodeData(String DATA_FILE) throws StateCensusAnalyserException{
		int noOfEntries = 0;
		try {
			Reader readFile = Files.newBufferedReader(Paths.get(DATA_FILE));
			CsvToBeanBuilder<CSVStates> user = new CsvToBeanBuilder<CSVStates>(readFile);
			user.withType(CSVStates.class);
			CsvToBean user1 = user.withIgnoreLeadingWhiteSpace(true).build();
			BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));
			int flag = 0;
			String line = "";
			while ((line = br.readLine()) != null) {
				if (!line.contains(","))
					throw new StateCensusAnalyserException(ExceptionType.INVALID_DELIMITER,
							"Invalid Delimiter in the File!!");
				if (flag == 0) {
					String[] headerArray = line.split(",");
					if (!(headerArray[0].equals("State") && headerArray[1].equals("TIN")
							&& headerArray[2].equals("StateCode")))
						throw new StateCensusAnalyserException(ExceptionType.INVALID_HEADER,
								"Invalid headers in File!! ");
					flag++;

				}
			}
			br.close();
			Iterator<CSVStates> userIterator = user1.iterator();
			while (userIterator.hasNext()) {
				CSVStates csvuser = userIterator.next();
				System.out.println(csvuser);
				noOfEntries++;
			}
		} catch (IOException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_FILE_PATH,
					"Invalid File Location given!!");
		}
		return noOfEntries;
	}
	
	public static void main(String[] args) throws StateCensusAnalyserException {
		System.out.println("Welcome to indian state census analyser");
		StateCensusAnalyser object = new StateCensusAnalyser();
		System.out.println("Enter 1 to enter census data 2 for state code");
		Scanner sc=new Scanner(System.in);
		int choice = sc.nextInt();
		if(choice==1)
			object.readData(CSV_CENSUS_FILE);
		else if(choice ==2)
			object.readCodeData(CSV_CENSUS_CODE_FILE);
		else
			System.out.println("enter 1 or 2 ");
	}
}
