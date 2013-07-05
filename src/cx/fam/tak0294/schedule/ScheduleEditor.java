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
	//	�����o.
	//------------------------------------------------------
	private EditText editTitle;	//�^�C�g��.
	private EditText editBody;		//�{��.
	private EditText editYear;		//�N.
	private EditText editMonth;	//��.
	private EditText editDay;		//��.
	private EditText editHour;		//��.
	private EditText editMinutes;	//��.
	private CheckBox checkAlarm;	//�A���[���w��`�F�b�N�{�b�N�X.
	private Button addButton;		//�X�P�W���[���ǉ��{�^��.
	private Button deleteButton;	//�X�P�W���[���폜�{�^��.
	private String sch_code;		//�X�V�p�L�[.
	
	//��ʍő啝�ŕ\��.
	private final int FP = ViewGroup.LayoutParams.FILL_PARENT;
	//�K�v�ȕ�(�R���e���c�̎��ۂ̕�)�ŕ\��.
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	
	/*
	 * �X�N���[���N���b�N���̃��X�i�[.
	 */
	public void onClick(View view)
	{
		if(view == addButton)
		{
			//MemoryCard.writeFile(this,"�X�P�W���[���o�^�{�^���N���b�N", "test.txt");
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
	 * �`�F�b�N�{�b�N�X�ύX���̃��X�i�[
	 * @param	view	�`�F�b�N�{�b�N�X�I�u�W�F�N�g
	 * @param	isChecked	true:�`�F�b�N	false:��`�F�b�N.
	 */
	public void onCheckedChanged(CompoundButton view, boolean isChecked)
	{
		return;
	}
	
    /*
     * ��ʂ̍쐬���ɌĂяo�����
     * @Override
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setLayout();
        //�t�@�C������f�[�^�ǂݍ���.
        //String test = MemoryCard.readFile(this, "test.txt");
        //�ǂݍ��񂾃f�[�^�𔽉f.
        //editTitle.setText(test);
        
        sch_code = "";
        
        //�J�����_�[����̒l���󂯎��.
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
		
		
        //DB�쐬.
        //db.createTableWithXml("schedule_tbl", false);
    }
    
    /*
     * ���C�A�E�g�̐ݒ�
     */
    private void setLayout()
    {
    	//���C�A�E�g�̐���.
    	LinearLayout layout = new LinearLayout(this);
    	setContentView(layout);
    	
    	layout.setOrientation(LinearLayout.VERTICAL);
    	layout.setGravity(Gravity.LEFT);

    	//�N�����p���C�A�E�g�̍쐬.
    	LinearLayout layoutDate = new LinearLayout(this);
    	layoutDate.setOrientation(LinearLayout.HORIZONTAL);
    	
    	//�N�e�L�X�g�̐���.
    	editYear = UIMaker.makeNumericEditor("", WC, WC, 100, 4, this);
    	layoutDate.addView(editYear);
    	layoutDate.addView(UIMaker.makeLabel("�N", this));
    	
    	
    	//���e�L�X�g�̐���.
    	editMonth = UIMaker.makeNumericEditor("", WC, WC, 65, 2, this);
    	layoutDate.addView(editMonth);
    	layoutDate.addView(UIMaker.makeLabel("��", this));
    	
    	
    	//���e�L�X�g�̐���.
    	editDay = UIMaker.makeNumericEditor("", WC, WC, 65, 2, this);
    	layoutDate.addView(editDay);
    	layoutDate.addView(UIMaker.makeLabel("��", this));
    	
    	//���t�ǉ�.
    	layout.addView(layoutDate);
    	
    	
    	
    	//�^�C�g�����x���̐���.
    	layout.addView(UIMaker.makeLabel("�^�C�g��", this));
   	
    	//�^�C�g���e�L�X�g�̐���.
    	editTitle = UIMaker.makeEditor("", FP, 80, this);
    	editTitle.setLines(1);
    	layout.addView(editTitle);
    	
    	//�{�����x���̐���.
    	layout.addView(UIMaker.makeLabel("�{��", this));
    	
    	//�{���e�L�X�g�̐���.
    	editBody = UIMaker.makeEditor("", FP, 400, this);
    	layout.addView(editBody);
    	
    	
    	//�{�^���p���C�A�E�g�쐬.
    	LinearLayout layoutButton = new LinearLayout(this);
    	layoutButton.setOrientation(LinearLayout.HORIZONTAL);
    	
    	//�ǉ��{�^��.
    	addButton = UIMaker.makeButton("�X�P�W���[���o�^", this);
    	addButton.setOnClickListener(this);
    	
    	//�폜�{�^��.
    	deleteButton = UIMaker.makeButton("�X�P�W���[���폜", this);
    	deleteButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// �폜�{�^���̃N���b�N���Ƀ_�C�A���O��\������.
				showDialog(ScheduleEditor.this, "", "�X�P�W���[���폜�{�^���N���b�N");
			}
		});

    	layoutButton.addView(addButton);
    	layoutButton.addView(deleteButton);
    	
    	//�{�^���ǉ�.
    	layout.addView(layoutButton);
    	
    }

    /*
	 * �_�C�A���O�̕\��
	 */
	public void showDialog(final Activity activity, String title, String text)
	{
		//�_�C�A���O����.
		Builder ad = new AlertDialog.Builder(activity);
		
		//�^�C�g���ݒ�.
		ad.setTitle(title);
		
		//���b�Z�[�W�ݒ�.
		ad.setMessage(text);
		
		//OK�{�^���N���b�N.
		ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				
			}
		});
		
		//�L�����Z���{�^���N���b�N.
		ad.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				
			}
		});
		
		//�_�C�A���O�\��.
		ad.create();
		ad.show();
	}


    
    
    
    
    
    
    
    
    
    
    
}