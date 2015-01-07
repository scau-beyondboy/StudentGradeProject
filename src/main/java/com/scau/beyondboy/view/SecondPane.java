package main.java.com.scau.beyondboy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import main.java.com.scau.beyondboy.model.MyListCellRenderer;
import main.java.com.scau.beyondboy.model.MyTableModel;

public class SecondPane  extends JFrame
{
    private final static  String PATH1="src/main/resources/images/";
    private final static  String PATH2="src/main/resources/data/";
    private final static  String PATH3="src/main/resources/storage";
    private String courseName="面向对象设计";
   /* private ImageIcon backImageIcon=new ImageIcon(PATH1+"Second_5.png");
    private JLabel backJLabel=new JLabel(backImageIcon);*/
    private JPanel imageJPanel;
    private JPanel jPanel1=new JPanel();
    MyListCellRenderer renderer=new MyListCellRenderer();
    private ImageIcon imageIcon1=new ImageIcon(PATH1+"Second_1.png");
	private JLabel courseJLabel=new JLabel(imageIcon1);
	private DefaultComboBoxModel model1=new DefaultComboBoxModel();
	private JComboBox courseBox=new JComboBox(model1);
	private String[] columnNames={"学号","姓名","成绩","班级名次","备注"};
	private MyTableModel model=new MyTableModel(columnNames,0);
	private MyJTable jTable=new MyJTable(model);
	private JPanel jPanel2=new JPanel();
	private JButton classJButton=new JButton();
	private JButton saveJButton=new JButton();
	private JButton exitJButton=new JButton();
	/*private ImageIcon imageIcon2=new ImageIcon(path1+"Second_2.png");
	private JLabel scoreJLabel=new JLabel(imageIcon2);
	private JComboBox<String> scoreBox=new JComboBox<String>();*/
	public SecondPane()
	{	
		Init();
		this.setTitle("输入学生成绩");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	//初始化界面
	private void Init()
	{
		imageJPanel=(JPanel)this.getContentPane();		
		imageJPanel.setLayout(new BorderLayout());
		jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));		
		jPanel1.add(courseJLabel);
		jPanel1.setBackground(Color.LIGHT_GRAY);
		setComBox();
		courseBox.setSize(100,courseJLabel.getHeight());
		jPanel1.add(courseBox);
		setButtonBackImage();
		jPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 40, 0));
		jPanel2.add(classJButton);
		jPanel2.add(saveJButton);
		jPanel2.add(exitJButton);
		jPanel2.setBackground(Color.LIGHT_GRAY);
		imageJPanel.add(jPanel1,BorderLayout.NORTH);
		imageJPanel.add(new JScrollPane(jTable));
		imageJPanel.add(jPanel2,BorderLayout.SOUTH);
		imageJPanel.setOpaque(false);
		setButtonListener();
	}
	//设置按钮监听
	private void setButtonListener()
	{
		classJButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new FileChooseView(model);
			}
		});
		saveJButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(new File(new File(PATH3).getAbsolutePath(),model.getClassName()+"-"+courseName+".dat")));
					out.writeObject(model.getDataVector());
					out.writeObject(getColumnNames());
					out.close();
				}
				catch (Exception e1)
				{
					System.out.println("保存文件异常");
					e1.printStackTrace();
				}
			}
		});
		exitJButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
		courseBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Object[] itemContent=(Object[])courseBox.getSelectedItem();
				 courseName=(String)itemContent[1];
			}
		});
	}
	private Vector getColumnNames()
	{
		Vector<String> columnNames=new Vector<String>();
		for(int i=0;i<jTable.getColumnCount();i++)
			columnNames.add(jTable.getColumnName(i));
		return columnNames;
	}
	//设置按钮的背景图片
	private void setButtonBackImage()
	{
		ImageIcon imageIcon1=new ImageIcon(PATH1+"Second_2.png");
		ImageIcon imageIcon2=new ImageIcon(PATH1+"Second_3.png");
		ImageIcon imageIcon3=new ImageIcon(PATH1+"Second_4.png");
		int width=imageIcon1.getIconWidth();
		int height=imageIcon1.getIconHeight();
		classJButton.setIcon(imageIcon1);
		classJButton.setPreferredSize(new Dimension(width, height));
		saveJButton.setIcon(imageIcon2);
		saveJButton.setPreferredSize(new Dimension(width, height));
		exitJButton.setIcon(imageIcon3);
		exitJButton.setPreferredSize(new Dimension(width, height));
	}
	/*//组合标签和下拉菜单
	private void Combinate(JLabel jLabel,JComboBox<String> jComboBox)
	{
	.
		JPanel jPanel=new JPanel();
		jPanel.add(jLabel);
		jPanel.add(jComboBox);
		jPanel1.add(jPanel);
	}*/
	 //设置下拉菜单的内容
	private void setComBox()
	{
		try
		(
				InputStreamReader reader=new InputStreamReader(new FileInputStream(PATH2+"course.txt"));
				BufferedReader bufferedReader=new BufferedReader(reader);
		)
		{
			String content=null;
			while((content=bufferedReader.readLine())!=null)
			{
				ImageIcon imageIcon=new ImageIcon(PATH1+content+".png");
				model1.addElement(new Object[]{imageIcon,content});				
			}
			courseBox.setRenderer(renderer);
		}
		catch (Exception e) 
		{
			System.out.println("课程下拉菜单异常");
		}
	}
}
