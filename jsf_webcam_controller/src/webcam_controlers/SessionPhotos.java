package webcam_controlers;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "sessionPhotos")
@SessionScoped
public class SessionPhotos {
	private  ArrayList<String> listOfFilesString = new ArrayList<String>();
    
	@PostConstruct
	public void ObtainPhotosFromFolder(){
		filesFromFolder generateList = new filesFromFolder("/home/hugo/captured_photos");
		listOfFilesString = generateList.getListOfFilesString(); 
	}

	public ArrayList<String> getListOfFilesString() {
		return listOfFilesString;
	}

	public void setListOfFilesString(ArrayList<String> listOfFilesString) {
		this.listOfFilesString = listOfFilesString;
	}

}
