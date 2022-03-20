import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stage2 extends World
{

    /**
     * Constructor for objects of class Stage2.
     * 
     */
    Chars character;
    Player player;
    Archer archer;
    public static GreenfootSound Stage2Music = new GreenfootSound("Stage2.mp3");
    Timer timer = new Timer("Time: ", 30);
    ScoreCounter currentScoreCounter = new ScoreCounter("Score: ");
    int maxEnemies = 3;
    
    public Stage2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        prepare();
    }

    public Stage2 (Chars character) {
        super(1280, 720, 1);
        this.character = character;
        addObject(character, 50, 300);
        addObject(currentScoreCounter, 1200, 40);
        addObject(timer, 640, 40);
        prepare();
    }

    public Stage2 (Archer paramArcher) {
        super(1280, 720, 1);
        this.archer = paramArcher;
        this.archer.cdCounter = null;
        prepare();
        archer.stageScore = 0;
        archer.setImage(new GreenfootImage("RightArcher1.png"));
        addObject(archer, 50, 300);
        addObject(archer.hpBar, 300, 300);
        addObject(archer.manaBar, 300, 300);
        addObject(currentScoreCounter, 1200, 40);
        addObject(timer, 640, 40);
        addObject(archer.imgGulden, 40, 40);
        addObject(archer.guldenCounter, 115, 40);
        addObject(archer.btnPotion, 40, 100);
        addObject(archer.potionCounter, 115, 100);
        
    }

    public Stage2 (Player paramPlayer) {
        super(1280, 720, 1);
        this.player = paramPlayer;
        this.player.cdCounter = null;
        prepare();
        player.stageScore = 0;
        player.setImage(new GreenfootImage("kanan1.png"));
        addObject(player, 50, 300);
        addObject(player.hpBar, 300, 300);
        addObject(player.manaBar, 300, 300);
        addObject(currentScoreCounter, 1200, 40);
        addObject(timer, 640, 40);
        addObject(player.imgGulden, 40, 40);
        addObject(player.guldenCounter, 115, 40);
        addObject(player.btnPotion, 40, 100);
        addObject(player.potionCounter, 115, 100);
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        BlackTree blackTree = new BlackTree();
        addObject(blackTree,1171,615);
        BlackTree blackTree2 = new BlackTree();
        addObject(blackTree2,121,53);
        BlackTree blackTree3 = new BlackTree();
        addObject(blackTree3, 1213,80);
        BlackTree blackTree4 = new BlackTree();
        addObject(blackTree4,514,218);
        Rocks rocks = new Rocks();
        addObject(rocks,104,628);
        rocks.setLocation(98,677);
        Rocks rocks2 = new Rocks();
        addObject(rocks2,248,681);
        removeObject(blackTree4);
        //spawnFirstEnemies();
    }
    
    public void spawnFirstEnemies(){
        Normal_Enemy csNormalEnemy = new Normal_Enemy(character);
        Miniboss csNormalEnemy1 = new Miniboss(character);
        Miniboss csNormalEnemy2 = new Miniboss(character);
        Normal_Enemy csNormalEnemy3 = new Normal_Enemy(character);
        Normal_Enemy csNormalEnemy4 = new Normal_Enemy(character);
        addObject(csNormalEnemy, this.getWidth() - 300, 360);
        addObject(csNormalEnemy1, this.getWidth() - 300, 180);
        addObject(csNormalEnemy2, this.getWidth() - 300, 540);
        addObject(csNormalEnemy3, 600, 30);
        addObject(csNormalEnemy4, 800, this.getHeight() - 30);
    }
    
    public void spawnEnemiesStage2(int maxEnemies){
        if((this.timer.getValue() > 0) && (getObjects(Enemy.class).size() < maxEnemies)){
            //jika mendapatkan chance, baru assign new musuh dan addobject
            if(Math.random() < 0.009) {
                  Miniboss miniboss = new Miniboss(character);
                  Normal_Enemy normalEnemy = new Normal_Enemy(character);
                  int a = Greenfoot.getRandomNumber(10) + 1;
                  switch(a){
                      case 1:
                          addObject(miniboss, 427, 50);
                          miniboss.updateHP(miniboss);
                          addObject(miniboss.hpBar, 427, 50);
                          break;
                      case 2:
                          addObject(miniboss,854, 50);
                          miniboss.updateHP(miniboss);
                          addObject(miniboss.hpBar,854, 50);
                          break;
                      case 3:
                          addObject(miniboss,getWidth() - 50, 360);
                          miniboss.updateHP(miniboss);
                          addObject(miniboss.hpBar,getWidth() - 50, 360);
                          break;
                      case 4:
                          addObject(miniboss, 854, getHeight() - 50);
                          miniboss.updateHP(miniboss);
                          addObject(miniboss.hpBar, 854, getHeight() - 50);
                          break;
                      case 5:
                          addObject(miniboss, 640, getHeight() - 50);
                          miniboss.updateHP(miniboss);
                          addObject(miniboss.hpBar, 640, getHeight() - 50);
                          break;
                      case 6:
                          addObject(normalEnemy, 427, 50);
                          normalEnemy.updateHP(normalEnemy);
                          addObject(normalEnemy.hpBar, 427, 50);
                          break;
                      case 7:
                          addObject(normalEnemy,854, 50);
                          normalEnemy.updateHP(normalEnemy);
                          addObject(normalEnemy.hpBar,854, 50);
                          break;
                      case 8:
                          addObject(normalEnemy,getWidth() - 50, 360);
                          normalEnemy.updateHP(normalEnemy);
                          addObject(normalEnemy.hpBar,getWidth() - 50, 360);
                          break;
                      case 9:
                          addObject(normalEnemy, 854, getHeight() - 50);
                          normalEnemy.updateHP(normalEnemy);
                          addObject(normalEnemy.hpBar, 854, getHeight() - 50);
                          break;
                      case 10:
                          addObject(normalEnemy, 640, getHeight() - 50);
                          normalEnemy.updateHP(normalEnemy);
                          addObject(normalEnemy.hpBar, 640, getHeight() - 50);
                          break;
                  }
            }
        }
    }
    
    public Enemy randomEnemyStage2(){
        Enemy enemy = new Normal_Enemy(character);
        
        if(Math.random() > 0.40){
            Normal_Enemy normalEnemy = new Normal_Enemy(character);
            enemy = normalEnemy;
        }
        else if (Math.random() < 0.40){
            Miniboss miniboss = new Miniboss(character);
            enemy = miniboss;
        }
        
        return enemy;
    }
    
    public void act(){
        Stage2Music.setVolume(20);
        Stage2Music.play();
        
        //jika player mati, musik stop
        if(this.player != null && this.player.hp <= 0){
            Stage2Music.stop();
        }
        else if(this.archer != null && this.archer.hp <= 0){
            Stage2Music.stop();
        }
        
        //jika timer > 0, spawn musuh
        if(this.timer.getValue() > 0){
            spawnEnemiesStage2(maxEnemies);    
        }
        
        //jika timer == 0 dan musuh habis, musik berhenti
        if(this.timer.getValue() == 0 && getObjects(Enemy.class).isEmpty()){
            Stage2Music.stop();
        }
    }
}
