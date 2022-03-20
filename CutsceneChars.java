import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneChars here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutsceneChars extends Actor
{
    /**
     * Act - do whatever the CutsceneChars wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    protected static int countRight = 0;
    protected static int countLeft = 0;
    protected static int countUp = 0;
    protected static int countDown = 0;
    protected static int countIdle = 0;
    protected static int animateIdleSpeed = 10;
    protected static int animateImage = 1;
    protected static int animateSpeed = 8;
      
    protected static String direction = "Right";
    
    public void act()
    {
        // Add your action code here.
        
    }
    
    //method buat jalan dll bikin di sini, di warisin ke subclassnya
    protected void moveUp(int destinationY){
        if (this.getY() != destinationY){
            this.setLocation(this.getX(), this.getY() - 1);
        }
    }
    
    protected void moveDown(int destinationY){
        if (this.getY() != destinationY){
            this.setLocation(this.getX(), this.getY() + 1);
        }
    }
    
    protected void moveRight(int destinationX){
        if (this.getX() != destinationX){
            this.setLocation(this.getX() + 1, this.getY());
        }
    }
    
    protected void moveLeft(int destinationX){
        if (this.getX() != destinationX){
            this.setLocation(this.getX() - 1, this.getY());
        }
    }
}
