import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ArcherAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArcherAttack extends Actor
{
    /**
     * Act - do whatever the ArcherAttack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
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
    
    void hitEnemy(){
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        
        if(enemy != null){
            ArrowHit splat = new ArrowHit();
            getWorld().addObject(splat, enemy.getX(), enemy.getY());
            this.setLocation(enemy.getX(), enemy.getY());
        }
    }
}
