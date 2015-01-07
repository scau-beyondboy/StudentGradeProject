package main.java.com.scau.beyondboy.model;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
/**
 * �Զ�����ģ��
 * @author lenovo
 */
public class MyTableModel extends DefaultTableModel
{
	private Vector<Integer> datas;
	private String className;
	public String getClassName()
	{
		return className;
	}
	public void setClassName(String className)
	{
		this.className = className;
	}
	private int i=0;	
	public MyTableModel(Object[][] data,Object[] columnNames)
	{
		super(data, columnNames);
		addTableModelListener(new MyTableModelListener());
	}
	public MyTableModel(Object[] data,int rowCount)
	{
		super(data, rowCount);
		addTableModelListener(new MyTableModelListener());
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
		
		return column==2;
	}
	//ʵ�ֱ��ģ�͵��ڲ�����
	private class MyTableModelListener implements TableModelListener
	{
		//���ñ�ע���ݺͳɼ�����
		@Override
		public void tableChanged(TableModelEvent e)
		{
			if(datas==null)
				datas=new Vector<Integer>();
			int column=e.getColumn();
			int firstrow=e.getFirstRow();						
			if(column==2)
			{
				Integer value=(Integer)getValueAt(firstrow, column);
				if(value!=null&&value>=0&&value<=100)
				{
					datas.add(value);
					i++;
					if(i==getRowCount())
					{
						int k=0;
						Integer[] copydatas=new Integer[getRowCount()];
						for(Integer s:datas)
						{
							copydatas[k++]=s;
						}						
						Arrays.sort(copydatas);
						for(int j=0;j<datas.size();j++)
						{
							int index=FindInsertIndex(copydatas, datas.size(), datas.get(j));
							setValueAt(i+1-index, j,3);
						}
					}
					Integer grades=value/10;
					switch (grades)
					{
						case 10:
						case 9:
							setValueAt("����",firstrow,4);							
							break;
						case 8:
							setValueAt("����",firstrow,4);							
							break;
						case 7:
							setValueAt("�е�",firstrow,4);							
							break;
						case 6:
							setValueAt("����",firstrow,4);							
							break;
						default:
							setValueAt("������",firstrow,4);							
							break;						
					}
				}
			}
		}
	}
	//��ѯ�ɼ��ϵĲ���λ��
	private int FindInsertIndex(Integer[] datas,int length,int data )
	{
		int high=length-1;
		int low=0;
		int index=-1;
		while(high>=low)
		{
			index=(low+high)/2;
			if(datas[index]>data)
				high=index-1;
			else
				low=index+1;
		}
		if(datas[index]<=data)
			index++;
			return index;
	}
}
