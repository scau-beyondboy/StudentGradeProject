package main.java.com.scau.beyondboy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.Vector;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import main.java.com.scau.beyondboy.model.MyJTableCellRenderer;
import main.java.com.scau.beyondboy.model.MyTableModel;
import main.java.com.scau.beyondboy.util.Constant;

public class FourPane extends JFrame
{
	private final String path=Constant.IMAGESPATH;
	private final  String storagepath=Constant.STORAGEPATH;
	private String choose="";
	private int index=0;
	private  File file=new File(storagepath);
	private ImageIcon imageIcon1=new ImageIcon(path+"Fourth_1.png");
	private JLabel gradesJLabel=new JLabel(imageIcon1);
	private JComboBox<String> gradesComboBox=new JComboBox<String>();
	private JButton showjButton=new JButton();
	private ImageIcon imageIcon2=new ImageIcon(path+"Fourth_3.png");
	private JLabel nubmerJLabel=new JLabel(imageIcon2);
	private JComboBox<Long> numberComboBox=new JComboBox<Long>();
	private ImageIcon imageIcon3=new ImageIcon(path+"Fourth_4.png");
	private JLabel modifyJLabel=new JLabel(imageIcon3);
	private JFormattedTextField modifyField=new JFormattedTextField(NumberFormat.getInstance());
	private JPanel jPanel1=new JPanel();
	private JPanel jPanel2=new JPanel();
	private JPanel jPanel3=new JPanel();
	private MyTableModel model=new MyTableModel();
	private MyJTable jTable=new MyJTable(model);
	private JButton saveButton=new JButton();
	private JButton exitButton=new JButton();
	private JPanel jPanel4=new JPanel();
	public FourPane()
	{
		Init();
		this.setTitle("修改学生成绩");
		this.setSize(600, 600);		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	private void Init()
	{
		jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		jPanel1.setBackground(Color.DARK_GRAY);
		JPanel jPanel=new JPanel();
		jPanel.add(gradesJLabel);
		jPanel.add(gradesComboBox);
		jPanel.setBackground(Color.DARK_GRAY);
		jPanel1.add(jPanel);
		jPanel1.add(showjButton);
		jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
		jPanel2.setBackground(Color.DARK_GRAY);
		numberComboBox.setPreferredSize(new Dimension(120, 35));
		Combinate(nubmerJLabel, numberComboBox);
		modifyField.setColumns(10);
		modifyField.setPreferredSize(new Dimension(10, 40));
		Combinate(modifyJLabel, modifyField);
		jPanel3.setLayout(new GridLayout(2, 1, 0, 20));
		jPanel3.setBackground(Color.DARK_GRAY);
		jPanel3.add(jPanel1);
		jPanel3.add(jPanel2);
		jPanel4.setLayout(new FlowLayout(FlowLayout.RIGHT, 40, 0));
		jPanel4.setBackground(Color.DARK_GRAY);
		setButtonBackImage();
		jPanel4.add(saveButton);
		jPanel4.add(exitButton);
		this.add(jPanel3,BorderLayout.NORTH);
		this.add(new JScrollPane(jTable));
		this.add(jPanel4,BorderLayout.SOUTH);
		setComBox();
		setListener();
	}
	//获取表头列名
	private Vector getColumnNames()
	{
		Vector<String> columnNames=new Vector<String>();
		for(int i=0;i<jTable.getColumnCount();i++)
			columnNames.add(jTable.getColumnName(i));
		return columnNames;
	}
	//组合标签和下拉菜单
	private void Combinate(JLabel jLabel,JComponent jComponent)
	{
		JPanel jPanel=new JPanel();
		jPanel.add(jLabel);
		jPanel.add(jComponent);
		jPanel.setBackground(Color.DARK_GRAY);
		jPanel2.add(jPanel);
	}
	 //设置按钮背景图片
	private void setButtonBackImage()
	{
		ImageIcon imageIcon1=new ImageIcon(path+"Fourth_2.png");
		ImageIcon imageIcon2=new ImageIcon(path+"Fourth_5.png");
		ImageIcon imageIcon3=new ImageIcon(path+"Fourth_6.png");
		int width=imageIcon1.getIconWidth();
		int height=imageIcon1.getIconHeight();
		showjButton.setIcon(imageIcon1);
		saveButton.setIcon(imageIcon2);
		exitButton.setIcon(imageIcon3);
		showjButton.setPreferredSize(new Dimension(width, height));
		saveButton.setPreferredSize(new Dimension(width, height));
		exitButton.setPreferredSize(new Dimension(width, height));
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
		numberComboBox.addItem(201330340625l);
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
		numberComboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				index=numberComboBox.getSelectedIndex();
			}
		});
		modifyField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode()==Event.ENTER)
				{
					model.setValueAt(Integer.parseInt(modifyField.getText()), index, 2);
				}
				super.keyReleased(e);
			}
		});
		showjButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if(choose.equals("")) choose=gradesComboBox.getItemAt(0);
					model.setRowCount(0);
					ObjectInputStream in=new ObjectInputStream(new FileInputStream(storagepath+choose));
					Vector rowData=(Vector) in.readObject();
					numberComboBox.removeAllItems();
					for(int i=0;i<rowData.size();i++)
					{
						Vector<Object> vector=(Vector<Object>)rowData.elementAt(i);
						numberComboBox.addItem((Long)vector.get(0));
					}
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
		saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if(jTable.isEditing())
						jTable.getCellEditor().stopCellEditing();
					ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(new File(new File(storagepath).getAbsolutePath(),choose)));
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
		exitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				FourPane.this.setVisible(false);
			}
		});
	}
	public static void main(String[] args)
	{
		new FourPane();
	}
}
