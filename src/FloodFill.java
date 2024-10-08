import javax.swing.*;

import Queue.Queue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Queue.*;
import Stack.*;


public class FloodFill {
	private BufferedImage img;
    private ImageMatrix imageMatrix;
    private DoublyLinkedList<BufferedImage> images = new DoublyLinkedList<>();
    private DynamicStack<Pixel> stack;
    private DynamicQueue<Pixel> queue;
    private View view;
    
    public FloodFill(BufferedImage Image, View view) {
    	this.img = Image;
        this.imageMatrix = new ImageMatrix(Image);
        this.stack = new DynamicStack<Pixel>(4);
        this.queue = new DynamicQueue<Pixel>(4);
        this.view = view;
    }

    public static boolean coordenadaValida(int x, int y, int largura, int altura) {
        return x >= 0 && x < largura && y >= 0 && y < altura;
    }
    
    public BufferedImage getImg() {
        return this.img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
    
    public int getImgWidth() {
        return this.img.getWidth();
    }

    public int getImgHeight() {
        return this.img.getHeight();
    }

    public void queueAlgorithm(int x, int y, Color newColor) {
    	if (!coordenadaValida(x, y, imageMatrix.Length, imageMatrix.Height)) {
            System.out.println("Coordenada Invalida.");
            return;
        }
    	
    	try {
    		Pixel firstPixel = imageMatrix.findPixel(x, y);
    		Color oldColor = firstPixel.getColor();
    		
    		if (oldColor.equals(newColor)) {
                System.out.println("Cores iguais.");
                return;
            }
    		
        	this.queue.enqueue(firstPixel);
        	
        	int iteration = 0;
        	while (!this.queue.isEmpty()) {
        		Pixel dequeuedPixel = queue.dequeue();
        		int px = dequeuedPixel.getX();
        		int py = dequeuedPixel.getY();
        		
        		if (px < 0 || px >= img.getWidth() || py < 0 || py >= img.getHeight()) {
        		    continue;
        		}
        		
        		if (dequeuedPixel.getColor().equals(oldColor)) {
        			img.setRGB(px, py, newColor.getRGB());
        			dequeuedPixel.setColor(newColor);

        			enqueueIfValid(px + 1, py, oldColor);
        			enqueueIfValid(px - 1, py, oldColor);
        			enqueueIfValid(px, py + 1, oldColor);
        			enqueueIfValid(px, py - 1, oldColor);
        			
                    
                    
                    if(iteration % 2 == 0) {
                    	view.updateImageDisplay();
                    }
                    
                    iteration++;
                }
        	}
        	imageMatrix.saveImageToFile("./flood_fill_output.png");
        	imageMatrix.printMatrix();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void stackAlgorithm(int x, int y, Color newColor) {
    	if (!coordenadaValida(x, y, imageMatrix.Length, imageMatrix.Height)) {
            System.out.println("Coordenada Invalida.");
            return;
        }
    	
    	try {

    		Pixel firstPixel = imageMatrix.findPixel(x, y);
    		Color oldColor = firstPixel.getColor();
    		
    		if (oldColor.equals(newColor)) {
                System.out.println("Cores iguais.");
                return;
            }
    		
    		stack.push(firstPixel);

    		int iteration = 0;
	        while (!stack.isEmpty()) {
	        	Pixel poppedPixel = stack.pop();
	            
	        	int px = poppedPixel.getX();
        		int py = poppedPixel.getY();
	
	            if (px < 0 || px >= img.getWidth() || py < 0 || py >= img.getHeight()) {
	                continue;
	            } 
	
	            if (poppedPixel.getColor().equals(oldColor)) {
	                img.setRGB(px, py, newColor.getRGB());
	                poppedPixel.setColor(newColor);
	                
	                pushIfValid(px + 1, py, oldColor);
	                pushIfValid(px, py + 1, oldColor);
	                pushIfValid(px - 1, py, oldColor);
	                pushIfValid(px, py - 1, oldColor);
	                
	                if(iteration % 20 == 0) {
                    	view.updateImageDisplay();
                    }
                    
                    iteration++;
	            }
	        }
	        imageMatrix.saveImageToFile("./flood_fill_output.png");
        	imageMatrix.printMatrix();
        	
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public BufferedImage imageMatrixToImage() throws Exception {
        BufferedImage img = new BufferedImage(imageMatrix.Length, imageMatrix.Height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < imageMatrix.Length; x++) {
            for (int y = 0; y < imageMatrix.Height; y++) {
                Pixel pixel = imageMatrix.findPixel(x, y);
                img.setRGB(x, y, pixel.getColor().getRGB());
            }
        }
        return img;
    }

    public void displayImages() {
        JFrame frame = new JFrame("Flood Fill Timelapse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(imageMatrix.Length, imageMatrix.Height);
        frame.setVisible(true);

        Node<BufferedImage> current = images.getBase();
        while (current != null) {
            try {
            	frame.getContentPane().getGraphics().drawImage(current.data, 0, 0, null);
                current = current.next;
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
           
        }
    }
    
    private void enqueueIfValid(int x, int y, Color oldColor) {
        try {
            Pixel neighborPixel = imageMatrix.findPixel(x, y);
            if (neighborPixel != null && neighborPixel.getColor().equals(oldColor) && !this.queue.contains(neighborPixel)) {
                this.queue.enqueue(neighborPixel);
                System.out.println("enqueued: " + neighborPixel.getX() + "," + neighborPixel.getY());
            }
        } catch (Exception e) {

            System.out.println("Erro ao encontrar pixel nas coordenadas (" + x + "," + y + ")");
        }
    }
    
    private void pushIfValid(int x, int y, Color oldColor) {
        try {
            Pixel neighborPixel = imageMatrix.findPixel(x, y);
            if (neighborPixel != null && neighborPixel.getColor().equals(oldColor) && !this.stack.contains(neighborPixel)) {
                this.stack.push(neighborPixel);
                System.out.println("pushed: " + neighborPixel.getX() + "," + neighborPixel.getY());
            }
        } catch (Exception e) {
            System.out.println("Erro ao encontrar pixel nas coordenadas (" + x + "," + y + ")");
        }
    }
}

