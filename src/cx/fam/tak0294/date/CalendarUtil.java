package cx.fam.tak0294.date;

import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;



public class CalendarUtil
{
	/*
	 * —^‚¦‚ç‚ê‚½CalendarƒNƒ‰ƒX‚ªj“úA‹x“ú‚©‚Ç‚¤‚©‚ğ”»’è‚µ‚Ä•Ô‚·
	 * @param	date	’²¸‘ÎÛ‚ÌCalendarƒNƒ‰ƒX‚ÌƒCƒ“ƒXƒ^ƒ“ƒX.
	 * @return	Object
	 */
	//private static CalendarUtil util = CalendarUtil.getInstance();
	private static CalendarUtil util = new CalendarUtil();
	Holiday[] holidays 	= null;
	Season[] season		= null;
		
	private CalendarUtil()
	{
		/*
		$seasons = array(
			//ŠJn”N,I—¹”N,ŒW”,”N’è”.
			array(1851,1899,3,19.8277,1983,'t•ª‚Ì“ú'),
			array(1900,1979,3,20.8357,1983,'t•ª‚Ì“ú'),
			array(1980,2099,3,20.8431,1980,'t•ª‚Ì“ú'),
			array(2100,2150,3,21.8510,1980,'t•ª‚Ì“ú'),
			array(1851,1899,9,22.2588,1983,'H•ª‚Ì“ú'),
			array(1900,1979,9,23.2588,1983,'H•ª‚Ì“ú'),
			array(1980,2099,9,23.2488,1980,'H•ª‚Ì“ú'),
			array(2100,2150,9,24.2488,1980,'H•ª‚Ì“ú')
		);
		*/
		season = new Season[]
        {
			new Season(1851, 1899, 3, 19.8277, 1983, "t•ª‚Ì“ú"),
			new Season(1900, 1979, 3, 20.8357, 1983, "t•ª‚Ì“ú"),
			new Season(1980, 2099, 3, 20.8431, 1980, "t•ª‚Ì“ú"),
			new Season(2100, 2150, 3, 21.8510, 1980, "t•ª‚Ì“ú"),
			new Season(1851, 1899, 9, 22.2588, 1983, "H•ª‚Ì“ú"),
			new Season(1900, 1979, 9, 23.2588, 1983, "H•ª‚Ì“ú"),
			new Season(1980, 2099, 9, 23.2488, 1980, "H•ª‚Ì“ú"),
			new Season(2100, 2150, 9, 24.2488, 1980, "H•ª‚Ì“ú")
        };

		holidays = new Holiday[]
		{
			new Holiday(0,0,1,1,0,0,"Œ³“ú"),					// 1Œ1“ú Œ³“ú
			new Holiday(1949,1999,1,15,0,0,"¬l‚Ì“ú"),		// 1Œ15“ú ¬l‚Ì“úi‹Œj º˜a24(1949)”N?•½¬11(1999)”N
			new Holiday(2000,0,1,0,2,1,"¬l‚Ì“ú"),			// 1Œ‘æ2Œ—j“ú ¬l‚Ì“ú •½¬12(2000)”N
			new Holiday(0,0,2,11,0,0,"Œš‘‹L”O‚Ì“ú"),			// 2Œ11“ú Œš‘‹L”O‚Ì“ú
			new Holiday(1949,1988,4,29,0,0,"“Vc’a¶“ú"),		// 4Œ29“ú “Vc’a¶“úiº˜aj º˜a24(1949)”N?º˜a63(1988)”N
			new Holiday(1989,2006,4,29,0,0,"‚İ‚Ç‚è‚Ì“ú"),		// 4Œ29“ú ‚İ‚Ç‚è‚Ì“ú •½¬Œ³(1989)”N?
			new Holiday(2007,0,4,29,0,0,"º˜a‚Ì“ú"),		// 4Œ29“ú º˜a‚Ì“ú (2007)”N?
			new Holiday(0,0,5,3,0,0,"Œ›–@‹L”O“ú"),			// 5Œ3“ú Œ›–@‹L”O“ú
			new Holiday(0,2006,5,4,0,0,"‘–¯‚Ì‹x“ú"),			// 5Œ4“ú ‘–¯‚Ì‹x“ú
			new Holiday(2007,0,5,4,0,0,"‚İ‚Ç‚è‚Ì“ú"),			// 5Œ4“ú ‚İ‚Ç‚è‚Ì“ú (2007)”N?
			new Holiday(0,0,5,5,0,0,"‚±‚Ç‚à‚Ì“ú"),			// 5Œ5“ú ‚±‚Ç‚à‚Ì“ú
			new Holiday(1996,2002,7,20,0,0,"ŠC‚Ì“ú"),			// 7Œ20“ú ŠC‚Ì“úi‹Œj •½¬8(1996)”N?•½¬14(2002)”N
			new Holiday(2003,0,7,0,3,1,"ŠC‚Ì“ú"),				// 7Œ‘æ3Œ—j“ú ŠC‚Ì“ú •½¬15(2003)”N
			new Holiday(1967,2002,9,15,0,0,"Œh˜V‚Ì“ú"),		// 9Œ15“ú Œh˜V‚Ì“úi‹Œj º˜a41(1967)”N?•½¬14(2002)”N
			new Holiday(2003,0,9,0,3,1,"Œh˜V‚Ì“ú"),			// 9Œ‘æ3Œ—j“ú Œh˜V‚Ì“ú •½¬15(2003)”N
			new Holiday(1966,1999,10,10,0,0,"‘Ìˆç‚Ì“ú"),		// 10Œ10“ú ‘Ìˆç‚Ì“úi‹Œj º˜a41(1966)”N?•½¬11(1999)”N
			new Holiday(2003,0,10,0,2,1,"‘Ìˆç‚Ì“ú"),			// 10Œ‘æ2Œ—j“ú ‘Ìˆç‚Ì“ú •½¬12(2000)”N
			new Holiday(0,0,11,3,0,0,"•¶‰»‚Ì“ú"),				// 11Œ3“ú •¶‰»‚Ì“ú
			new Holiday(0,0,11,23,0,0,"‹Î˜JŠ´Ó‚Ì“ú"),		// 11Œ23“ú ‹Î˜JŠ´Ó‚Ì“ú
			new Holiday(1989,0,12,23,0,0,"“Vc’a¶“ú"),		// 12Œ23“ú “Vc’a¶“úi•½¬j •½¬Œ³(1989)”N? 
			new Holiday(1959,1959,4,10,0,0,"c‘¾q–¾me‰¤‚ÌŒ‹¥‚Ì‹V"),	// 4Œ10“ú c‘¾q–¾me‰¤‚ÌŒ‹¥‚Ì‹V º˜a34”N  (1959”N) 
			new Holiday(1985,1985,2,24,0,0,"º˜a“Vc‚Ì‘å‘r‚Ì—ç"),			// 2Œ24“ú º˜a“Vc‚Ì‘å‘r‚Ì—ç •½¬Œ³”N  (1985”N) 
			new Holiday(1990,1990,11,12,0,0,"‘¦ˆÊ—ç³“a‚Ì‹V"),				// 11Œ12“ú ‘¦ˆÊ—ç³“a‚Ì‹V •½¬ 2”N  (1990”N) 
			new Holiday(1993,1993,6,9,0,0,"c‘¾q“¿me‰¤‚ÌŒ‹¥‚Ì‹V")		// 2Œ24“ú c‘¾q“¿me‰¤‚ÌŒ‹¥‚Ì‹V •½¬ 5”N  (1993”N)
		};
	}
	
