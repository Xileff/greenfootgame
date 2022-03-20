import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ChooseJob here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChooseJob extends World
{

    /**
     * Constructor for objects of class ChooseJob.
     * 
     */
    ChooseArcher chooseArcher = new ChooseArcher();
    ChooseSwordsman chooseSwordsman = new ChooseSwordsman();
    GreenfootSound MenuBacksound = new GreenfootSound("Menu.mp3");
    public Chars character;
    public Player player;
    public Archer archer;
    
    public ChooseJob()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        prepare();
    }
    
    public ChooseJob(Player player){
        super(1280, 720, 1); 
        this.player = player;
        player.setImage(new GreenfootImage("transparent-w13.png"));
        addObject(chooseSwordsman,640,408);
        addObject(player,857,417);
    }
    
    public ChooseJob(Archer archer){
        super(1280, 720, 1); 
        this.archer = archer;
        archer.setImage(new GreenfootImage("transparent-w13.png"));
        addObject(chooseArcher,640,408);
        addObject(archer,857,417);
    }
    
    public ChooseJob(Chars character){
        super(1280, 720, 1); 
        this.character = character;
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(chooseArcher,521,408);
        addObject(chooseSwordsman,827,417);
    }
    
    public void act(){
        playMusic();
        stopMusic();
    }
    
    void playMusic(){
        MenuBacksound.setVolume(50);
        MenuBacksound.playLoop();            
    }
    
    void stopMusic(){
        if(Greenfoot.mouseClicked(chooseArcher) || Greenfoot.mouseClicked(chooseSwordsman)){
            MenuBacksound.stop();
        }
    }
}
