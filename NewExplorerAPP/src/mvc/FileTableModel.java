/*
 * Created on 2005-5-7
 * Finishing CodeFans.net
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mvc;

import gui.*;
import java.io.File;
import java.util.*;

import javax.swing.table.AbstractTableModel;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileTableModel extends AbstractTableModel {
    private Vector files = new Vector();
    private static final int NAME_COLUMN = 0;
    private static final int SIZE_COLUMN = -1;
    private static final int TYPE_COLUMN = -2;
    private static final int DATE_COLUMN = -3;
	private String[] columnNames = {"Name","Size(Bytes)","Type","Date Modified"};
    
	public FileTableModel (){
        
    }
    
    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        // TODO Auto-generated method stub
        return files.size();
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }
    
	public String getColumnName(int column) {
		return columnNames[column];
	}	
	
    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int row, int col) {
        // TODO Auto-generated method stub
		File clickFile = (File)files.get(row);
		switch (col){
			case 0 :return new CellElement(clickFile);//(clickFile.getName().length() == 0)? clickFile.getPath():clickFile.getName();
			case 1 :return new Long(clickFile.length());
			case 2 :return isDir(clickFile);
			case 3 :return new Date(clickFile.lastModified());
		}
		return null;
    }
    
	public String isDir(File file){
		if(file.isDirectory()){
			return "<Dir>";
		}
		else if(file.isFile()){
			return "<File>";
		}
		else return "Unknow";
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex){
	    return (columnIndex == NAME_COLUMN
	            ||columnIndex == SIZE_COLUMN
	            ||columnIndex == TYPE_COLUMN
	            ||columnIndex == DATE_COLUMN);
	}
	
    public void addAll(Collection all){
        files.clear();
        if(all != null)
            files.addAll(all);
        this.fireTableDataChanged();
    }
}
