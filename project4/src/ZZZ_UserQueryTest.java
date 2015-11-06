import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;


@SuppressWarnings("deprecation")
public class ZZZ_UserQueryTest 
{
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/*
	@Test
	public void selectStringTest() throws IOException, UserQueryException
	{
		String prompt = "Please enter a value in the list: ";
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("TEST");
		try
		{
			String test = UserQuery.selectString(br, prompt, strings);
			Assert.assertEquals(test, "TEST");
		}
		catch(UserQueryException error)
		{
			Assert.assertEquals(error.getMessage(), "Ending program.");
		}		
	}
	
	@Test
	public void test()
	{
		int date1 = 19940101;
		int val1 = date1%100;
		int val2 = ((date1-val1)/100)%100;
		int val3 = (((date1-val1)/100) - val2)/100;
		System.out.println(val3 + "/" + val2 + "/" + val1);
	}
	*/
	
	@Test
	public void test2()
	{
		MonthlyData month = new MonthlyData();
		String[] construct = new String[50];
		for(int i=0; i<construct.length; i++) construct[i] = Integer.toString(i);
		DailyData.setDataFields(construct);
		month.add(new DailyData(construct));
		month.add(new DailyData(construct));
		month.add(new DailyData(construct));
		
		System.out.println(month.days.get(2));
	}

}
