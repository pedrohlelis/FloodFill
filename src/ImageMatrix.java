
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
                // Obtém a cor do pixel (x, y)
                Color pixelColor = new Color(Image.getRGB(x, y));

                // Cria um novo Pixel com suas coordenadas e cor
                Pixel pixel = new Pixel(x, y, pixelColor);

                newList.add(pixel);
            }
        }
	}
	
	public Pixel findPixel(int x, int y) throws Exception {
//		if(x >= Length || y >= Height || x < 0 || y < 0) {
//			return null;
//		}
		
		DoublyLinkedList<Pixel> Yrow = (DoublyLinkedList<Pixel>) pixelMatrix.get(y);
		
		
        return (Pixel) Yrow.get(x);
    }
	
	public void printMatrix() throws Exception {
	    for (int i = 0; i < pixelMatrix.size(); i++) {
	        
	        DoublyLinkedList<Pixel> row = (DoublyLinkedList<Pixel>) pixelMatrix.get(i);
	        
	        for (int j = 0; j < row.size(); j++) {
	            Pixel pixel = row.get(j);
	            
	            if(pixel.color.getRed() == 255 && pixel.color.getGreen() == 255 && pixel.color.getBlue() == 255) {
	            	System.out.print(1 + " ");
	            }
	            else if(pixel.color.getRed() == 255 && pixel.color.getGreen() == 0 && pixel.color.getBlue() == 255) {
	            	System.out.print(2 + " ");
	            }
	            else {
	            	System.out.print(0 + " ");
	            }
	        }
	        
	        
	        System.out.println();
	    }
	}
	
	public BufferedImage generateImage() throws Exception {
	    // Create a new BufferedImage with the same dimensions as the matrix
	    BufferedImage newImage = new BufferedImage(Length, Height, BufferedImage.TYPE_INT_RGB);
	    
	    // Loop through each pixel in the matrix and set the corresponding pixel in the image
	    for (int y = 0; y < Height; y++) {
	        DoublyLinkedList<Pixel> row = (DoublyLinkedList<Pixel>) pixelMatrix.get(y);
	        
	        for (int x = 0; x < Length; x++) {
	            Pixel pixel = row.get(x);
	            
	            // Get the color of the current pixel
	            Color color = pixel.color;
	            
	            // Set the RGB value of the pixel in the BufferedImage
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
}
