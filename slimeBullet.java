import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class slimeBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class slimeBullet extends EnemyAttack
{
    /**
     * Act - do whatever the slimeBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int rotation;
    int damage = 1;
    
    public slimeBullet(int rotation){
        this.rotation = rotation;
    }
    
    public void act()
    {
        // Add your action code here.
        flyToPlayer();
        if(isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
        hitPlayer();
        hitEnvironment();
    }
    
    void flyToPlayer(){
        setRotation(rotation);
        move(3);
    }
    
    void hitPlayer(){
        Chars character = (Chars) getOneIntersectingObject(Chars.class);
        
        if(character != null){
            bulletSplat splat = new bulletSplat();
            getWorld().addObject(splat, character.getX(), character.getY());
            this.setLocation(character.getX(), character.getY());
        }
    }
    
    void hitEnvironment(){
        Environment environment = (Environment) getOneIntersectingObject(Environment.class);
        
        if(environment != null){
            bulletSplat splat = new bulletSplat();
            getWorld().addObject(splat, environment.getX(), environment.getY());
            this.setLocation(environment.getX(), environment.getY());
        }
    }   
}
