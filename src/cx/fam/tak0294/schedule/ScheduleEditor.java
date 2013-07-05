package cx.fam.tak0294.schedule;

import java.util.*;

import cx.fam.tak0294.storage.DBHelper;
import cx.fam.tak0294.storage.MemoryCard;
import cx.fam.tak0294.ui.UIMaker;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ScheduleEditor extends Activity
							  implements OnCheckedChangeListener, OnClickListener
{
	//------------------------------------------------------
	//	メンバ.
	//------------------------------------------------------
	private EditText editTitle;	//タイトル.
	private EditText editBody;		//本文.
	private EditText editYear;		//年.
	private EditText editMonth;	//月.
	private EditText editDay;		//日.
	private EditText editHour;		//時.
	private EditText editMinutes;	//分.
	private CheckBox checkAlarm;	//アラーム指定チェックボックス.
	private Button addButton;		//スケジュール追加ボタン.
	private Button deleteButton;	//スケジュール削除ボタン.
	private String sch_code;		//更新用キー.
	
	//画面最大幅で表示.
	private final int FP = ViewGroup.LayoutParams.FILL_PARENT;
	//必要な幅(コンテンツの実際の幅)で表示.
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	
	/*
	 * スクリーンクリック時のリスナー.
	 */
	public void onClick(View view)
	{
		if(view == addButton)
		{
			//MemoryCard.writeFile(this,"スケジュール登録ボタンクリック", "test.txt");
			DBHelper db = new DBHelper(this);
			HashMap<String, String> in = new HashMap<String, String>();
			in.put("sch_code", sch_code);
			in.put("sch_title", editTitle.getText().toString());
			in.put("sch_body", editBody.getText().toString());
			in.put("sch_date", editYear.getText().toString() + "/" + editMonth.getText().toString() + "/" + editDay.getText().toString() + "/");
			db.set("schedule_tbl", in);
			finish();
		}
		/*
		else if(view == getButton)
		{
			DBHelper db = new DBHelper(this);
			String code = editCode.getText().toString();
			HashMap<String, String> in = new HashMap<String, String>();
			in.put("sch_code", code);
			ArrayList<HashMap<String, String>> schedule = db.get("schedule_tbl", in);
			editTitle.setText(schedule.get(0).get("sch_title"));
			editBody.setText(schedule.get(0).get("sch_body"));
		}
		*/
		return;
	}
	
	/*
	 * チェックボックス変更時のリスナー
	 * @param	view	チェックボックスオブジェクト
	 * @param	isChecked	true:チェック	false:非チェック.
	 */
	public void onCheckedChanged(CompoundButton view, boolean isChecked)
	{
		return;
	}
	
    /*
     * 画面の作成時に呼び出される
     * @Override
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setLayout();
        //ファイルからデータ読み込み.
        //String test = MemoryCard.readFile(this, "test.txt");
        //読み込んだデータを反映.
        //editTitle.setText(test);
        
        sch_code = "";
        
        //カレンダーからの値を受け取る.
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
        	editYear.setText(extras.getString("year"));
        	editMonth.setText(extras.getString("month"));
        	editDay.setText(extras.getString("day"));
        }
        
        DBHelper db = new DBHelper(this);
		HashMap<String, String> in = new HashMap<String, String>();
		in.put("sch_date", editYear.getText().toString() + "/" + editMonth.getText().toString() + "/" + editDay.getText().toString() + "/");
		ArrayList<HashMap<String, String>> res = db.get("schedule_tbl", in);
		
		if(res.get(0) != null)
		{
			editTitle.setText(res.get(0).get("sch_title"));
			editBody.setText(res.get(0).get("sch_body"));
			sch_code = res.get(0).get("sch_code");
		}
		
		
        //DB作成.
        //db.createTableWithXml("schedule_tbl", false);
    }
    
    /*
     * レイアウトの設定
     */
    private void setLayout()
    {
    	//レイアウトの生成.
    	LinearLayout layout = new LinearLayout(this);
    	setContentView(layout);
    	
    	layout.setOrientation(LinearLayout.VERTICAL);
    	layout.setGravity(Gravity.LEFT);

    	//年月日用レイアウトの作成.
    	LinearLayout layoutDate = new LinearLayout(this);
    	layoutDate.setOrientation(LinearLayout.HORIZONTAL);
    	
    	//年テキストの生成.
    	editYear = UIMaker.makeNumericEditor("", WC, WC, 100, 4, this);
    	layoutDate.addView(editYear);
    	layoutDate.addView(UIMaker.makeLabel("年", this));
    	
    	
    	//月テキストの生成.
    	editMonth = UIMaker.makeNumericEditor("", WC, WC, 65, 2, this);
    	layoutDate.addView(editMonth);
    	layoutDate.addView(UIMaker.makeLabel("月", this));
    	
    	
    	//日テキストの生成.
    	editDay = UIMaker.makeNumericEditor("", WC, WC, 65, 2, this);
    	layoutDate.addView(editDay);
    	layoutDate.addView(UIMaker.makeLabel("日", this));
    	
    	//日付追加.
    	layout.addView(layoutDate);
    	
    	
    	
    	//タイトルラベルの生成.
    	layout.addView(UIMaker.makeLabel("タイトル", this));
   	
    	//タイトルテキストの生成.
    	editTitle = UIMaker.makeEditor("", FP, 80, this);
    	editTitle.setLines(1);
    	layout.addView(editTitle);
    	
    	//本文ラベルの生成.
    	layout.addView(UIMaker.makeLabel("本文", this));
    	
    	//本文テキストの生成.
    	editBody = UIMaker.makeEditor("", FP, 400, this);
    	layout.addView(editBody);
    	
    	
    	//ボタン用レイアウト作成.
    	LinearLayout layoutButton = new LinearLayout(this);
    	layoutButton.setOrientation(LinearLayout.HORIZONTAL);
    	
    	//追加ボタン.
    	addButton = UIMaker.makeButton("スケジュール登録", this);
    	addButton.setOnClickListener(this);
    	
    	//削除ボタン.
    	deleteButton = UIMaker.makeButton("スケジュール削除", this);
    	deleteButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// 削除ボタンのクリック時にダイアログを表示する.
				showDialog(ScheduleEditor.this, "", "スケジュール削除ボタンクリック");
			}
		});

    	layoutButton.addView(addButton);
    	layoutButton.addView(deleteButton);
    	
    	//ボタン追加.
    	layout.addView(layoutButton);
    	
    }

    /*
	 * ダイアログの表示
	 */
	public void showDialog(final Activity activity, String title, String text)
	{
		//ダイアログ生成.
		Builder ad = new AlertDialog.Builder(activity);
		
		//タイトル設定.
		ad.setTitle(title);
		
		//メッセージ設定.
		ad.setMessage(text);
		
		//OKボタンクリック.
		ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
		});
		
		//キャンセルボタンクリック.
		ad.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
		});
		
		//ダイアログ表示.
		ad.create();
		ad.show();
	}


    
    
    
    
    
    
    
    
    
    
    
}