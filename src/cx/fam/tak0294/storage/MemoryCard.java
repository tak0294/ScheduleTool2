package cx.fam.tak0294.storage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.LinkedHashMap;
import android.content.Context;

public class MemoryCard
{
	/*
	 * �v���p�e�B�t�@�C���̓ǂݍ���.
	 * @param	context		�R���e�L�X�g
	 * @param	fileName	�ǂݍ��ރt�@�C����.
	 * @param	return	�v���p�e�B�̒l���i�[���ꂽ�}�b�v�I�u�W�F�N�g.
	 * @throws	Exception
	 */
	public static HashMap<String, String> readProperties(Context context, String fileName)
	{
		//�t�@�C���̑���.
		if(!(new File(context.getFilesDir().getPath() + "/" + fileName)).exists())
		{
			return null;
		}
		
		InputStream in = null;
		HashMap<String, String> ret = new LinkedHashMap<String, String>();
		Properties properties 		= new Properties();
		
		try
		{
			//�t�@�C������InputStream�擾.
			in = context.openFileInput(fileName);
			
			//�v���p�e�B�t�@�C���̃��[�h.
			properties.load(in);
			
			//�v���p�e�B�̃L�[�ƒl���n�b�V���}�b�v�Ɋi�[.
			Set<Object> keys = properties.keySet();
			for(Iterator<Object> it = keys.iterator();it.hasNext();)
			{
				String key 		= (String)it.next();
				String value	= (String)properties.getProperty(key);
				ret.put(key, value);
			}
			
			//InputStream�N���[�Y.
			in.close();
			return ret;
		}
		catch(Exception e)
		{
			try
			{
				if(in != null)	in.close();
			}
			catch(Exception e2)
			{}
			return null;
		}
	}
	
	
	/*
	 * �}�b�v�I�u�W�F�N�g�̃f�[�^���v���p�e�B�t�@�C���ɏo��.
	 * @param	context		�R���e�L�X�g.
	 * @param	map			�v���p�e�B�t�@�C���ɏo�͂���}�b�v�I�u�W�F�N�g.
	 * @param	fileName	�o�̓t�@�C����.
	 * @throws	Exception
	 */
	public static void writeProperties(Context context, HashMap<String, String> map, String fileName)
	{
		OutputStream out = null;
		Properties properties = new Properties();
		
		try
		{
			//�R���e�L�X�g����OutputStream�擾.
			out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			
			//�}�b�v�I�u�W�F�N�g����L�[�̒l���擾.
			Set<String> keys = map.keySet();
			
			//�v���p�e�B�ɃL�[�ƒl�ݒ�.
			for(Iterator<String> it =  keys.iterator();it.hasNext();)
			{
				String key 		= (String)it.next();
				String value	= (String)map.get(key);
				properties.setProperty(key, value);
			}
			
			//�v���p�e�B�̒l���m��.
			properties.store(out, null);
			
			//OutputStream���N���[�Y.
			out.close();
		}
		catch(Exception e)
		{
			try
			{
				if(out != null)	out.close();
			}
			catch(Exception e2){}
		}
	}
	
	
	
	/*
	 * �e�L�X�g�t�@�C���̏�������
	 * @param	context		�R���e�L�X�g
	 * @param	str			�t�@�C���ɏo�͂��镶����
	 * @param	fileName	�o�͂���t�@�C����
	 * @throws	Exception
	 */
	public static void writeFile(Context context, String str, String fileName)
	{
		writeBinaryFile(context, str.getBytes(), fileName);
	}
	
	/*
	 * �o�C�i���f�[�^�̏�������
	 * @param	context		�R���e�L�X�g
	 * @param	data		�t�@�C���ɏo�͂���o�C�g�z��.
	 * @param	fileName	�o�͂���t�@�C����
	 * @throws	Exception
	 */
	public static void writeBinaryFile(Context context, byte[] data, String fileName)
	{
		OutputStream out = null;
		try
		{
			//�t�@�C���o�̓X�g���[�����擾.
			out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			//�t�@�C���o�̓X�g���[���Ƀf�[�^���o��.
			out.write(data, 0, data.length);
			//�t�@�C���o�̓X�g���[�����N���[�Y.
		}
		catch(Exception e)
		{
			try
			{
				if(out != null)	out.close();
			}
			catch(Exception e2)
			{}
		}
	}
	
	/*
	 * �t�@�C������e�L�X�g�f�[�^�̓ǂݍ���
	 * @param	context		�R���e�L�X�g
	 * @param	fileName	�ǂݍ��ރt�@�C����
	 * @return		�ǂݍ��񂾃t�@�C���̃e�L�X�g.
	 * @throws	Exception
	 */
	public static String readFile(Context context, String fileName)
	{
		//�ǂݍ��ރt�@�C���̑��݃`�F�b�N.
		if(!(new File(context.getFilesDir().getPath() + "/" + fileName)).exists())
		{
			return "";
		}
		
		//�t�@�C�����o�C�g�z��œǂݍ���.
		byte[] w = readBinaryFile(context, fileName);
		return new String(w);
	}
	
	/*
	 * �o�C�i���t�@�C���̓ǂݍ���.
	 * @param	context		�R���e�L�X�g
	 * @param	fileName	�ǂݍ��ރt�@�C����
	 * @return	�ǂݍ��񂾃t�@�C���̃o�C�g�z��.
	 * @throws	Exception
	 */
	public static byte[] readBinaryFile(Context context, String fileName)
	{
		int size;
		byte[] w = new byte[128];
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try
		{
			//Context����C���v�b�g�X�g���[���̎擾.
			in = context.openFileInput(fileName);
			//�o�C�g�z���OutputStream���擾.
			out = new ByteArrayOutputStream();
			//�t�@�C����ǂݍ���ByateArrayOutputStream�ɒ��߂�.
			while(true)
			{
				size = in.read(w);
				if(size <= 0)	break;
				out.write(w, 0, size);
			}
			
			//InputStraem��OutputStream�̃N���[�Y����.
			in.close();
			out.close();
			//ByteArrayStream����o�C�g�z����擾���ԋp.
			return out.toByteArray();
		}
		catch(Exception e)
		{
			try
			{
				if(in!=null)	in.close();
				if(out!=null)	out.close();
			}
			catch(Exception e2)
			{}
			
			return null;
		}
	}
}




















