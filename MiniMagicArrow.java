import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiniMagicArrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniMagicArrow extends ArcherAttack
{
    /**
     * Act - do whatever the MiniMagicArrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int rotation;
    int ATK;
    SimpleTimer flyTimer = new SimpleTimer();
    
    public MiniMagicArrow(int rotation, int ATK){
        this.rotation = rotation;
        this.ATK = ATK;
    }
    
    public void act()
    {
        // Add your action code here.
        this.setRotation(this.rotation);
        move(3);
        if(flyTimer.millisElapsed() > 500){
            move(7);
        }
        hitEnemy();
        if(isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
    }
    
    void fly(){
        SimpleTimer flyTimer = new SimpleTimer();
        
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
}
