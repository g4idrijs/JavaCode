package mvc;
import java.io.File;
import java.util.Collection;

public interface FileView {
    public Collection handleGetRootFiles() throws Exception;
    public Collection handleGetRootNodes() throws Exception;
    public Collection handleGetAll(File file) throws Exception;
    public Collection handleGetAllDirectories(File file) throws Exception;
    public void fireDeleteFile() throws Exception;
    public void fireCreateFile(File file) throws Exception;
    public void fireModifyFileName(File file) throws Exception;
}
