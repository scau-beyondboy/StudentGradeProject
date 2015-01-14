package main.java.com.scau.beyondboy.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.AttributedCharacterIterator;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.java.com.scau.beyondboy.util.Constant;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class SixthPane extends JFrame
{
	private  String choose="";
	private boolean isHasChat=false;
	private final String path=Constant.IMAGESPATH;
	private final  String storagepath=Constant.STORAGEPATH;
	private  File file=new File(storagepath);
	private ImageIcon imageIcon1=new ImageIcon(path+"Sixth_1.png");
	private JLabel gradesJLabel=new JLabel(imageIcon1);
	private JComboBox<String> gradesComboBox=new JComboBox<String>();
	private JPanel jPanel1=new JPanel();
	private JButton histogramButton=new JButton();
	private JButton pieButton=new JButton();
	private JButton exitButton=new JButton();
	private JPanel jPanel2=new JPanel();
	private ChartFrame chartFrame; 
	private Component chartJPanel;
	//private ChartFrame chartFrame;
	public SixthPane()
	{
		init();
		this.setTitle("�γ̿��Գɼ�����");
		this.setSize(700, 600);		
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
		jPanel2.add(histogramButton);
		jPanel2.add(pieButton);
		jPanel2.add(exitButton);
		//setHistogramChart();
		//setPieChart();
		this.add(jPanel1,BorderLayout.NORTH);
		//this.add(histogramChartFrame.getContentPane());
		//this.add(chartFrame.getContentPane());
		this.add(jPanel2,BorderLayout.SOUTH);
		setButtonBackImage();
		setListener();
	}
	//�����������
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
		histogramButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if(choose.equals("")) choose=gradesComboBox.getItemAt(0);
					ObjectInputStream in=new ObjectInputStream(new FileInputStream(storagepath+choose));
					Vector rowData=(Vector) in.readObject();
					Vector <Integer> datas=new Vector<Integer>();
					for(int i=0;i<rowData.size();i++)
					{
						Vector<Object> vector=(Vector<Object>)rowData.elementAt(i);
						datas.add((Integer)vector.get(2));
					}
					Vector<Integer> vector=shoDatas(datas);
					setHistogramChart(vector);
					if(isHasChat){SixthPane.this.remove(chartJPanel);SixthPane.this.repaint();};
					chartJPanel=chartFrame.getContentPane();
					SixthPane.this.add(chartJPanel,BorderLayout.CENTER);
					SixthPane.this.validate();
					isHasChat=true;
				}
				catch(Exception ex)
				{
					System.out.println("��ȡ�ļ��쳣");
					ex.printStackTrace();
				}
			}
		});
		pieButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if(choose.equals("")) choose=gradesComboBox.getItemAt(0);
					ObjectInputStream in=new ObjectInputStream(new FileInputStream(storagepath+choose));
					Vector rowData=(Vector) in.readObject();
					Vector <Integer> datas=new Vector<Integer>();
					for(int i=0;i<rowData.size();i++)
					{
						Vector<Object> vector=(Vector<Object>)rowData.elementAt(i);
						datas.add((Integer)vector.get(2));
					}
					Vector<Integer> vector=shoDatas(datas);
					setPieChart(vector);					
					if(isHasChat){SixthPane.this.remove(chartJPanel);SixthPane.this.repaint();};
					chartJPanel=chartFrame.getContentPane();
					SixthPane.this.add(chartJPanel,BorderLayout.CENTER);
					SixthPane.this.validate();
					isHasChat=true;
				}
				catch(Exception ex)
				{
					System.out.println("��ȡ�ļ��쳣");
					ex.printStackTrace();
				}
			}
		});
	  }
	 //��ʾ����
	private Vector<Integer> shoDatas(Vector<Integer> vector)
	{
		int nubmer=vector.size();
		Collections.sort(vector);
		Vector<Integer> datas=new Vector<Integer>();
		int[] a=new int[5];
		for(int i=0;i<nubmer;i++)
		{
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
		datas.add(a[0]);
		datas.add(a[1]);
		datas.add(a[2]);
		datas.add(a[3]);
		datas.add(a[4]);
		return datas;
	}
	 //���ð�ť����ͼƬ
	private void setButtonBackImage()
	{
		ImageIcon imageIcon1=new ImageIcon(path+"Sixth_2.png");
		ImageIcon imageIcon2=new ImageIcon(path+"Sixth_3.png");
		ImageIcon imageIcon3=new ImageIcon(path+"Sixth_4.png");
		int width=imageIcon1.getIconWidth();
		int height=imageIcon1.getIconHeight();
		histogramButton.setIcon(imageIcon1);
		pieButton.setIcon(imageIcon2);
		exitButton.setIcon(imageIcon3);
		histogramButton.setPreferredSize(new Dimension(width, height));
		pieButton.setPreferredSize(new Dimension(width, height));
		exitButton.setPreferredSize(new Dimension(width, height));
	}
	//���������˵�������
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
	 //���ñ�ͼ
	private void setPieChart(Vector<Integer> vector)
	{
		// ������ͼ���ݶ���  		 
        DefaultPieDataset dfp = new DefaultPieDataset();   
        dfp.setValue("<60", vector.get(0));   
        dfp.setValue("60-69��",vector.get(1));  
        dfp.setValue("70-79��",vector.get(2));  
        dfp.setValue("80-89��",vector.get(3));   
        dfp.setValue("��90��",vector.get(4));   
        // ��״ͼ�Ľ���취  
        // createpieChart3D����3D��ͼ  
        // createpieChart������ͼ  
        JFreeChart chart = ChartFactory.createPieChart3D("CityInfoPort��˾��֯�ܹ�ͼ",dfp, true, true, true);  
        // ͼƬ����ɫ  
        chart.setBackgroundPaint(Color.gray);  
        // ���ñ�������  
       chartFrame = new ChartFrame("CityInfoPort��˾��֯�ܹ�ͼ ",chart, true);  
        // ȡ��3D��ͼ����  
        PiePlot3D plot = (PiePlot3D) chart.getPlot();  
        // ͼ�α߿���ɫ  
        plot.setBaseSectionOutlinePaint(Color.RED);  
        // plot.setBaseSectionPaint(Color.WHITE);  
        // ͼ�α߿��ϸ  
        plot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));   
        // ָ��ͼƬ��͸����(0.0-1.0)  
        plot.setForegroundAlpha(0.8f);  
        // ָ����ʾ�ı�ͼ��Բ��(false)����Բ��(true)  
        plot.setCircular(false);         
        // ���õ�һ�� ����section �Ŀ�ʼλ�ã�Ĭ����12���ӷ���  
        plot.setStartAngle(360);  
        // ���������ͣ��ʾ  
        plot.setToolTipGenerator(new StandardPieToolTipGenerator()); 
        // ����ͻ����ʾ�����ݿ�  
        plot.setExplodePercent("One",0.1D);  
        // ���ñ�ͼ�����ֱ�ǩ����  
        plot.setLabelFont(new Font("����", Font.ITALIC, 20));  
        // ���÷ֱ���ɫ  
        plot.setSectionPaint(0, new Color(244, 194, 144));  
        // ����ͼ��˵��Legend�ϵ�����  
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 20));  
        // ���������ʽ  
        Font font = new java.awt.Font("����", java.awt.Font.CENTER_BASELINE,50); 
        TextTitle title = new TextTitle("�ɼ���״ͼ");  
        title.setFont(font);  
        // ��������,�ǳ��ؼ���Ȼ����������,�·�������  
        chart.setTitle(title);  
       /* histogramChartFrame.pack();  
        histogramChartFrame.setVisible(true);  */
	}
	//������״ͼ
	private void setHistogramChart(Vector<Integer> vector)
	{
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		 dataset.addValue(vector.get(0), "����", "<60");  
	     dataset.addValue(vector.get(1), "����","60-69��");  	 
	     dataset.addValue(vector.get(2), "����","70-79��");  
	     dataset.addValue(vector.get(3), "����","80-89��");	 
	     dataset.addValue(vector.get(4), "����", "��90��"); 
	     JFreeChart chart = ChartFactory.createBarChart3D("ˮ������ͼ", "ѧ��������Χ","ѧ������", dataset, PlotOrientation.VERTICAL, true, true, true);  
	      chartFrame = new ChartFrame("ˮ������ͼ ", chart, true);  
	     // �Զ����趨����ɫ  
	     // chart.setBackgroundPaint(Color.getHSBColor(23,192,223));  
	     chart.setBackgroundPaint(Color.gray);  
	     // ��� plot��3dBarΪCategoryPlot  
	     CategoryPlot categoryPlot = chart.getCategoryPlot();  
	     // �趨ͼ��������ʾ���ֱ���ɫ  
	     categoryPlot.setBackgroundPaint(Color.white);  
	     // ������������  
	    // categoryPlot.setDomainGridlinePaint(Color.black);  
	     // ���������߿ɼ�  
	     categoryPlot.setDomainGridlinesVisible(false);  
	     // ������������  
	     categoryPlot.setRangeGridlinePaint(Color.black);  
	     // ��Ҫ���࣬�������ɸ���Ч��  
	     // BarRenderer3D renderer=(BarRenderer3D) categoryPlot.getRenderer();  
	     // ��ȡ������  
	     NumberAxis numberaxis = (NumberAxis) categoryPlot.getRangeAxis();  
	     // ����������ı�������ʹ�С  
	     numberaxis.setLabelFont(new Font("����", Font.CENTER_BASELINE, 24));  
	     // ���ô����������ֵ��������ɫ  
	     numberaxis.setLabelPaint(Color.MAGENTA);  
	     // ���ô����������������ɫ  
	     numberaxis.setTickLabelPaint(Color.black);  
	     // ����������ɫ  
	     numberaxis.setTickMarkPaint(Color.BLUE);  
	    // ���ô�������ֵ  
	     numberaxis.setAutoTickUnitSelection(false);  
	     numberaxis.setTickUnit(new NumberTickUnit(5));  
	     // ��ȡ������  
	     CategoryAxis domainAxis = categoryPlot.getDomainAxis();  
	     // ���ú�����ı�������ʹ�С  
	     domainAxis.setLabelFont(new Font("����", Font.PLAIN, 24));  
	     // ���ú����������ֵ��������ɫ  
	     domainAxis.setTickLabelPaint(Color.RED);  
	     // ���ú����������ֵ������  
	     domainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 30));  
	     // ���ú��������ʾ  
	     domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.4));  
	     // ���������˵ײ��������������  
	     chart.getLegend().setItemFont(new Font("����", 0, 30));  
	     // ����ͼ������  
	     Font font = new java.awt.Font("����", java.awt.Font.CENTER_BASELINE, 50);  
	     TextTitle title = new TextTitle("�ɼ���״ͼ");  
	     title.getBackgroundPaint();  
	     title.setFont(font);  
	     // ���ñ����������ɫ  
	     title.setPaint(Color.RED);  
	     chart.setTitle(title);  
	}
	public static void main(String[] args)
	{
		new SixthPane();
	}
}
