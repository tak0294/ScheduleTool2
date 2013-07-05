package cx.fam.tak0294.schedule;

import java.util.ArrayList;
import java.util.HashMap;

import cx.fam.tak0294.storage.DBHelper;
import cx.fam.tak0294.ui.UIMaker;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/*
 * スケジュールリスト表示アクティビティ
 */
public class ScheduleList extends Activity implements OnClickListener
{
	/*
	 * メンバ.
	 */
	private ListView 	listView;
	private Button		addButton;
	private final static int REQUEST_SCHEDULE_EDITOR = 0;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	
	/*
	 * 画面起動時に実行.
	 */
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setLayout();
		getArrayAdapter();
	}
	
	/*
	 * レイアウトの設定
	 */
	private void setLayout()
	{
		//スケジュール一覧リストの作成.
		listView = new ListView(this);
		listView.setFocusableInTouchMode(true);
		
		//レイアウトの生成.
		LinearLayout linearLayout = new LinearLayout(this);
		setContentView(linearLayout);
		
		//レイアウト上に縦に配置.
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		
		//レイアウトにスケジュール一覧を追加.
		linearLayout.addView(listView);
		
		//スケジュール追加ボタンを生成.
		addButton = UIMaker.makeButton("スケジュールを追加", this);
		addButton.setOnClickListener(this);
		
		//レイアウトにレイアウトパラメータを設定.
		linearLayout.addView(addButton, new LinearLayout.LayoutParams(WC, WC));
	}
	
	/*
	 * アクティビティ呼び出しの結果を取得.
	 * @param	requestCode		アクティビティを呼び出した際のリクエストコード
	 * @param	resultCode		アクティビティからの戻り値
	 * @param	intent		アクティビティ側で引き継がれるIntent
	 */
	protected void onAcitivityResult(int requestCode, int resultCode, Intent intent)
	{
		if(requestCode == REQUEST_SCHEDULE_EDITOR)
		{
			
		}
	}
	
	/*
	 * ListViewのアップデートアダプタ
	 * @param	ArrayList<HashMap<String, Sting>>	ListViewに表示するスケジュール一覧.
	 */
	private void getArrayAdapter()
	{
		DBHelper db = new DBHelper(this);
		ArrayList<HashMap<String,String>> schedule_list = db.get("schedule_tbl", new HashMap<String, String>());
		
		String[] res = new String[schedule_list.size()];
		for(int ii=0;ii<schedule_list.size();ii++)
		{
			String tmp = "[" + schedule_list.get(ii).get("sch_title") + "]";
			res[ii] = tmp;
		}
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.schedulelistlayout, res);
		listView.setAdapter(arrayAdapter);
		
	}
	
	/*
	 * ボタン押下時にスケジュール詳細画面へ遷移する
	 */
	public void onClick(View view)
	{
		try
		{
			if(view == addButton)
			{
				Intent intent = new Intent(
						ScheduleList.this,
						ScheduleEditor.class
				);
				
				startActivityForResult(intent, REQUEST_SCHEDULE_EDITOR);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
























