package mvc;
import gui.*;
import basic.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.File;
import java.util.Collection;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.border.*;
import javax.swing.JPopupMenu;
import javax.swing.BorderFactory;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.*;

public class FileViewImpl extends JFrame implements FileView,ActionListener {
    private FileModel model;
    private FileControllerImpl control;
    private JTable table;
    private JTree tree;
    private FileTBModel tableModel;
    private Collection roots;
    private DefaultTreeModel treeModel;
    private JLabel addressLabel;
    private JTextField addressTextField;
    private JButton upBtn;
    private JButton addFolderBtn;
    private JButton addFileBtn;
    private JButton deleteBtn;    
    private JButton exitBtn;
    private File currentFile;
    private TNode currentNode;
    private TreePath curPath;
    private boolean isTable = false;
    private JPopupMenu popMenu;
    private JMenuItem addFolderMenu;
    private JMenuItem addFileMenu;
    private JMenuItem deleteMenu;
    
    /**
     * The tree mouse selection event processor
     * @author George Wen
     *
     * TODO To change the template for this generated type comment go to
     * Window - Preferences - Java - Code Style - Code Templates
     */
    private class TreeSelectionProcesser implements TreeSelectionListener{
		public void valueChanged(TreeSelectionEvent e){
			try{
			    curPath = tree.getSelectionPath();
			    currentNode = (TNode)e.getPath().getLastPathComponent();		        
			    currentFile = currentNode.getFile();
				tree.scrollPathToVisible(curPath);
			    addressTextField.setText(currentFile.getAbsolutePath());
			    isTable = false;

			    if(!currentFile.getName().equals("My Computer")){
			        Collection files = control.handleGetAll(currentNode.getFile());
			        tableModel.addAllFiles(files);
			        upBtn.setEnabled(true);
			        addFolderBtn.setEnabled(true);
			        addFileBtn.setEnabled(true);
			        addFolderMenu.setEnabled(true);
			        addFileMenu.setEnabled(true);
			        if(!((TNode)currentNode.getParent()).isRoot()){
			            deleteBtn.setEnabled(true);
			            deleteMenu.setEnabled(true);
			        }
			        else{
			            deleteBtn.setEnabled(false);
			            deleteMenu.setEnabled(false);
			        }
			    }
			    else{
			        upBtn.setEnabled(false);
			        addFolderBtn.setEnabled(false);
			        addFileBtn.setEnabled(false);
			        deleteBtn.setEnabled(false);
			        addFolderMenu.setEnabled(false);
			        addFileMenu.setEnabled(false);
			        deleteMenu.setEnabled(false);
			        currentNode = (TNode)treeModel.getRoot();
			        tableModel.addAllFiles(control.handleGetRootFiles());
			    }
			}
			catch(Exception ee){
	            JOptionPane.showMessageDialog(null,"Read file error!","Error",JOptionPane.ERROR_MESSAGE);
			    ee.printStackTrace();
			}
		}
	}
    
    /**
     * The tree will be expand event processor
     * @author George Wen
     *
     * TODO To change the template for this generated type comment go to
     * Window - Preferences - Java - Code Style - Code Templates
     */
	private class TreeWillExpandProcessor implements TreeWillExpandListener{		
	    public void treeWillExpand(TreeExpansionEvent e){
			try{
			    currentNode = (TNode)e.getPath().getLastPathComponent();
			    if(!currentNode.getName().equals("My Computer")){
				    Collection directories = control.handleGetAllDirectories(currentNode.getFile());
				    appendNodes(currentNode,directories);
				    treeModel.nodeStructureChanged(currentNode);
			    }
			}
			catch(Exception ee){
	            JOptionPane.showMessageDialog(null,"Read file error!","Error",JOptionPane.ERROR_MESSAGE);
	            ee.printStackTrace();
			}
		}
		
		public void treeWillCollapse(TreeExpansionEvent ec){
		} 
	}
    
