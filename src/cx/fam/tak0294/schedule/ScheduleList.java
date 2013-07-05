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
 * �X�P�W���[�����X�g�\���A�N�e�B�r�e�B
 */
public class ScheduleList extends Activity implements OnClickListener
{
	/*
	 * �����o.
	 */
	private ListView 	listView;
	private Button		addButton;
	private final static int REQUEST_SCHEDULE_EDITOR = 0;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	
	/*
	 * ��ʋN�����Ɏ��s.
	 */
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setLayout();
		getArrayAdapter();
	}
	
	/*
	 * ���C�A�E�g�̐ݒ�
	 */
	private void setLayout()
	{
		//�X�P�W���[���ꗗ���X�g�̍쐬.
		listView = new ListView(this);
		listView.setFocusableInTouchMode(true);
		
		//���C�A�E�g�̐���.
		LinearLayout linearLayout = new LinearLayout(this);
		setContentView(linearLayout);
		
		//���C�A�E�g��ɏc�ɔz�u.
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		
		//���C�A�E�g�ɃX�P�W���[���ꗗ��ǉ�.
		linearLayout.addView(listView);
		
		//�X�P�W���[���ǉ��{�^���𐶐�.
		addButton = UIMaker.makeButton("�X�P�W���[����ǉ�", this);
		addButton.setOnClickListener(this);
		
		//���C�A�E�g�Ƀ��C�A�E�g�p�����[�^��ݒ�.
		linearLayout.addView(addButton, new LinearLayout.LayoutParams(WC, WC));
	}
	
	/*
	 * �A�N�e�B�r�e�B�Ăяo���̌��ʂ��擾.
	 * @param	requestCode		�A�N�e�B�r�e�B���Ăяo�����ۂ̃��N�G�X�g�R�[�h
	 * @param	resultCode		�A�N�e�B�r�e�B����̖߂�l
	 * @param	intent		�A�N�e�B�r�e�B���ň����p�����Intent
	 */
	protected void onAcitivityResult(int requestCode, int resultCode, Intent intent)
	{
		if(requestCode == REQUEST_SCHEDULE_EDITOR)
		{
			
		}
	}
	
	/*
	 * ListView�̃A�b�v�f�[�g�A�_�v�^
	 * @param	ArrayList<HashMap<String, Sting>>	ListView�ɕ\������X�P�W���[���ꗗ.
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
	 * �{�^���������ɃX�P�W���[���ڍ׉�ʂ֑J�ڂ���
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
























