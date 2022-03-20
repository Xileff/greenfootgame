import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneStage1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutsceneStage2 extends Cutscene
{

    /**
     * Constructor for objects of class CutsceneStage1.
     * 
     */
    Player player;
    Archer archer;
    CutscenePlayer csPlayer;
    CutsceneArcher csArcher;
    CutsceneNormalEnemy csNormalEnemy = new CutsceneNormalEnemy();
    CutsceneMiniboss csMiniboss1 = new CutsceneMiniboss();
    CutsceneNormalEnemy csNormalEnemy2 = new CutsceneNormalEnemy();
    CutsceneMiniboss csMiniboss2 = new CutsceneMiniboss();
    CutsceneNormalEnemy csNormalEnemy4 = new CutsceneNormalEnemy();
    ContinueBtn continueBtn = new ContinueBtn();
    GreenfootSound clickSound = new GreenfootSound("main-menu-button.wav");
    CutsceneText cs2text1 = new CutsceneText("cs2text1.png");
    CutsceneText cs2text2 = new CutsceneText("cs2text2.png");
    CutsceneText cs2text3 = new CutsceneText("cs2text3.png");
    CutsceneText cs2text4 = new CutsceneText("cs2text4.png");
    
    public CutsceneStage2()
    {
        
    }
    
    public CutsceneStage2(Player paramPlayer){
        this.player = paramPlayer;
        csPlayer = new CutscenePlayer();
        prepare();
    }
    
    public CutsceneStage2(Archer paramArcher){
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
        addObject(csNormalEnemy, this.getWidth(), 360);
        addObject(csMiniboss1, this.getWidth(), 180);
        addObject(csMiniboss2, this.getWidth(), 540);
        addObject(csNormalEnemy2, 800, 0);
        addObject(csNormalEnemy4, 800, this.getHeight());
        addObject(continueBtn,1155,607);
        addObject(cs2text4, 640, 605);
        addObject(cs2text3, 640, 605);
        addObject(cs2text2, 640, 605);
        addObject(cs2text1, 640, 605);
    }
    
    public void act(){
        if(this.csPlayer != null){
            this.csPlayer.moveRight(400);
        }
        
        else if (this.csArcher != null){
            this.csArcher.moveRight(400);
        }
        
        this.csNormalEnemy.moveLeft(960);
        this.csMiniboss1.moveLeft(1088);
        this.csMiniboss2.moveLeft(1088);
        this.csNormalEnemy2.moveDown(120);
        this.csNormalEnemy4.moveUp(600);
        
        if(Greenfoot.mouseClicked(continueBtn)){
            continueBtn.getImage().scale((int)Math.round(continueBtn.getImage().getWidth()*0.8),(int)Math.round(continueBtn.getImage().getHeight()*0.8));
            clickSound.play();
            Greenfoot.delay(62);
            if(this.player != null){
                Greenfoot.setWorld(new Stage2(this.player));    
            }
            
            else if(this.archer != null){
                Greenfoot.setWorld(new Stage2(this.archer));
            }
        }
    }
}
