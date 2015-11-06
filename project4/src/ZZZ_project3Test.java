import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.GregorianCalendar;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ZZZ_project3Test {

	@Test
	public void observationTest() {
		//create the observations
		Observation testOb1 = new Observation(80);
		Observation testOb2 = new Observation(90);
		//should be invalid
		Observation testOb3 = new Observation();
		Observation testOb4 = new Observation();
		
		//test getValue
		Assert.assertEquals(testOb1.getValue(), 80.0, 0.001);
		Assert.assertEquals(testOb2.getValue(), 90.0, 0.001);
		Assert.assertEquals(testOb3.getValue(), -990.0, 0.001);
		
		//test getValid
		Assert.assertEquals(testOb1.getValid(), true);
		Assert.assertEquals(testOb3.getValid(), false);
		
		//test toString
		Assert.assertEquals(testOb1.toString(), "80.0");
		Assert.assertEquals(testOb3.toString(), "invalid");
		
		//test isGreaterThan and isLessThan
		Assert.assertEquals(testOb1.isGreaterThan(testOb2), false);
		Assert.assertEquals(testOb2.isGreaterThan(testOb1), true);
		Assert.assertEquals(testOb3.isGreaterThan(testOb2), false);
		Assert.assertEquals(testOb3.isGreaterThan(testOb4), false);
		
		Assert.assertEquals(testOb1.isLessThan(testOb2), true);
		Assert.assertEquals(testOb2.isLessThan(testOb1), false);
		Assert.assertEquals(testOb3.isLessThan(testOb2), false);
		Assert.assertEquals(testOb3.isLessThan(testOb4), false);
	}
	
	@Test
	public void dailyDataTest() throws IOException {
		//create a reader to test some file entries
		BufferedReader br = new BufferedReader(new FileReader ("data\\allData1994_2000.csv"));
		//create an array to store these DailyData objects
		DailyData[] list = new DailyData[1000];
		//use the first line to set the data fields since it is a static method
		DailyData.setDataFields(br.readLine().split(","));
		
		for (int i = 0; i < list.length; i++) {
			list[i] = new DailyData(br.readLine().split(","));
		}
		
		//make sure data was parsed correctly
		Assert.assertEquals(list[0].getYear(), 1994);
		Assert.assertEquals(list[0].getMonth(), 1);
		Assert.assertEquals(list[0].getDay(), 1);
		Assert.assertEquals(list[0].getStationId(), "ACME");
		
		//test getDate and toString methods
		Assert.assertEquals(list[1].getDate(), "1/1/1994 at ADAX");
		Assert.assertEquals(list[1].toString(), "Day: 1/1/1994 at ADAX");
		
		//test getMinimumStat, getMaximumStat, and getAverageStat methods
		Assert.assertEquals(list[0].getMaximumStat("doesn't matter"), list[0]);
		Assert.assertEquals(list[0].getMinimumStat("doesn't matter"), list[0]);
		Assert.assertEquals(list[0].getAverageStat("SMAX").getValue(), 44.86, 0.001);
		
		//test compareTo method
		Assert.assertEquals(list[0].compareTo(list[1]), 0);
		Assert.assertEquals(list[0].compareTo(list[999]), -1);
	}
	
	@Test
	public void dataInfoTest() {
		DataInfo info = new DataInfo("Max Temperature", "TMAX", "Farenheit", false, "Max air temperature for a set");
		
		//test the getter methods
		Assert.assertEquals(info.getVariableName(), "Max Temperature");
		Assert.assertEquals(info.getVariableId(), "TMAX");
		Assert.assertEquals(info.getUnit(), "Farenheit");
		Assert.assertEquals(info.isPositive(), false);
		Assert.assertEquals(info.isPositive(), false);
		//Assert.assertEquals(info.getDescription(), "Max air temperature for a set");															);
	}
	
	@Test
	public void stationInfoTest() {
		//create GregorianCalendar objects
		GregorianCalendar gc1 = new GregorianCalendar(1994,0,1);
		GregorianCalendar gc2 = new GregorianCalendar(2000,12,31);
		//create the StationInfo object
		StationInfo info = new StationInfo("WYNO", "Wynona", "Wynona", -90.223, 23.333,gc1,gc2);
		
		//test getters
		Assert.assertEquals("WYNO", info.getStationId());
		Assert.assertEquals("Wynona", info.getName());
		Assert.assertEquals("Wynona", info.getCity());
		Assert.assertEquals(-90.223, info.getNlat(), 0.001);
		Assert.assertEquals(23.333,  info.getElon(), 0.001);
		Assert.assertEquals(gc1, info.getDatc());
		Assert.assertEquals(gc2, info.getDatd());
		Assert.assertEquals((info.formatDate(info.getDatc())), "Jan 01, 1994");
		//Assert.assertEquals(expected, actual);
	}
}
