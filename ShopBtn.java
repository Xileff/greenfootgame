import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShopBtn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShopBtn extends Button
{
    /**
     * Act - do whatever the ShopBtn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootSound clickSound = new GreenfootSound("main-menu-button.wav");
    Player player;
    Archer archer;
    
    public ShopBtn(){
        
    }
    
    public ShopBtn(Player player){
        this.player = player;
    }
    
    public ShopBtn(Archer archer){
        this.archer = archer;
    }
    
    public void act()
    {
        clicked();
    }
    
    void clicked(){
        if(Greenfoot.mouseClicked(this)){
            getImage().scale((int)Math.round(getImage().getWidth()*0.9),(int)Math.round(getImage().getHeight()*0.9));
            clickSound.play();
            Greenfoot.delay(5);
            if(this.player != null){
                Greenfoot.setWorld(new Shop(this.player));
            }
            else if(this.archer != null){
                Greenfoot.setWorld(new Shop(this.archer));
            }
        }
    }
}
