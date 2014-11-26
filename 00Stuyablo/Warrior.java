public class Warrior extends Adventurer {

    public Warrior(String name) {
	super(name);
    }

    private int rage;

    public int getRage() {
	return rage;
    }
    public void setRage(int rage) {
	if (this.rage == rage) {
	    return;
	}
	else if (this.rage == 0 && rage > 0) {
	    System.out.println(getName() + " became enraged!");
	}
	else if (this.rage > 0 && rage <= 0) {
	    rage = 0;
	    System.out.println(getName() + " is no longer enraged.");
	}
	else if (rage > this.rage) {
	    System.out.println(getName() + "\'s rage increased!");
	}
	else if (rage < this.rage) {
	    System.out.println(getName() + "\'s rage decreased.");
	}
	this.rage = rage;
    }

    // TODO write regular attack
    public void attack(Adventurer target) {
	System.out.println(getName() + " hacked at " + target.getName());
	if (hit(target)) {
	    target.takeDamage(damageCalc(4, target));
	    setRage(getRage() - 1);
	}
    }

    @Override
    public void specialAttack(Adventurer target) {
	System.out.println(getName() + " slashed at " + target.getName() + " with his sword!");
	if (hit(target)) {
	    if (getRage() > 0)
		System.out.println("Rage: " + getRage());
	    target.takeDamage(damageCalc(6, target));
	    rage--;
	    if (rage < 0)
		rage = 0;
	    else if (rage == 0)
		System.out.println(getName() + " is no longer enraged!");
	}
	else {
	    System.out.println("The move missed...");
	}
    }

    @Override
    public String getStats() {
	return super.getStats() + "\t" + getRage() + " RAGE";
    }

    @Override
    protected double _damageCalcInternal(int base, Adventurer target) {
	// Rage bonus - diminishing returns
	return super._damageCalcInternal(base, target) 
	    + Math.sqrt(2 * getRage());
    }

    @Override
    protected void takeDamage(int damage) {
	super.takeDamage(damage);
	if (rage == 0)
	    System.out.println(getName() + " became enraged!");
	else
	    System.out.println(getName() + " is enraged!");
	// Rage increases with diminishing returns
	rage += (int) Math.sqrt(damage);
    }

    @Override
    public Warrior clone() {
	Warrior miniMe = new Warrior(getName());
	miniMe.setHP(getHP());
	miniMe.setStrength(getStrength());
	miniMe.setIntel(getIntel());
	miniMe.setDex(getDex());
	return miniMe;
    }

}
