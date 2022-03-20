import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BackBtn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackBtn extends Button
{
    /**
     * Act - do whatever the BackBtn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Player player;
    Archer archer;
    
    public BackBtn(){
        
    }
    
    public BackBtn(Player player){
        this.player = player;
    }
    
    public BackBtn(Archer archer){
        this.archer = archer;
    }
    
    public void act()
    {
        // Add your action code here.
        
    }
}
