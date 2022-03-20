import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackBtn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackBtn extends Button
{
    /**
     * Act - do whatever the AttackBtn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Chars character;
    
    public void act()
    {
        Chars character = getWorld().getObjects(Chars.class).get(0);
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)){
            getImage().scale((int)Math.round(getImage().getWidth()*0.9),(int)Math.round(getImage().getHeight()*0.9));
        }
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.delay(5);
            Greenfoot.setWorld(new Stage1(character));
        }
    }
}
