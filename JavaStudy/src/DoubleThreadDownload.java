
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoubleThreadDownload {
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    public static void main(String[] args) throws Exception {
        System.out.println("\n**********");
        int threadCount = 50;

        String path = "http://free2.macx.cn:8182/tools/network/FileZilla38.dmg";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            int length = conn.getContentLength();

            int block = length % threadCount == 0 ? length / threadCount : length / threadCount + 1;

            File file = new File(getFileName(path));

            RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");

            accessFile.setLength(length);
            accessFile.close();

            System.out.println(DATE_FORMAT.format(new Date()) + path);
            System.out.println("大小为：" + (length / 1024D / 1024D) + " mb");
            System.out.println("存放地址：" + file.getAbsolutePath());
            for (int i = 0; i < threadCount; i++) {
                new DoubleDownThread(i, url, file, block).start();
            }
        }
    }

    private static String getFileName(String path) {
        return "/Users/g4idrijs/Desktop/" + path.substring(path.lastIndexOf("/") + 1);
    }
}

class DoubleDownThread extends Thread {

    private int id;
    private URL url;
    private File file;
    private int block;

    public DoubleDownThread(int id, URL url, File file, int block) {
        super();
        this.id = id;
        this.url = url;
        this.file = file;
        this.block = block;
    }

    @Override
    public void run() {
        try {
            RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");

            int start = id * block;

            int end = (id + 1) * block - 1;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //...E/?!??? +-?I?L>>Y? +-o?
            conn.setConnectTimeout(5000);

            conn.setRequestMethod("GET");

            conn.setRequestProperty("Range", "bytes=" + start + "-" + end);

            if (conn.getResponseCode() == 206) {

                accessFile.seek(start);

                InputStream in = conn.getInputStream();
                byte[] bys = new byte[1024];
                int length = 0;
                while ((length = in.read(bys, 0, bys.length)) != -1) {
                    accessFile.write(bys, 0, length);
                }
                accessFile.close();
                in.close();
            }
            System.out.println(DoubleThreadDownload.DATE_FORMAT.format(new Date()) + (id + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
