import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimeChallenge extends World
{

    /**
     * Constructor for objects of class Stage3.
     * 
     */
    Chars character;
    Player player;
    Archer archer;
    Boss boss = new Boss(character);
    Miniboss miniboss = new Miniboss(character);
    Normal_Enemy normalEnemy = new Normal_Enemy(character);
    GreenfootSound TimeChallengeMusic = new GreenfootSound("TimeChallenge.mp3");
    Timer timer = new Timer("Time: ", 180);
    int maxEnemies = 5;    
    ScoreCounter currentScoreCounter = new ScoreCounter("Score: ");
    ScoreCounter highestScoreCounter = new ScoreCounter("Highest Score: ");

    public TimeChallenge()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        prepare();
    }

    public TimeChallenge (Chars character) {
        super(1280, 720, 1);
        addObject(character, 300, 300);
        addObject(timer, 640, 50);
        addObject(currentScoreCounter, 1200, 100);
        addObject(highestScoreCounter, 1200, 40);
        prepare();
    }

    public TimeChallenge (Archer paramArcher) {
        super(1280, 720, 1);
        this.archer = paramArcher;
        this.archer.setImage(new GreenfootImage("RightArcher1.png"));
        addObject(this.archer, 300, 300);
        addObject(this.archer.hpBar, 300, 300);
        addObject(this.archer.manaBar, 300, 300);
        this.archer.challengeCurrentScore = 0;
        this.archer.cdCounter = null;
        prepare();
        addObject(this.timer, 640, 40);
        addObject(this.archer.imgGulden, 40, 40);
        addObject(this.archer.guldenCounter, 115, 40);
        addObject(this.archer.btnPotion, 40, 100);
        addObject(this.archer.potionCounter, 115, 100);
        addObject(this.currentScoreCounter, 1200, 100);
        addObject(this.highestScoreCounter, 1200, 40);
    }

    public TimeChallenge (Player paramPlayer) {
        super(1280, 720, 1);
        this.player = paramPlayer;
        this.player.setImage(new GreenfootImage("kanan1.png"));
        addObject(this.player, 300, 300);
        addObject(this.player.hpBar, 300, 300);
        addObject(this.player.manaBar, 300, 300);
        this.player.challengeCurrentScore = 0;
        this.player.cdCounter = null;
        prepare();
        addObject(this.timer, 640, 40);
        addObject(this.player.imgGulden, 40, 40);
        addObject(this.player.guldenCounter, 115, 40);
        addObject(this.player.btnPotion, 40, 100);
        addObject(this.player.potionCounter, 115, 100);
        addObject(this.currentScoreCounter, 1200, 100);
        addObject(this.highestScoreCounter, 1200, 40);
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        BrownRocks brownRocks1 = new BrownRocks();
        BrownRocks brownRocks2 = new BrownRocks();
        
        addObject(brownRocks1, 116, 229);
        addObject(brownRocks2, 1040, 144);
        
        Skull skull1 = new Skull();
        Skull skull2 = new Skull();
        Skull skull3 = new Skull();
        
        addObject(skull1, 1140, 677);
        addObject(skull2, 1195, 670);
        addObject(skull3, 1253, 664);
        addObject(miniboss, 900, 300);
        
        BlackTree blackTree1 = new BlackTree();
        addObject(blackTree1, 130, 607);
        
        //addObject(boss, 800, 500);
        //addObject(normalEnemy, 1000, 640);
    }
    
    void setPlayerHighestChallengeScore(){
        //ketika waktu sudah habis, property high score di player akan diperbarui
         if(timer.getValue() == 0){
             if(player != null){
                 if(player.challengeHighestScore < player.challengeCurrentScore){
                     player.challengeHighestScore = player.challengeCurrentScore;
                     player.challengeCurrentScore = 0;
                 }    
             }
             
             else if (archer != null){
                 if(archer.challengeHighestScore < archer.challengeCurrentScore){
                     archer.challengeHighestScore = archer.challengeCurrentScore;
                     archer.challengeCurrentScore = 0;
                 }
             }
        }
    }
    
    public void spawnEnemiesStage3(int maxEnemies){
        if((this.timer.getValue() > 0) && (getObjects(Enemy.class).size() < maxEnemies)){
            //jika mendapatkan chance, baru assign new musuh dan addobject
            if(Math.random() < 0.009) {
                  Miniboss miniboss = new Miniboss(character);
                  Normal_Enemy normalEnemy = new Normal_Enemy(character);
                  Boss boss = new Boss(character);
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
                      case 11:
                          addObject(boss, 640, getHeight() - 50);
                          boss.updateHP(boss);
                          addObject(boss.hpBar, 640, getHeight() - 50);
                          break;
                      case 12:
                          addObject(boss, 854, getHeight() - 50);
                          boss.updateHP(boss);
                          addObject(boss.hpBar, 854, getHeight() - 50);
                          break;
                 }
            }
        }
    }
    
    public void act(){
        TimeChallengeMusic.setVolume(80);
        TimeChallengeMusic.play();
        
        //jika pemain mati, hentikan musik
        if(this.player != null && this.player.hp <= 0){
            TimeChallengeMusic.stop();
        }
        else if(this.archer != null && this.archer.hp <= 0){
            TimeChallengeMusic.stop();
        }
        
        //jika timer > 0, spawn musuh
        if(this.timer.getValue() > 0){
            spawnEnemiesStage3(maxEnemies);
        }
        
        //jika timer == 0 dan musuh habis, musik berhenti
        if(this.timer.getValue() == 0){
            TimeChallengeMusic.stop();
        }
        
        //setelah hentikan musik, tentukan high score
        if(player != null){
            highestScoreCounter.setValue(player.challengeHighestScore);
            currentScoreCounter.setValue(player.challengeCurrentScore);
            setPlayerHighestChallengeScore();
        }
        
        else if(archer != null){
            highestScoreCounter.setValue(archer.challengeHighestScore);
            currentScoreCounter.setValue(archer.challengeCurrentScore);
            setPlayerHighestChallengeScore();
        }
    }
}