	public static boolean isHoliday(Calendar date)
	{
		int targetYear 		= date.get(Calendar.YEAR);
		int targetMonth		= date.get(Calendar.MONTH)+1;
		int targetDay		= date.get(Calendar.DAY_OF_MONTH);
		int targetWeekDay	= date.get(Calendar.DAY_OF_WEEK);
		
		String state = "";
		int count = 0;
		/*
		 * foreach($holidays as $holiday)
		//”N,Œ”ÍˆÍ”»’è
		if($holiday[0] <= $date['year'] && ($holiday[1] == 0 || $date['year'] <= $holiday[1]) && $holiday[2] == $date['month'])
			if($holiday[3] == 0)
			{
				//T‚Å”»’è
				if($holiday[5] == $date['wday'])
					if($holiday[4] == (int)(($date['day']-$date['wday'])/7)+1)
					{
						$state = $holiday[6];
						$count ++;
					}
			}
			else
			{
				//“ú‚Å”»’è
				if($holiday[3] == $date['day'])
				{
					$state = $holiday[6];
					$count ++;
				}
			}
		 */
		if(util.holidays != null)
		{
			for(int ii=0;ii<util.holidays.length;ii++)
			{
				Holiday holiday = util.holidays[ii];
				if(holiday.startYear <= targetYear && (holiday.endYear == 0 || targetYear <= holiday.endYear) &&
				   holiday.month == targetMonth)
				{
					if(holiday.day == 0)
					{
						//T‚Å”»’è.
						if((holiday.weekday+1) == targetWeekDay)
						{
							if(holiday.week == (int)((targetDay - (targetWeekDay-1))/7)+1)
							{
								state = holiday.name;
								count++;
							}
						}
					}
					else
					{
						//“ú‚Å”»’è.
						if(holiday.day == targetDay)
						{
							state = holiday.name;
							count++;
						}
					}
				}
			}
		}	

		/*
		 * 	//t•ªH•ª.
		 */
		for(int ii=0;ii<util.season.length;ii++)
		{
			Season holiday = util.season[ii];
			//”NAŒ”ÍˆÍ”»’è.
			if(holiday.startYear < targetYear && targetYear <= holiday.endYear && holiday.month == targetMonth)
			{
				int dayx = (int)(holiday.bias + 0.24194*(int)(targetYear - 1980) - (int)((targetYear - holiday.yearst)/4));
				if(dayx == targetDay)
				{
					state = holiday.name;
					count++;
				}
			}
		}
		
		/*
		 * ‘–¯‚Ì‹x“ú
		 */
		
		if(targetYear >= 2003 & targetMonth == 9 & targetWeekDay == Calendar.TUESDAY)
		{
			Calendar tmp = (Calendar)date.clone();
			tmp.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)+1);
			
