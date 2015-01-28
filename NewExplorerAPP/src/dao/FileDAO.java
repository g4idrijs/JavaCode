
package dao;

import java.io.File;
import java.util.Collection;
import java.util.*;
import basic.TNode;

/*
 * Created on 2005-5-6
 * Finishing CodeFans.net
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileDAO {
    
    public Collection handleGetRoots() throws Exception{
        File [] roots = File.listRoots();
        if(roots.length >0){
            Vector rootNodes = new Vector();
            for (int i=0;i<roots.length;i++){
                if(roots[i] != null)
                    rootNodes.add(roots[i]);
            }
            return rootNodes;
        }
        return null;
    }
    
    public Collection handleGetRootNodes() throws Exception{
        File [] roots = File.listRoots();
        if(roots.length >0){
            Vector rootNodes = new Vector();
            for (int i=0;i<roots.length;i++){
                if(roots[i] != null)
                    rootNodes.add(new TNode(roots[i].getAbsolutePath()));
            }
            return rootNodes;
        }
        return null;
    }
    
    public Collection handleGetAll(File file) throws Exception{
        File [] allFiles = file.listFiles();
        if(allFiles != null && allFiles.length >0){
            Vector dirctories = new Vector();
            Vector files = new Vector();
            for (int i=0;i<allFiles.length;i++){
                if(allFiles[i].isDirectory() && !allFiles[i].isHidden())
                    dirctories.add(allFiles[i]);
                else
                    if(allFiles[i].isFile() && !allFiles[i].isHidden())
                        files.add(allFiles[i]);
            }
            for(int i=0;i<files.size();i++){
                dirctories.add(files.get(i));
            }
            return dirctories;
        }
        return null;
    }
    
    public Collection handleGetAllDirectories(File file) throws Exception{
        File [] files = file.listFiles();
        if(files != null && files.length >0){
            Vector allDirectories = new Vector();
            for (int i=0;i<files.length;i++){
                if(files[i].isDirectory() && !files[i].isHidden()){
                    allDirectories.add(files[i]);
                }
            }
            return allDirectories;
        }
        return null;
    }
    
    public File deleteFile(File f) throws Exception{
        File p = f.getParentFile();
        f.delete();
        return p;
    }
    
    public File createFile(File f) throws Exception{
        f.createNewFile();
        return f;	        
    }
    
    public File createFolder(File f) throws Exception{
        f.mkdir();
        return f;
    }
    
    public String handleModifyFileName(File oldFile,File newFile) throws Exception{
        boolean renamed = oldFile.renameTo(newFile);
        if(renamed){
            return newFile.getName();
        }
        else{
            return "Rename "+oldFile.getName()+" failure!";
        }
    }
}
