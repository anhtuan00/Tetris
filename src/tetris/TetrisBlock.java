/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;

/**
 *
 * @author Admin
 */
public class TetrisBlock {
    
    private int [][] shape;
    private Color color;
    private int x,y;
    private int[][][] shapes;
    private int currentRotation;
    
    
    public TetrisBlock(int[][] shape, Color color ){
        this.shape = shape;
        this.color = color;
        
        initShapes();
        
    }
    
    private void initShapes(){
        shapes = new int[4][][];
        
        for (int i = 0; i < 4; i++) {
            
            int r = shape[0].length;
            int c = shape.length;
            
            shapes[i] = new int[r][c];
            
            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    
                    shapes[i][y][x] = shape[c - x - 1][y];
                    
                }
                
            }
            
            shape = shapes[i];
            
        }
    }
    
    public void spawn(int gridWidth){
        
        currentRotation = 0;
        shape = shapes[currentRotation];
        
        y= -getHeight();
        x = (gridWidth - getWidth()) / 2;
    }

    /**
     * @return the shape
     */
    public int[][] getShape() {
        return shape;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    public int getHeight(){
        return shape.length;
    }
    
    public int getWidth(){
        return shape[0].length; 
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
    public void moveDown(){
        y++;
    }
    
    public void moveLeft(){
        x--;
    }
    
    public void moveRight(){
        x++;
    }
    
    public void rotate(){
        currentRotation++;
        if (currentRotation >3) {
            currentRotation=0;
        }
            

        shape = shapes[currentRotation];
        
    }
    
    public int getBottomEdge(){
        return y + getHeight();
    }
    
    public int getLeftEdge(){
        return x;
    }
    
    public int getRightEdge(){
        return x + getWidth();
    }
    
}
