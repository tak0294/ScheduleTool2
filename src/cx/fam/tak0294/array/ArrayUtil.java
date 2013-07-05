package cx.fam.tak0294.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ArrayUtil
{
	/*
	 * ArrayList���w��̃X�v���b�^�ŋ�؂���������Ƃ��ĕԂ�.
	 * @param	split	�X�v���b�^������
	 * @param	array	�Ώۂ�ArrayList
	 * @retrun	String
	 */
	public static String join(String split, ArrayList<String> array)
	{
		String res = "";
		
		for(int ii=0;ii<array.size();ii++)
		{
			res += array.get(ii);
			if(ii<(array.size()-1))
				res += split;
		}
		
		return res;
	}
	
	/*
	 * Set<String>���w��̃X�v���b�^�ŋ�؂���������Ƃ��ĕԂ�.
	 * @param	split	�X�v���b�^������
	 * @param	set		�Ώۂ�Set<String>
	 * @return	String
	 */
	public static String join(String split, Set<String> set)
	{
		String res = "";
		
		for(Iterator<String> it=set.iterator();it.hasNext();)
		{
			res += it.next();
			
			if(it.hasNext())
				res += split;
		}
		
		return res;
	}
	
	/*
	 * HashMap<String, String>��GET�N�G���`����ArrayList�ɕϊ�.
	 * @param	map		�Ώۂ�HashMap<String, String>
	 * @return	ArrayList<String>
	 */
	public static ArrayList<String> mapToList(HashMap<String, String> map)
	{
		ArrayList<String> res = new ArrayList<String>();
		Set<String> keys = map.keySet();
		
		for(Iterator<String> it=keys.iterator();it.hasNext();)
		{
			String key = it.next();
			String tmp = "";
			tmp = key + "=" + map.get(key);
			res.add(tmp);
		}
		
		return res;
	}
}
