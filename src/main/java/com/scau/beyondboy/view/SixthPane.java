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
		this.setTitle("课程考试成绩分析");
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
					System.out.println("读取文件异常");
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
					System.out.println("读取文件异常");
					ex.printStackTrace();
				}
			}
		});
	  }
	 //显示数据
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
	 //设置按钮背景图片
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
	 //设置饼图
	private void setPieChart(Vector<Integer> vector)
	{
		// 创建饼图数据对象  		 
        DefaultPieDataset dfp = new DefaultPieDataset();   
        dfp.setValue("<60", vector.get(0));   
        dfp.setValue("60-69分",vector.get(1));  
        dfp.setValue("70-79分",vector.get(2));  
        dfp.setValue("80-89分",vector.get(3));   
        dfp.setValue("≥90分",vector.get(4));   
        // 饼状图的解决办法  
        // createpieChart3D创建3D饼图  
        // createpieChart创建饼图  
        JFreeChart chart = ChartFactory.createPieChart3D("CityInfoPort公司组织架构图",dfp, true, true, true);  
        // 图片背景色  
        chart.setBackgroundPaint(Color.gray);  
        // 设置标题文字  
       chartFrame = new ChartFrame("CityInfoPort公司组织架构图 ",chart, true);  
        // 取得3D饼图对象  
        PiePlot3D plot = (PiePlot3D) chart.getPlot();  
        // 图形边框颜色  
        plot.setBaseSectionOutlinePaint(Color.RED);  
        // plot.setBaseSectionPaint(Color.WHITE);  
        // 图形边框粗细  
        plot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));   
        // 指定图片的透明度(0.0-1.0)  
        plot.setForegroundAlpha(0.8f);  
        // 指定显示的饼图上圆形(false)还椭圆形(true)  
        plot.setCircular(false);         
        // 设置第一个 饼块section 的开始位置，默认是12点钟方向  
        plot.setStartAngle(360);  
        // 设置鼠标悬停提示  
        plot.setToolTipGenerator(new StandardPieToolTipGenerator()); 
        // 设置突出显示的数据块  
        plot.setExplodePercent("One",0.1D);  
        // 设置饼图各部分标签字体  
        plot.setLabelFont(new Font("宋体", Font.ITALIC, 20));  
        // 设置分饼颜色  
        plot.setSectionPaint(0, new Color(244, 194, 144));  
        // 设置图例说明Legend上的文字  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));  
        // 定义字体格式  
        Font font = new java.awt.Font("黑体", java.awt.Font.CENTER_BASELINE,50); 
        TextTitle title = new TextTitle("成绩饼状图");  
        title.setFont(font);  
        // 设置字体,非常关键不然会出现乱码的,下方的字体  
        chart.setTitle(title);  
       /* histogramChartFrame.pack();  
        histogramChartFrame.setVisible(true);  */
	}
	//设置柱状图
	private void setHistogramChart(Vector<Integer> vector)
	{
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		 dataset.addValue(vector.get(0), "分数", "<60");  
	     dataset.addValue(vector.get(1), "分数","60-69分");  	 
	     dataset.addValue(vector.get(2), "分数","70-79分");  
	     dataset.addValue(vector.get(3), "分数","80-89分");	 
	     dataset.addValue(vector.get(4), "分数", "≥90分"); 
	     JFreeChart chart = ChartFactory.createBarChart3D("水果产量图", "学生分数范围","学生人数", dataset, PlotOrientation.VERTICAL, true, true, true);  
	      chartFrame = new ChartFrame("水果产量图 ", chart, true);  
	     // 自定义设定背景色  
	     // chart.setBackgroundPaint(Color.getHSBColor(23,192,223));  
	     chart.setBackgroundPaint(Color.gray);  
	     // 获得 plot：3dBar为CategoryPlot  
	     CategoryPlot categoryPlot = chart.getCategoryPlot();  
	     // 设定图表数据显示部分背景色  
	     categoryPlot.setBackgroundPaint(Color.white);  
	     // 横坐标网格线  
	    // categoryPlot.setDomainGridlinePaint(Color.black);  
	     // 设置网格线可见  
	     categoryPlot.setDomainGridlinesVisible(false);  
	     // 纵坐标网格线  
	     categoryPlot.setRangeGridlinePaint(Color.black);  
	     // 重要的类，负责生成各种效果  
	     // BarRenderer3D renderer=(BarRenderer3D) categoryPlot.getRenderer();  
	     // 获取纵坐标  
	     NumberAxis numberaxis = (NumberAxis) categoryPlot.getRangeAxis();  
	     // 设置纵坐标的标题字体和大小  
	     numberaxis.setLabelFont(new Font("黑体", Font.CENTER_BASELINE, 24));  
	     // 设置丛坐标的坐标值的字体颜色  
	     numberaxis.setLabelPaint(Color.MAGENTA);  
	     // 设置丛坐标的坐标轴标尺颜色  
	     numberaxis.setTickLabelPaint(Color.black);  
	     // 坐标轴标尺颜色  
	     numberaxis.setTickMarkPaint(Color.BLUE);  
	    // 设置丛坐标间距值  
	     numberaxis.setAutoTickUnitSelection(false);  
	     numberaxis.setTickUnit(new NumberTickUnit(5));  
	     // 获取横坐标  
	     CategoryAxis domainAxis = categoryPlot.getDomainAxis();  
	     // 设置横坐标的标题字体和大小  
	     domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 24));  
	     // 设置横坐标的坐标值的字体颜色  
	     domainAxis.setTickLabelPaint(Color.RED);  
	     // 设置横坐标的坐标值的字体  
	     domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 30));  
	     // 设置横坐标的显示  
	     domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.4));  
	     // 这句代码解决了底部汉字乱码的问题  
	     chart.getLegend().setItemFont(new Font("黑体", 0, 30));  
	     // 设置图例标题  
	     Font font = new java.awt.Font("黑体", java.awt.Font.CENTER_BASELINE, 50);  
	     TextTitle title = new TextTitle("成绩柱状图");  
	     title.getBackgroundPaint();  
	     title.setFont(font);  
	     // 设置标题的字体颜色  
	     title.setPaint(Color.RED);  
	     chart.setTitle(title);  
	}
	public static void main(String[] args)
	{
		new SixthPane();
	}
}
