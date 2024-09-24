
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args) {
		
		BufferedImage image;
		try {
			image = ImageIO.read(new File("./flood_fill_input7x5.png"));
			FloodFill flood = new FloodFill(image);
			
			Color newColor = new Color(255, 0, 255);
			
			flood.algorithm(6, 2, newColor);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
