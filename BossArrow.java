import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class slimeBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossArrow extends EnemyAttack
{
    /**
     * Act - do whatever the slimeBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int rotation;
    protected int damage = 5;
    private GreenfootSound soundHit = new GreenfootSound("demonArrowHit.wav");
    
    public BossArrow(int rotation){
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
        move(4);
    }
    
    void hitPlayer(){
        Chars character = (Chars) getOneIntersectingObject(Chars.class);
        
        if(character != null){
            soundHit.play();
            ArrowHit splat = new ArrowHit();
            getWorld().addObject(splat, character.getX(), character.getY());
            this.setLocation(character.getX(), character.getY());
        }
    }
    
    void hitEnvironment(){
        Environment environment = (Environment) getOneIntersectingObject(Environment.class);
        
        if(environment != null){
            ArrowHit splat = new ArrowHit();
            getWorld().addObject(splat, environment.getX(), environment.getY());
            getWorld().removeObject(this);
            return;
        }
    }   
}
