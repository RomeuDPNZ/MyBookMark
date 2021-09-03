package mybookmark.datecalculations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversion {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String getString(Date date) { 
		return sdf.format(date.toString());
	}
	
	public static Date getDate(String date) throws Exception { 
		return sdf.parse(date);
	}
	
}