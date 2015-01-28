
package mvc;

import java.io.File;
import java.util.Collection;

public interface FileController {
    public Collection handleGetRootFiles() throws Exception;
    public Collection handleGetRootNodes() throws Exception;
    public Collection handleGetAll(File file) throws Exception;
    public Collection handleGetAllDirectories(File file) throws Exception;
    public void deleteFile(File[] f) throws Exception;
    public File createFile(String f,String file) throws Exception;
    public String handleModifyFileName(File oldFile,File newFile) throws Exception;
}
