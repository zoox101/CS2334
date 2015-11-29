import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;


public class StationInfoListTest2Official {
	public static DataInfoList dataInfoList;
	public static StationInfoList stationInfoList;
	
	
	@BeforeClass
	public static void setUp() throws FileNotFoundException, IOException{
		
		
		dataInfoList = new DataInfoList("data/DataTranslation.csv");
		DailyData.setDataInfoList(dataInfoList);
		
		stationInfoList = new StationInfoList("data/geoinfo.csv");
		
		stationInfoList.loadData("data/allData2001_2002.csv");
		
	}

	/** 
	 * Test Statistics
	 */
	@Test
	public void test1() {
		Observation o = stationInfoList.getAverageStat("OKMU", "WSMN");
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(1.0241328981054787, o.getValue(), 0.00001);
		
		DailyData min =  stationInfoList.getMinimumStat("OKMU", "WSMN");
		o = min.getAverageStat("WSMN");
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(0.02, o.getValue(), 0.00001);

		DailyData max =  stationInfoList.getMaximumStat("OKMU", "WSMN");
		o = max.getAverageStat("WSMN");
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(10.6, o.getValue(), 0.00001);
	}
	

	/** 
	 * Test Statistics
	 */
	@Test
	public void test2() {
		String stationId = "OKMU";
		String variableId = "CDEG";
		Observation o = stationInfoList.getAverageStat(stationId, variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(5.151525953661034, o.getValue(), 0.00001);
		
		DailyData min =  stationInfoList.getMinimumStat(stationId, variableId);
		o = min.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(0.0, o.getValue(), 0.00001);

		DailyData max =  stationInfoList.getMaximumStat(stationId, variableId);
		o = max.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(26.04, o.getValue(), 0.00001);
	}
	


	/** 
	 * Test Statistics
	 */
	@Test
	public void test3() {
		String stationId = "OKMU";
		String variableId = "PMIN";
		Observation o = stationInfoList.getAverageStat(stationId, variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(29.227977662570403, o.getValue(), 0.00001);
		
		DailyData min =  stationInfoList.getMinimumStat(stationId, variableId);
		o = min.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(28.61, o.getValue(), 0.00001);

		DailyData max =  stationInfoList.getMaximumStat(stationId, variableId);
		o = max.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(29.85, o.getValue(), 0.00001);
	}
	


	/** 
	 * Test Statistics
	 */
	@Test
	public void test4() {
		String stationId = "OKMU";
		String variableId = "9AVG";
		Observation o = stationInfoList.getAverageStat(stationId, variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(60.739264752944194, o.getValue(), 0.00001);
		
		DailyData min =  stationInfoList.getMinimumStat(stationId, variableId);
		o = min.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(17.52, o.getValue(), 0.00001);

		DailyData max =  stationInfoList.getMaximumStat(stationId, variableId);
		o = max.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(88.47, o.getValue(), 0.00001);
	}
	


	/** 
	 * Test Statistics
	 */
	@Test
	public void test5() {
		String stationId = "WEAT";
		String variableId = "VDEF";
		Observation o = stationInfoList.getAverageStat(stationId, variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(8.177825381376131, o.getValue(), 0.00001);
		
		DailyData min =  stationInfoList.getMinimumStat(stationId, variableId);
		o = min.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(0.0, o.getValue(), 0.00001);

		DailyData max =  stationInfoList.getMaximumStat(stationId, variableId);
		o = max.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(34.19, o.getValue(), 0.00001);
		Assert.assertEquals(12, max.getDay());
		Assert.assertEquals(7, max.getMonth());
		Assert.assertEquals(2001, max.getYear());
	}
	

	

	/** 
	 * Test Statistics
	 */
	@Test
	public void test6() {
		String stationId = "WEAT";
		String variableId = "CDEG";
		Observation o = stationInfoList.getAverageStat(stationId, variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(5.390259856630825, o.getValue(), 0.00001);
		
		DailyData min =  stationInfoList.getMinimumStat(stationId, variableId);
		o = min.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(0.0, o.getValue(), 0.00001);

		DailyData max =  stationInfoList.getMaximumStat(stationId, variableId);
		o = max.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(27.11, o.getValue(), 0.00001);
	}
	


	/** 
	 * Test Statistics
	 */
	@Test
	public void test7() {
		String stationId = "WEAT";
		String variableId = "PMIN";
		Observation o = stationInfoList.getAverageStat(stationId, variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(28.065546562980032, o.getValue(), 0.00001);
		
		DailyData min =  stationInfoList.getMinimumStat(stationId, variableId);
		o = min.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(27.45, o.getValue(), 0.00001);

		DailyData max =  stationInfoList.getMaximumStat(stationId, variableId);
		o = max.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(28.6, o.getValue(), 0.00001);
	}
	


	/** 
	 * Test Statistics
	 */
	@Test
	public void test8() {
		String stationId = "WEAT";
		String variableId = "MSLP";
		Observation o = stationInfoList.getAverageStat(stationId, variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(30.030447228622627, o.getValue(), 0.00001);
		
		DailyData min =  stationInfoList.getMinimumStat(stationId, variableId);
		o = min.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(29.34, o.getValue(), 0.00001);

		DailyData max =  stationInfoList.getMaximumStat(stationId, variableId);
		o = max.getAverageStat(variableId);
		Assert.assertTrue(o.getValid());
		Assert.assertEquals(30.58, o.getValue(), 0.00001);
	}
	

	


	/** 
	 * Test Statistics: invalid values
	 */
	@Test
	public void test9() {
		String stationId = "TULN";
		String variableId = "WSMX";
		Observation o = stationInfoList.getAverageStat(stationId, variableId);
		Assert.assertFalse(o.getValid());
		//Assert.assertEquals(60.739264752944194, o.getValue(), 0.00001);
		
		DailyData min =  stationInfoList.getMinimumStat(stationId, variableId);
		o = min.getAverageStat(variableId);
		Assert.assertFalse(o.getValid());
		//Assert.assertEquals(17.52, o.getValue(), 0.00001);

		DailyData max =  stationInfoList.getMaximumStat(stationId, variableId);
		o = max.getAverageStat(variableId);
		Assert.assertFalse(o.getValid());
		//Assert.assertEquals(88.47, o.getValue(), 0.00001);
	}
	


	/** 
	 * Test min/max Date
	 */
	@Test
	public void test10() {
		String stationId = "WEAT";
		String variableId = "VDEF";
		
		DailyData min =  stationInfoList.getMinimumStat(stationId, variableId);
		Assert.assertEquals(13, min.getDay());
		Assert.assertEquals(2, min.getMonth());
		Assert.assertEquals(2001, min.getYear());
		
		DailyData max =  stationInfoList.getMaximumStat(stationId, variableId);
		Assert.assertEquals(12, max.getDay());
		Assert.assertEquals(7, max.getMonth());
		Assert.assertEquals(2001, max.getYear());
	}
	
	

	/** 
	 * Test min/max Date
	 */
	@Test
	public void test11() {
		String stationId = "SHAW";
		String variableId = "TMAX";
		
		DailyData min =  stationInfoList.getMinimumStat(stationId, variableId);
		Assert.assertEquals(2, min.getDay());
		Assert.assertEquals(3, min.getMonth());
		Assert.assertEquals(2002, min.getYear());
		
		DailyData max =  stationInfoList.getMaximumStat(stationId, variableId);
		Assert.assertEquals(22, max.getDay());
		Assert.assertEquals(7, max.getMonth());
		Assert.assertEquals(2001, max.getYear());
	}
	
	
	
}
