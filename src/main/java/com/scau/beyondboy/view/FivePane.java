package main.java.com.scau.beyondboy.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import main.java.com.scau.beyondboy.util.Constant;

public class FivePane extends JFrame
{
	private  String choose="";
	private final String path=Constant.IMAGESPATH;
	private final  String storagepath=Constant.STORAGEPATH;
	private  File file=new File(storagepath);
	private ImageIcon imageIcon1=new ImageIcon(path+"Fiveth_1.png");
	private JLabel gradesJLabel=new JLabel(imageIcon1);
	private JComboBox<String> gradesComboBox=new JComboBox<String>();
	private JPanel jPanel1=new JPanel();
	private JTextPane showTextPane=new JTextPane();
	private JButton showButton=new JButton();
	private JButton exitButton=new JButton();
	private JPanel jPanel2=new JPanel();
	public FivePane()
	{
		init();
		this.setTitle("课程考试成绩分析");
		this.setSize(600, 600);		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	private void init()
	{
		jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		jPanel1.setBackground(Color.DARK_GRAY);
		jPanel1.add(gradesJLabel);
		setComBox();
		jPanel1.add(gradesComboBox);
		jPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT,40,0));
		jPanel2.setBackground(Color.DARK_GRAY);
		jPanel2.add(showButton);
		jPanel2.add(exitButton);
		this.add(jPanel1,BorderLayout.NORTH);
		showTextPane.setEditable(false);
		this.add(showTextPane);
		this.add(jPanel2,BorderLayout.SOUTH);
		setButtonBackImage();
		setListener();
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
		showButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					showTextPane.setText("");
					if(choose.equals("")) choose=gradesComboBox.getItemAt(0);
					ObjectInputStream in=new ObjectInputStream(new FileInputStream(storagepath+choose));
					Vector rowData=(Vector) in.readObject();
					Vector <Integer> datas=new Vector<Integer>();
					for(int i=0;i<rowData.size();i++)
					{
						Vector<Object> vector=(Vector<Object>)rowData.elementAt(i);
						datas.add((Integer)vector.get(2));
					}
					shoDatas(datas);
					SimpleAttributeSet attributeSet=new SimpleAttributeSet();
					StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_CENTER);
					showTextPane.getStyledDocument().setParagraphAttributes(0, (showTextPane.getDocument()).getLength(), attributeSet, false);
				}
				catch(Exception ex)
				{
					System.out.println("读取文件异常");
					ex.printStackTrace();
				}
			}
		});
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
	private void setDoc(String str,Color color)
	{
		SimpleAttributeSet attributeSet=new SimpleAttributeSet();
		StyleConstants.setForeground(attributeSet, color);
		StyleConstants.setBold(attributeSet, true);
		StyleConstants.setFontSize(attributeSet, 27);
		StyleConstants.setAlignment(attributeSet, 1);
		insert(str, attributeSet);
	}
	private void insert(String str,AttributeSet attributeSet)
	{
		try
		{
			Document document=showTextPane.getDocument();
			document.insertString(document.getLength(), str, attributeSet);
		} catch (Exception e)
		{
			System.out.println("添加字符异常");
			e.printStackTrace();
		}
	}
	 //显示数据
	private void shoDatas(Vector<Integer> vector)
	{
		NumberFormat format=NumberFormat.getNumberInstance() ;
		format.setMaximumFractionDigits(2);
		int nubmer=vector.size();
		Collections.sort(vector);
		Vector<Integer> datas=new Vector<Integer>();
		datas.add(vector.get(nubmer-1));
		datas.add(vector.get(0));
		int[] a=new int[5];
		Integer sum=0;
		for(int i=0;i<nubmer;i++)
		{
			sum+=vector.get(i);
			Integer grades=vector.get(i)/10;
			switch (grades)
			{
				case 10:
				case 9:
					a[4]+=1;							
					break;
				case 8:
					a[3]+=1;							
					break;
				case 7:
					a[2]+=1;							
					break;
				case 6:
					a[1]+=1;							
					break;
				default:
					a[0]+=1;							
					break;						
			}
		}
		datas.add(sum/nubmer);
		datas.add(a[0]);
		datas.add(a[1]);
		datas.add(a[2]);
		datas.add(a[3]);
		datas.add(a[4]);
		Color color1=Color.DARK_GRAY;
		Color color2=Color.BLUE;
		Color color3=Color.RED;
		setDoc("最高分："+datas.get(0)+"分,最低分："+datas.get(1)+"分,平均分："+datas.get(2)+"分", color3);
		setDoc("\n不及格(分数<60)："+datas.get(3)+"人,占"+format.format(datas.get(3)/(double)nubmer), color2);
		setDoc("\n及格(60=<分数<70)："+datas.get(4)+"人,占"+format.format(datas.get(4)/(double)nubmer), color1);
		setDoc("\n中等(70=<分数<80)："+datas.get(5)+"人,占"+format.format(datas.get(5)/(double)nubmer), color2);
		setDoc("\n良好(80=<分数<90)："+datas.get(6)+"人,占"+format.format(datas.get(6)/(double)nubmer), color1);
		setDoc("\n优秀(90=<分数<=100)："+datas.get(7)+"人,占"+format.format(datas.get(7)/(double)nubmer), color3);
	}
	 //设置按钮背景图片
	private void setButtonBackImage()
	{
		ImageIcon imageIcon1=new ImageIcon(path+"Fiveth_2.png");
		ImageIcon imageIcon2=new ImageIcon(path+"Fiveth_3.png");
		int width=imageIcon1.getIconWidth();
		int height=imageIcon1.getIconHeight();
		showButton.setIcon(imageIcon1);
		exitButton.setIcon(imageIcon2);
		showButton.setPreferredSize(new Dimension(width, height));
		exitButton.setPreferredSize(new Dimension(width, height));
	}
	public static void main(String[] args)
	{
		new FivePane();
	}
}
