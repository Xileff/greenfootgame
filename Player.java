import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Chars {
    /**
     * Act - do whatever the player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String progress = "Stage1";    
    protected int hp = 50;
    protected int mana = 100;
    protected int gulden = 0;
    protected int potion = 5;
    protected int ATK = 10;
    protected int cooldown = 5;
    protected Counter guldenCounter;
    protected Counter potionCounter;
    protected CooldownCounter cdCounter;
    protected HealthBar hpBar;
    protected ManaBar manaBar;
    protected Potion btnPotion = new Potion();
    GreenfootSound potionSound = new GreenfootSound("potion.mp3");
    protected SimpleTimer timer = new SimpleTimer();
    protected SimpleTimer potionTimer = new SimpleTimer();
    protected GuldenStay imgGulden = new GuldenStay();
    
    //variabel skor
    protected int stageScore;
    
    protected int challengeCurrentScore;
    protected int challengeHighestScore;
    
    //kalau ATK berniali 20. maka sekali serang, kurangin 40 HP musuh
    // maka 5 kali hit, HP musuh yg 200 bisa habis
    //jadi nanti ATK nya dibagi 2 dulu baru kurangin hp musuh 
    
    private static int countRight = 0;
    private static int countLeft = 0;
    private static int countUp = 0;
    private static int countDown = 0;
    private static int countIdle = 0;
    private static int animateImage = 1;
    private static int animateSpeed = 8;
      
    protected static String direction = "Right";

    private SimpleTimer talkTimer = new SimpleTimer();
    private GreenfootSound voiceLine1 = new GreenfootSound("swordsmanVoice1.wav");
    private GreenfootSound voiceLine2 = new GreenfootSound("swordsmanVoice2.wav");
    
    Player(){
        setImage(new GreenfootImage("kanan1.png"));
        voiceLine1.setVolume(20);
        voiceLine2.setVolume(20);
    }
    
    public void act()
    {
        // Add your action code here.
        countRight++;
        countLeft++;
        countUp++;
        countDown++;
        countIdle++;
        moveControl();
        Attack();
        playVoice();
        hitByScratch();
        hitBySlimeBullet();
        hitByBossArrow();
        updateHP();
        updateMana();
        updateGulden();
        updatePotion();
        usePotion();
        nextStage();
    }
    
    void setDirectionRight(){
        if(this.direction != "Right") {
            this.direction = "Right";
            this.setImage("kanan1.png");
        }
    }
    
    void setDirectionLeft() {
        if(this.direction != "Left") {
            this.direction = "Left";
            this.setImage("kiri1.png");
        }
    }
    
    void setDirectionUp(){
        if(this.direction != "Up") {
            this.direction = "Up";
            this.setImage("BackSword1.png");
        }
    }
    
    void setDirectionDown(){
        if(this.direction != "Down") {
            this.direction = "Down";
            this.setImage("FrontSword1.png");
        }
    }
    
    void animateRight() {
        if (countRight % animateSpeed == 0) {
                if(animateImage > 2){
                    animateImage = 1;
                }
                
                setImage("kanan" + animateImage + ".png");
                animateImage++;
            }
    }
    
    void animateLeft() {
        if (countLeft % animateSpeed == 0) {
                if(animateImage > 2){
                    animateImage = 1;
                }
                
                setImage("kiri" + animateImage + ".png");
                animateImage++;
            }
        }
    
    void animateUp(){
        if (countUp % animateSpeed == 0) {
                if(animateImage > 1){
                    animateImage = 1;
                }
                
                setImage("BackSword" + animateImage + ".png");
                animateImage++;
            }
        }
        
    void animateDown() {
        if (countDown % animateSpeed == 0) {
            if(animateImage > 3){
                    animateImage = 1;
                }
                setImage("FrontSword" + animateImage + ".png");
                animateImage++;
        }
    }
    
    void animateAttack(){
        if(this.direction == "Right"){
            this.setImage("SwordAttackRight.png");
        }
        else if(this.direction == "Left"){
            this.setImage("SwordAttackLeft.png");
        }
    }
    
    void animateDeath(){
        this.setImage("SwordDeath.png");
        Greenfoot.delay(124);
    }
    
    void Attack(){       
           if(Greenfoot.isKeyDown("space") && timer.millisElapsed() > 400){
               basicAttack();
               timer.mark();
           }
           
           else if(Greenfoot.isKeyDown("Enter") && timer.millisElapsed() > 400){
               if(this.mana >= 30 && this.cdCounter == null){
                   updateCooldown();
                   this.mana -= 30;
                   this.manaBar.loseMana(30);
                   throwKnife();
                   timer.mark();
               }
               
               else {
                   basicAttack();
                   timer.mark();
               }
               
           }
    }
    
    void basicAttack(){
        animateAttack();
        SwordAttack swordAtk = new SwordAttack(this);
        getWorld().addObject(swordAtk, getX() + 20, getY());
    }
    
    void skillAttack(){
        animateAttack();
        SwordSkill swordSkl = new SwordSkill(this);
        getWorld().addObject(swordSkl, getX(), getY());
    }
    
    void throwKnife(){
        Knife knife = new Knife(this);
        this.getWorld().addObject(knife, this.getX(), this.getY());
    }
    
    void teleport(int x, int y){
        setLocation(x, y);
    }
    
    //Method gerak secara keseluruhan(method belok + ubah gambar karakter)
    void moveControl(){
        if(Greenfoot.isKeyDown("W")) {
                moveUp(4);
            }
        
        else if (Greenfoot.isKeyDown("A")) {
                moveLeft(4);
            }
        
        else if (Greenfoot.isKeyDown("S")) {
                moveDown(4);
            }
        
        else if (Greenfoot.isKeyDown("D")) {
                moveRight(4);       
            }
    }    
    
    boolean noObstacle(){
        boolean a = true;
        Environment environment = (Environment) getOneIntersectingObject(Environment.class);
        if(environment == null) {
            a = true;
        }
        else if (environment != null){
            a = false;
        }
        return a;
    }
        
    //Method belok secara modular(terpisah)
    void moveRight(int distance){
        setDirectionRight();
        animateRight();
        if(noObstacle() == true){
            this.setLocation(getX() + distance , getY());    
        }
        
        else if(noObstacle() == false){
            this.setLocation(getX() - distance - 10, getY());    
        }
    }
    
    void moveLeft(int distance){
        setDirectionLeft();
        animateLeft();
        if(noObstacle() == true){
            this.setLocation(getX() - distance, getY());    
        }
        
        else if(noObstacle() == false) {
            this.setLocation(getX() + distance + 10, getY());    
        }
    }
    
    void moveUp(int distance){
        setDirectionUp();
        animateUp();
        if(noObstacle() == true){
            this.setLocation(getX(), getY() - distance);    
        }
        
        else if(noObstacle() == false) {
            this.setLocation(getX(), getY() + distance + 10);    
        }
    }
    
    void moveDown(int distance){
        setDirectionDown();
        animateDown();
        if(noObstacle() == true){
            this.setLocation(getX(), getY() + distance);    
        }
        
        else if(noObstacle() == false) {
            this.setLocation(getX(), getY() - distance - 10);    
        }
    }
    
    //hp bar
    void updateHP(){
        if(this.hpBar == null){
            HealthBar hpBar = new HealthBar(this);
            this.hpBar = hpBar;
            getWorld().addObject(this.hpBar, this.getX(), this.getY() - 85);
        }
        this.hpBar.setLocation(this.getX(), this.getY() - 85);
    }
    
    //mana bar
    void updateMana(){
        if(this.manaBar == null){
            ManaBar manaBar = new ManaBar(this);
            this.manaBar = manaBar;
            getWorld().addObject(this.manaBar, this.getX(), this.getY() - 15);
        }
        this.manaBar.setLocation(this.getX(), this.getY() - 15);
    }
    
    void hitByScratch(){
        //SimpleTimer hitTimer = new SimpleTimer();
        Scratch scratch = (Scratch) getOneIntersectingObject(Scratch.class);
        
        if(scratch != null){
            //nanti di sini munculin pop up besaran dmg atk, tapi
            //nilainya dari basicATK yg belum dibagi 2
            this.hp -= scratch.damage;
            this.hpBar.loseHealth(scratch.damage);
            if(this.hp <= 0){
                this.hp = 100;
                this.hpBar.health = this.hp;
                animateDeath();           
                Greenfoot.setWorld(new GameOver(this));
            }
        }
    }
    
    void hitBySlimeBullet(){
        //SimpleTimer hitTimer = new SimpleTimer();
        slimeBullet bullet = (slimeBullet) getOneIntersectingObject(slimeBullet.class);
        
        if(bullet != null){
            //nanti di sini munculin pop up besaran dmg atk, tapi
            //nilainya dari basicATK yg belum dibagi 2
            this.hp -= bullet.damage;
            this.hpBar.loseHealth(bullet.damage);
            if(this.hp <= 0) {
                this.hp = 100;
                this.hpBar.health = this.hp;
                animateDeath();
                Greenfoot.setWorld(new GameOver(this));
            }
            getWorld().removeObject(bullet);
        }
    }
    
    void hitByBossArrow(){
        //SimpleTimer hitTimer = new SimpleTimer();
        BossArrow arrow = (BossArrow) getOneIntersectingObject(BossArrow.class);
        
        if(arrow != null){
            //nanti di sini munculin pop up besaran dmg atk, tapi
            //nilainya dari basicATK yg belum dibagi 2
            this.hp -= arrow.damage;
            this.hpBar.loseHealth(arrow.damage);
            if(this.hp <= 0) {
                this.hp = 100;
                animateDeath();
                Greenfoot.setWorld(new GameOver(this));
            }
            getWorld().removeObject(arrow);
        }
    }
    
    void updateGulden(){
        if(this.guldenCounter == null){
            getWorld().addObject(imgGulden, 40, 40);
            Counter guldenCounter = new Counter("Gulden: ");
            this.guldenCounter = guldenCounter;
            getWorld().addObject(this.guldenCounter, 115, 40);
        }
        this.guldenCounter.setValue(this.gulden);
    }
    
    void updatePotion(){
        if(this.potionCounter == null){
            getWorld().addObject(btnPotion, 40, 100);
            Counter potionCounter = new Counter("Potion: ");
            this.potionCounter = potionCounter;
            getWorld().addObject(this.potionCounter, 115, 100);
        }
        this.potionCounter.setValue(this.potion);
    }
    
    void updateCooldown(){
        if(this.cdCounter == null){
            CooldownCounter cdCounter = new CooldownCounter(this, "CD: ", cooldown);
            this.cdCounter = cdCounter;
            getWorld().addObject(this.cdCounter, this.getX(), this.getY()- 115);
        }
    }
    
    void usePotion(){
        if(Greenfoot.isKeyDown("P") && this.potionTimer.millisElapsed() > 500){
            if(this.potion >= 1){
                potionSound.setVolume(70);
                potionSound.play();
                 if((this.hp += 10) <= 50){
                    this.hp += 10;
                    this.potion -= 1;
                    this.hpBar.health = this.hp;
                }
            
                else if((this.hp += 10) >= 50){
                    this.hp = 50;
                    this.potion -= 1;
                    this.hpBar.health = this.hp;
                }   
                potionTimer.mark();
            }
        }
    }
    
    void nextStage(){
        //nanti ini if nya diganti jadi timer 1 menit
        //isi if yang sebelumnya getWorld().getObjects(Enemy.class).isEmpty()
        boolean isStage = getWorld().getClass() == Stage1.class || getWorld().getClass() == Stage2.class || getWorld().getClass() == Stage3.class;
        boolean isChallenge = getWorld().getClass() == TimeChallenge.class;
        
        if(!getWorld().getObjects(Timer.class).isEmpty()){
            //buat world biasa
            if(getWorld().getObjects(Timer.class).get(0).getValue() == 0 && isStage){
                //timer akan bernilai 0 dan menunggu sampai skor stage selesai dikonversi menjadi gulden
                getWorld().getObjects(Timer.class).get(0).setValue(0);
                ScoreCounter scoreCounter = getWorld().getObjects(ScoreCounter.class).get(0);
                int scoreToGulden = scoreCounter.getValue();
                int prize = scoreToGulden;
                scoreToGulden = 0;
                
                if(scoreToGulden == 0){
                    if(this.progress == "Stage1"){
                        if(getWorld().getObjects(Enemy.class).isEmpty()){
                            this.gulden += prize;
                            prize = 0;
                            this.progress = "Stage2";
                            Greenfoot.setWorld(new PrologueStage2(this));
                        }
                    }
                    
                    else if(this.progress == "Stage2"){
                        if(getWorld().getObjects(Enemy.class).isEmpty()){
                            this.gulden += prize;
                            prize = 0;
                            this.progress = "Stage3";
                            Greenfoot.setWorld(new PrologueStage3(this));    
                        }
                    }
                    
                    else if(this.progress == "Stage3"){
                        //jika boss sudah mati baru ganti stage
                        if(getWorld().getObjects(Boss.class).isEmpty()){
                            this.gulden += prize;
                            prize = 0;
                            this.progress = "Completed";
                            Greenfoot.setWorld(new MyWorld(this));   
                        }
                    }
                    
                    else if(this.progress == "Completed"){
                        if(getWorld().getObjects(Enemy.class).isEmpty()){
                            this.gulden += prize;
                            prize = 0;
                            Greenfoot.setWorld(new MyWorld(this));
                        }
                    }    
                }
            }
            
            else if (getWorld().getObjects(Timer.class).get(0).getValue() == 0 && isChallenge) {
                Greenfoot.setWorld(new MyWorld(this));
            }
        }
    }
    
    void playVoice(){
        if(!getWorld().getObjects(Enemy.class).isEmpty()){
             if(talkTimer.millisElapsed() > 8000){
                int a = Greenfoot.getRandomNumber(2) + 1;
                if(a == 1){
                    voiceLine1.play();
                }
                else if (a == 2){
                    voiceLine2.play();
                }
                talkTimer.mark();
            }   
        }
    }
}