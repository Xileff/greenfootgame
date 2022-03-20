import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{

    /**
     * Constructor for objects of class GameOver.
     * 
     */
    Player player;
    Archer archer;
    BtnMainMenu btnMainMenu = new BtnMainMenu();
    BtnRetry btnRetry = new BtnRetry();
    public GameOver()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        prepare();
    }
    
    public GameOver(Player player){
        super(1280, 720, 1);
        this.player = player;
        prepare();
    }
    
    public GameOver(Archer archer){
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
        addObject(btnMainMenu,645,445);
        addObject(btnRetry,645,595);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(btnMainMenu)){
            btnMainMenu.getImage().scale((int)Math.round(btnMainMenu.getImage().getWidth()*0.9),(int)Math.round(btnMainMenu.getImage().getHeight()*0.9));
            if(this.player != null){
                this.player.hp = 50;
                this.player.hpBar.health = 50;
                Greenfoot.setWorld(new MyWorld(this.player));    
            }
            else if(this.archer != null){
                this.archer.hp = 50;
                this.archer.hpBar.health = 50;
                Greenfoot.setWorld(new MyWorld(this.archer));    
            }
        }
        else if(Greenfoot.mouseClicked(btnRetry)){
            btnRetry.getImage().scale((int)Math.round(btnRetry.getImage().getWidth()*0.9),(int)Math.round(btnRetry.getImage().getHeight()*0.9));
            if(this.player != null){
                this.player.hp = 50;
                this.player.hpBar.health = 50;
                this.player.stageScore = 0;
                if(this.player.progress == "Stage1"){
                    Greenfoot.setWorld(new Stage1(this.player));
                }
                else if (this.player.progress == "Stage2"){
                    Greenfoot.setWorld(new Stage2(this.player));
                }
                else if(this.player.progress == "Stage3"){
                    Greenfoot.setWorld(new Stage3(this.player));
                }
                else if(this.player.progress == "Completed"){
                    Greenfoot.setWorld(new TimeChallenge(this.player));
                }
            }
            else if(this.archer != null) {
                this.archer.hp = 50;
                this.archer.hpBar.health = 50;
                this.archer.stageScore = 0;
                if(this.archer.progress == "Stage1"){
                    Greenfoot.setWorld(new Stage1(this.archer));
                }
                else if (this.archer.progress == "Stage2"){
                    Greenfoot.setWorld(new Stage2(this.archer));
                }
                else if(this.archer.progress == "Stage3"){
                    Greenfoot.setWorld(new Stage3(this.archer));
                }
                else if(this.archer.progress == "Completed"){
                    Greenfoot.setWorld(new TimeChallenge(this.archer));
                }    
            }
        }
    }
}
