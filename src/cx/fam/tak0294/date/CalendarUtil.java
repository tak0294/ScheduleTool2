package cx.fam.tak0294.date;

import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;



public class CalendarUtil
{
	/*
	 * �^����ꂽCalendar�N���X���j���A�x�����ǂ����𔻒肵�ĕԂ�
	 * @param	date	�����Ώۂ�Calendar�N���X�̃C���X�^���X.
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
			//�J�n�N,�I���N,�W��,�N�萔.
			array(1851,1899,3,19.8277,1983,'�t���̓�'),
			array(1900,1979,3,20.8357,1983,'�t���̓�'),
			array(1980,2099,3,20.8431,1980,'�t���̓�'),
			array(2100,2150,3,21.8510,1980,'�t���̓�'),
			array(1851,1899,9,22.2588,1983,'�H���̓�'),
			array(1900,1979,9,23.2588,1983,'�H���̓�'),
			array(1980,2099,9,23.2488,1980,'�H���̓�'),
			array(2100,2150,9,24.2488,1980,'�H���̓�')
		);
		*/
		season = new Season[]
        {
			new Season(1851, 1899, 3, 19.8277, 1983, "�t���̓�"),
			new Season(1900, 1979, 3, 20.8357, 1983, "�t���̓�"),
			new Season(1980, 2099, 3, 20.8431, 1980, "�t���̓�"),
			new Season(2100, 2150, 3, 21.8510, 1980, "�t���̓�"),
			new Season(1851, 1899, 9, 22.2588, 1983, "�H���̓�"),
			new Season(1900, 1979, 9, 23.2588, 1983, "�H���̓�"),
			new Season(1980, 2099, 9, 23.2488, 1980, "�H���̓�"),
			new Season(2100, 2150, 9, 24.2488, 1980, "�H���̓�")
        };

		holidays = new Holiday[]
		{
			new Holiday(0,0,1,1,0,0,"����"),					// 1��1�� ����
			new Holiday(1949,1999,1,15,0,0,"���l�̓�"),		// 1��15�� ���l�̓��i���j ���a24(1949)�N?����11(1999)�N
			new Holiday(2000,0,1,0,2,1,"���l�̓�"),			// 1����2���j�� ���l�̓� ����12(2000)�N
			new Holiday(0,0,2,11,0,0,"�����L�O�̓�"),			// 2��11�� �����L�O�̓�
			new Holiday(1949,1988,4,29,0,0,"�V�c�a����"),		// 4��29�� �V�c�a�����i���a�j ���a24(1949)�N?���a63(1988)�N
			new Holiday(1989,2006,4,29,0,0,"�݂ǂ�̓�"),		// 4��29�� �݂ǂ�̓� ������(1989)�N?
			new Holiday(2007,0,4,29,0,0,"���a�̓�"),		// 4��29�� ���a�̓� (2007)�N?
			new Holiday(0,0,5,3,0,0,"���@�L�O��"),			// 5��3�� ���@�L�O��
			new Holiday(0,2006,5,4,0,0,"�����̋x��"),			// 5��4�� �����̋x��
			new Holiday(2007,0,5,4,0,0,"�݂ǂ�̓�"),			// 5��4�� �݂ǂ�̓� (2007)�N?
			new Holiday(0,0,5,5,0,0,"���ǂ��̓�"),			// 5��5�� ���ǂ��̓�
			new Holiday(1996,2002,7,20,0,0,"�C�̓�"),			// 7��20�� �C�̓��i���j ����8(1996)�N?����14(2002)�N
			new Holiday(2003,0,7,0,3,1,"�C�̓�"),				// 7����3���j�� �C�̓� ����15(2003)�N
			new Holiday(1967,2002,9,15,0,0,"�h�V�̓�"),		// 9��15�� �h�V�̓��i���j ���a41(1967)�N?����14(2002)�N
			new Holiday(2003,0,9,0,3,1,"�h�V�̓�"),			// 9����3���j�� �h�V�̓� ����15(2003)�N
			new Holiday(1966,1999,10,10,0,0,"�̈�̓�"),		// 10��10�� �̈�̓��i���j ���a41(1966)�N?����11(1999)�N
			new Holiday(2003,0,10,0,2,1,"�̈�̓�"),			// 10����2���j�� �̈�̓� ����12(2000)�N
			new Holiday(0,0,11,3,0,0,"�����̓�"),				// 11��3�� �����̓�
			new Holiday(0,0,11,23,0,0,"�ΘJ���ӂ̓�"),		// 11��23�� �ΘJ���ӂ̓�
			new Holiday(1989,0,12,23,0,0,"�V�c�a����"),		// 12��23�� �V�c�a�����i�����j ������(1989)�N? 
			new Holiday(1959,1959,4,10,0,0,"�c���q���m�e���̌����̋V"),	// 4��10�� �c���q���m�e���̌����̋V ���a34�N  (1959�N) 
			new Holiday(1985,1985,2,24,0,0,"���a�V�c�̑�r�̗�"),			// 2��24�� ���a�V�c�̑�r�̗� �������N  (1985�N) 
			new Holiday(1990,1990,11,12,0,0,"���ʗ琳�a�̋V"),				// 11��12�� ���ʗ琳�a�̋V ���� 2�N  (1990�N) 
			new Holiday(1993,1993,6,9,0,0,"�c���q���m�e���̌����̋V")		// 2��24�� �c���q���m�e���̌����̋V ���� 5�N  (1993�N)
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
		//�N,���͈͔���
		if($holiday[0] <= $date['year'] && ($holiday[1] == 0 || $date['year'] <= $holiday[1]) && $holiday[2] == $date['month'])
			if($holiday[3] == 0)
			{
				//�T�Ŕ���
				if($holiday[5] == $date['wday'])
					if($holiday[4] == (int)(($date['day']-$date['wday'])/7)+1)
					{
						$state = $holiday[6];
						$count ++;
					}
			}
			else
			{
				//���Ŕ���
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
						//�T�Ŕ���.
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
						//���Ŕ���.
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
		 * 	//�t���H��.
		 */
		for(int ii=0;ii<util.season.length;ii++)
		{
			Season holiday = util.season[ii];
			//�N�A���͈͔���.
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
		 * �����̋x��
		 */
		
		if(targetYear >= 2003 & targetMonth == 9 & targetWeekDay == Calendar.TUESDAY)
		{
			Calendar tmp = (Calendar)date.clone();
			tmp.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)+1);
			
			if(isHoliday(tmp) == true)
			{
				state = "�����̏j��";
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
					$state = '�U�֋x��';
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
				state = "�U�֋x��";
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
		public int weekday;		//�j��
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
