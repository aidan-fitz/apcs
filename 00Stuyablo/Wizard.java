public class Wizard extends Adventurer {

    public Wizard(String name) {
	super(name);
	setMana(20);
    }

    private int mana;

    public int getMana() {
	return mana;
    }
    public void setMana(int mana) {
	this.mana = mana;
    }

    @Override
    public void attack(Adventurer target) {
	System.out.println(getName() + " unleashed a spell on " + target.getName());
	if (hit(target)) {
	    int base = 4;
	    if (mana > 0 && rand.nextDouble() < 0.1) {
		base += rand.nextInt(3) + 1;
		mana -= 1;
		System.out.println("Sparkles came out!");
	    }
	    target.takeDamage(damageCalc(base, target));
	}
	else {
	    missed(target);
	}
    }

    @Override
    public void specialAttack(Adventurer target) {
	if (mana > 0) {
	    System.out.println(getName() + " shot at " + target.getName() + " with mystic fire!");
	    if (hit(target)) {
		target.takeDamage(damageCalc(6, target));
		mana -= 2;
	    }
	    else {
		missed(target);
		mana -= 1;
	    }
	}
	else {
	    System.out.println(getName() + " is out of mana....");
	    attack(target);
	}
    }

    @Override
    public String getStats() {
	return getStats() + "\t" + getMana() + " MANA";
    }

    @Override
    public Wizard clone() {
	Wizard miniMe = new Wizard(getName());
	miniMe.setHP(getHP());
	miniMe.setStrength(getStrength());
	miniMe.setIntel(getIntel());
	miniMe.setDex(getDex());
	miniMe.setMana(getMana());
	return miniMe;
    }

}