	/**
	 * This is the table mouse click event processor
	 * @author George Wen
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
    private class mouseClickedProcessor extends MouseAdapter {
        public void mouseClicked(MouseEvent e){  
            int clickedIndex = table.getSelectedRow();
            if(clickedIndex >=0){
                isTable = true;
                currentFile = tableModel.getFile(clickedIndex);
                addressTextField.setText(currentFile.getAbsolutePath()); 
                if(!currentNode.getFile().getName().equals("My Computer")){
                    deleteBtn.setEnabled(true);
                    deleteMenu.setEnabled(true);
                }
            }
            if(e.getClickCount()>=2){
                if(currentFile.isDirectory()){
                    try{
    			        upBtn.setEnabled(true);
    			        if(!tree.isExpanded(curPath))
    			            tree.expandPath(curPath);
    			        if(currentNode.getChildCount()>0){
    			            for(int i=0;i<currentNode.getChildCount();i++){
    			                TNode temp = (TNode)currentNode.getChildAt(i);
    			                if(temp.getFile().getPath().equalsIgnoreCase(currentFile.getPath())){
    			                    TreeNode[] nodes = treeModel.getPathToRoot(temp);
    			        			curPath = new TreePath(nodes);
    			        			tree.setSelectionPath(curPath);
    			                    break;
    			                }
    			            }
    			        }
                    }
                    catch(Exception ee){
                        ee.printStackTrace();
                    }
                }
            }
            else if(e.getButton() == e.BUTTON3){
                popMenu.show(table,e.getX(),e.getY());
            }
        }
    }    
    
    public FileViewImpl(FileModel model){
        super("Resources Explore...");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        upBtn = new JButton(new ImageIcon("/icons/up.png"));upBtn.setEnabled(false);
        upBtn.setPreferredSize(new Dimension(20,18));
        addressLabel = new JLabel("Address: ");
        addressTextField = new JTextField();
        addressTextField.setForeground(Color.BLUE);
        addFolderBtn = new JButton("Add Folder");addFolderBtn.setEnabled(false);
        addFileBtn = new JButton("Add File");addFileBtn.setEnabled(false);
        deleteBtn = new JButton("Delete Me");deleteBtn.setEnabled(false);
        exitBtn = new JButton("Exit");
        popMenu = new JPopupMenu("PopMenu");
        addFolderMenu = new JMenuItem("AddFolder");addFolderMenu.setEnabled(false);popMenu.add(addFolderMenu);
        addFileMenu = new JMenuItem("AddFile");addFileMenu.setEnabled(false);popMenu.add(addFileMenu);
        deleteMenu = new JMenuItem("Delete");deleteMenu.setEnabled(false);popMenu.add(deleteMenu);
        
        try{
            this.model = model;
            model.addViews(this);
            control = new FileControllerImpl(this,model);
            roots = control.handleGetRootNodes();//ªÒµ√“™º”µΩ∏˘Ω⁄µ„µƒ¥≈≈Ã≈Ã∑˚ ˝◊È
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        /**
         * Treeµƒ≥ı ºªØ∫Õœ‡πÿ≈‰÷√,TNodeŒ™ ˜µƒNode¿‡,∏˘Ω⁄µ„Œ™root,rootsŒ™“™º”µΩ∏˘Ω⁄µ„…œµƒ¥≈≈Ã≈Ã∑˚
         * appendNodesŒ™ÃÌº”Ω⁄µ„µƒ∑Ω∑®,µ˜”√ ±¥´»Î∏∏Ω⁄µ„node,∫Õ“™ÃÌº”µΩ∏√Ω⁄µ„µƒΩ⁄µ„ ˝◊ÈCollection ¿‡–Œ
         */
        TNode root = new TNode("My Computer");
        currentNode = root;
        Vector tn = (Vector)roots;
        if(tn.size()>0){
            for(int i=0;i<tn.size();i++){
                TNode tmp = (TNode)tn.get(i);
                tmp.add(new DefaultMutableTreeNode(""));
                root.add(tmp);
            }
        }

        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        tree.setRowHeight(20);
        TreeSelectionModel sm = tree.getSelectionModel();
        sm.setSelectionMode(1);
        tree.setSelectionModel(sm);
        DefaultTreeCellRenderer treeRender = (DefaultTreeCellRenderer)tree.getCellRenderer();
        treeRender.setLeafIcon(new ImageIcon("/icons/foldercolse.png"));
        treeRender.setClosedIcon(new ImageIcon("/icons/foldercolse.png"));
        treeRender.setOpenIcon(new ImageIcon("/icons/folderopen.png"));
        treeRender.setBackgroundNonSelectionColor(Color.white);
        treeRender.setBackgroundSelectionColor(Color.ORANGE);
        treeRender.setBorderSelectionColor(Color.pink);
        treeRender.setTextNonSelectionColor(Color.black);
        treeRender.setTextSelectionColor(Color.blue);
		tree.putClientProperty("JTree.lineStyle","Horizontal");
        
