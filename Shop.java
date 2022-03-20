import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class World_Shop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shop extends World
{

    /**
     * Constructor for objects of class World_Shop.
     * 
     */
    Chars character;
    Player player;
    Archer archer;
    BtnBuyPotion btnBuyPotion = new BtnBuyPotion();
    BtnUpgradeATK btnUpgradeATK = new BtnUpgradeATK();
    BackBtn btnBack = new BackBtn();
    
    public Shop(Player player)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        this.player = player;
        this.player.setImage("transparent-w13.png");
        addObject(player, 1280, 720);
        addObject(player.imgGulden, 40, 40);
        addObject(player.guldenCounter, 115, 40);
        addObject(player.btnPotion, 40, 100);
        addObject(player.potionCounter, 115, 100);
        prepare();
    }
    
    public Shop(Archer archer)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        this.archer = archer;
        this.archer.setImage("transparent-w13.png");
        addObject(archer, 1280, 720);
        addObject(archer.imgGulden, 40, 40);
        addObject(archer.guldenCounter, 115, 40);
        addObject(archer.btnPotion, 40, 100);
        addObject(archer.potionCounter, 115, 100);
        prepare();
    }
    
    public void prepare(){
        addObject(btnBuyPotion, 640, 345);
        addObject(btnUpgradeATK, 640, 485);
        addObject(btnBack, 100, 550);
    }
    
    public void act(){
        returnToMenu();
        if(Greenfoot.mouseClicked(btnBuyPotion)){
             if(this.player != null){
                 if(this.player.gulden >= 2){
                     this.player.potion += 1;
                     this.player.gulden -= 2;    
                 }
                 else {
                     //unable
                 }
             }
             else if(this.archer != null){
                 if(this.archer.gulden >= 2){
                     this.archer.potion += 1;
                     this.archer.gulden -= 2;    
                 }
                 else {
                     //unable
                 }
             }
        }
        else if(Greenfoot.mouseClicked(btnUpgradeATK)){
            if(this.player != null){
                if(this.player.gulden >= 10){
                    this.player.ATK += 2;
                    this.player.gulden -= 10;    
                }
                else {
                    //putar suara "unab;e"
                }
            }
            else if(this.archer != null){
                if(this.archer.gulden >= 10){
                    this.archer.ATK += 2;
                    this.archer.SubATK += 1;
                    this.archer.gulden -= 10;    
                }
                else {
                    //putar suara "unab;e"
                }
            }
        }
    }
    
    void returnToMenu(){
        if(Greenfoot.mouseClicked(btnBack) && this.player != null){
            Greenfoot.setWorld(new MyWorld(this.player));
        }
        else if(Greenfoot.mouseClicked(btnBack) && this.archer != null){
            Greenfoot.setWorld(new MyWorld(this.archer));
        }
    }
}