package Controller;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import Model.Pixel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class MainController implements Initializable{

	@FXML private ImageView imageBefore;
	@FXML private ImageView imageAfter;
	@FXML private Slider slider;
	@FXML private Button edit;
	@FXML private Button load;
	
	private List<Pixel> pixelList = new ArrayList<Pixel>();
	private Image img;
	private PixelReader pixelReader;
	private PixelWriter pixelWriter;
	private WritableImage wimg;
	
	public void loadImage() throws IOException {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(null);
		FileInputStream fis = new FileInputStream(file);
		this.img = new Image(fis);
		imageBefore.setImage(img);
		this.pixelReader = img.getPixelReader();
		this.wimg = new WritableImage((int)img.getWidth(),(int)img.getHeight());
		this.pixelWriter = wimg.getPixelWriter();
		
		}
        
	public void enableEdit () {
		
	}
	
	public void setBrightness() {
		for(int readY=0;readY<img.getHeight();readY++) {
           
			for(int readX=0; readX<img.getWidth();readX++) {
                Color color = pixelReader.getColor(readX,readY);
                if(slider.getValue()>0) {
                	color = color.deriveColor(0, 0, slider.getValue()/10, 0);
                	pixelWriter.setColor(readX,readY,color);
                	
                }
                if(slider.getValue()<0) {
                    	
                	color = color.deriveColor(1, 1, 1+slider.getValue()/10, 1);
                    pixelWriter.setColor(readX,readY,color);
                    
                }
            }
        }
		imageAfter.setImage(wimg);
	}
	
	
		@Override
	public void initialize(URL location, ResourceBundle resources) {
		
			
		}
		
	}