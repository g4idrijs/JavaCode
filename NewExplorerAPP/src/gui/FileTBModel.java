/*
 * Created on 2005-5-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gui;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.*;
/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileTBModel extends AbstractTableModel {
    private Vector files = new Vector();
    private static final int NAME_COLUMN = -1;
    private static final int SIZE_COLUMN = -2;
    private static final int TYPE_COLUMN = -3;
    private static final int DATE_COLUMN = -4;
    private String[] columnNames = {"Name","Size","Type","Date Modified"};
    
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
	
    public File getFile(int index){
        return (File)files.get(index);
    }   
    
    public void addAllFiles(Collection all){
        files.clear();
        if(all != null)
            files.addAll(all);
        this.fireTableDataChanged();
    }
}