		/**
		 
		 */
        tableModel = new FileTBModel();
        try{
            tableModel.addAllFiles(control.handleGetRootFiles());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        table = new JTable(tableModel);
        table.setShowGrid(false);
        setColumnWidth(0,300);
        setColumnWidth(3,150);
        table.setSelectionBackground(Color.ORANGE);
        table.setSelectionForeground(Color.blue);
        table.setRowHeight(20);
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn columnName = columnModel.getColumn(0);
		columnName.setCellRenderer(new TblCellRenderer());
        
		/**
		 
		 */
		JPanel southBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southBtnPanel.setBorder(new TitledBorder(""));
		southBtnPanel.add(addFolderBtn);
		southBtnPanel.add(addFileBtn);
		southBtnPanel.add(deleteBtn);
		southBtnPanel.add(exitBtn);
		this.getContentPane().add(southBtnPanel,BorderLayout.SOUTH);
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBorder(BorderFactory.createTitledBorder("Folder Tree"));
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBorder(new TitledBorder("File Resorceses"));
		JPanel upBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		upBtnPanel.add(upBtn);
		upBtnPanel.add(addressLabel);
		JPanel rightTopTextFieldPanel = new JPanel(new BorderLayout());
		rightTopTextFieldPanel.setBorder(new TitledBorder(""));
		rightTopTextFieldPanel.add(upBtnPanel,BorderLayout.WEST);
		rightTopTextFieldPanel.add(addressTextField);
		leftPanel.add(new JScrollPane(tree));
		JScrollPane rightScrollPane = new JScrollPane(table);
		rightScrollPane.setBorder(new TitledBorder("File Detail"));
		
		rightPanel.add(rightTopTextFieldPanel,BorderLayout.NORTH);
		rightPanel.add(rightScrollPane);
		
        JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftPanel,rightPanel);
        jsp.setDividerSize(3);
        jsp.setDividerLocation(200);
        this.getContentPane().add(jsp);

