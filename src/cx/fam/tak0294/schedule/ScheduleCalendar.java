package cx.fam.tak0294.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import cx.fam.tak0294.date.CalendarUtil;
import cx.fam.tak0294.storage.DBHelper;
import cx.fam.tak0294.ui.UIMaker;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;

public class ScheduleCalendar extends Activity implements OnClickListener
{
	/*
	 * �����o.
	 */
	 private int currentYear 	= -1;
	 private int currentMonth	= -1;
	 private final static int REQUEST_SCHEDULE_CALENDAR = 1;
	 
	 private LinearLayout wrapLayout = null;
	 private Button prevButton;
	 private Button nextButton;
	 
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent intent)
	 {
		setLayout(); 
	 }
	 
	/*
	 * �{�^���̃N���b�N���X�i.
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view)
	{
		try
		{
			//�O�̌�.
			if(view == prevButton)
			{
				currentMonth--;
				setLayout();
			}
			//���̌�.
			else if(view == nextButton)
			{
				currentMonth++;
				setLayout();
			}
			else
			{
				Intent intent = new Intent(
						ScheduleCalendar.this,
						ScheduleEditor.class
				);
				
				intent.putExtra("year", Integer.toString(currentYear));
				intent.putExtra("month", Integer.toString(currentMonth+1));
				intent.putExtra("day", (String)view.getTag());
				
				startActivityForResult(intent, REQUEST_SCHEDULE_CALENDAR);
			}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		DBHelper db = new DBHelper(this);
		db.createTableWithXml("schedule_tbl", false);
		setLayout();
	}
	
	private int getLastDay(int year, int month)
	{
		Calendar date = Calendar.getInstance();
		date.set(year, month, 0);
		
		return date.get(Calendar.DATE);
	}
	
	private void setLayout()
	{
		//���C�A�E�g�̐���.
		if(wrapLayout != null)
			wrapLayout.removeAllViews();
		wrapLayout = new LinearLayout(this);
		wrapLayout.setOrientation(LinearLayout.VERTICAL);
		wrapLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		setContentView(wrapLayout);
		
    	//���t�I�u�W�F�N�g.
		Calendar now  = Calendar.getInstance();
		Calendar date = Calendar.getInstance();
		if(currentYear == -1)
			currentYear = date.get(Calendar.YEAR);
		if(currentMonth == -1)
			currentMonth = date.get(Calendar.MONTH);
		date.set(currentYear, currentMonth, 1);
    	int currentLastDay = date.getActualMaximum(Calendar.DATE);
    	
    	RelativeLayout buttonLayout = new RelativeLayout(this);
    	wrapLayout.addView(buttonLayout);
    	
    	//�߂�{�^���쐬.
    	prevButton = UIMaker.makeButton("<<", this);
    	prevButton.setOnClickListener(this);
    	    	
    	//�i�ރ{�^���쐬.
    	nextButton = UIMaker.makeButton(">>", this);
    	nextButton.setOnClickListener(this);
    	
    	//RelaiveLayout���쐬.
    	RelativeLayout.LayoutParams pPrev = UIMaker.createRelativeParam(UIMaker.WC, UIMaker.WC);
    	RelativeLayout.LayoutParams pNext = UIMaker.createRelativeParam(UIMaker.WC, UIMaker.WC);
    	
    	//�{�^���z�u�paddRule.
    	pPrev.addRule(RelativeLayout.ALIGN_PARENT_LEFT,  wrapLayout.getId());
    	pNext.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, wrapLayout.getId());
    	
    	buttonLayout.addView(prevButton, pPrev);
    	buttonLayout.addView(nextButton, pNext);
    	
    	//���x���쐬.
    	TextView currentCalendarLabel = UIMaker.makeLabel(date.get(Calendar.YEAR) + "�N" + (date.get(Calendar.MONTH)+1) + "��", this);
    	wrapLayout.addView(currentCalendarLabel);
    	
    	//���j�n�܂�łȂ��ꍇ�̓p�f�B���O����.
    	LinearLayout layout = null;
    	layout = new LinearLayout(this);
    	layout.setOrientation(LinearLayout.HORIZONTAL);
    	layout.setGravity(Gravity.LEFT);
    	
    	if(date.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
    	{
    		for(int ii=date.get(Calendar.DAY_OF_WEEK);ii>1;ii--)
    		{
    			Button btn = UIMaker.makeButton("--\n-", this);
        		btn.setLayoutParams(new LayoutParams(65, 80));
        		btn.setEnabled(false);
            	layout.addView(btn);
    		}
    	}
    	
        DBHelper db = new DBHelper(this);
		HashMap<String, String> in = new HashMap<String, String>();
    	
    	//�{�^���쐬.
    	int weekCount = date.get(Calendar.DAY_OF_WEEK);
    	for(int ii=1;ii<=currentLastDay;ii++)
    	{
    		date.set(Calendar.DAY_OF_MONTH, ii);
    		
    		if(weekCount == Calendar.SUNDAY)
    		{
    			layout = new LinearLayout(this);
    	    	layout.setOrientation(LinearLayout.HORIZONTAL);
    	    	layout.setGravity(Gravity.LEFT);
    		}
    		else if(weekCount == Calendar.SATURDAY || ii == currentLastDay)
    		{
    			wrapLayout.addView(layout);
    	
    		}
    		
    		Button btn = UIMaker.makeButton(Integer.toString(ii), this);
    		
    		if(weekCount == Calendar.SATURDAY)
    		{
    			btn.setTextColor(Color.BLUE);
    			weekCount = 0;
    		}
    		if(weekCount == Calendar.SUNDAY || CalendarUtil.isHoliday(date))
    		{
    			btn.setTextColor(Color.RED);
    		}

    		//����.
    		if(date.get(Calendar.YEAR) == now.get(Calendar.YEAR) &&
    		   date.get(Calendar.MONTH) == now.get(Calendar.MONTH) &&
    		   date.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH))
    		{
    			btn.setBackgroundColor(Color.CYAN);
    		}
    		
    		in.put("sch_date", date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.DAY_OF_MONTH) + "/");
    		ArrayList<HashMap<String, String>> res = db.get("schedule_tbl", in);
    		
    		if(res.get(0).get("sch_code") != "")
    		{
    			btn.append("\n*");
    		}
    		else
    		{
    			btn.append("\n-");
    		}
    		
    		LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(65, 80);
    		btn.setLayoutParams(l);
    		btn.setTag(Integer.toString(ii));
    		btn.setOnClickListener(this);
        	layout.addView(btn);
        	weekCount++;
    	}
    	
	}
}
