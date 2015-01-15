package main.java.com.scau.beyondboy.view;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame
{	
	public CardLayout cardLayout=new CardLayout();
	public JPanel cardPanel=new JPanel(cardLayout)
	{
		protected void paintComponent(Graphics g)
		{
			super.paintComponents(g);
			g.drawImage(backImageIcon.getImage(), 0, 0,700, 700, this);
		};
	};
	private ImageIcon backImageIcon=new ImageIcon("src/main/resources/images/First_1.png");
	//private JLabel backJLabel=new JLabel(backImageIcon);
	//private JPanel imageJPanel;
	public Main()
	{
		init();
		this.setTitle("欢迎进入学生成绩分析系统");
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}	
	private void init()
	{
		/*cardPanel.add(new FirstPane(cardLayout,cardPanel),"登陆界面");
		cardPanel.add(new SecondPane(cardLayout,cardPanel),"输入班级成绩");
		cardPanel.add(new ThirdPane(cardLayout,cardPanel),"查看班级成绩");
		cardPanel.add(new FourPane(cardLayout,cardPanel),"修改班级成绩");
		cardPanel.add(new FivePane(cardLayout,cardPanel),"课程成绩分析");
		cardPanel.add(new SixthPane(cardLayout,cardPanel),"成绩分析图");*/
		this.add(cardPanel);
	}
	public static void main(String[] args)
	{
		new Main();
	}
}
