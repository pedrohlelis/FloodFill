import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class View extends JFrame {
	   	private JLabel imageLabel;
	   	private BufferedImage image;
	   	private FloodFill floodFill;
	    private JButton selectImageButton, selectColorButton, stackFloodFillButton, queueFloodFillButton;
	    private JTextField xField, yField;
	    private Color selectedColor;

	    public View() {
	    	
	        setTitle("Flood Fill Image");
	        setSize(800, 600);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        
	        imageLabel = new JLabel();
	        imageLabel.setHorizontalAlignment(JLabel.CENTER);
	        imageLabel.setVerticalAlignment(JLabel.CENTER);

	        
	        xField = new JTextField(5);
	        yField = new JTextField(5);
	        
	        
	        selectImageButton = new JButton("Select Image");
	        selectImageButton.addActionListener(e -> selectImage());

	        selectColorButton = new JButton("Select Color");
	        selectColorButton.addActionListener(e -> selectColor());

	        queueFloodFillButton = new JButton("Flood Fill using QUEUE");
	        queueFloodFillButton.addActionListener(e -> {
	            if (image != null && selectedColor != null) {
	                try {
	                    int startX = Integer.parseInt(xField.getText());
	                    int startY = Integer.parseInt(yField.getText());
	                    
	                    performQueueFloodFill(startX, startY, selectedColor);
	                    imageLabel.repaint();
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(this, "Invalid coordinates!", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "Please select an image and color before flood fill.");
	            }
	        });
	        
	        stackFloodFillButton = new JButton("Flood Fill using STACK");
	        stackFloodFillButton.addActionListener(e -> {
	            if (image != null && selectedColor != null) {
	                try {
	                    int startX = Integer.parseInt(xField.getText());
	                    int startY = Integer.parseInt(yField.getText());
	                    
	                    performStackFloodFill(startX, startY, selectedColor);
	                    imageLabel.repaint();
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(this, "Invalid coordinates!", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "Please select an image and color before flood fill.");
	            }
	        });

	        JPanel inputPanel = new JPanel();
	        inputPanel.add(new JLabel("X:"));
	        inputPanel.add(xField);
	        inputPanel.add(new JLabel("Y:"));
	        inputPanel.add(yField);
	        inputPanel.add(selectImageButton);
	        inputPanel.add(selectColorButton);
	        inputPanel.add(queueFloodFillButton);
	        inputPanel.add(stackFloodFillButton);

	        setLayout(new BorderLayout());
	        add(imageLabel, BorderLayout.CENTER);
	        add(inputPanel, BorderLayout.SOUTH);
	    }

	    private void selectImage() {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Select an image");
	        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));

	        int result = fileChooser.showOpenDialog(this);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();
	            try {
	                image = ImageIO.read(selectedFile);
	                imageLabel.setIcon(new ImageIcon(image));
	                this.floodFill = new FloodFill(image, this);
	            } catch (IOException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Error loading image", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }

	    
	    public void updateImageDisplay() {
	        imageLabel.setIcon(new ImageIcon(image));
	    }
	    
	    private void selectColor() {
	    	Color chosenColor = JColorChooser.showDialog(this, "Choose a Color", selectedColor);
	        if (chosenColor != null) {
	            selectedColor = chosenColor;  
	        }
	    }


	    private void performQueueFloodFill(int x, int y, Color replacementColor) {
	        new Thread(() -> {
	            try {
					floodFill.queueAlgorithm(x, y, replacementColor);
				} catch (Exception e) {

					e.printStackTrace();
				}
	        }).start();
	    }
	    
	    private void performStackFloodFill(int x, int y, Color replacementColor) {
	        new Thread(() -> {
	            try {
					floodFill.stackAlgorithm(x, y, replacementColor);
				} catch (Exception e) {

					e.printStackTrace();
				}
	        }).start();
	    }
}
