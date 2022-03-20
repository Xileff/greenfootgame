import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private ShopBtn shopBtn;
    private BattleBtn battleBtn = new BattleBtn();
    private GreenfootSound MenuBacksound = new GreenfootSound("Menu.mp3");
    public Player player;
    public Archer archer;
    public Chars character;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        prepare();
        addObject(battleBtn,640,360);
    }
    
    public MyWorld(Player player){
        super(1280, 720, 1); 
        this.player = player;
        player.setImage("transparent-w13.png");
        prepare();
        if(this.player.progress == "Completed"){
            battleBtn.setImage("btnTimeChallenge.png");    
        }
        addObject(battleBtn,640,325);
        shopBtn = new ShopBtn(this.player);
        addObject(shopBtn,640,475);
        addObject(player, 0, 0);
    }
    
    public MyWorld(Archer archer){
        super(1280, 720, 1);
        this.archer = archer;
        archer.setImage("transparent-w13.png");
        prepare();
        if(this.archer.progress == "Completed"){
            battleBtn.setImage("btnTimeChallenge.png");    
        }
        addObject(battleBtn,640,300);
        shopBtn = new ShopBtn(this.archer);
        addObject(shopBtn,640,475);
        addObject(archer, 0, 0);
    }
    
    private void prepare(){
        imgSwordsman img1 = new imgSwordsman();
        imgArcher img2 = new imgArcher();
        addObject(img1, 260, 400);
        addObject(img2, 1035, 400);
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
        if(Greenfoot.mouseClicked(battleBtn) || Greenfoot.mouseClicked(shopBtn)){
            MenuBacksound.stop();
        }
    }
}
