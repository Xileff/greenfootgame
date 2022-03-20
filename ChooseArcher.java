import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ChooseArcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChooseArcher extends Button
{
    /**
     * Act - do whatever the ChooseArcher wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootSound ArcherIntro = new GreenfootSound("button-archer.wav");
    
    public void act()
    {
        // Add your action code here.
        clicked();
    }
    
    void clicked(){
        if(Greenfoot.mouseClicked(this)){
            ArcherIntro.play();
            getImage().scale((int)Math.round(getImage().getWidth()*0.9),(int)Math.round(getImage().getHeight()*0.9));
            Greenfoot.delay(186);
            setBattleWorld();
        }
    }
    
    void setBattleWorld(){
        if(getWorld().getObjects(Archer.class).isEmpty()){
            Archer archer = new Archer();
            Greenfoot.setWorld(new Tutorials(archer));
        }
        
        else {
            Archer archer = getWorld().getObjects(Archer.class).get(0);
            //kalo masih ga bisa, coba taroh karakter chibi di bawah karakter gede
            if(archer.progress == "Stage1"){
                Greenfoot.setWorld(new Stage1(archer));
            }
            
            else if(archer.progress == "Stage2"){
                Greenfoot.setWorld(new Stage2(archer));
            }
            
            else if(archer.progress == "Stage3"){
                Greenfoot.setWorld(new Stage3(archer));
            }
            
            else if(archer.progress == "Completed"){
                Greenfoot.setWorld(new TimeChallenge(archer));
            }
        }
    }
}
