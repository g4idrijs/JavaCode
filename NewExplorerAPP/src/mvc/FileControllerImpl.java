
package mvc;

import java.io.File;
import java.util.Collection;
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
public class FileControllerImpl implements FileController {
    private FileView view;
    private FileModel model;
    
    public FileControllerImpl(FileView view,FileModel model){
        this.view = view;
        this.model = model;
    }
    
    public Collection handleGetRootNodes() throws Exception{
        return view.handleGetRootNodes();
    }
    
    /* (non-Javadoc)
     * @see FileController#handleGetRoots()
     */
    public Collection handleGetRootFiles() throws Exception {
        // TODO Auto-generated method stub
        return view.handleGetRootFiles();
    }

    /* (non-Javadoc)
     * @see FileController#handleGetFiles(java.io.File)
     */
    public Collection handleGetAll(File file) throws Exception {
        // TODO Auto-generated method stub
        return view.handleGetAll(file);
    }

    /* (non-Javadoc)
     * @see FileController#handleGetDirectories(java.io.File)
     */
    public Collection handleGetAllDirectories(File file) throws Exception {
        // TODO Auto-generated method stub
        return view.handleGetAllDirectories(file);
    }

    /* (non-Javadoc)
     * @see FileController#handleDeleteFile(java.io.File)
     */
    public void deleteFile(File[] file) throws Exception {
        // TODO Auto-generated method stub
        model.deleteFile(file);
    }
    
    /* (non-Javadoc)
     * @see FileController#handleCreateFile(java.io.File)
     */
    public File createFile(String f,String file) throws Exception {
        // TODO Auto-generated method stub
        return model.createFile(f,file);
    }

    /* (non-Javadoc)
     * @see FileController#handleModifyFile(java.io.File)
     */
    public String handleModifyFileName(File oldFile,File newFile) throws Exception{
        // TODO Auto-generated method stub
        return model.handleModifyFileName(oldFile,newFile);
    }

}
