import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PrologueStage1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tutorials extends World
{

    /**
     * Constructor for objects of class PrologueStage1.
     * 
     */
    Chars character;
    Player player;
    Archer archer;
    ContinueBtn continueBtn = new ContinueBtn();
    GreenfootSound clickSound = new GreenfootSound("main-menu-button.wav");
    
    public Tutorials()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        prepare();
    }
    
    public Tutorials(Player player){
        super(1280, 720, 1);
        this.player = player;
        prepare();
    }
    
    public Tutorials(Archer archer){
        super(1280, 720, 1);
        this.archer = archer;    
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(continueBtn,1155,607);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(continueBtn)){
            continueBtn.getImage().scale((int)Math.round(continueBtn.getImage().getWidth()*0.8),(int)Math.round(continueBtn.getImage().getHeight()*0.8));
            clickSound.play();
            Greenfoot.delay(62);
            if(this.player != null){
                Greenfoot.setWorld(new PrologueStage1(this.player));    
            }
            
            else if(this.archer != null){
                Greenfoot.setWorld(new PrologueStage1(this.archer));
            }
            
        }
        
    }
}
