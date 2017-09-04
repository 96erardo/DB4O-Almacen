/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Decorations;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;

/**
 *
 * @author GerardoAGL
 */
public class ImageBorder implements Border{
    
    private BufferedImage image;
    private int imageWidth;
    private double imageHeight;
    private double percentage;
    private double imageCenter;
    
    public ImageBorder(BufferedImage image){
        
        this.image = image;
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();
        this.imageCenter = image.getWidth()/2;
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if(image != null){
            
            percentage = height/this.imageHeight;
            int newWidth = (int)Math.round(this.imageWidth * percentage);
            int center = center(width, newWidth);
            System.out.println(center);
            g.drawImage(image, center, 0, newWidth, height, null);
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
    
    private int center(int containerWidth, int newWidth){
        int center = (int)Math.round((containerWidth/2) - (newWidth/2));
        return center;
    }
    
}
