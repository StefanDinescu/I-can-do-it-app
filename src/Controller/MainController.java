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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imageBefore.setCache(true);
		imageBefore.setCacheHint(CacheHint.SPEED);
		imageAfter.setCache(true);
		imageAfter.setCacheHint(CacheHint.SPEED);
		this.setBrightness();		
			
		}
	
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
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				ColorAdjust colorAdjust = new ColorAdjust();
				colorAdjust.setBrightness(slider.getValue()/10);
		    	imageAfter.setImage(img);
		    	imageAfter.setEffect(colorAdjust);
			}
		});
	}
}