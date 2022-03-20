import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{   
    protected int HP;
    protected HealthBar hpBar;
    protected Exclamation exclamationMark;
    Random random = new Random();
    protected int rotation;
    public Chars player;
    protected SimpleTimer timer = new SimpleTimer();
    
    Enemy(Chars paramPlayer) {
        this.player = paramPlayer;
    }
    
    public void act()
    {
        
    }
    
    //Cek apakah ada objek yg menghalangi pergerakan, jika ya maka putar arah 90 derajat
    void checkObstacle() {
        Actor environment = getOneIntersectingObject(Environment.class);
        if(environment!=null){
            turn(180);
        }
    }
    
    //Musuh bergerak, jika ada di ujung window maka musuh akan berputar arah 90 derajat. Kemudian ada kemungkinan 0.4% untuk musuh berpindah arah secara random.
    void moveAround(){
        move(2);
        
        if (isAtEdge()) {
            turn(180);
        }
        
        else if (Greenfoot.getRandomNumber(500) < 2) {
            turn(90);
        }
    }
    
    void scratchPlayer() {
        //monster nyamperin player(bisa ke depan atau belakang player)
        if(!getObjectsInRange(300, Chars.class).isEmpty()){
            //player dipassing dari constuctor
            Chars player = getObjectsInRange(300, Chars.class).get(0);
            int playerX = player.getX();
            int playerY = player.getY();
            int dir = Greenfoot.getRandomNumber(4) + 1;
            int keepDistance = 100;

            if (dir == 1) {
                if(!(this.getX() == playerX - keepDistance)){
                    turnTowards(playerX - keepDistance, playerY);
                }
            }
            
            else if (dir == 2) {
                if (!(this.getX() == playerX + keepDistance)) {
                    turnTowards(player.getX() + keepDistance, player.getY());
                }
            }
            
            else if (dir == 3) {
                if (!(this.getY() == playerY - keepDistance)) {
                    turnTowards(player.getX(), player.getY() - keepDistance);
                }
            }
            
            else if (dir == 4) {
                if (!(this.getX() == playerY + keepDistance)) {
                    turnTowards(player.getX(), player.getY() + keepDistance);
                }
            }
            
            if (!intersects(player)) {
                move(1);
            }
            
            else {
                setLocation(getX(), getY());
                scratch();
            }
        }
        
        else {
            moveAround();
        }
    }
    
    void scratch(){
        if(!getObjectsInRange(170, Chars.class).isEmpty()){
            Chars character = getObjectsInRange(170, Chars.class).get(0);
            if(timer.millisElapsed() > 1000){
               //nanti di sini tambahin if else, jika job player apa, panggil attack yang mana
               Scratch scratch = new Scratch();
               getWorld().addObject(scratch, character.getX(), character.getY());
               character.hp -= scratch.damage;
               timer.mark();
            }
        }
    }
    
    void generateGulden(){
        if(!getWorld().getObjects(Player.class).isEmpty()){
            Player player = getWorld().getObjects(Player.class).get(0);
            Gulden gold = new Gulden();
            int randomGuldenAmount = Greenfoot.getRandomNumber(10) + 1;
            getWorld().addObject(gold, player.getX() - 50, player.getY() - 30);
            FloatingText dmgText = new FloatingText(Integer.toString(randomGuldenAmount), Color.YELLOW, 1500);
            getWorld().addObject(dmgText, player.getX() + 70, player.getY() - 30);
            player.gulden += randomGuldenAmount;
        }
        else if(!getWorld().getObjects(Archer.class).isEmpty()){
            Archer archer = getWorld().getObjects(Archer.class).get(0);
            Gulden gold = new Gulden();
            int randomGuldenAmount = Greenfoot.getRandomNumber(10) + 1;
            getWorld().addObject(gold, archer.getX() - 50, archer.getY() - 30);
            FloatingText dmgText = new FloatingText(Integer.toString(randomGuldenAmount), Color.YELLOW, 1500);
            getWorld().addObject(dmgText, archer.getX() + 70, archer.getY() - 30);
            archer.gulden += randomGuldenAmount;
        }
    }
    
    void giveChallengeScore(int score){
        //buat timechallenge
        if(getWorld().getClass() == TimeChallenge.class){
            if(!getWorld().getObjects(Player.class).isEmpty()){
                Player player = getWorld().getObjects(Player.class).get(0);
                player.challengeCurrentScore += score;
                getWorld().getObjects(ScoreCounter.class).get(0).setValue(player.challengeCurrentScore);
            }
            else if (!getWorld().getObjects(Archer.class).isEmpty()) {
                //TO BE IMPLEMENTED
                Archer archer = getWorld().getObjects(Archer.class).get(0);
                archer.challengeCurrentScore += score;
                getWorld().getObjects(ScoreCounter.class).get(0).setValue(archer.challengeCurrentScore);
            }
        }
        
        //buat skor di stage 1,2,3
        else if((getWorld().getClass() == Stage1.class) || (getWorld().getClass() == Stage2.class) || (getWorld().getClass() == Stage3.class)){
            if(!getWorld().getObjects(Player.class).isEmpty()){
                Player player = getWorld().getObjects(Player.class).get(0);
                player.stageScore += score;
                getWorld().getObjects(ScoreCounter.class).get(0).setValue(player.stageScore);
            }
            else if (!getWorld().getObjects(Archer.class).isEmpty()) {
                Archer archer = getWorld().getObjects(Archer.class).get(0);
                archer.stageScore += score;
                getWorld().getObjects(ScoreCounter.class).get(0).setValue(archer.stageScore);
            }
        }
    }
    
    /*void giveStageScore(int score){
        if(getWorld().getClass() == Stage1.class){
            if(!getWorld().getObjects(Player.class).isEmpty()){
                Player player = getWorld().getObjects(Player.class).get(0);
                player.challengeCurrentScore += score;
            }
            else if (!getWorld().getObjects(Archer.class).isEmpty()) {
                Archer archer = getWorld().getObjects(Archer.class).get(0);
                archer.challengeCurrentScore += score;
            }
        }
    }*/
    
    void updateHP(Enemy enemy){
        if(enemy!=null){
            if(enemy.hpBar == null){
                HealthBar hpBar = new HealthBar(enemy);
                enemy.hpBar = hpBar;
                getWorld().addObject(hpBar, enemy.getX(), enemy.getY() - 20);
            }
            enemy.hpBar.setLocation(enemy.getX(), enemy.getY() - 50);   
        }
    }
}
