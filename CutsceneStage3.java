import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneStage1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutsceneStage3 extends Cutscene
{

    /**
     * Constructor for objects of class CutsceneStage1.
     * 
     */
    Player player;
    Archer archer;
    CutscenePlayer csPlayer;
    CutsceneArcher csArcher;
    CutsceneMiniboss csMiniboss1 = new CutsceneMiniboss();
    CutsceneMiniboss csMiniboss2 = new CutsceneMiniboss();
    CutsceneMiniboss csMiniboss3 = new CutsceneMiniboss();
    CutsceneMiniboss csMiniboss4 = new CutsceneMiniboss();
    CutsceneMiniboss csMiniboss5 = new CutsceneMiniboss();
    ContinueBtn continueBtn = new ContinueBtn();
    GreenfootSound clickSound = new GreenfootSound("main-menu-button.wav");
    CutsceneText cs3text1 = new CutsceneText("cs3text1.png");
    CutsceneText cs3text2 = new CutsceneText("cs3text2.png");
    CutsceneText cs3text3 = new CutsceneText("cs3text3.png");
    CutsceneText cs3text4 = new CutsceneText("cs3text4.png");
    
    public CutsceneStage3()
    {
        
    }
    
    public CutsceneStage3(Player paramPlayer){
        this.player = paramPlayer;
        csPlayer = new CutscenePlayer();
        prepare();
    }
    
    public CutsceneStage3(Archer paramArcher){
        this.archer = paramArcher;
        csArcher = new CutsceneArcher();
        prepare();
    }
    
    public void prepare(){
        if(this.csPlayer != null){
            addObject(csPlayer, 0, 360);
        }
        else if(this.csArcher != null){
            addObject(csArcher, 0, 360);
        }

        addObject(csMiniboss1, this.getWidth(), 180);
        addObject(csMiniboss2, this.getWidth(), 288);
        addObject(csMiniboss3, this.getWidth(), 360);
        addObject(csMiniboss4, this.getWidth(), 432);
        addObject(csMiniboss5, this.getWidth(), 540);
        addObject(continueBtn,1155,607);
        addObject(cs3text4, 640, 605);
        addObject(cs3text3, 640, 605);
        addObject(cs3text2, 640, 605);
        addObject(cs3text1, 640, 605);
    }
    
    public void act(){
        if(this.csPlayer != null){
            this.csPlayer.moveRight(400);
        }
        
        else if (this.csArcher != null){
            this.csArcher.moveRight(400);
        }
        
        this.csMiniboss1.moveLeft(this.getWidth() - 100);
        this.csMiniboss2.moveLeft(this.getWidth() - 200);
        this.csMiniboss3.moveLeft(this.getWidth() - 300);
        this.csMiniboss4.moveLeft(this.getWidth() - 200);
        this.csMiniboss5.moveLeft(this.getWidth() - 100);
        
        
        if(Greenfoot.mouseClicked(continueBtn)){
            continueBtn.getImage().scale((int)Math.round(continueBtn.getImage().getWidth()*0.8),(int)Math.round(continueBtn.getImage().getHeight()*0.8));
            clickSound.play();
            Greenfoot.delay(62);
            if(this.player != null){
                Greenfoot.setWorld(new Stage3(this.player));    
            }
            
            else if(this.archer != null){
                Greenfoot.setWorld(new Stage3(this.archer));
            }
        }
    }
}
