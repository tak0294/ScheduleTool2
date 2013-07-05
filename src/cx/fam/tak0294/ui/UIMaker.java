package cx.fam.tak0294.ui;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UIMaker
{
	//��ʍő啝�ŕ\��.
	public static final int FP = ViewGroup.LayoutParams.FILL_PARENT;
	//�K�v�ȕ�(�R���e���c�̎��ۂ̕�)�ŕ\��.
	public static final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	
	/*
	 * RelativeLayoutParams�̐���.
	 * @param	w	��.
	 * @param	h	����
	 * @return RelativeLayout.LayoutParams
	 */
	public static RelativeLayout.LayoutParams createRelativeParam(int w, int h)
	{
		return new RelativeLayout.LayoutParams(w, h);
	}
	
	/*
	 * ���x���̐���.
	 * @param text	TextView�ɐݒ肷�镶����
	 * @param con	TextView��ǉ�����Context
	 * @return	������̐ݒ肳�ꂽTextView�I�u�W�F�N�g
	 */
	 public static TextView makeLabel(String text, Context con)
	 {
		 //TextView����.
		 TextView label = new TextView(con);
		 
		 //������ݒ�.
		 label.setText(text);
		 
		 //TextView�̕\������K�v�ŏ����ɐݒ�.
		 int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
		 label.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
		 
		 return label;
	 }
	 
	 /*
	  * �e�L�X�g�G�f�B�^�̐���.
	  * @param	text	�\�����镶����
	  * @param	w	EditText�̉���
	  * @param	h	EditText�̏c��
	  * @param con	TextView��ǉ�����Context
	  * @return	�������ꂽTextEdit�I�u�W�F�N�g
	  */
	 public static EditText makeEditor(String text, int w, int h, Context con)
	 {
		 //EditText����
		 EditText editor = new EditText(con);
		 
		 //�����e�L�X�g�̐ݒ�.
		 editor.setText(text);
		 
		 //editor�����񂹁A��񂹂ɐݒ�.
		 editor.setGravity(Gravity.LEFT|Gravity.TOP);
		 
		 //editor�̕\�����镶���񂪕\�������z�����ꍇ�X�N���[����\������悤�ݒ�.
		 editor.setHorizontallyScrolling(true);
		 
		 //editor�̉����A�c���ݒ�.
		 editor.setLayoutParams(new LayoutParams(w,h));
		 
		 return editor;
	 }
	
	 
	 
	 /*
	  * ���l�̂ݓ��͉\�ȃG�f�B�^�̐���.
	  * @param	text	�\�����镶����
	  * @param	w	EditText�̉���
	  * @param	h	EditText�̏c��
	  * @param	width	�\����
	  * @param	maxlen	���͉\�ȍő包��
	  * @param	con	TextEdit��ǉ�����Context
	  */
	 public static EditText makeNumericEditor(String text, int w, int h, int width, int maxlen, Context con)
	 {
		 //EditText����.
		 EditText editor = UIMaker.makeEditor(text, w, h, con);
		 
		 //�\�����ݒ�.
		 editor.setWidth(width);
		 
		 //�ő包���ݒ�.
		 editor.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxlen)});
		 
		 //���l�̂ݓ��͉\�ɐݒ�.
		 editor.setInputType(InputType.TYPE_CLASS_NUMBER);
		 
		 return editor;
	 }
	 
	 
	 
	 /*
	  * �`�F�b�N�{�b�N�X�̐���
	  * @param	label	�`�F�b�N�{�b�N�X�ɕ\�����镶����.
	  * @param	con		�`�F�b�N�{�b�N�X��ǉ�����Context
	  * @return CheckBox
	  */
	 public static CheckBox makeCheckBox(String label, Context con)
	 {
		 CheckBox check = new CheckBox(con);
		 check.setText(label);
		 return check;
	 }
	 
	 
	 
	 /*
	  * �{�^���̐���.
	  * @param	label	�{�^���ɕ\�����镶����.
	  * @param	con		�{�^����ǉ�����Context
	  * @return	Button
	  */
	 public static Button makeButton(String label, Context con)
	 {
		 Button button = new Button(con);
		 button.setText(label);
		 
		 return button;
	 }
}





