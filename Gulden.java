import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gulden here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gulden extends Actor
{
    SimpleTimer timer = new SimpleTimer();
    /**
     * Act - do whatever the Gulden wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img = new GreenfootImage("coin.png");
    
    public Gulden(){
        setImage(img);
    }
    
    public void disappear(){
        if(timer.millisElapsed() > 1500){
            img = null;
            getWorld().removeObject(this);    
        }
    }
    
    public void floatUp(){
        setLocation(getX(), getY()-1);
    }
    
    public void act(){
        floatUp();
        disappear();
    }
}
