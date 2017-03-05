package webcam_controlers;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "pictureBean")
@SessionScoped
public class PictureBean {
	private String file ="/home/hugo/Downloads/20161007-19-59-08(3).jpg";
    private StreamedContent myImage;
    
    @PostConstruct
    public void init() throws IOException {
         StreamedContent myImage = new DefaultStreamedContent(new FileInputStream(new File(file)));
    }

    public StreamedContent getMyImage() {
        return myImage;
    }

    public void setMyImage(StreamedContent myImage) {
        this.myImage = myImage;
    }

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}