			if(isHoliday(tmp) == true)
			{
				state = "‘–¯‚Ìj“ú";
				count++;
			}
		}
		
		
		/*
		 * if($date['wday']!=0 && $count==0 && $compensatory)
			{
				$change_holyday = true;
				for($yi=1;$yi<=$date['wday'];$yi++)
				{
					if(check_festival_original("{$datestr} -{$yi}day", false ,$tempstate)==TRUE)
					{
					}
					else
					{
						$change_holyday = false;
						break;
					}
				}
				if($change_holyday)
				{
					$state = 'U‘Ö‹x“ú';
					$count++;
				}
			}
		 */
		/*
		if(targetWeekDay != 0 && count == 0)
		{
			boolean change_holiday = true;
			Calendar tmp = (Calendar)date.clone();
			for(int yi=1;yi<=targetWeekDay;yi++)
			{
				tmp.set(Calendar.DAY_OF_MONTH, tmp.get(Calendar.DAY_OF_MONTH)-yi);
				if(isHoliday(tmp) != true)
				{
					change_holiday = false;
					break;
				}
			}
			
			if(change_holiday)
			{
				state = "U‘Ö‹x“ú";
				count++;
			}
		}
		*/
		return (count>0);
	}
	
	private class Season
	{
		public int startYear;
		public int endYear;
		public int month;
		public double bias;
		public int yearst;
		public String name;
		
		public Season(int startYear, int endYear, int month, double bias, int yearst, String name)
		{
			this.startYear = startYear;
			this.endYear   = endYear;
			this.month     = month;
			this.bias		= bias;
			this.yearst		= yearst;
			this.name		= name;
		}
	}
	
	private class Holiday
	{
		public int startYear;
		public int endYear;
		public int month;
		public int day;
		public int week;			
		public int weekday;		//—j“ú
		public String name;
		
		public Holiday(int startYear, int endYear, int month, int day, int week, int weekday, String name)
		{
			this.startYear = startYear;
			this.endYear = endYear;
			this.month = month;
			this.day = day;
			this.week = week;
			this.weekday = weekday;
			this.name = name;
		}
		
		
	}
}
