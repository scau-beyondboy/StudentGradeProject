package main.java.com.scau.beyondboy.view;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import main.java.com.scau.beyondboy.model.MyTableModel;

public class FileChooseView  
{
	private final static String PATH="src/main/resources/images/";
	private String filePath;
	private JFileChooser chooser=new JFileChooser(".");
	ExtensionFileFilter filter=new ExtensionFileFilter();
	private MyTableModel model;
	public FileChooseView(MyTableModel model)
	{
		this.model=model;
		Init();
	}
	private void Init()
	{
		filter.addExtension("txt");
		filter.setDescription("�����ļ�(*.txt)");
		chooser.addChoosableFileFilter(filter);
		//��ֹ"�ļ�����"�����б�����ʾ�����ļ�ѡ��
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileView(new MyFileView(filter));
		chooser.addPropertyChangeListener(new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				if(evt.getPropertyName()==JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
				{
					File f=(File) evt.getNewValue();
					if(f==null)
						return;
					 //����ļ�·��
					filePath=f.getPath();
					String className=filePath.substring(filePath.lastIndexOf("\\")+1,filePath.lastIndexOf("."));
					model.setClassName(className);
				}
			}
		});
		chooser.setPreferredSize(new Dimension(590,550));
		int result=chooser.showDialog(new JFrame(), "�����ļ�");
		if(result==JFileChooser.APPROVE_OPTION)
		{
			setJtableData(filePath);
		}
	}
	//�Զ���һ��FileView�࣬����Ϊָ�����͵��ļ����ļ�������ͼ��
	private class  MyFileView extends FileView
	{
		private FileFilter fileFilter;
		public MyFileView(FileFilter fileFilter)
		{
			super();
			this.fileFilter = fileFilter;
		}
		//��д�÷�����Ϊ�ļ��У��ļ�����ͼ��
		@Override
		public Icon getIcon(File f)
		{
			if(!f.isDirectory()&&fileFilter.accept(f))
			{
				return super.getIcon(f);
			}
			else if(f.isDirectory())
			{
				//��ȡ���и�·��
				File[] files=File.listRoots();
				for(File file:files)
				{
					//�����·���Ǹ�·��
					if(file.equals(f))
					{
						return new ImageIcon(PATH+"dsk.png");
					}
				}
				return new ImageIcon(PATH+"folder.png");
			}
			else
			{
				return null;
			}
		}
	}
	//�ļ�������
	private class ExtensionFileFilter extends FileFilter
	{
		private String description;
		//�洢�ļ�����չ��
		private ArrayList<String> extensions=new ArrayList<String>();
		public void addExtension(String extension)
		{
			if(!extension.startsWith("."))
			{
				extension="."+extension;
				extensions.add(extension.toLowerCase());
			}
		}
		public void setDescription(String description)
		{
			this.description = description;
		}
		@Override
		public boolean accept(File f)
		{
			//���f���ļ����򷵻�TRUE
			if(f.isDirectory()) return true;
			else
			{
				String name=f.getName().toLowerCase();
				//����ļ���ƥ�䣬�򷵻�true
				for(String extension:extensions)
				{
					if(name.endsWith(extension))
						return true;
				}
				return false;
			}
		}
		@Override
		public String getDescription()
		{
			return description;
		}		
	}
	private void setJtableData(String fileName)
	{
		if(model.getDataVector()!=null)
			model.getDataVector().removeAllElements();
		String str1="";
		String[] strs;
		try
		(

				InputStreamReader reader=new InputStreamReader(new FileInputStream(fileName));
				BufferedReader bufferedReader=new BufferedReader(reader);
		)		
		{
			//���һ�е�����
			while((str1=bufferedReader.readLine())!=null)
			{
				//һ���зָ����� 
				strs=str1.split(" +");
				Vector<Object> vector=new Vector<Object>();
				vector.add(Long.parseLong(strs[0]));
				vector.add(strs[1]);
				//���һ������
				model.addRow(vector);
			}
			return;
		} catch (Exception e)
		{
			System.out.println("���ذ༶��Ϣ�ɹ�");
			e.printStackTrace();
			return;
		}	
	}
}
