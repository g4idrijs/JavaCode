import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Test_Save{
    public static void main(String[] strs) throws Exception {
        System.out.println("\n**********");
        String[] arrs={
                "zhangsan,23,福建",
                "lisi,30,上海",
                "wangwu,43,北京",
                "laolin,21,重庆",
                "ximenqing,67,贵州"
        };
    	FileOutputStream fos = null;
		fos = new FileOutputStream(new File("aa.txt"),false);
		
		OutputStreamWriter oswutf = null;
		oswutf = new OutputStreamWriter(fos, "utf-8");
        BufferedWriter  bwutf=new BufferedWriter(oswutf);
        bwutf.write("\n:::::utf-8:::::\n\n");
        for(String arr:arrs){
			bwutf.write(arr+"\t\n");
        }
        bwutf.close();
	oswutf.close();
	fos.close();
        System.out.println("输出完成！");

		/*fos = new FileOutputStream(new File("aa.txt"),true);
        OutputStreamWriter osw = null;
		osw = new OutputStreamWriter(fos, "gbk");
        BufferedWriter  bw=new BufferedWriter(osw);
        bw.write("\n\n\n:::::gbk:::::\n\n");
        for(String arr:arrs){
			bw.write(arr+"\t\n");
        }
        
		bw.close();
		osw.close();
		fos.close();*/
    }
}