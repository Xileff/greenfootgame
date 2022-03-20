import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutsceneText extends Actor
{
    /**
     * Act - do whatever the CutsceneText wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int imageTimer;
    String imgLink;
    GreenfootSound clickSound = new GreenfootSound("main-menu-button.wav");
    
    CutsceneText(String imgName){
        this.imgLink = imgName;
        this.setImage(imgName);
    }
    
    public void act()
    {
        // Add your action code here.
        zoomAndShrink(imgLink);
        remove();
    }
    
    void zoomAndShrink(String paramImg){
        imageTimer = (imageTimer+1)%120;  // 10 = #sizes, 6 = delay, 2 = #directions (10*6*2 = 120)
        if (imageTimer%12 == 0) // time to change image
        {
            GreenfootImage image = new GreenfootImage(paramImg);
            int imgWidth = image.getWidth(); // image width
            int imgHeight = image.getHeight(); // image height
            int additional = imageTimer/6; // amount to add to width and height (if growing)
            if (imageTimer > 60) additional = 20-imageTimer/6; // amount to add to width and height (if shrinking)
            image.scale(imgWidth+additional, imgHeight+additional);
            setImage(image);
        }
    }
    
    void remove(){
        if(Greenfoot.mouseClicked(this)){
            clickSound.setVolume(50);
            clickSound.play();
            getWorld().removeObject(this);
            return;
        }
    }
}
