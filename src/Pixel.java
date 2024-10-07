
import java.awt.Color;
import java.awt.image.BufferedImage;



public class Pixel {
	private int x, y;
    private Color color;

    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Color getColor() {
    	return this.color;
    }
    
    public void setColor(Color color) {
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
