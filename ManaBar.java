import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ManaBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ManaBar extends Actor
{
    /**
     * Act - do whatever the ManaBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Player player;
    Archer archer;
    int mana = 0;
    int manaBarWidth = 100;
    int manaBarHeight = 15;
    int pixelsPerMana = 0;
    
    public void act()
    {
        // Add your action code here.
        updatePlayerMana();
        updateArcherMana();
    }
    
    ManaBar(Player player){
        this.player = player;
        this.mana = player.mana;
        pixelsPerMana = (int)manaBarWidth/mana;
    }
    
    ManaBar(Archer archer){
        this.archer = archer;
        this.mana = archer.mana;
        pixelsPerMana = (int)manaBarWidth/mana;
    }
    
    void update(){
        setImage(new GreenfootImage(manaBarWidth + 2, manaBarWidth + 2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.WHITE);
        myImage.drawRect(0, 0, manaBarWidth + 1, manaBarHeight + 1);
        if((this.player!=null) || (this.archer!=null)){
            myImage.setColor(Color.CYAN);
        }
        myImage.fillRect(1, 1, mana * pixelsPerMana, manaBarHeight);
    }
    
    void loseMana(int manaLost){
        mana -= manaLost;
    }
    
    void gainMana(int manaGained){
        mana += manaGained;
    }
    
    void updatePlayerMana(){
        if(this.player != null){
            player.manaBar.update();
        }
    }
    
    void updateArcherMana(){
        if(this.archer != null){
            archer.manaBar.update();
        }
    }
    
    void updateArcherHP(){
        if(this.archer != null){
            archer.hpBar.update();
        }
    }
}
