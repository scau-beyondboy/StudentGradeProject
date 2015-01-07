package main.java.com.scau.beyondboy.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
/**
 * �Զ����Jcombox��Ԫ���������
 * @author lenovo
 */
public class MyListCellRenderer   implements ListCellRenderer
{
	private JLabel jLabel=new JLabel(" ",JLabel.LEFT);
	private Border lineBorder=BorderFactory.createLineBorder(Color.black, 1);
	private Border emtBorder=BorderFactory.createEmptyBorder(2, 2, 2, 2);
	/**
	 * �����Զ���ĵ�Ԫ��
	 */
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		Object[]  pair=(Object[])value;
		jLabel.setOpaque(true);
		jLabel.setIcon((ImageIcon)pair[0]);
		jLabel.setText(pair[1].toString());
		if(isSelected)
		{
			jLabel.setForeground(list.getSelectionForeground());
			jLabel.setBackground(list.getSelectionBackground());
		}
		else
		{
			jLabel.setForeground(list.getForeground());
			jLabel.setBackground(list.getBackground());
		}
		jLabel.setBorder(cellHasFocus? lineBorder:emtBorder);
		return jLabel;
	}	
}
