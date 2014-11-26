import java.util.Random;

public abstract class Adventurer {

    public Adventurer(String name) {
	setName(name);
	setHP(25);
	setStrength(20);
	setIntel(20);
	setDex(20);
    }

    private String name;
    private int hp;

    public int getHP() {
	if (hp < 0)
	    return 0;
	return hp;
    }
    public void setHP(int newHP) {
	hp = newHP;
    }

    public String getName() {
	return name;
    }
    public void setName(String newName) {
	name = newName;
    }

    private int str, intel, dex;
    private double accuracy, evasion;

    public int getStrength() {
	return str;
    }
    public void setStrength(int str) {
	this.str = str;
    }

    public int getIntel() {
	return intel;
    }
    public void setIntel(int intel) {
	this.intel = intel;
    }

    public int getDex() {
	return dex;
    }
    public void setDex(int dex) {
	this.dex = dex;
    }

    protected boolean hit(Adventurer target) {
	return rand.nextDouble() < getDex() / (double) target.getDex();
    }

    protected final int damageCalc(int base, Adventurer target) {
	// Calls the _damageCalcInternal routine, which you override,
	// then handles rounding (ensures damage always >= 1)
	double x = _damageCalcInternal(base, target);
	if (Math.round(x) <= 0)
	    return 1;
	return (int) Math.round(x);
    }

    protected double _damageCalcInternal(int base, Adventurer target) {
	// Default interpretation - feel free to override
	// Snuck in the golden ratio huehue
	return base * (double) getStrength() / target.getStrength() 
	    * (0.61803398875 + rand.nextDouble());
    }

    // This is Commuиism - oиe Яaиdom for all!
    // Tip: rand.nextDouble() gives you a number in [0.0, 1.0)
    protected static final Random rand = new Random();

    protected void takeDamage(int damage) {
	hp -= damage;
	System.out.println(getName() + " took " + damage + " damage!");
	if (isDead())
	    System.out.println(getName() + " was defeated!");
    }

    public abstract void attack(Adventurer target);
    public abstract void specialAttack(Adventurer target);

    public String getStats() {
	return getName() + " / " + getClass().getSimpleName() + "\n"
	    + getHP() + " HP\t" + getStrength() + " STR\t"
	    + getIntel() + " INT\t" + getDex() + " DEX";
    }

    public final String getType() {
	return getClass().getSimpleName();
    }

    public final boolean isDead() {
	return hp <= 0;
    }

    protected final void missed(Adventurer target) {
	System.out.println("The move missed " + target.getName() + "...");
    }
}
