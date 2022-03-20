import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bulletSplat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bulletSplat extends Actor
{
    /**
     * Act - do whatever the bulletSplat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SimpleTimer timer = new SimpleTimer();
    
    public void act()
    {
        // Add your action code here.
        if(timer.millisElapsed() > 300){
            getWorld().removeObject(this);
            timer.mark();
            return;
        }
    }
}
