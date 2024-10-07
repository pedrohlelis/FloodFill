
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class ImageMatrix {
	public BufferedImage Image;
	public DoublyLinkedList pixelMatrix;
	public int Length;
	public int Height;
	
	public ImageMatrix(BufferedImage image) {
		this.Image = image;
		this.pixelMatrix = new DoublyLinkedList<DoublyLinkedList<Pixel>>();
		createMatrix();
	}
	
	private void createMatrix() {
        int width = Image.getWidth();
        int height = Image.getHeight();
        
        this.Length = width;
        this.Height = height;

        for (int y = 0; y < height; y++) {
        	DoublyLinkedList newList = new DoublyLinkedList<Pixel>();
        	pixelMatrix.add(newList);
            for (int x = 0; x < width; x++) {
                
                Color pixelColor = new Color(Image.getRGB(x, y));

                Pixel pixel = new Pixel(x, y, pixelColor);

                newList.add(pixel);
            }
        }
	}
	
	public Pixel findPixel(int x, int y) throws Exception {
		if (x < 0 || x >= Length || y < 0 || y >= Height) {
	        throw new Exception("Position out of bounds: (" + x + ", " + y + ")");
	    }
		
		DoublyLinkedList<Pixel> Yrow = (DoublyLinkedList<Pixel>) pixelMatrix.get(y);
		
		
        return (Pixel) Yrow.get(x);
    }
	
	public void printMatrix() throws Exception {
	    for (int i = 0; i < pixelMatrix.getSize(); i++) {
	        
	        DoublyLinkedList<Pixel> row = (DoublyLinkedList<Pixel>) pixelMatrix.get(i);
	        
	        for (int j = 0; j < row.getSize(); j++) {
	            Pixel pixel = row.get(j);
	            
	            if(pixel.getColor().getRed() == 255 && pixel.getColor().getGreen() == 255 && pixel.getColor().getBlue() == 255) {
	            	System.out.print(1 + " ");
	            }
	            else if(pixel.getColor().getRed() == 0 && pixel.getColor().getGreen() == 0 && pixel.getColor().getBlue() == 0) {
	            	System.out.print(0 + " ");
	            }
	            else {
	            	System.out.print(2 + " ");
	            }
	        }
	        
	        
	        System.out.println();
	    }
	}
	
	public BufferedImage generateImage() throws Exception {
	    BufferedImage newImage = new BufferedImage(Length, Height, BufferedImage.TYPE_INT_RGB);
	    
	    for (int y = 0; y < Height; y++) {
	        DoublyLinkedList<Pixel> row = (DoublyLinkedList<Pixel>) pixelMatrix.get(y);
	        
	        for (int x = 0; x < Length; x++) {
	            Pixel pixel = row.get(x);
	            
	            Color color = pixel.getColor();
	            
	            newImage.setRGB(x, y, color.getRGB());
	        }
	    }
	    
	    return newImage;
	}
	
	public void saveImageToFile(String filePath) throws Exception {
	    BufferedImage image = generateImage();
	    File outputFile = new File(filePath);
	    ImageIO.write(image, "png", outputFile);
	}
	
	public BufferedImage revertToImage() throws Exception {
        BufferedImage img = new BufferedImage(this.Length, this.Height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < this.Length; x++) {
            for (int y = 0; y < this.Height; y++) {
                Pixel pixel = findPixel(x, y);
                img.setRGB(x, y, pixel.getColor().getRGB());
            }
        }
        return img;
    }
}
