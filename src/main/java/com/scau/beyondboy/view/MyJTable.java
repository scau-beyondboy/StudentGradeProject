package main.java.com.scau.beyondboy.view;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
/**
 * 自定义的表格
 * @author lenovo
 */
public class MyJTable  extends JTable
{
	//设置单击单元格就编辑
	@Override
	public void changeSelection(int rowIndex, int columnIndex, boolean toggle,
			boolean extend)
	{
		super.changeSelection(rowIndex, columnIndex, toggle, extend);
		super.editCellAt(rowIndex, columnIndex, null);
	}
	public MyJTable(int numRows,int numColumns)
	{
		super(numRows, numColumns);
	}
	public MyJTable(Object[][] rowData,Object[] columnData)
	{
		super(rowData,columnData);
	}
	public MyJTable(TableModel model)
	{
		super(model);
	}
	public MyJTable(TableModel model,TableColumnModel columnModel)
	{
		super(model,columnModel);
	}
	public MyJTable(TableModel model,TableColumnModel columnModel,ListSelectionModel selectionModel)
	{
		super(model,columnModel,selectionModel);
	}
	public MyJTable(Vector rowData,Vector columnNames)
	{
		super(rowData, columnNames);
	}
}
