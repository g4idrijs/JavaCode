
package basic;

import javax.swing.tree.DefaultMutableTreeNode;
/*
 * Created on 2005-5-7
 * Finishing CodeFans.net
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.*;
/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TNode extends DefaultMutableTreeNode {
    private File file;
    public TNode (File file){
        super(file.getName());
        this.file = file;
    }
    public TNode (String fileName){      
        super(fileName);
        this.file = new File(fileName);
        
    }
    public File getFile (){
        return file;
    }
    public void setFile (File file){
        this.file = file;
    }
    
    public String getName(){
        if(file.getName().length()<=0)
            return file.getAbsolutePath();
        else 
            return file.getName();
    }
}
