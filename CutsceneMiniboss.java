import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneMiniboss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutsceneMiniboss extends CutsceneEnemy
{
    /**
     * Act - do whatever the CutsceneMiniboss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    //animasi
    private int countRight = 0;
    private int countLeft = 0;
    private int countAttack = 0;
    private int animateImage = 1;
    private int animateSpeed = 10;
    private int animateAttackImage = 1;
    private int animateAttackSpeed = 31;
    
    //suara
    protected SimpleTimer idleTimer = new SimpleTimer();
    GreenfootSound zombieAttack = new GreenfootSound("zombie-attack.mp3");
    GreenfootSound zombieDie = new GreenfootSound("zombie-die.mp3");
    GreenfootSound zombieMove = new GreenfootSound("zombie-move.wav");
    GreenfootSound zombieIdle = new GreenfootSound("zombie-idle.mp3");
    
    public void act()
    {
        // Add your action code here.
        countLeft++;
        playVoice();
    }
    
    @Override protected void moveLeft(int destinationX){
        if(this.getX() != destinationX){
            animateLeft();
            super.moveLeft(destinationX);
        }
        
        else {
            this.setImage("skeleton-attack-left.png");
        }
    }
    
    void animateLeft() {
        if (countLeft % animateSpeed == 0) {
            countLeft = 0;
            if(animateImage > 2){
                animateImage = 0;
            }
            setImage("skeleton-left-" + animateImage + ".png");
            animateImage++;
            }
    }
    
    void playVoice(){
         if(idleTimer.millisElapsed() > 8000){
            zombieIdle.setVolume(50);
            zombieIdle.play();
            idleTimer.mark();
        }   
    }
}
