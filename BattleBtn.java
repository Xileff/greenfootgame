import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleBtn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleBtn extends Button
{
    /**
     * Act - do whatever the BattleBtn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootSound clickSound = new GreenfootSound("main-menu-button.wav");
    Character character;
    Player player;
    Archer archer;
    
    public void act()
    {
        clicked();
    }
    
    void clicked(){
        if(Greenfoot.mouseClicked(this)){
            clickSound.play();
            getImage().scale((int)Math.round(getImage().getWidth()*0.9),(int)Math.round(getImage().getHeight()*0.9));
            Greenfoot.delay(5);
            if(!getWorld().getObjects(Player.class).isEmpty()){
                this.player = getWorld().getObjects(Player.class).get(0);
                Greenfoot.setWorld(new ChooseJob(this.player));    
            }
            else if (!getWorld().getObjects(Archer.class).isEmpty()) {
                this.archer = getWorld().getObjects(Archer.class).get(0);
                Greenfoot.setWorld(new ChooseJob(this.archer));    
            }
            else {
                Greenfoot.setWorld(new ChooseJob());    
            }
        }
    }
}
