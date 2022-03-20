import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasicArrow extends ArcherAttack
{
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Archer archer;
    String direction;
    int ATK;
    
    public BasicArrow(Archer archer){
        this.archer = archer;
        this.direction = archer.direction;
        this.ATK = archer.ATK;
    }
    
    public void act()
    {
        // Add your action code here.
        moveAllDirection();
        
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        if(enemy != null){
            restoreMana();
            setLocation(enemy.getX(), enemy.getY());
            ArrowHit splat = new ArrowHit();
            getWorld().addObject(splat, enemy.getX(), enemy.getY());
            this.setLocation(enemy.getX(), enemy.getY());
            return;
        }
        
        else if(isAtEdge()){
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
    
    void restoreMana(){
        if((this.archer.mana += 10) <= 100){
            this.archer.mana += 10;
            this.archer.manaBar.mana = archer.mana;
        }
        
        else {
            this.archer.mana = 100;
            this.archer.manaBar.mana = 100;
        }
    }
    
    
}
