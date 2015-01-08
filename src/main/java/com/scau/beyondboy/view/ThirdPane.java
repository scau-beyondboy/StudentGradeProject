package main.java.com.scau.beyondboy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import main.java.com.scau.beyondboy.model.MyJTableCellRenderer;
import main.java.com.scau.beyondboy.model.ThirdTableModel;
import main.java.com.scau.beyondboy.util.Constant;

public class ThirdPane extends JFrame
{
	private final String path=Constant.IMAGESPATH;
	private final  String storagepath=Constant.STORAGEPATH;
	private String choose="2010级网络工程1班-面向对象设计.dat";
	private  File file=new File(storagepath);
	private ImageIcon imageIcon1=new ImageIcon(path+"Third_1.png");
	private JLabel gradesJLabel=new JLabel(imageIcon1);
	private JComboBox<String> gradesComboBox=new JComboBox<String>();
	private JPanel jPanel1=new JPanel();
	private ThirdTableModel model=new ThirdTableModel();
	private JTable jTable=new JTable(model);
	private JPanel jPanel2=new JPanel();
	private JButton queryButton=new JButton();
	private JButton exitButton=new JButton();
	public ThirdPane()
	{
		Init();
		this.setTitle("输入学生成绩");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	private void Init()
	{
		jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		jPanel1.add(gradesJLabel);
		setComBox();
		jPanel1.add(gradesComboBox);
		jPanel1.setBackground(Color.DARK_GRAY);
		jPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 40,0));
		setButtonBackImage();		
		jPanel2.add(queryButton);
		jPanel2.add(exitButton);
		jPanel2.setBackground(Color.DARK_GRAY);
		this.add(jPanel1,BorderLayout.NORTH);
		this.add(new JScrollPane(jTable));
		this.add(jPanel2,BorderLayout.SOUTH);
		setListener();
	}
	//设置下拉菜单的内容
	private void setComBox()
	{
		File[] files=file.listFiles();
		for(File s:files)
		{
			if(s.getName().endsWith(".dat"))
			{
				gradesComboBox.addItem(s.getName());
			}
		}
	}
	//设置组件监听
	private void setListener()
	{
		gradesComboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				choose=(String)gradesComboBox.getSelectedItem();
			}
		});
		queryButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					model.setRowCount(0);
					ObjectInputStream in=new ObjectInputStream(new FileInputStream(storagepath+choose));
					Vector rowData=(Vector) in.readObject();
					Vector columnNames=(Vector)in.readObject();
					model.setDataVector(rowData, columnNames);
					TableColumn remarks=jTable.getColumn("备注");
					remarks.setCellRenderer(new MyJTableCellRenderer());
					in.close();
				}
				catch(Exception ex)
				{
					System.out.println("读取文件异常");
					ex.printStackTrace();
				}
			}
		});
		exitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ThirdPane.this.setVisible(false);
			}
		});
	}
	 //设置按钮背景图片
	private void setButtonBackImage()
	{
		ImageIcon imageIcon1=new ImageIcon(path+"Third_2.png");
		ImageIcon imageIcon2=new ImageIcon(path+"Third_3.png");
		int width=imageIcon1.getIconWidth();
		int height=imageIcon1.getIconHeight();
		queryButton.setIcon(imageIcon1);
		exitButton.setIcon(imageIcon2);
		queryButton.setPreferredSize(new Dimension(width, height));
		exitButton.setPreferredSize(new Dimension(width, height));
	}
	public static void main(String[] args)
	{
		new ThirdPane();
	}
}
