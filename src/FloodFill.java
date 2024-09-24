
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class FloodFill {
	public ImageMatrix grid;
	
	public FloodFill(BufferedImage Image) {
		this.grid = new ImageMatrix(Image);
	}


    public static boolean coordenadaValida(int x, int y, int largura, int altura) {
        return x >= 0 && x < largura && y >= 0 && y < altura;
    }

    public void algorithm(int x, int y, Color newColor ) {
    	if(!coordenadaValida(x, y, grid.Length, grid.Height)) {
    		System.out.println("Coordenada Invalida.");
    		return;
    	}
    	
    	try {
    				
    	Pixel pixel = grid.findPixel(x, y);
    	
    	Color oldColor = pixel.color;

    	
    	if(oldColor.equals(newColor)) {
    		System.out.println("Cores iguais.");
    		return;
    	}

    	System.out.println("Initial Matrix:");
    	grid.printMatrix();
    	
    	Queue<Pixel> PixelQueue = new Queue<Pixel>();
    	PixelQueue.enqueue(pixel);
    	
    	while (!PixelQueue.isEmpty()) {
    		Pixel currentPixel = PixelQueue.dequeue();
    		System.out.println("current pixel: " + currentPixel.x +"," + currentPixel.y);
    		if(!currentPixel.color.equals(newColor)) {
    			currentPixel.color = newColor;
    			System.out.println("pintou: " + currentPixel.x +"," + currentPixel.y);
    		}
    			
    			
    			if((currentPixel.x + 1) < grid.Length) {
    				enqueueIfValid((currentPixel.x + 1), currentPixel.y, oldColor, PixelQueue);
    			}
    			if((currentPixel.x - 1) >= 0) {
    				enqueueIfValid((currentPixel.x - 1), currentPixel.y, oldColor, PixelQueue);
    			}
    			if((currentPixel.y + 1) < grid.Height) {
    				enqueueIfValid(currentPixel.x, (currentPixel.y + 1), oldColor, PixelQueue);
    			}
    			if((currentPixel.y - 1) >= 0) {
    				enqueueIfValid(currentPixel.x, (currentPixel.y - 1), oldColor, PixelQueue);
    			}
    			
    			System.out.println("queue after checking adjacent pixels:");
        		PixelQueue.printQueue();
    	}
    	
    	System.out.println("Final Matrix:");
    	grid.printMatrix();
    	
    	PixelQueue.printQueue();
    	
    	grid.saveImageToFile("flood_fill_ouput");
    	
    	} catch(Exception e) {
    		System.out.println("erro ao encontrar cor do pixel nas coordenadas (" + x + "," + y + ")");
    		e.printStackTrace();
    	}

    }
    
    private void enqueueIfValid(int x, int y, Color oldColor, Queue<Pixel> pixelQueue) {
        try {
            Pixel neighborPixel = grid.findPixel(x, y);
            if (neighborPixel != null && neighborPixel.color.equals(oldColor) && !pixelQueue.contains(neighborPixel)) {
                pixelQueue.enqueue(neighborPixel);
                System.out.println("enqueued: " + neighborPixel.x + "," + neighborPixel.y);
            }
        } catch (Exception e) {

            System.out.println("Erro ao encontrar pixel nas coordenadas (" + x + "," + y + ")");
        }
    }
}