        tree.addTreeSelectionListener(new TreeSelectionProcesser());
        tree.addTreeWillExpandListener(new TreeWillExpandProcessor());       
        table.addMouseListener(new mouseClickedProcessor()); 
        tree.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(e.getButton() == e.BUTTON3)
                    popMenu.show(tree,e.getX(),e.getY());
            }
        });
        addFolderBtn.addActionListener(this);
        addFolderMenu.addActionListener(this);
        addFileBtn.addActionListener(this);
        addFileMenu.addActionListener(this);
        deleteBtn.addActionListener(this);
        deleteMenu.addActionListener(this);
        exitBtn.addActionListener(this);
        upBtn.addActionListener(this);
        this.setSize(700,450);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }
    
    /**
     * Append nodes to the tree 
     * @param node,parent node that will appended other nodes
     * @param vct,nodes file sets that you want to append
     */
    public void appendNodes(DefaultMutableTreeNode node,Collection vct){        
        Vector newVct = new Vector();
        if(vct != null){
            newVct = (Vector)vct;
            node.removeAllChildren();
        }
        else{
            node.removeAllChildren();
            return;
        }
        for(int i=0;i<vct.size();i++){
            File file = (File)newVct.get(i);
            TNode tnode = new TNode(file);
            tnode.add(new DefaultMutableTreeNode(""));
            node.add(tnode);
        }
    }
    
    /**
     * Rsize the table columnWidth
     */
    public void setColumnWidth(int pColumn, int pWidth){
        TableColumnModel colModel = table.getColumnModel();
        colModel.getColumn(pColumn).setPreferredWidth(pWidth);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == upBtn){
            try{
                curPath = curPath.getParentPath();
                if(curPath != null){                    
	        		tree.setSelectionPath(curPath); 
                }
            }
            catch(Exception ee){
	            ee.printStackTrace();
            }
        }
        else if(e.getSource() == addFolderBtn || e.getSource() == addFolderMenu){
            try{
	            String buf = JOptionPane.showInputDialog(this,"Pls Input FileName: ","new Folder");
	            createFile("d",buf);
            }
            catch(Exception ee){
                ee.printStackTrace();
            }
        }
        else if(e.getSource() == addFileBtn || e.getSource() == addFileMenu){
            try{
	            String buf = JOptionPane.showInputDialog(this,"Pls Input FileName: ","new File");
	            createFile("f",buf);
            }
            catch(Exception ee){
                ee.printStackTrace();;
            }
        }
        else if(e.getSource() == deleteBtn || e.getSource() == deleteMenu){ 
                deleteFile();
        }
        else if(e.getSource() == exitBtn){
            System.exit(0);
        }
    }
    
    /**
     * Get all root nodes with the system root files
     */
    public Collection handleGetRootNodes() throws Exception{
        return model.handleGetRootNodes();
    }
    
    /**
     * Get all root files of system
     */
    public Collection handleGetRootFiles() throws Exception {
        // TODO Auto-generated method stub
        return model.handleGetRootFiles();
    }

    /**
     * This method invoke by controller,return a collection sets of the file with you supplied file
     * @param file,this is you supplied file that you want to get all files under it
     */
    public Collection handleGetAll(File file) throws Exception {
        // TODO Auto-generated method stub
        return model.handleGetAll(file);
    }

    /**
     * This method invoke by controller,return a collection sets of the directories with you supplied file
     * @param file,this is you supplied file that you want to get the directories under it
     */
    public Collection handleGetAllDirectories(File file) throws Exception {
        // TODO Auto-generated method stub
        return model.handleGetAllDirectories(file);
    }

    /**
     * This method invoke by model when file deleted
     * the end refresh the table and tree
     */
    public void fireDeleteFile() throws Exception {
        // TODO Auto-generated method stub
        try{
            if(isTable){
                refreshNode();
            }
            else{                    
                curPath = curPath.getParentPath();
                tree.setSelectionPath(curPath);
                refreshNode();
            }                 
        }
        catch(Exception ee){
            JOptionPane.showMessageDialog(this,"Delete failure,folder not empty or file protected!");
            ee.printStackTrace();
        }
    }  
    
    /**
     * This method invoke by model when a new file created,the "file" is the new file created,
     * the end refresh the table and tree 
     */
    public void fireCreateFile(File file) throws Exception {
        // TODO Auto-generated method stub
        refreshNode();
        if(!tree.isExpanded(curPath))
            tree.expandPath(curPath);
    }
    
    /* (non-Javadoc)
     * @see FileView#fireModifyFile(java.io.File)
     */
    public void fireModifyFileName(File file) throws Exception {
        // TODO Auto-generated method stub

    }    
    
    /**
     * Create a new file or folder
     * @param f this is the file type, "f" to create a file,"d" to create a folder
     * @param file
     */
    public void createFile(String f,String file){
        try{
	        if(file != null && f.equals("f")){
                file = file.trim();
                String newFile = "";
                if(currentFile.isDirectory())
                    newFile = currentFile.getAbsolutePath()+"/"+file;
	            else
	                newFile = currentNode.getFile().getAbsolutePath()+"/"+file;
                control.createFile("f",newFile);
	        }
	        else if(file != null && f.equals("d")){
	            file = file.trim();
                String newFile = "";
                if(currentFile.isDirectory())
                    newFile = currentFile.getAbsolutePath()+"/"+file;
	            else
	                newFile = currentNode.getFile().getAbsolutePath()+"/"+file;
                control.createFile("d",newFile);
	        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Deleted the all files that you selected,but the file can not be deleted
     * that contain the another file under or sub-folder
     */
    public void deleteFile(){
        try{
            if(isTable){
                int[] indeics = table.getSelectedRows();
                if(indeics != null && indeics.length>0){
	                File[] buf = new File[indeics.length];
	                for(int i=0;i<indeics.length;i++){
	                    buf[i] = tableModel.getFile(indeics[i]);
	                }
	                control.deleteFile(buf); 
                }
            }
            else{
                File[] buf = {currentNode.getFile()};
                control.deleteFile(buf);
            }
        }
        catch(Exception ee){
            JOptionPane.showMessageDialog(this,"Delete failure,folder not empty or file protected!");
            ee.printStackTrace();
        }
    }
    
    /**
     * Refresh the Nodes and table when deleted or created a file 
     */
    public void refreshNode(){
        try{
	        Collection files = control.handleGetAll(currentNode.getFile());
	        tableModel.addAllFiles(files);
	        appendNodes(currentNode,control.handleGetAllDirectories(currentNode.getFile()));
	        treeModel.nodeStructureChanged(currentNode);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
