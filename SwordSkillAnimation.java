import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SwordSkillAnimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SwordSkillAnimation extends PlayerAttack
{
    /**
     * Act - do whatever the SwordSkillAnimation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GifImage gifSwordSkill = new GifImage("swordSkill1.gif");
    SimpleTimer timer = new SimpleTimer();
    
    public void act()
    {
        // Add your action code here.
        setImage(gifSwordSkill.getCurrentImage());
        if(timer.millisElapsed() > 400){
            getWorld().removeObject(this);
        }
    }
}
