
import java.awt.Color;
import java.awt.image.BufferedImage;



public class Pixel {
	int x, y;
    Color color;

    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public String toString() {
    	if(color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255) {
    		return "("+ x + ", "+ y +", 1"+")";
        }
        else if(color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 255) {
        	return "("+ x + ", "+ y +", 2"+")";
        }
        else {
        	return "("+ x + ", "+ y +", 0"+")";
        }
        
    }
}
