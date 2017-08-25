package news360.automation.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestUtils {
	public final static String getDateTimeAsFilenameStr() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		df.setTimeZone(TimeZone.getDefault());
		return (df.format(new Date()));		
	}	
}
