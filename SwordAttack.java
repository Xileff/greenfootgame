import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;

/**
 * Write a description of class basicAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SwordAttack extends PlayerAttack
{
    /**
     * Act - do whatever the basicAttack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SimpleTimer timer = new SimpleTimer();
    GreenfootSound soundSlash = new GreenfootSound("BasicAttack_Sword.mp3");
    GreenfootSound soundSplat = new GreenfootSound("SwordSlash.mp3");
    Player player;
    int ATK = 0;
    String direction;
    
    SwordAttack(/*String direction, int PlayerATK, */Player player){
        GreenfootImage basicAtkImg;
        this.player = player;
        this.ATK = player.ATK;
        this.direction = player.direction;
        int reduceSize = 300;
        if (direction == "Right") {
            basicAtkImg = new GreenfootImage("BasicAttack_Right.png");
            basicAtkImg.scale(basicAtkImg.getWidth() - reduceSize, basicAtkImg.getHeight() - reduceSize);   
            this.setImage(basicAtkImg);
        }
        
        else if (direction == "Left") {
            basicAtkImg = new GreenfootImage("BasicAttack_Left.png");
            basicAtkImg.scale(basicAtkImg.getWidth() - reduceSize, basicAtkImg.getHeight() - reduceSize);   
            this.setImage(basicAtkImg);
        }
        
        else if (direction == "Up") {
            basicAtkImg = new GreenfootImage("BasicAttack_Up.png"); 
            basicAtkImg.scale(basicAtkImg.getWidth() - reduceSize, basicAtkImg.getHeight() - reduceSize);   
            this.setImage(basicAtkImg);
        }
        
        else if (direction == "Down") {
            basicAtkImg = new GreenfootImage("BasicAttack_Down.png");
            basicAtkImg.scale(basicAtkImg.getWidth() - reduceSize, basicAtkImg.getHeight() - reduceSize);   
            this.setImage(basicAtkImg);
        }
    }
    
    public void act()
    {
        appear();
        soundSlash.setVolume(40);
        soundSlash.play();
    }
    
    //buat manggil suara dan hapus diri
    void appear(){
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        
        if(enemy != null){
            FloatingText dmgText = new FloatingText(Integer.toString(this.ATK * 2), Color.YELLOW, 500);
            getWorld().addObject(dmgText, enemy.getX(), enemy.getY() - 20);
            //getWorld().showText(Integer.toString(this.ATK * 2), enemy.getX(), enemy.getY() - 20);
            soundSplat.setVolume(20);
            soundSplat.play();
            restoreMana();
            getWorld().removeObject(this);
            timer.mark();
            return;
        }
        
        else if(timer.millisElapsed() > 150){
            getWorld().removeObject(this);
            timer.mark();
        }
    }
    
    void restoreMana(){
        if((player.mana += 10) <= 100){
            player.mana += 10;
            player.manaBar.mana = player.mana;
        }
        
        else {
            player.mana = 100;
            player.manaBar.mana = 100;
        }
    }
}
