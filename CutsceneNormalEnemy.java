import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneNormalEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutsceneNormalEnemy extends CutsceneEnemy
{
    /**
     * Act - do whatever the CutsceneNormalEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int imageTimer;
    
    public void act()
    {
        // Add your action code here.
        zoomAndShrink();
    }
    
    void zoomAndShrink(){
        imageTimer = (imageTimer+1)%120;  // 10 = #sizes, 6 = delay, 2 = #directions (10*6*2 = 120)
        if (imageTimer%6 == 0) // time to change image
        {
            GreenfootImage image = new GreenfootImage("slime-1.png");
            if(this.getImage().toString() == "slime-1.png"){
                image = new GreenfootImage("slime-1.png"); // new copy of original
            }
            else if (this.getImage().toString() == "slime-2.png"){
                image = new GreenfootImage("slime-2.png"); // new copy of original
            }
            int imgWidth = image.getWidth(); // image width
            int imgHeight = image.getHeight(); // image height
            int additional = imageTimer/6; // amount to add to width and height (if growing)
            if (imageTimer > 60) additional = 20-imageTimer/6; // amount to add to width and height (if shrinking)
            image.scale(imgWidth+additional, imgHeight+additional);
            setImage(image);
        }
    }
}
