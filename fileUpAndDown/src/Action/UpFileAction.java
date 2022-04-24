package Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.*;

public class UpFileAction extends ActionSupport {
    private static final  long  serialVersionUID=1L;
    private  File file;             //用户上传的文件
    private  String  fileFileName;  //用户上传的文件名
    private  String  fileContentType; //上传文件的类型

    public  String  execute() throws  Exception{
        System.out.println(file.getName());
        InputStream is=new FileInputStream(file);//文件输入流
        String uploadPath= ServletActionContext.getServletContext().getRealPath("/upload");
        File toFile=new File(uploadPath,this.getFileFileName());
        OutputStream os=new FileOutputStream(toFile);
        byte[] buffer=new byte[1024];
        int len=0;
        while (-1!=(len=is.read(buffer,0,buffer.length))){
            os.write(buffer);
        }
        is.close();//关闭输入流
        os.close();//关闭输出流
        return   SUCCESS;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
}
