package cx.fam.tak0294.date;

import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;



public class CalendarUtil
{
	/*
	 * 与えられたCalendarクラスが祝日、休日かどうかを判定して返す
	 * @param	date	調査対象のCalendarクラスのインスタンス.
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
			//開始年,終了年,係数,年定数.
			array(1851,1899,3,19.8277,1983,'春分の日'),
			array(1900,1979,3,20.8357,1983,'春分の日'),
			array(1980,2099,3,20.8431,1980,'春分の日'),
			array(2100,2150,3,21.8510,1980,'春分の日'),
			array(1851,1899,9,22.2588,1983,'秋分の日'),
			array(1900,1979,9,23.2588,1983,'秋分の日'),
			array(1980,2099,9,23.2488,1980,'秋分の日'),
			array(2100,2150,9,24.2488,1980,'秋分の日')
		);
		*/
		season = new Season[]
        {
			new Season(1851, 1899, 3, 19.8277, 1983, "春分の日"),
			new Season(1900, 1979, 3, 20.8357, 1983, "春分の日"),
			new Season(1980, 2099, 3, 20.8431, 1980, "春分の日"),
			new Season(2100, 2150, 3, 21.8510, 1980, "春分の日"),
			new Season(1851, 1899, 9, 22.2588, 1983, "秋分の日"),
			new Season(1900, 1979, 9, 23.2588, 1983, "秋分の日"),
			new Season(1980, 2099, 9, 23.2488, 1980, "秋分の日"),
			new Season(2100, 2150, 9, 24.2488, 1980, "秋分の日")
        };

		holidays = new Holiday[]
		{
			new Holiday(0,0,1,1,0,0,"元日"),					// 1月1日 元日
			new Holiday(1949,1999,1,15,0,0,"成人の日"),		// 1月15日 成人の日（旧） 昭和24(1949)年?平成11(1999)年
			new Holiday(2000,0,1,0,2,1,"成人の日"),			// 1月第2月曜日 成人の日 平成12(2000)年
			new Holiday(0,0,2,11,0,0,"建国記念の日"),			// 2月11日 建国記念の日
			new Holiday(1949,1988,4,29,0,0,"天皇誕生日"),		// 4月29日 天皇誕生日（昭和） 昭和24(1949)年?昭和63(1988)年
			new Holiday(1989,2006,4,29,0,0,"みどりの日"),		// 4月29日 みどりの日 平成元(1989)年?
			new Holiday(2007,0,4,29,0,0,"昭和の日"),		// 4月29日 昭和の日 (2007)年?
			new Holiday(0,0,5,3,0,0,"憲法記念日"),			// 5月3日 憲法記念日
			new Holiday(0,2006,5,4,0,0,"国民の休日"),			// 5月4日 国民の休日
			new Holiday(2007,0,5,4,0,0,"みどりの日"),			// 5月4日 みどりの日 (2007)年?
			new Holiday(0,0,5,5,0,0,"こどもの日"),			// 5月5日 こどもの日
			new Holiday(1996,2002,7,20,0,0,"海の日"),			// 7月20日 海の日（旧） 平成8(1996)年?平成14(2002)年
			new Holiday(2003,0,7,0,3,1,"海の日"),				// 7月第3月曜日 海の日 平成15(2003)年
			new Holiday(1967,2002,9,15,0,0,"敬老の日"),		// 9月15日 敬老の日（旧） 昭和41(1967)年?平成14(2002)年
			new Holiday(2003,0,9,0,3,1,"敬老の日"),			// 9月第3月曜日 敬老の日 平成15(2003)年
			new Holiday(1966,1999,10,10,0,0,"体育の日"),		// 10月10日 体育の日（旧） 昭和41(1966)年?平成11(1999)年
			new Holiday(2003,0,10,0,2,1,"体育の日"),			// 10月第2月曜日 体育の日 平成12(2000)年
			new Holiday(0,0,11,3,0,0,"文化の日"),				// 11月3日 文化の日
			new Holiday(0,0,11,23,0,0,"勤労感謝の日"),		// 11月23日 勤労感謝の日
			new Holiday(1989,0,12,23,0,0,"天皇誕生日"),		// 12月23日 天皇誕生日（平成） 平成元(1989)年? 
			new Holiday(1959,1959,4,10,0,0,"皇太子明仁親王の結婚の儀"),	// 4月10日 皇太子明仁親王の結婚の儀 昭和34年  (1959年) 
			new Holiday(1985,1985,2,24,0,0,"昭和天皇の大喪の礼"),			// 2月24日 昭和天皇の大喪の礼 平成元年  (1985年) 
			new Holiday(1990,1990,11,12,0,0,"即位礼正殿の儀"),				// 11月12日 即位礼正殿の儀 平成 2年  (1990年) 
			new Holiday(1993,1993,6,9,0,0,"皇太子徳仁親王の結婚の儀")		// 2月24日 皇太子徳仁親王の結婚の儀 平成 5年  (1993年)
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
		//年,月範囲判定
		if($holiday[0] <= $date['year'] && ($holiday[1] == 0 || $date['year'] <= $holiday[1]) && $holiday[2] == $date['month'])
			if($holiday[3] == 0)
			{
				//週で判定
				if($holiday[5] == $date['wday'])
					if($holiday[4] == (int)(($date['day']-$date['wday'])/7)+1)
					{
						$state = $holiday[6];
						$count ++;
					}
			}
			else
			{
				//日で判定
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
						//週で判定.
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
						//日で判定.
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
		 * 	//春分秋分.
		 */
		for(int ii=0;ii<util.season.length;ii++)
		{
			Season holiday = util.season[ii];
			//年、月範囲判定.
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
		 * 国民の休日
		 */
		
		if(targetYear >= 2003 & targetMonth == 9 & targetWeekDay == Calendar.TUESDAY)
		{
			Calendar tmp = (Calendar)date.clone();
			tmp.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)+1);
			
			if(isHoliday(tmp) == true)
			{
				state = "国民の祝日";
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
					$state = '振替休日';
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
				state = "振替休日";
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
		public int weekday;		//曜日
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
