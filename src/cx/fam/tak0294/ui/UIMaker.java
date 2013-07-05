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
	//画面最大幅で表示.
	public static final int FP = ViewGroup.LayoutParams.FILL_PARENT;
	//必要な幅(コンテンツの実際の幅)で表示.
	public static final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	
	/*
	 * RelativeLayoutParamsの生成.
	 * @param	w	幅.
	 * @param	h	高さ
	 * @return RelativeLayout.LayoutParams
	 */
	public static RelativeLayout.LayoutParams createRelativeParam(int w, int h)
	{
		return new RelativeLayout.LayoutParams(w, h);
	}
	
	/*
	 * ラベルの生成.
	 * @param text	TextViewに設定する文字列
	 * @param con	TextViewを追加するContext
	 * @return	文字列の設定されたTextViewオブジェクト
	 */
	 public static TextView makeLabel(String text, Context con)
	 {
		 //TextView生成.
		 TextView label = new TextView(con);
		 
		 //文字列設定.
		 label.setText(text);
		 
		 //TextViewの表示幅を必要最小限に設定.
		 int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
		 label.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
		 
		 return label;
	 }
	 
	 /*
	  * テキストエディタの生成.
	  * @param	text	表示する文字列
	  * @param	w	EditTextの横幅
	  * @param	h	EditTextの縦幅
	  * @param con	TextViewを追加するContext
	  * @return	生成されたTextEditオブジェクト
	  */
	 public static EditText makeEditor(String text, int w, int h, Context con)
	 {
		 //EditText生成
		 EditText editor = new EditText(con);
		 
		 //初期テキストの設定.
		 editor.setText(text);
		 
		 //editorを左寄せ、上寄せに設定.
		 editor.setGravity(Gravity.LEFT|Gravity.TOP);
		 
		 //editorの表示する文字列が表示幅を越えた場合スクロールを表示するよう設定.
		 editor.setHorizontallyScrolling(true);
		 
		 //editorの横幅、縦幅設定.
		 editor.setLayoutParams(new LayoutParams(w,h));
		 
		 return editor;
	 }
	
	 
	 
	 /*
	  * 数値のみ入力可能なエディタの生成.
	  * @param	text	表示する文字列
	  * @param	w	EditTextの横幅
	  * @param	h	EditTextの縦幅
	  * @param	width	表示幅
	  * @param	maxlen	入力可能な最大桁数
	  * @param	con	TextEditを追加するContext
	  */
	 public static EditText makeNumericEditor(String text, int w, int h, int width, int maxlen, Context con)
	 {
		 //EditText生成.
		 EditText editor = UIMaker.makeEditor(text, w, h, con);
		 
		 //表示幅設定.
		 editor.setWidth(width);
		 
		 //最大桁数設定.
		 editor.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxlen)});
		 
		 //数値のみ入力可能に設定.
		 editor.setInputType(InputType.TYPE_CLASS_NUMBER);
		 
		 return editor;
	 }
	 
	 
	 
	 /*
	  * チェックボックスの生成
	  * @param	label	チェックボックスに表示する文字列.
	  * @param	con		チェックボックスを追加するContext
	  * @return CheckBox
	  */
	 public static CheckBox makeCheckBox(String label, Context con)
	 {
		 CheckBox check = new CheckBox(con);
		 check.setText(label);
		 return check;
	 }
	 
	 
	 
	 /*
	  * ボタンの生成.
	  * @param	label	ボタンに表示する文字列.
	  * @param	con		ボタンを追加するContext
	  * @return	Button
	  */
	 public static Button makeButton(String label, Context con)
	 {
		 Button button = new Button(con);
		 button.setText(label);
		 
		 return button;
	 }
}





