import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scratch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scratch extends EnemyAttack
{
    /**
     * Act - do whatever the Scratch wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SimpleTimer timer = new SimpleTimer();
    GreenfootSound soundSplat = new GreenfootSound("SwordSlash.mp3");
    GreenfootImage ScratchImg = new GreenfootImage("scratch.png");
    int damage = 1;
    
    Scratch(){
        this.setImage(ScratchImg);
    }
    
    public void act()
    {
        // Add your action code here.
        appear();
    }
    
    //buka suara dan hilangin diri sendiri
    void appear(){
        Chars character = (Chars) getOneIntersectingObject(Chars.class);
        
        if(character != null){
            soundSplat.setVolume(20);
            soundSplat.play();
            getWorld().removeObject(this);
        }
        
        else if(timer.millisElapsed() > 100){
            getWorld().removeObject(this);
            timer.mark();
        }
    }
}
