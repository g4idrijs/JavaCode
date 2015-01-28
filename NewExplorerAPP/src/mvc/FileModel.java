/*
 * Created on 2005-5-6
 * Finishing CodeFans.net
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mvc;

import java.util.*;
import java.io.*;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface FileModel {
    public void addViews(FileView view) throws Exception;
    public Collection handleGetRootFiles() throws Exception;
    public Collection handleGetRootNodes() throws Exception;
    public Collection handleGetAll(File file) throws Exception;
    public Collection handleGetAllDirectories(File file) throws Exception;
    public void deleteFile(File[] f) throws Exception;
    public File createFile(String f,String file) throws Exception;
    public String handleModifyFileName(File oldFile,File newFile) throws Exception;
}
