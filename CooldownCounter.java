import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CooldownCounter extends Counter
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
    Archer archer;
    Player player;
    int millis;
    
    public CooldownCounter(Player player, String prefix, int seconds)
    {
        background = new GreenfootImage("Counter.png");  // get image from class
        millis = seconds * 62;
        value = seconds;
        this.player = player;
        this.target = 0;
        this.prefix = prefix;
        updateImage();
    }
    
    public CooldownCounter(Archer archer, String prefix, int seconds)
    {
        background = new GreenfootImage("Counter.png");  // get image from class
        millis = seconds * 62;
        value = seconds;
        this.archer = archer;
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
    
    public void positioning(){
        if(this.archer != null){
            setLocation(archer.getX(), archer.getY() - 115);
        }
        else if (this.player != null){
            setLocation(player.getX(), player.getY() - 115);
        }
    }
    
    public void act()
    {
        // Add your action code here.
        positioning();
        millis--;
        if ((millis % 62 == 0) && value > this.target) {
            value--;
            updateImage();
        }
        
        if(this.value == 0){
            if(this.archer != null) {
                this.archer.cdCounter = null;
                getWorld().removeObject(this);
            }
            
            else if(this.player != null) {
                this.player.cdCounter = null;
                getWorld().removeObject(this);
            }
        }
    }
}
