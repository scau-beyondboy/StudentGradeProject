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
		this.setTitle("��ӭ����ѧ���ɼ�����ϵͳ");
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}	
	private void init()
	{
		/*cardPanel.add(new FirstPane(cardLayout,cardPanel),"��½����");
		cardPanel.add(new SecondPane(cardLayout,cardPanel),"����༶�ɼ�");
		cardPanel.add(new ThirdPane(cardLayout,cardPanel),"�鿴�༶�ɼ�");
		cardPanel.add(new FourPane(cardLayout,cardPanel),"�޸İ༶�ɼ�");
		cardPanel.add(new FivePane(cardLayout,cardPanel),"�γ̳ɼ�����");
		cardPanel.add(new SixthPane(cardLayout,cardPanel),"�ɼ�����ͼ");*/
		this.add(cardPanel);
	}
	public static void main(String[] args)
	{
		new Main();
	}
}
