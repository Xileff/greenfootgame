import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Player player;
    Archer archer;
    Enemy enemy;
    Boss boss;
    Miniboss miniboss;
    Normal_Enemy normalEnemy;
    int health = 0;
    int healthBarWidth = 100;
    int healthBarHeight = 15;
    int pixelsPerHealthPoint = 0;
    
    public void act()
    {
        // Add your action code here.
        updatePlayerHP();
        updateArcherHP();
        updateBossHP();
        updateMinibossHP();
        updateNormalEnemyHP();
    }
    
    HealthBar(Player player){
        this.player = player;
        this.health = player.hp;
        pixelsPerHealthPoint = (int)healthBarWidth/health;
    }
    
    HealthBar(Archer archer){
        this.archer = archer;
        this.health = archer.hp;
        pixelsPerHealthPoint = (int)healthBarWidth/health;
    }
    
    HealthBar(Enemy enemy){
        this.enemy = enemy;
        this.health = enemy.HP;
        pixelsPerHealthPoint = (int)this.healthBarWidth/health;
    }
    
    HealthBar(Boss boss){
        this.boss = boss;
        this.health = boss.HP; //500
        this.healthBarWidth = boss.HP;
        pixelsPerHealthPoint = (int)this.healthBarWidth/health;
    }
    
    HealthBar(Miniboss miniboss){
        this.miniboss = miniboss;
        this.health = miniboss.HP;
        pixelsPerHealthPoint = (int)this.healthBarWidth/health;
        /* if(miniboss != null && miniboss.HP > 0){
            update();
        } */
    }
    
    HealthBar(Normal_Enemy normalEnemy){
        this.normalEnemy = normalEnemy;
        this.health = normalEnemy.HP;
        pixelsPerHealthPoint = (int)this.healthBarWidth/health;
    }
    
    void update(){
        setImage(new GreenfootImage(healthBarWidth + 2, healthBarHeight + 2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.WHITE);
        myImage.drawRect(0, 0, healthBarWidth + 1, healthBarHeight + 1);
        if((this.player!=null) || (this.archer!=null)){
            myImage.setColor(Color.GREEN);
        }
        else {
            myImage.setColor(Color.RED);   
        }
        myImage.fillRect(1, 1, health * pixelsPerHealthPoint, healthBarHeight);
    }
    
    void loseHealth(int hpLost){
        health -= hpLost;
    }
    
    void updatePlayerHP(){
        if(this.player != null){
            player.hpBar.update();
        }
    }
    
    void updateArcherHP(){
        if(this.archer != null){
            archer.hpBar.update();
        }
    }
    
    void updateBossHP(){
        if(this.boss != null){
            boss.hpBar.update();
        }
    }
    
    void updateMinibossHP(){
        if(this.miniboss != null){
            miniboss.hpBar.update();
        }
    }
    
    void updateNormalEnemyHP(){
        if(this.normalEnemy!= null){
            normalEnemy.hpBar.update();
        }
    }
}
