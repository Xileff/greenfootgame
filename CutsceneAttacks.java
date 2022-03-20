import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneAttacks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutsceneAttacks extends CutsceneChars
{
    /**
     * Act - do whatever the CutsceneAttacks wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    void moveUp(){
        setRotation(270);
        move(10);
    }
    
    void moveDown(){
        setRotation(90);
        move(10);
    }
    
    void moveRight(){
        setRotation(0);
        move(10);
    }
    
    void moveLeft(){
        setRotation(180);
        move(10);
    }
}
