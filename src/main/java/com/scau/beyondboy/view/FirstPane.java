package main.java.com.scau.beyondboy.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstPane extends JFrame
{	
	private JButton enterJButton=new JButton();
	private JButton queryJButton=new JButton();
	private JButton modifyJButton=new JButton();
	private JButton analyseJButton=new JButton();
	private JButton grapheJButton=new JButton();
	private ImageIcon backImageIcon=new ImageIcon("src/main/resources/images/First_1.png");
	private JLabel backJLabel=new JLabel(backImageIcon);
	private JPanel imageJPanel;
	public FirstPane()
	{
		Init();
		this.setTitle("欢迎进入学生成绩分析系统");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	//初始化界面
	private void Init()
	{
		backJLabel.setBounds(0,0,backImageIcon.getIconWidth(),backImageIcon.getIconHeight());
		this.getLayeredPane().add(backJLabel, new Integer(Integer.MIN_VALUE));
		imageJPanel=(JPanel)this.getContentPane();
		imageJPanel.setOpaque(false);
		imageJPanel.setLayout(null);
		setBackImage();
	}
	//设置背景图
	private void setBackImage()
	{
		ImageIcon backImageIcon1=new ImageIcon("src/main/resources/images/First_2.png");
		ImageIcon backImageIcon2=new ImageIcon("src/main/resources/images/First_3.png");
		ImageIcon backImageIcon3=new ImageIcon("src/main/resources/images/First_4.png");
		ImageIcon backImageIcon4=new ImageIcon("src/main/resources/images/First_5.png");
		ImageIcon backImageIcon5=new ImageIcon("src/main/resources/images/First_6.png");
		enterJButton.setIcon(backImageIcon1);
		enterJButton.setBounds(100, 20, backImageIcon1.getIconWidth(), backImageIcon1.getIconHeight());
		queryJButton.setIcon(backImageIcon2);
		queryJButton.setBounds(100, 120, backImageIcon1.getIconWidth(), backImageIcon1.getIconHeight());
		modifyJButton.setIcon(backImageIcon3);
		modifyJButton.setBounds(100, 220, backImageIcon1.getIconWidth(), backImageIcon1.getIconHeight());
		analyseJButton.setIcon(backImageIcon4);
		analyseJButton.setBounds(100, 320, backImageIcon1.getIconWidth(), backImageIcon1.getIconHeight());
		grapheJButton.setIcon(backImageIcon5);
		grapheJButton.setBounds(100, 420, backImageIcon1.getIconWidth(), backImageIcon1.getIconHeight());
		imageJPanel.add(enterJButton);
		imageJPanel.add(queryJButton);
		imageJPanel.add(modifyJButton);
		imageJPanel.add(analyseJButton);
		imageJPanel.add(grapheJButton);
	}
}
