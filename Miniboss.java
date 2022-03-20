import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class Miniboss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Miniboss extends Enemy
{
    /**
     * Act - do whatever the Miniboss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int rotation;
    protected int HP = 100;
    protected HealthBar hpBar;
    private boolean alerted;
    protected Exclamation exclamationMark;
    
    //animasi
    private int countRight = 0;
    private int countLeft = 0;
    private int countAttack = 0;
    private int animateImage = 1;
    private int animateSpeed = 10;
    private int animateAttackImage = 1;
    private int animateAttackSpeed = 31;
    
    //suara
    protected SimpleTimer idleTimer = new SimpleTimer();
    GreenfootSound zombieAttack = new GreenfootSound("zombie-attack.mp3");
    GreenfootSound zombieDie = new GreenfootSound("zombie-die.mp3");
    GreenfootSound zombieMove = new GreenfootSound("zombie-move.wav");
    GreenfootSound zombieIdle = new GreenfootSound("zombie-idle.mp3");
    
    Miniboss(Chars mainPlayer){
        super(mainPlayer);
        setImage(new GreenfootImage("skeleton-left-2.png"));
    }
    
    public void act()
    {
        // Add your action code here.
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
        findAndScratchPlayer();
        rotation = getRotation();
        setRotation(0);
        hitBySword();
        if(this.HP <= 0){
            return;
        }
        hitByArrow();
        if(this.HP <= 0){
            return;
        }
        hitBySwordSkill();
        if(this.HP <= 0){
            return;
        }
        updateHP(this);
        if(isTouching(Chars.class)){
            animateAttack();
        }
        else {
            animate();
        }
        countRight++;
        countLeft++;
        countAttack++;
    }
    
    void updateHP(Miniboss miniboss){
        if(miniboss.hpBar == null){
            HealthBar hpBar = new HealthBar(miniboss);
            miniboss.hpBar = hpBar;
            getWorld().addObject(hpBar, miniboss.getX(), miniboss.getY() - 90);
        }
        miniboss.hpBar.setLocation(miniboss.getX(), miniboss.getY() - 90);
    }
    
    void hitBySword(){
        SwordAttack swordAtk = (SwordAttack) getOneIntersectingObject(SwordAttack.class);
        
        if(swordAtk!= null){
            //nanti di sini munculin pop up besaran dmg atk, tapi
            //nilainya dari basicATK yg belum dibagi 2
            this.HP -= swordAtk.ATK;
            this.hpBar.loseHealth(swordAtk.ATK);
         
            if(this.HP <= 0){
                zombieDie.play();
                generateGulden();
                giveChallengeScore(5);
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
                zombieDie.play();
                generateGulden();
                giveChallengeScore(5);
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
                zombieDie.play();
                generateGulden();
                giveChallengeScore(5);
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
                zombieDie.play();
                generateGulden();
                giveChallengeScore(5);
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
            
            if(this.HP <= 0){
                zombieDie.play();
                generateGulden();
                giveChallengeScore(5);
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
    
    void findAndScratchPlayer() {
        //monster nyamperin player(bisa ke depan atau belakang player)
        if(!getObjectsInRange(300, Chars.class).isEmpty()){
            //player dipassing dari constuctor
            Chars player = getObjectsInRange(300, Chars.class).get(0);
            int playerX = player.getX();
            int playerY = player.getY();
            int dir = Greenfoot.getRandomNumber(4) + 1;
            int keepDistance = 100;
            if (dir == 1) {
                if(!(this.getX() == playerX - keepDistance)){
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
            
            if (!intersects(player)) {
                move(1);
            }
            
            else {
                setLocation(getX(), getY());
                scratch();
                zombieAttack.setVolume(25);
                zombieAttack.play();
            }
        }
        
        else {
            moveAround();
            playVoice();
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
            countRight = 0;
            if(animateImage > 2){
                animateImage = 0;
            }
            setImage("skeleton-right-" + animateImage + ".png");
            animateImage++;
            }
    }
    
    void animateLeft() {
        if (countLeft % animateSpeed == 0) {
            countLeft = 0;
            if(animateImage > 2){
                animateImage = 0;
            }
            setImage("skeleton-left-" + animateImage + ".png");
            animateImage++;
            }
    }
    
    void animateAttack(){
        if (countAttack % animateAttackSpeed == 0){
            countAttack = 0;
            Chars chars = getWorld().getObjects(Chars.class).get(0);
            if(chars.getX() < this.getX()){
                if(animateImage == 1){
                setImage("skeleton-attack-left.png");        
                }
                else if(animateImage == 2){
                    setImage("skeleton-left-1.png");        
                }
                else if(animateImage > 2){
                    animateImage = 0;
                }
            }
            else if(chars.getX() > this.getX()){
                if(animateImage == 1){
                setImage("skeleton-attack-right.png");        
                }
                else if(animateImage == 2){
                    setImage("skeleton-right-1.png");        
                }
                else if(animateImage > 2){
                    animateImage = 0;
                }
            }
            animateImage++;
        }
    }
    
    void playVoice(){
         if(idleTimer.millisElapsed() > 8000){
            zombieIdle.setVolume(50);
            zombieIdle.play();
            idleTimer.mark();
        }   
    }
}