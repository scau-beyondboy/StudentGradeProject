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
			String content=(String)value;
			if(content.trim().length()!=0)
			switch (content)
			{
				case "����":
					component.setForeground(Color.green);
					component.setBackground(Color.white);
					break;
				case "����":
					component.setForeground(Color.blue);
					component.setBackground(Color.white);
					break;
				case "�е�":
					component.setForeground(Color.darkGray);
					component.setBackground(Color.white);
					break;
				case "����":
					component.setForeground(Color.orange);
					component.setBackground(Color.white);
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
