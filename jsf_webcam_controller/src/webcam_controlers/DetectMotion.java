package webcam_controlers;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;

 
@ManagedBean(name = "detectMotion")
@SessionScoped
public class DetectMotion {

	private static final int INTERVAL = 100; // ms

	private Webcam webcam = Webcam.getDefault();
	private int threshold = 25;
	private int inertia = 1000; // how long motion is valid
	private String stopInSeconds;
	private String destinyFolder = System.getProperty("user.home")
			+ "/captured_photos";
	private ArrayList<String> listOfObtaiedImages = new ArrayList<String>();

	public String record() {

		ExecutorService executor = Executors.newFixedThreadPool(1);

		// webcam.getLock().disable();
		//
		// webcam.close();
		// webcam.open();
		executor.execute(new Runnable() {
			public void run() {
				//possible ones are [176x144] [320x240] [640x480]
				//webcam.setViewSize(new Dimension(640, 480));
				webcam.setViewSize(new Dimension(640, 480));
				WebcamMotionDetector detector = new WebcamMotionDetector(webcam);
				detector.setInterval(INTERVAL);
				detector.start();

				

				DateFormat dateFormat_day = new SimpleDateFormat("yyyyMMdd");
				Date date2 = new Date();

				// crating destiny folder if it doesnt exists
				File destinyFoldercheck = new File(destinyFolder);
				if (!destinyFoldercheck.exists()) {
					boolean result = false;

					try {
						destinyFoldercheck.mkdir();
						result = true;
					} catch (SecurityException se) {
						// handle it
					}
					if (result) {
						System.out.println("outputoFolder crated created");
					}
				}

				File todaysFolder = new File(destinyFolder + "/"
						+ dateFormat_day.format(date2).toString().replace(":", "_"));

				// if the directory does not exist, create it
				if (!todaysFolder.exists()) {
					boolean result = false;

					try {
						todaysFolder.mkdir();
						result = true;
					} catch (SecurityException se) {
						// handle it
					}
					if (result) {
						System.out.println("DIR created");
					}
				}

				long startTime = System.currentTimeMillis();
				long endTime = startTime + Integer.parseInt(stopInSeconds)
						* 1000;
				while (System.currentTimeMillis() < endTime) {
					if (detector.isMotion()) {

						BufferedImage image = webcam.getImage();

 
						DateFormat dateFormat_with_hour = new SimpleDateFormat(
								"yyyyMMdd-HH_mm_ss");
						Date date = new Date();

						String filename = destinyFolder + "/"
								+ dateFormat_day.format(date2) + "/"
								+ dateFormat_with_hour.format(date).replace(":", "_") + ".jpg";
						listOfObtaiedImages.add(filename);
						System.out.println(filename);

						try {
							BufferedOutputStream imageOutputStream = new BufferedOutputStream(
									new FileOutputStream(new File(filename)));
							ImageIO.write(image, "JPG", imageOutputStream);
							imageOutputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					try {
						Thread.sleep(INTERVAL * 2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		webcam.close();
		webcam.getLock().disable();
		return null;
	}

	public String getStopInSeconds() {
		return stopInSeconds;
	}

	public void setStopInSeconds(String stopInSeconds) {
		this.stopInSeconds = stopInSeconds;
	}

	public String getDestinyFolder() {
		return destinyFolder;
	}

	public void setDestinyFolder(String destinyFolder) {
		this.destinyFolder = destinyFolder;
	}

	public ArrayList<String> getListOfObtaiedImages() {
		return listOfObtaiedImages;
	}

	public void setListOfObtaiedImages(ArrayList<String> listOfObtaiedImages) {
		this.listOfObtaiedImages = listOfObtaiedImages;
	}

}
