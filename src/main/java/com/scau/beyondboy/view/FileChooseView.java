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
		filter.setDescription("导入文件(*.txt)");
		chooser.addChoosableFileFilter(filter);
		//禁止"文件类型"下拉列表中显示所有文件选项
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
					 //获得文件路径
					filePath=f.getPath();
					String className=filePath.substring(filePath.lastIndexOf("\\")+1,filePath.lastIndexOf("."));
					model.setClassName(className);
				}
			}
		});
		chooser.setPreferredSize(new Dimension(590,550));
		int result=chooser.showDialog(new JFrame(), "导入文件");
		if(result==JFileChooser.APPROVE_OPTION)
		{
			setJtableData(filePath);
		}
	}
	//自定义一个FileView类，用于为指定类型的文件或文件夹设置图标
	private class  MyFileView extends FileView
	{
		private FileFilter fileFilter;
		public MyFileView(FileFilter fileFilter)
		{
			super();
			this.fileFilter = fileFilter;
		}
		//重写该方法，为文件夹，文件设置图标
		@Override
		public Icon getIcon(File f)
		{
			if(!f.isDirectory()&&fileFilter.accept(f))
			{
				return super.getIcon(f);
			}
			else if(f.isDirectory())
			{
				//获取所有根路径
				File[] files=File.listRoots();
				for(File file:files)
				{
					//如果该路径是根路径
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
	//文件过滤器
	private class ExtensionFileFilter extends FileFilter
	{
		private String description;
		//存储文件名扩展名
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
			//如果f是文件，则返回TRUE
			if(f.isDirectory()) return true;
			else
			{
				String name=f.getName().toLowerCase();
				//如果文件名匹配，则返回true
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
			//获得一行的数据
			while((str1=bufferedReader.readLine())!=null)
			{
				//一空行分割数据 
				strs=str1.split(" +");
				Vector<Object> vector=new Vector<Object>();
				vector.add(Long.parseLong(strs[0]));
				vector.add(strs[1]);
				//添加一行数据
				model.addRow(vector);
			}
			return;
		} catch (Exception e)
		{
			System.out.println("加载班级信息成功");
			e.printStackTrace();
			return;
		}	
	}
}
