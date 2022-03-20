import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ChooseSwordsman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChooseSwordsman extends Button
{
    /**
     * Act - do whatever the ChooseSwordsman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootSound SwordsmanIntro = new GreenfootSound("button-swordsman.wav");
    
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)){
             SwordsmanIntro.play();
             getImage().scale((int)Math.round(getImage().getWidth()*0.9),(int)Math.round(getImage().getHeight()*0.9));
             Greenfoot.delay(186);
             setBattleWorld();   
        }
    }
    
    void setBattleWorld(){
        if(getWorld().getObjects(Player.class).isEmpty()){
            Player player = new Player();
            Greenfoot.setWorld(new Tutorials(player));
        }
        
        else {
            Player player = getWorld().getObjects(Player.class).get(0);
            //kalo masih ga bisa, coba taroh karakter chibi di bawah karakter gede
            if(player.progress == "Stage1"){
                Greenfoot.setWorld(new Stage1(player));
            }
            
            else if(player.progress == "Stage2"){
                Greenfoot.setWorld(new Stage2(player));
            }
            
            else if(player.progress == "Stage3"){
                Greenfoot.setWorld(new Stage3(player));
            }
            
            else if(player.progress == "Completed"){
                Greenfoot.setWorld(new TimeChallenge(player));
            }
        }
    }
}
