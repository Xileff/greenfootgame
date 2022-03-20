import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Enemy
{
    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int rotation;
    protected int HP = 300;
    protected HealthBar hpBar;
    protected boolean alerted = false;
    private int countRight = 0;
    private int countLeft = 0;
    private int countAttack = 0;
    private int animateImage = 1;
    private int animateSpeed = 10;
    private int animateAttackImage = 1;
    private int animateAttackSpeed = 31;
    private int animationCounter = 0;
    private int shootDelay = 61;
    private SimpleTimer attackTimer = new SimpleTimer();
    
    //suara
    GreenfootSound demonAttack1 = new GreenfootSound("demonVoice1.mp3");
    GreenfootSound demonAttack2 = new GreenfootSound("demonVoice2.mp3");
    GreenfootSound demonDie = new GreenfootSound("demonDie.mp3");
    GreenfootSound demonMove = new GreenfootSound("demonFootsteps.mp3");
    protected SimpleTimer idleTimer = new SimpleTimer();
    
    Boss(Chars mainPlayer) {
        super(mainPlayer);
    }
    
    public void act()
    {   
        setRotation(rotation);
        checkObstacle();
        hitByArrow();
        if(this.HP <= 0){
            return;
        }
        hitByMagicArrow();
        if(this.HP <= 0){
            return;
        }
        hitByMiniMagicArrow();
        if(this.HP <= 0){
            return;
        }
        shootArrowToPlayer();
        rotation = getRotation();
        setRotation(0);
        hitBySword();
        if(this.HP <= 0){
            return;
        }
        hitBySwordSkill();
        if(this.HP <= 0){
            return;
        }
        updateHP(this);
        if(!getObjectsInRange(600, Chars.class).isEmpty()){
            animateAttack();
        }
        else {
            animate();   
        }
        countRight++;
        countLeft++;
        countAttack++;
    }
    //hp bar
    void updateHP(Boss boss){
        if(boss!=null){
            if(boss.hpBar == null){
                HealthBar hpBar = new HealthBar(boss);
                boss.hpBar = hpBar;
                getWorld().addObject(hpBar, boss.getX(), boss.getY() - 20);
            }
            boss.hpBar.setLocation(boss.getX(), boss.getY() - 50);   
        }
    }
    
    void hitBySword(){
        SwordAttack swordAtk = (SwordAttack) getOneIntersectingObject(SwordAttack.class);
        
        if(swordAtk!= null){
            //nanti di sini munculin pop up besaran dmg atk, tapi
            //nilainya dari basicATK yg belum dibagi 2
            this.HP -= swordAtk.ATK;
            this.hpBar.loseHealth(swordAtk.ATK);
            if(this.HP <= 0){
                demonDie.play();
                generateGulden();
                giveChallengeScore(10);
                getWorld().removeObject(this.hpBar);
                getWorld().removeObject(this);
            }
        }
    }
    
    void hitBySwordSkill(){
        SwordSkill swordSkl = (SwordSkill) getOneIntersectingObject(SwordSkill.class);
        
        if(swordSkl != null){
            FloatingText dmgText = new FloatingText(Integer.toString(swordSkl.ATK * 2), Color.CYAN, 500);
            getWorld().addObject(dmgText, this.getX(), this.getY() - 20);
            this.HP -= swordSkl.ATK * 2;
            this.hpBar.loseHealth(swordSkl.ATK * 2);
            if(this.HP <= 0){
                demonDie.play();
                generateGulden();
                giveChallengeScore(10);
                getWorld().removeObject(this.hpBar);
                getWorld().removeObject(this);
            }
        }
    }
    
    void hitByArrow(){ //sudah diperbaiki, masalahnya ada di seekarcher
        BasicArrow arrow = (BasicArrow) getOneIntersectingObject(BasicArrow.class);
        
        if(arrow != null){
            FloatingText dmgText = new FloatingText(Integer.toString(arrow.ATK * 2), Color.YELLOW, 500);
            getWorld().addObject(dmgText, this.getX(), this.getY() - 20);
            this.alerted = true;
            seekArcher(1);
            this.HP -= arrow.ATK;
            this.hpBar.loseHealth(arrow.ATK);
            getWorld().removeObject(arrow);
            if(this.HP <= 0){
                demonDie.play();
                generateGulden();
                giveChallengeScore(10);
                getWorld().removeObject(this.hpBar);
                getWorld().removeObject(this.exclamationMark);
                getWorld().removeObject(this);
            }
        }
        
        else if(arrow == null) {
            this.alerted = false;
        }
    }
    
    void hitByMagicArrow(){
        MagicArrow arrow = (MagicArrow) getOneIntersectingObject(MagicArrow.class);
        
        if(arrow != null){
            FloatingText dmgText = new FloatingText(Integer.toString(arrow.ATK * 2), Color.YELLOW, 500);
            getWorld().addObject(dmgText, this.getX(), this.getY() - 20);
            this.alerted = true;
            seekArcher(1);
            this.HP -= arrow.ATK;
            this.hpBar.loseHealth(arrow.ATK);
            
            if(this.HP <= 0){
                generateGulden();
                giveChallengeScore(10);
                getWorld().removeObject(this.hpBar);
                getWorld().removeObject(this.exclamationMark);
                getWorld().removeObject(this);
            }
        }
        
        else if(arrow == null) {
            this.alerted = false;
        }
    }
    
    void hitByMiniMagicArrow(){
        MiniMagicArrow arrow = (MiniMagicArrow) getOneIntersectingObject(MiniMagicArrow.class);
        
        if(arrow != null){
            FloatingText dmgText = new FloatingText(Integer.toString(arrow.ATK * 2), Color.YELLOW, 500);
            getWorld().addObject(dmgText, this.getX(), this.getY() - 20);
            this.alerted = true;
            seekArcher(1);
            this.HP -= arrow.ATK;
            this.hpBar.loseHealth(arrow.ATK);
            //getWorld().removeObject(arrow);
            
            if(this.HP <= 0){
                demonDie.play();
                generateGulden();
                giveChallengeScore(10);
                getWorld().removeObject(this.hpBar);
                getWorld().removeObject(this.exclamationMark);
                getWorld().removeObject(this);
            }
        }
        
        else if(arrow == null) {
            this.alerted = false;
        }
    }
    
    void seekArcher(int movementSpeed){
        int seekDuration = 0;
        Archer archer = getWorld().getObjects(Archer.class).get(0);
        if(this.exclamationMark == null){
             this.exclamationMark = new Exclamation(this, 186);   
        }
        //hanya tambahkan tanda seru ketika musuh belum memilikinya. tujuannya untuk mengurangi penggunaan memory RAM
        if(this.alerted == true){
            //false supaya tidak munculin exclamation lagi ketika sudah ada
            this.alerted = false;
            getWorld().addObject(this.exclamationMark, getX(), getY() - 100);
        }
        move(movementSpeed);
        turnTowards(archer.getX(), archer.getY());
        seekDuration++;
        if(seekDuration == 186){
            return;
        }
    }
    
    void shootArrowToPlayer() {
        if(!getObjectsInRange(800, Chars.class).isEmpty()){
            //player dipassing dari constuctor
            Chars player = getObjectsInRange(800, Chars.class).get(0);
            int playerX = player.getX();
            int playerY = player.getY();
            int dir = Greenfoot.getRandomNumber(4) + 1;
            int keepDistance = 700;

            if (dir == 1) {
                if(!(this.getX() == playerX - keepDistance)) {
                    turnTowards(playerX - keepDistance, playerY);
                }
            }
            
            else if (dir == 2) {
                if (!(this.getX() == playerX + keepDistance)) {
                    turnTowards(player.getX() + keepDistance, player.getY());
                }
            }
            
            else if (dir == 3) {
                if (!(this.getY() == playerY - keepDistance)) {
                    turnTowards(player.getX(), player.getY() - keepDistance);
                }
            }
            
            else if (dir == 4) {
                if (!(this.getX() == playerY + keepDistance)) {
                    turnTowards(player.getX(), player.getY() + keepDistance);
                }
            }
            
            if (getObjectsInRange(600, Chars.class).isEmpty()) {
                move(1);
            }
            
            else if (!getObjectsInRange(600, Chars.class).isEmpty()) {
                setLocation(getX(), getY());
                turnTowards(player.getX(), player.getY());
                shootDelay++;                
                if(shootDelay == 93){
                     attackVoice();
                     BossArrow bullet = new BossArrow(this.getRotation());
                     BossArrow bullet1 = new BossArrow(this.getRotation() - 40);
                     BossArrow bullet2 = new BossArrow(this.getRotation() + 40);
                     getWorld().addObject(bullet, getX(), getY());
                     getWorld().addObject(bullet1, getX(), getY());
                     getWorld().addObject(bullet2, getX(), getY());
                     shootDelay = 0;
                }               
            }
        }
        
        else {
            moveAround();
            moveSound();
        }
    }
    
    public void animate(){
      Chars chars = getWorld().getObjects(Chars.class).get(0);
      if(chars.getX() < this.getX()){
          animateLeft();
        }
      else if(chars.getX() > this.getX()){
         animateRight();
      }
    }
    
    void animateRight() {
        if (countRight % animateSpeed == 0) {
                if(animateImage > 2){
                    animateImage = 1;
                }
                setImage("demon-right-" + animateImage + ".png");
                animateImage++;
        }
    }
    
    void animateLeft() {
        if (countLeft % animateSpeed == 0) {
                if(animateImage > 2){
                    animateImage = 1;
                }
                setImage("demon-left-" + animateImage + ".png");
                animateImage++;
        }
    }
    
    void animateAttack(){
        if (countAttack % animateAttackSpeed == 0){
            countAttack = 0;
            Chars chars = getWorld().getObjects(Chars.class).get(0);
            if(chars.getX() < this.getX()){
                if(animateImage == 1){
                    setImage("demon-attack-left-1.png");        
                }
                else if(animateImage == 2){
                    setImage("demon-attack-left-2.png");     
                }
                else if(animateImage > 2){
                    animateImage = 0;
                }
            }
            else if(chars.getX() > this.getX()){
                if(animateImage == 1){
                    setImage("demon-attack-right-1.png");     
                }
                else if(animateImage == 2){
                    setImage("demon-attack-right-2.png");     
                }
                else if(animateImage > 2){
                    animateImage = 0;
                }
            }
            animateImage++;
        }
    }
    
    void attackVoice(){
        //playsound
        demonAttack1.setVolume(50);
        demonAttack1.play();
    }
    
    void moveSound(){
        if(idleTimer.millisElapsed() > 8000){
            demonMove.setVolume(50);
            demonMove.play();
        }
    }
}