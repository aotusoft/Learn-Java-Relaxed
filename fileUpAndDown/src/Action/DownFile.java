package Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.InputStream;

public class DownFile  extends ActionSupport {
    public InputStream getDownloadFile(){
        return ServletActionContext.getServletContext().getResourceAsStream("/upload/1.txt");
    }
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
