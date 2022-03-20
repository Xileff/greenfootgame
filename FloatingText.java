import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DamageText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FloatingText extends Actor
{
    /**
     * Act - do whatever the DamageText wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    SimpleTimer timer = new SimpleTimer();
    int millisDisappear = 0;
    
    public FloatingText(String text, Color color, int millisDisappear){
        this.millisDisappear = millisDisappear;
        img = new GreenfootImage(text.length() * 20, 30);
        img.setColor(color);
        img.drawString(text, 2, 20);
        img.scale(img.getWidth()*5, img.getHeight()*5);
        setImage(img);
    }
    
    public void disappear(){
        //500
        if(timer.millisElapsed() > this.millisDisappear){
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