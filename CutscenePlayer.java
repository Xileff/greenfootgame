import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneArcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutscenePlayer extends CutsceneChars
{
    /**
     * Act - do whatever the CutsceneArcher wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        countRight++;
        countLeft++;
        countUp++;
        countDown++;
        countIdle++;
    }
    
    void setDirectionRight(){
        if(this.direction != "Right") {
            this.direction = "Right";
            this.setImage("kanan1.png");
        }
    }
    
    void setDirectionLeft() {
        if(this.direction != "Left") {
            this.direction = "Left";
            this.setImage("kiri1.png");
        }
    }
    
    void animateRight() {
        if (countRight % animateSpeed == 0) {
                if(animateImage > 2){
                    animateImage = 1;
                }
                
                setImage("kanan" + animateImage + ".png");
                animateImage++;
            }
    }
    
    void animateLeft() {
        if (countLeft % animateSpeed == 0) {
                if(animateImage > 2){
                    animateImage = 1;
                }
                
                setImage("kiri" + animateImage + ".png");
                animateImage++;
            }
        }
    
    @Override protected void moveRight(int destinationX){
        setDirectionRight();
        if(this.getX() != destinationX){
            animateRight();
            super.moveRight(destinationX);
        }
        
        else {
            this.setImage("SwordAttackRight.png");
        }
    }
}
