
package mvc;

import java.io.File;
import java.util.Collection;
import java.util.*;

import dao.FileDAO;
/*
 * Created on 2005-5-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileModelImpl implements FileModel {
    Vector views = new Vector();
    FileDAO filedao;
    public FileModelImpl(FileDAO filedao){
        this.filedao = filedao;
    }
    
    /* (non-Javadoc)
     * @see FileModel#addViews()
     */
    public void addViews(FileView view) throws Exception{
        //TODO Auto-generated method stub
        views.add(view);
    }
    
    public Collection handleGetRootNodes() throws Exception{
        return filedao.handleGetRootNodes();
    }
    
    /* (non-Javadoc)
     * @see FileModel#handleGetRoots()
     */
    public Collection handleGetRootFiles() throws Exception {
        // TODO Auto-generated method stub
        return filedao.handleGetRoots();
    }

    /* (non-Javadoc)
     * @see FileModel#handleGetFiles(java.io.File)
     */
    public Collection handleGetAll(File file) throws Exception {
        // TODO Auto-generated method stub
        return filedao.handleGetAll(file);
    }

    /* (non-Javadoc)
     * @see FileModel#handleGetDirectories(java.io.File)
     */
    public Collection handleGetAllDirectories(File file) throws Exception {
        // TODO Auto-generated method stub
        return filedao.handleGetAllDirectories(file);
    }

    /* (non-Javadoc)
     * @see FileModel#handleDeleteFile(java.io.File)
     */
    public void deleteFile(File[] file) throws Exception {
        // TODO Auto-generated method stub
        for(int i=0;i<file.length;i++){
            filedao.deleteFile(file[i]);
        }
        for(int i=0;i<views.size();i++){
            FileView fv = (FileView)views.get(i);
            fv.fireDeleteFile();
        }
    }
    
    /* (non-Javadoc)
     * @see FileModel#handleCreateFile(java.io.File)
     */
    public File createFile(String f,String file) throws Exception {
        // TODO Auto-generated method stub
        try{
            File temp = null;
	        if(file != null && f.equals("f")){
                file = file.trim();
	            temp = new File(file);
	            filedao.createFile(temp);
	            for(int i=0;i<views.size();i++){
	                FileView fv = (FileView)views.get(i);
	                fv.fireCreateFile(temp);
	            }
	            return temp;
	        }
	        else if(file != null && f.equals("d")){
	            file = file.trim();
	            temp = new File(file);
	            filedao.createFolder(temp); 
	            for(int i=0;i<views.size();i++){
	                FileView fv = (FileView)views.get(i);
	                fv.fireCreateFile(temp);
	            }
	            return temp;
	        }	        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see FileModel#handleModifyFile(java.io.File)
     */
    public String handleModifyFileName(File oldFile,File newFile) throws Exception{
        // TODO Auto-generated method stub
        String string = filedao.handleModifyFileName(oldFile,newFile);
        for(int i=0;i<views.size();i++){
            ((FileView)views.get(i)).fireModifyFileName(newFile);
        }
        return string;
    }

}
