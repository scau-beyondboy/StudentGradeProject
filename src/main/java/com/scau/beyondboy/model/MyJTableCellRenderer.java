package main.java.com.scau.beyondboy.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyJTableCellRenderer extends DefaultTableCellRenderer
{
	//设置该Jatble列的绘制器
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
				case "优秀":
					component.setForeground(Color.green);
					component.setBackground(Color.white);
					break;
				case "良好":
					component.setForeground(Color.blue);
					component.setBackground(Color.white);
					break;
				case "中等":
					component.setForeground(Color.darkGray);
					component.setBackground(Color.white);
					break;
				case "及格":
					component.setForeground(Color.orange);
					component.setBackground(Color.white);
					break;
				case "不及格":
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
