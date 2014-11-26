public class Rogue extends Adventurer {

    public Rogue(String name) {
	super(name);
	setStamina(100);
    }

    private int stamina;

    public int getStamina() {
	return stamina;
    }
    public void setStamina(int stamina) {
	this.stamina = stamina;
    }

    @Override
    public void specialAttack(Adventurer target) {
	System.out.println(getName() + " slashed up " + target.getName() + "!");
	if (hit(target)) {
	    target.takeDamage(damageCalc(7, target));
	    stamina -= 5;
	}
	else {
	    System.out.println("The move missed...");
	    stamina -= 8;
	}
    }

    @Override
    public void attack(Adventurer target) {
	System.out.println(getName() + " snuck behind " + target.getName() + " and attacked!");
	if (hit(target)) {
	    target.takeDamage(damageCalc(4, target));
	    stamina -= 3;
	}
	else {
	    System.out.println("The move missed...");
	    stamina -= 5;
	}
    }

    @Override
    public String getStats() {
	return super.getStats() + "\t" + getStamina() + " STAMINA";
    }

    @Override
    public Rogue clone() {
	Rogue miniMe = new Rogue(getName());
	miniMe.setHP(getHP());
	miniMe.setStrength(getStrength());
	miniMe.setIntel(getIntel());
	miniMe.setDex(getDex());
	miniMe.setStamina(getStamina());
	return miniMe;
    }


}
