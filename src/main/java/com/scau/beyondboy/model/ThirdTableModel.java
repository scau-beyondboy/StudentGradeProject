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
	 //���ص�Ԫ�������
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
   //�趨ֻ�гɼ���һ�в��ܱ༭
 	@Override
 	public boolean isCellEditable(int row, int column)
 	{ 		
 		return false;
 	}
}
