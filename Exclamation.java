import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Exclamation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Exclamation extends Actor
{
    /**
     * Act - do whatever the Exclamation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Enemy enemy;
    Boss boss;
    Miniboss miniboss;
    Normal_Enemy normalEnemy;
    int duration;
    
    /* public Exclamation(Enemy paramEnemy, int paramDuration){
        this.enemy = paramEnemy;
        this.duration = paramDuration;
    } */
    
    public Exclamation(Boss paramBoss, int paramDuration){
        this.boss = paramBoss;
        this.duration = paramDuration;
    }
    
    public Exclamation(Miniboss paramMiniboss, int paramDuration){
        this.miniboss = paramMiniboss;
        this.duration = paramDuration;
    }
    
    public Exclamation(Normal_Enemy paramNormalEnemy, int paramDuration){
        this.normalEnemy = paramNormalEnemy;
        this.duration = paramDuration;
    }
    
    public void act()
    {
        // Add your action code here.
        
        
        if(this.boss != null){
            this.setLocation(this.boss.getX(), this.boss.getY() - 100);
        }
        else if(this.miniboss != null){
            this.setLocation(this.miniboss.getX(), this.miniboss.getY() - 100);
        }
        else if(this.normalEnemy != null){
            this.setLocation(this.normalEnemy.getX(), this.normalEnemy.getY() - 100);
        }
        /*
        if(this.enemy != null){
            this.setLocation(this.enemy.getX(), this.enemy.getY() - 100);
        }*/
        
        duration--;
        if(duration == 0){
            if(this.boss != null){
                this.boss.exclamationMark = null;
            }
            else if(this.miniboss != null){
                this.miniboss.exclamationMark = null;
            }
            else if (this.normalEnemy != null){
                this.normalEnemy.exclamationMark = null;
            }
            /*
            if(this.enemy != null && this.enemy.exclamationMark != null){
                this.enemy.exclamationMark = null;
            }*/
            getWorld().removeObject(this);
            return;
        }
    }
}
