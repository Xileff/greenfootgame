import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knife here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knife extends Actor
{
    /**
     * Act - do whatever the Knife wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Player player;
    String direction;
    public static int BossX = 0;
    public static int BossY = 0;
    public static int MinibossX = 0;
    public static int MinibossY = 0;
    public static int NormalEnemyX = 0;
    public static int NormalEnemyY = 0;
    
    Knife(Player player){
        this.player = player;
        this.direction = player.direction;
    }
    
    public void act()
    {
        // Add your action code here.
        moveAllDirection();
        BossStabbed();
        MinibossStabbed();
        NormalEnemyStabbed();
        if(isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
        
        else if((this.BossX != 0) && (this.BossY != 0)){
            this.BossX = 0;
            this.BossY = 0;
            getWorld().removeObject(this);
            return;
        }
        
        else if((this.MinibossX != 0) && (this.MinibossY != 0)){
            this.MinibossX = 0;
            this.MinibossY = 0;
            getWorld().removeObject(this);
            return;
        }
        
        else if((this.NormalEnemyX != 0) && (this.NormalEnemyY != 0)){
            this.NormalEnemyX = 0;
            this.NormalEnemyY = 0;
            getWorld().removeObject(this);
            return;
        }
    }
    
    void moveAllDirection(){
        if(direction == "Right"){
            moveRight();
        }
        
        else if(direction == "Left"){
            moveLeft();
        }
        
        else if (direction == "Up"){
            moveUp();
        }
        
        else if (direction == "Down") {
            moveDown();
        }
    }
    
    void moveUp(){
        setRotation(270);
        move(10);
    }
    
    void moveDown(){
        setRotation(90);
        move(10);
    }
    
    void moveRight(){
        setRotation(0);
        move(10);
    }
    
    void moveLeft(){
        setRotation(180);
        move(10);
    }
    
    void BossStabbed(){
        if(isTouching(Boss.class)){
            Boss boss = (Boss)getOneIntersectingObject(Boss.class);
            SwordSkillAnimation swordSklAnimation = new SwordSkillAnimation();
            this.BossX = boss.getX();
            this.BossY = boss.getY();
            player.teleport(BossX, BossY);
            getWorld().addObject(swordSklAnimation, BossX, BossY);
            player.skillAttack();
        }
    }
    
    void MinibossStabbed(){
        if(isTouching(Miniboss.class)){
            Miniboss miniboss = (Miniboss)getOneIntersectingObject(Miniboss.class);
            SwordSkillAnimation swordSklAnimation = new SwordSkillAnimation();
            this.MinibossX = miniboss.getX();
            this.MinibossY = miniboss.getY();
            player.teleport(MinibossX, MinibossY);
            getWorld().addObject(swordSklAnimation, MinibossX, MinibossY);
            player.skillAttack();
        }
    }
    
    void NormalEnemyStabbed(){
        if(isTouching(Normal_Enemy.class)){
            Normal_Enemy normalEnemy = (Normal_Enemy)getOneIntersectingObject(Normal_Enemy.class);
            SwordSkillAnimation swordSklAnimation = new SwordSkillAnimation();
            this.NormalEnemyX = normalEnemy.getX();
            this.NormalEnemyY = normalEnemy.getY();
            player.teleport(NormalEnemyX, NormalEnemyY);
            getWorld().addObject(swordSklAnimation, NormalEnemyX, NormalEnemyY);
            player.skillAttack();
        }
    }
}