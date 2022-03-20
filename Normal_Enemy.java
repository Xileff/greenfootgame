import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Normal_Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Normal_Enemy extends Enemy
{
    /**
     * Act - do whatever the Normal_Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int rotation;
    int HP = 100;
    HealthBar hpBar;
    int shootDelay = 92;
    String direction = "Left";
    int imageTimer;
    boolean alerted;
    GreenfootSound slimeAttack = new GreenfootSound("slime-attack.mp3");
    GreenfootSound slimeDie = new GreenfootSound("slime-die.mp3");
    SimpleTimer idleTimer = new SimpleTimer();
    
    Normal_Enemy(Player mainPlayer) {
        super(mainPlayer);
        HP = 100;
    }
    
    Normal_Enemy(Chars mainPlayer) {
        super(mainPlayer);
        HP = 100;
    }
    
    Normal_Enemy(Archer mainPlayer){
        super(mainPlayer);
        HP = 100;
    }
    
    public void act()
    {
        // Add your action code here.
        //setImage(slime.getCurrentImage());
        setRotation(rotation);
        animate();
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
        shootSlimeToPlayer();
        zoomAndShrink();
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
    }
    
    void updateHP(Normal_Enemy normalEnemy){
        if(this.hpBar == null){
            HealthBar hpBar = new HealthBar(normalEnemy);
            normalEnemy.hpBar = hpBar;
            getWorld().addObject(hpBar, normalEnemy.getX(), normalEnemy.getY() - 50);
        }
        normalEnemy.hpBar.setLocation(normalEnemy.getX(), normalEnemy.getY() - 50);
    }
    
    void hitBySword(){
        SwordAttack swordAtk = (SwordAttack) getOneIntersectingObject(SwordAttack.class);
        
        if(swordAtk!= null){
            //nanti di sini munculin pop up besaran dmg atk, tapi
            //nilainya dari basicATK yg belum dibagi 2
            this.HP -= swordAtk.ATK;
            this.hpBar.loseHealth(swordAtk.ATK);
            if(this.HP <= 0){
                slimeDie.play();
                generateGulden();
                giveChallengeScore(2);
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
                slimeDie.play();
                generateGulden();
                giveChallengeScore(2);
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
                generateGulden();
                giveChallengeScore(2);
                slimeDie.play();
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
                slimeDie.play();
                generateGulden();
                giveChallengeScore(2);
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
                slimeDie.play();
                generateGulden();
                giveChallengeScore(2);
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
    
    void shootSlimeToPlayer() {
        //monster nyamperin player(bisa ke depan atau belakang player)
        if(!getObjectsInRange(300, Chars.class).isEmpty()){
            //player dipassing dari constuctor
            Chars player = getObjectsInRange(300, Chars.class).get(0);
            int playerX = player.getX();
            int playerY = player.getY();
            int dir = Greenfoot.getRandomNumber(4) + 1;
            int keepDistance = 100;

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
            
            if (getObjectsInRange(200, Chars.class).isEmpty()) {
                move(1);
            }
            
            else if (!getObjectsInRange(200, Chars.class).isEmpty()) {
                setLocation(getX(), getY());
                turnTowards(player.getX(), player.getY());
                shootDelay++;                
                if(shootDelay == 93){
                     slimeAttack.play();
                     slimeBullet bullet = new slimeBullet(this.getRotation());
                     getWorld().addObject(bullet, getX(), getY());
                     shootDelay = 0;
                }               
            }
        }
        
        else {
            moveAround();
            //playVoice();
        }
    }
    
    public void animate(){
      Chars chars = getWorld().getObjects(Chars.class).get(0);
      if(chars.getX() < this.getX()){
          this.direction = "Left";
        }
      else if(chars.getX() > this.getX()){
         this.direction = "Right";
      }
    }
    
    void playVoice(){
         if(idleTimer.millisElapsed() > 8000){
            //playsound
            idleTimer.mark();
        }   
    }
    
    void zoomAndShrink(){
        imageTimer = (imageTimer+1)%120;  // 10 = #sizes, 6 = delay, 2 = #directions (10*6*2 = 120)
        if (imageTimer%6 == 0) // time to change image
        {
            GreenfootImage image = new GreenfootImage("slime-1.png");
            if(this.direction == "Left"){
                image = new GreenfootImage("slime-1.png"); // new copy of original
            }
            else if (this.direction == "Right"){
                image = new GreenfootImage("slime-2.png"); // new copy of original
            }
            int imgWidth = image.getWidth(); // image width
            int imgHeight = image.getHeight(); // image height
            int additional = imageTimer/6; // amount to add to width and height (if growing)
            if (imageTimer > 60) additional = 20-imageTimer/6; // amount to add to width and height (if shrinking)
            image.scale(imgWidth+additional, imgHeight+additional);
            setImage(image);
        }
    }
}
