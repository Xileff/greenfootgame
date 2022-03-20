import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stage1 extends World
{
    Chars character;
    Archer archer;
    Player player;
    public static GreenfootSound Stage1Music = new GreenfootSound("Stage1.mp3");
    Normal_Enemy firstNormalEnemy = new Normal_Enemy(character);
    Timer timer = new Timer("Time: ", 30);
    int Score;
    ScoreCounter currentScoreCounter = new ScoreCounter("Score: ");
    int maxEnemies = 5;
    
    Stage1 () {
        super(1280, 720, 1);            
        prepare();
    }
    
    Stage1(Chars character){
        super(1280, 720, 1);
        addObject(character, 50, 360);
        prepare();
        addObject(timer, 640, 40);
    }
    
    Stage1(Archer paramArcher){
        super(1280, 720, 1);
        this.archer = paramArcher;
        this.archer.stageScore = 0;
        this.archer.cdCounter = null;
        archer.setImage(new GreenfootImage("RightArcher1.png"));
        prepare();
        addObject(this.archer, 50, 300);
        //biar tetap ada hpbar dan manabar jika mati lalu retry
        if(this.archer.hpBar != null && this.archer.manaBar != null && this.archer.btnPotion != null && this.archer.guldenCounter != null && this.archer.imgGulden != null && this.archer.potionCounter != null){
            addObject(this.archer.hpBar, 300, 300);
            addObject(this.archer.manaBar, 300, 300);    
            addObject(this.archer.btnPotion, 40, 100);
            addObject(this.archer.guldenCounter, 115, 40);
            addObject(this.archer.imgGulden, 40, 40);
            addObject(this.archer.potionCounter, 115, 100);
        }
        addObject(currentScoreCounter, 1200, 40);
        addObject(timer, 640, 40);
    }
    
    Stage1(Player paramPlayer){
        super(1280, 720, 1);
        this.player = paramPlayer;
        this.player.stageScore = 0;
        this.player.cdCounter = null;
        player.setImage(new GreenfootImage("kanan1.png"));
        prepare();
        addObject(this.player, 50, 300);
        //biar tetap ada hpbar dan manabar jika mati lalu retry
        //addobject di dalam if bakal dijalanin belakangan meskipun ada di atas prepare
        //jadi sengaja taroh prepare dulu biar counter golden ama counter potion ga ketutupan objek di prepare
        if(this.player.hpBar != null && this.player.manaBar != null && this.player.btnPotion != null && this.player.guldenCounter != null && this.player.imgGulden != null && this.player.potionCounter != null){
            addObject(this.player.hpBar, 300, 300);
            addObject(this.player.manaBar, 300, 300);    
            addObject(this.player.btnPotion, 40, 100);
            addObject(this.player.guldenCounter, 115, 40);
            addObject(this.player.imgGulden, 40, 40);
            addObject(this.player.potionCounter, 115, 100);
        }
        addObject(currentScoreCounter, 1200, 40);
        addObject(timer, 640, 40);
    }
    /**
     * Constructor for objects of class Stage1.
     * 
     */
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Rocks rocks = new Rocks();
        Rocks rocks2 = new Rocks();
        Rocks rocks3 = new Rocks();
        Trees trees = new Trees();
        Pond pond = new Pond();
        addObject(pond, 56, 6);
        addObject(rocks,1175,643);
        addObject(rocks2,18,673);
        addObject(rocks3,1032,677);
        addObject(trees,1180,120);
        spawnFirstEnemies();
        //addObject(firstNormalEnemy,getWidth() - 30, 360);
    }
    
    public void spawnFirstEnemies(){
        Normal_Enemy csNormalEnemy = new Normal_Enemy(character);
        Normal_Enemy csNormalEnemy1 = new Normal_Enemy(character);
        Normal_Enemy csNormalEnemy2 = new Normal_Enemy(character);
        Normal_Enemy csNormalEnemy3 = new Normal_Enemy(character);
        Normal_Enemy csNormalEnemy4 = new Normal_Enemy(character);
        addObject(csNormalEnemy, this.getWidth() - 300, 360);
        addObject(csNormalEnemy1, this.getWidth() - 300, 180);
        addObject(csNormalEnemy2, this.getWidth() - 800, 540);
        addObject(csNormalEnemy3, 600, 30);
        addObject(csNormalEnemy4, 800, this.getHeight() - 30);
    }
    
    public void spawnEnemiesStage1(int maxEnemies){
        if((this.timer.getValue() > 0) && (getObjects(Enemy.class).size() < maxEnemies)){
            //jika mendapatkan chance, baru assign new musuh dan addobject
            if(Math.random() < 0.009) {
                  Normal_Enemy normalEnemy = new Normal_Enemy(character);
                  int a = Greenfoot.getRandomNumber(5) + 1;
                  switch(a){
                      case 1:
                          addObject(normalEnemy,427, 30);
                          normalEnemy.updateHP(normalEnemy);
                          addObject(normalEnemy.hpBar, 427, 30);
                          break;
                      case 2:
                          addObject(normalEnemy,854, 30);
                          normalEnemy.updateHP(normalEnemy);
                          addObject(normalEnemy.hpBar,854, 30);
                          break;
                      case 3:
                          addObject(normalEnemy,getWidth() - 30, 360);
                          normalEnemy.updateHP(normalEnemy);
                          addObject(normalEnemy.hpBar,getWidth() - 30, 360);
                          break;
                      case 4:
                          addObject(normalEnemy, 640, getHeight() - 50);
                          normalEnemy.updateHP(normalEnemy);
                          addObject(normalEnemy.hpBar, 640, getHeight() - 50);
                          break;
                      case 5:
                          addObject(normalEnemy, 427, getHeight() - 50);
                          normalEnemy.updateHP(normalEnemy);
                          addObject(normalEnemy.hpBar, 427, getHeight() - 50);
                          break;
                  }
            }
        }
    }
    
    public void act(){
        //nanti ini if nya diganti jadi timer 1 menit
        Stage1Music.setVolume(20);
        Stage1Music.play();
        
        //stop music jika mati
        if(this.player != null && this.player.hp <= 0){
            Stage1Music.stop();
        }
        else if(this.archer != null && this.archer.hp <= 0){
            Stage1Music.stop();
        }
        
        //spawn musuh jika timer > 1
        if(this.timer.getValue() > 1){
            spawnEnemiesStage1(maxEnemies);    
        }
        
        //stop music jika timer == 0
        if(this.timer.getValue() == 0 && getObjects(Enemy.class).isEmpty()){
            Stage1Music.stop();
        }
    }
}