package main.java.com.scau.beyondboy.model;

import javax.swing.table.DefaultTableModel;

public class ThirdTableModel extends DefaultTableModel
{
	public ThirdTableModel()
	{
		super();
	}
	public ThirdTableModel(Object[][] data,Object[] columnNames)
	{
		super(data, columnNames);
	}
	public ThirdTableModel(Object[] data,int rowCount)
	{
		super(data, rowCount);
	}
	 //返回单元格的类型
     @Override
	public Class<?> getColumnClass(int column)
	{
		if(column==1&&column==4)
			return String.class;
		else if(column==0)
			return Long.class;
		else
			return Integer.class;
	}
   //设定只有成绩那一列才能编辑
 	@Override
 	public boolean isCellEditable(int row, int column)
 	{ 		
 		return false;
 	}
}
