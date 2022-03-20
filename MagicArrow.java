import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagicArrow extends ArcherAttack
{
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Archer archer;
    String direction;
    int ATK;
    public static int BossX = 0;
    public static int BossY = 0;
    public static int MinibossX = 0;
    public static int MinibossY = 0;
    public static int NormalEnemyX = 0;
    public static int NormalEnemyY = 0;
    
    public MagicArrow(Archer archer){
        this.archer = archer;
        this.direction = archer.direction;
        this.ATK = archer.ATK;
    }
    
    public void act()
    {
        // Add your action code here
        moveAllDirection();
        BossStabbed();
        MinibossStabbed();
        NormalEnemyStabbed();
        hitEnemy();
        if(isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
        
        else if((this.BossX != 0) && (this.BossY != 0)){
            multiShot(this.BossX, this.BossY, this.archer.SubATK);
            this.BossX = 0;
            this.BossY = 0;
            getWorld().removeObject(this);
            return;
        }
        
        else if((this.MinibossX != 0) && (this.MinibossY != 0)){
            multiShot(this.MinibossX, this.MinibossY, this.archer.SubATK);
            this.MinibossX = 0;
            this.MinibossY = 0;
            getWorld().removeObject(this);
            return;
        }
        
        else if((this.NormalEnemyX != 0) && (this.NormalEnemyY != 0)){
            multiShot(this.NormalEnemyX, this.NormalEnemyY, this.archer.SubATK);
            this.NormalEnemyX = 0;
            this.NormalEnemyY = 0;
            getWorld().removeObject(this);
            return;
        }
    }
    
    void moveAllDirection(){
        if(direction == "Right") {
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
    
    void BossStabbed(){
        if(isTouching(Boss.class)){
            Boss boss = (Boss)getOneIntersectingObject(Boss.class);
            if(boss != null){
                FloatingText dmgText = new FloatingText(Integer.toString(this.ATK * 2), Color.YELLOW, 500);
                getWorld().addObject(dmgText, boss.getX(), boss.getY() - 20);
                this.BossX = boss.getX();
                this.BossY = boss.getY();
            }
        }
    }
    
    void MinibossStabbed(){
        if(isTouching(Miniboss.class)){
            Miniboss miniboss = (Miniboss)getOneIntersectingObject(Miniboss.class);

            this.MinibossX = miniboss.getX();
            this.MinibossY = miniboss.getY();
            
        }
    }
    
    void NormalEnemyStabbed(){
        if(isTouching(Normal_Enemy.class)){
            Normal_Enemy normalEnemy = (Normal_Enemy)getOneIntersectingObject(Normal_Enemy.class);
            this.NormalEnemyX = normalEnemy.getX();
            this.NormalEnemyY = normalEnemy.getY();
            
        }
    }
    
    //buat manggil suara dan hapus diri
    void hitEnemy(){
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        if(enemy != null){
            FloatingText dmgText = new FloatingText(Integer.toString(this.ATK * 2), Color.YELLOW, 500);
            getWorld().addObject(dmgText, enemy.getX(), enemy.getY() - 20);
            ArrowHit splat = new ArrowHit();
            getWorld().addObject(splat, enemy.getX(), enemy.getY());
        }
    }

           
    void multiShot(int x, int y, int ATK){
        for(int rotation = 0; rotation <= 360; rotation+=45){
                MiniMagicArrow arrow = new MiniMagicArrow(rotation, ATK);
                getWorld().addObject(arrow, x, y);    
        }
    }
}