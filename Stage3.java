import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stage3 extends World
{

    /**
     * Constructor for objects of class Stage3.
     * 
     */
    Chars character;
    Player player;
    Archer archer;
    public static GreenfootSound Stage3Music = new GreenfootSound("Stage3.mp3");
    public static GreenfootSound Stage3MusicBoss = new GreenfootSound("Stage3-Boss.mp3");
    Boss boss = new Boss(character);
    Miniboss firstMiniBoss = new Miniboss(character);
    Timer timer = new Timer("Time: ", 60);
    ScoreCounter currentScoreCounter = new ScoreCounter("Score: ");
    int maxEnemies = 3;
    boolean hasBoss = false;
    
    public Stage3()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        prepare();
    }

    public Stage3 (Chars character) {
        super(1280, 720, 1);
        addObject(character, 50, 300);
        addObject(timer, 640, 40);
        prepare();
    }

    public Stage3 (Archer paramArcher) {
        super(1280, 720, 1);
        this.archer = paramArcher;
        this.archer.cdCounter = null;
        this.archer.stageScore = 0;
        this.archer.setImage(new GreenfootImage("RightArcher1.png"));
        prepare();
        addObject(this.archer, 50, 300);
        addObject(this.archer.hpBar, 300, 300);
        addObject(this.archer.manaBar, 300, 300);
        addObject(this.currentScoreCounter, 1200, 40);
        addObject(this.timer, 640, 40);
        addObject(this.archer.imgGulden, 40, 40);
        addObject(this.archer.guldenCounter, 115, 40);
        addObject(this.archer.btnPotion, 40, 100);
        addObject(this.archer.potionCounter, 115, 100);
    }

    public Stage3 (Player paramPlayer) {
        super(1280, 720, 1);
        this.player = paramPlayer;
        this.player.cdCounter = null;
        this.player.stageScore = 0;
        this.player.setImage(new GreenfootImage("kanan1.png"));
        prepare();
        addObject(this.player, 50, 300);
        addObject(this.player.hpBar, 300, 300);
        addObject(this.player.manaBar, 300, 300);
        addObject(this.currentScoreCounter, 1200, 40);
        addObject(this.timer, 640, 40);
        addObject(this.player.imgGulden, 40, 40);
        addObject(this.player.guldenCounter, 115, 40);
        addObject(this.player.btnPotion, 40, 100);
        addObject(this.player.potionCounter, 115, 100);
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        BrownRocks brownRocks1 = new BrownRocks();
        BrownRocks brownRocks2 = new BrownRocks();
        
        addObject(brownRocks1, 55, 32);
        addObject(brownRocks2, 1165, 70);
        
        Skull skull1 = new Skull();
        Skull skull2 = new Skull();
        Skull skull3 = new Skull();
        
        addObject(skull1, 1140, 677);
        addObject(skull2, 1195, 670);
        addObject(skull3, 1253, 664);
        
        BlackTree blackTree1 = new BlackTree();
        addObject(blackTree1, 88, 614);
        addObject(firstMiniBoss, 640, getHeight() - 50);
        
        spawnFirstEnemies();
    }
    
    public void spawnFirstEnemies(){
        Miniboss csMiniboss1 = new Miniboss(character);
        Miniboss csMiniboss2 = new Miniboss(character);
        Miniboss csMiniboss3 = new Miniboss(character);
        Miniboss csMiniboss4 = new Miniboss(character);
        Miniboss csMiniboss5 = new Miniboss(character);
        
        addObject(csMiniboss1, this.getWidth() - 500, 180);
        addObject(csMiniboss2, this.getWidth(), 288);
        addObject(csMiniboss3, this.getWidth(), 360);
        addObject(csMiniboss4, this.getWidth(), 432);
        addObject(csMiniboss5, this.getWidth() - 500, 540);
    }
    
    public void spawnEnemiesStage3(int maxEnemies){
        if(getObjects(Miniboss.class).size() < maxEnemies){
            //jika mendapatkan chance, baru assign new musuh dan addobject
            if(Math.random() < 0.009) {
                  Miniboss miniboss = new Miniboss(character);
                  int a = Greenfoot.getRandomNumber(3) + 1;
                  switch(a){
                      case 1:
                          addObject(miniboss, 640, 50);
                          miniboss.updateHP(miniboss);
                          addObject(miniboss.hpBar, 640, 50);
                          break;
                      case 2:
                          addObject(miniboss,getWidth() - 50, 480);
                          miniboss.updateHP(miniboss);
                          addObject(miniboss.hpBar, getWidth() - 50, 480);
                          break;
                      case 3:
                          addObject(miniboss,640, getHeight() - 50);
                          miniboss.updateHP(miniboss);
                          addObject(miniboss.hpBar, 640, getHeight() - 50);
                          break;
                  }
            }
        }
    }
    
    public void act(){
        Stage3Music.setVolume(20);
        Stage3MusicBoss.setVolume(50);
        
        //jika mati, hentikan musik
        if(this.player != null && this.player.hp <= 0){
            Stage3Music.stop();
            Stage3MusicBoss.stop();
        }
        else if(this.archer != null && this.archer.hp <= 0){
            Stage3Music.stop();
            Stage3MusicBoss.stop();
        }
        
        //jika waktu > 30 detik. masih munculin miniboss
        if(timer.getValue() > 30){
            Stage3Music.play();
            spawnEnemiesStage3(maxEnemies);
        }
        //jika waktu < 30 detik dan boss tidak ada; munculin 1 boss. ganti musik
        else if (timer.getValue() < 30 && hasBoss == false){
            Stage3Music.stop();
            Stage3MusicBoss.play();
            addObject(boss, getWidth() - 50, 360);
            hasBoss = true;
        }
        
        else if(hasBoss == true){
            if(getObjects(Boss.class).isEmpty()){
                if(this.player != null){
                    this.player.progress = "Completed";
                    Greenfoot.setWorld(new MyWorld(this.player));   
                }
                else if(this.archer != null){
                    this.archer.progress = "Completed";
                    Greenfoot.setWorld(new MyWorld(this.archer));   
                }
            }
        }
        
        //jika boss mati atau pemain mati, hentikan musik
        if(this.player != null && this.player.hp < 1){
            Stage3MusicBoss.stop();
        }
        
        else if (this.archer != null && this.archer.hp < 1) {
            Stage3MusicBoss.stop();
        }
        
        else if(getObjects(Enemy.class).isEmpty() && timer.getValue() == 0){
            Stage3MusicBoss.stop();
        }
    }
}
