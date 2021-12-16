/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class GameArea extends JPanel 
{
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    
    private Color[][] background;
    
    private TetrisBlock block;
    
    
    public GameArea(JPanel placeholder, int columns){
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
        
        background = new Color[gridRows][gridColumns];
        
        
        
}
    
    public void spawnBlock(){
        block = new TetrisBlock(new int[][] { {1,0}, {1,0} , {1,1}}, Color.blue);
        
        block.spawn(gridColumns);
        
}
    
    public boolean moveBlockDown(){
        
        if (checkBottom() == false){
            moveBlockToBackground();
            return false;
        }
        
        block.moveDown();
        repaint();
        return true;
    }
    
    public void moveBlockRight(){
        
        if (checkRight() == false){
            return;
        }
        
        block.moveRight();
        repaint();
        
    
}
    public void moveBlockLeft(){
        
        if (checkLeft() == false){
            return;
        }
        
        block.moveLeft();
        repaint();
    
}
    public void dropBlock(){
        
        while ( checkBottom()){
            block.moveDown();
        }
        
        repaint();
    
}
    public void roatateBlock(){
        
        block.rotate();
        repaint();
    
}
    
    public boolean checkBottom(){
        if( block.getBottomEdge() == gridRows){
            return false;
        }
        
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int col = 0; col < w; col++) {
            
            for (int row = h - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    
                    if (y<0) {
                        break;
                        
                    }
                    
                    if (background[y][x] != null) {
                        return false;   
                        
                    }
                    break;
                    
                }
                
            }
            
        }
        
        
        return true;
        
    }
    
    public boolean checkLeft(){
        if(block.getLeftEdge() == 0){
            return false;
        }
        
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int row = 0; row < h; row++) {
            
            for (int col = 0; col < w; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    
                    if (y<0) {
                        break;
                        
                    }
                    
                    if (background[y][x] != null) {
                        return false;   
                        
                    }
                    break;
                    
                }
                
            }
            
        }
        
        return true;
        
    }
    
    public boolean checkRight(){
        if(block.getRightEdge() == gridColumns){
            return false;
        }
        
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int row = 0; row < h; row++) {
            
            for (int col = w - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    
                    if (y<0) {
                        break;
                        
                    }
                    
                    if (background[y][x] != null) {
                        return false;   
                        
                    }
                    break;
                    
                }
                
            }
            
        }
        
        return true;
        
    }
    
    private void moveBlockToBackground(){
        
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int xPos = block.getX();
        int yPos = block.getY();
        
        Color color = block.getColor();
        
        for (int r = 0; r < h; r++){
            for (int c = 0; c < w; c++){
                if(shape[r][c] == 1){
                    
                    background[r+yPos][c+xPos]= color;
                }
            }
        }
        
    }
    
    private void drawBlock(Graphics g)
    {
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int[][] shape = block.getShape();
        
        
        
        
        for(int row = 0; row<h; row++){
            for(int col = 0; col<w; col++){
                if (shape[row][col] == 1 ){
                    
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;
                    
                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }
    
    private void drawBackground(Graphics g){
        
        Color color;
        
        for(int r = 0; r<gridRows; r++){
                for(int c = 0; c<gridColumns; c++){
                    
                    color = background[r][c];
                    
                    if(color !=null){
                        int x = c * gridCellSize;
                        int y = r * gridCellSize;
                    
                        drawGridSquare(g, color, x, y);
                }
            }
        }
    }
    
    private void drawGridSquare(Graphics g, Color color, int x, int y){
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
            drawBackground(g);
            drawBlock(g);
        }
    }
