package main.java.com.scau.beyondboy.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyJTableCellRenderer extends DefaultTableCellRenderer
{
	//���ø�Jatble�еĻ�����
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)
	{
		Component component=super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row, column);
		if(column==4)
		{
			System.out.println(value);
			String content=(String)value;
			if(content.trim().length()!=0)
			switch (content)
			{
				case "����":
					component.setForeground(Color.green);
					break;
				case "����":
					component.setForeground(Color.blue);
					break;
				case "�е�":
					component.setForeground(Color.darkGray);
					break;
				case "����":
					component.setForeground(Color.orange);
					break;
				case "������":
					component.setForeground(Color.black);
					component.setBackground(Color.red);
					break;
				default:
					break;
			}
		}
		return component;
	}
}
