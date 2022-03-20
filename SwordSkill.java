import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Skill here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SwordSkill extends PlayerAttack
{
    /**
     * Act - do whatever the Skill wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SimpleTimer timer = new SimpleTimer();
    GreenfootSound soundSlash = new GreenfootSound("BasicAttack_Sword.mp3");
    GreenfootSound soundSplat = new GreenfootSound("SwordSlash.mp3");
    //GifImage gifSwordSkill = new GifImage("swordSkill.gif");
    Player player;
    int ATK = 0;
    
    SwordSkill(Player player){
        this.ATK = 3 * player.ATK;
    }
    
    public void act()
    {
        // Add your action code here.
        //setImage(gifSwordSkill.getCurrentImage());
        Boss boss = (Boss) getOneIntersectingObject(Boss.class);
        Miniboss miniboss = (Miniboss) getOneIntersectingObject(Miniboss.class);
        Normal_Enemy normalEnemy = (Normal_Enemy) getOneIntersectingObject(Normal_Enemy.class);
        soundSlash.setVolume(70);
        
        if(boss != null){    
            soundSlash.play();
            getWorld().removeObject(this);
            return;
        }
        
        else if(miniboss != null){
            soundSlash.play();
            getWorld().removeObject(this);
            return;
        }
        
        else if(normalEnemy != null){
            soundSlash.play();
            getWorld().removeObject(this);
            return;
        }
        
        else if(timer.millisElapsed() > 500){
            timer.mark();
            getWorld().removeObject(this);
            return;
        }
    }
}
