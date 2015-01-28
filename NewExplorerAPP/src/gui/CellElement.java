package gui;
import java.io.File;
import javax.swing.ImageIcon;

public class CellElement {
    private final ImageIcon folderIcon;
    private final ImageIcon fileIcon;
    private final ImageIcon unknow;
    private final File file;
    
    public CellElement(File file){
        this.file = file;
        folderIcon = new ImageIcon("/icons/foldercolse.png");
        fileIcon = new ImageIcon("/icons/file.png");
        unknow = new ImageIcon("/icons/unknow.gif");
    }
    public ImageIcon getImageIcon(){
      if(file.isDirectory())  
           return folderIcon; 
       else if(file.isFile())
           return fileIcon;
       else return unknow;
    }
    public File getFile(){
        return file;
    }
}
