import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Counter
{
    /**
     * Act - do whatever the Timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    public int value;
    private int target;
    private String prefix;
    int millis;
    
    public Timer(String prefix, int seconds)
    {
        background = new GreenfootImage("Counter.png");  // get image from class
        millis = seconds * 62;
        value = seconds;
        this.target = 0;
        this.prefix = prefix;
        updateImage();
    }
    
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage("Counter.png");
        GreenfootImage text = new GreenfootImage(prefix + value, 22, Color.BLACK, transparent);
        
        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }
        
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                        (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }
    
    public int getValue()
    {
        return value;
    }
    
    public void act()
    {
        // Add your action code here.
        millis--;
        if ((millis % 62 == 0) && value > this.target) {
            value--;
            updateImage();
        }
    }
}
