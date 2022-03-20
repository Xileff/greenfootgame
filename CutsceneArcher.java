import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneArcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutsceneArcher extends CutsceneChars
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
    
    void setDirectionUp(){
        if(this.direction != "Up") {
            this.direction = "Up";
            this.setImage("RightArcher1.png");
        }
    }
    
    void setDirectionDown(){
        if(this.direction != "Down") {
            this.direction = "Down";
            this.setImage("ArcherFrontStop.png");
        }
    }
    
    void setDirectionRight(){
        if(this.direction != "Right") {
            this.direction = "Right";
            this.setImage("RightArcher1.png");
        }
    }
    
    void setDirectionLeft() {
        if(this.direction != "Left") {
            this.direction = "Left";
            this.setImage("LeftArcher1.png");
        }
    }
    
    void animateRight() {
        if (countRight % animateSpeed == 0) {
                if(animateImage > 2){
                    animateImage = 1;
                }
                
                setImage("RightArcher" + animateImage + ".png");
                animateImage++;
            }
    }
    
    void animateLeft() {
        if (countLeft % animateSpeed == 0) {
                if(animateImage > 2){
                    animateImage = 1;
                }
                
                setImage("LeftArcher" + animateImage + ".png");
                animateImage++;
            }
        }
    
    void animateUp(){
        if (countUp % animateSpeed == 0) {
                if(animateImage > 1){
                    animateImage = 1;
                }
                
                setImage("BackArcher" + animateImage + ".png");
                animateImage++;
            }
        }
        
    void animateDown() {
        if (countDown % animateSpeed == 0) {
            if(animateImage > 2){
                    animateImage = 1;
                }
                setImage("FrontArcher" + animateImage + ".png");
                animateImage++;
        }
    }
    
    @Override protected void moveUp(int destinationY){
        setDirectionUp();
        if(this.getY() != destinationY){
            animateUp();
            super.moveUp(destinationY);
        }
        
        else {
            this.setImage("BackArcher1.png");
        }
    }
    
    @Override protected void moveRight(int destinationX){
        setDirectionRight();
        if(this.getX() != destinationX){
            animateRight();
            super.moveRight(destinationX);
        }
        
        else {
            this.setImage("ArcherAttackRight.png");
        }
    }
}
