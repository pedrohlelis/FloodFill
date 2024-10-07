import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class FloodFillImage extends JFrame {
	   private JLabel imageLabel;
	    private BufferedImage image;
	    private JButton selectImageButton, selectColorButton, floodFillButton;
	    private JTextField xField, yField;
	    private Color selectedColor;

	    public FloodFillImage() {
	        setTitle("Flood Fill Image");
	        setSize(800, 600);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); // Center the frame
	        
	        // Create label for image
	        imageLabel = new JLabel();
	        imageLabel.setHorizontalAlignment(JLabel.CENTER);
	        imageLabel.setVerticalAlignment(JLabel.CENTER);

	        // Create text fields for x and y coordinates
	        xField = new JTextField(5);
	        yField = new JTextField(5);
	        
	        // Buttons
	        selectImageButton = new JButton("Select Image");
	        selectImageButton.addActionListener(e -> selectImage());

	        selectColorButton = new JButton("Select Color");
	        selectColorButton.addActionListener(e -> selectColor());

	        floodFillButton = new JButton("Flood Fill");
	        floodFillButton.addActionListener(e -> {
	            if (image != null && selectedColor != null) {
	                try {
	                    int startX = Integer.parseInt(xField.getText());
	                    int startY = Integer.parseInt(yField.getText());
	                    performFloodFill(startX, startY, selectedColor);
	                    imageLabel.repaint();
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(this, "Invalid coordinates!", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "Please select an image and color before flood fill.");
	            }
	        });

	        // Panel for coordinates and buttons
	        JPanel inputPanel = new JPanel();
	        inputPanel.add(new JLabel("X:"));
	        inputPanel.add(xField);
	        inputPanel.add(new JLabel("Y:"));
	        inputPanel.add(yField);
	        inputPanel.add(selectImageButton);
	        inputPanel.add(selectColorButton);
	        inputPanel.add(floodFillButton);

	        // Layout
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
	            } catch (IOException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Error loading image", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }

	    private void selectColor() {
	        selectedColor = JColorChooser.showDialog(this, "Choose a Color", Color.BLACK);
	    }

	    private void performFloodFill(int x, int y, Color newColor) {
	        if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()) {
	            JOptionPane.showMessageDialog(this, "Invalid coordinates!", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        int targetColor = image.getRGB(x, y);
	        int replacementColor = newColor.getRGB();

	        if (targetColor != replacementColor) {
	            floodFillRecursive(x, y, targetColor, replacementColor);
	        }
	    }

	    private void floodFillRecursive(int x, int y, int targetColor, int replacementColor) {
	        if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()) return;

	        if (image.getRGB(x, y) != targetColor) return;

	        image.setRGB(x, y, replacementColor);

	        floodFillRecursive(x + 1, y, targetColor, replacementColor);
	        floodFillRecursive(x - 1, y, targetColor, replacementColor);
	        floodFillRecursive(x, y + 1, targetColor, replacementColor);
	        floodFillRecursive(x, y - 1, targetColor, replacementColor);
	    }
}
