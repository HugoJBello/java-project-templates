package webcam_controlers;
 
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
@ManagedBean(name="fileDownloadView")
public class FileDownloadView {
     
    private StreamedContent file;
	
    @PostConstruct
    public void init() {        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/optimus.jpg");
        file = new DefaultStreamedContent(stream, "/home/hugo/captured_photos/20161008-17:53:46.jpg");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}