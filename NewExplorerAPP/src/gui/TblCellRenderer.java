package gui;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.color.*;

public class TblCellRenderer implements TableCellRenderer {
    private JLabel celItem;
    /* (non-Javadoc)
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable arg0, Object arg1,
            boolean arg2, boolean arg3, int row, int col) {
        // TODO Auto-generated method stub
        CellElement item = (CellElement)arg1;
        celItem = new JLabel(item.getImageIcon());
        celItem.setText((item.getFile().getName().length() == 0)? item.getFile().getPath():item.getFile().getName());
        celItem.setHorizontalAlignment(celItem.LEFT);
/*        if(row%2 == 0) 
            setBackground(Color.white);
        else if(row%2 == 1) 
            setBackground(new Color(206,231,255)); */
        return celItem;
    }
}
