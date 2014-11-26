import java.util.Scanner;
import java.util.Random;

public class Game {

    static final Scanner in = new Scanner(System.in);

    static final Random rand = new Random();

    static final char key() {
	return in.nextLine().toUpperCase().charAt(0);
    }

    public static void main(String[] args) {
	printCopyright();

	Adventurer[] players = makePlayerArray();
	boolean go = true;
	while (go)
	    go = battle(players);
    }

    static boolean battle(Adventurer[] players) {
	Adventurer opponent = makeOpponent();

	boolean continueBattle = true;
	while(continueBattle) {
	    continueBattle = round(players, opponent);
	}

	// End of match
	printStats(players, opponent);

	System.out.print("Start another battle? (Y/N)");
	if (key() == 'Y') {
	    revive(players);
	    return true;
	}
	return false;
    }

    // Returns true if the battle should continue, false if it should end
    static boolean round(Adventurer[] players, Adventurer opponent) {
	printStats(players, opponent);

	for (int i = 0; i < players.length; i++) {
	    // skip dead people
	    if (players[i].isDead())
		continue;
	    
	    // User's turn
	    userTurn: for (int j = 0; j < 3; j++) {
		System.out.println("P" + (i+1) + ": What should " + players[i].getName() + " do?");
		System.out.println("  A: attack");
		System.out.println("  S: special attack");
		System.out.println("  D: do nothing");
		System.out.println("  F: forfeit");
		switch (key()) {
		case 'A':
		    players[i].attack(opponent);
		    break userTurn;
		case 'S':
		    players[i].specialAttack(opponent);
		    break userTurn;
		case 'D':
		    break userTurn;
		case 'F':
		    if (forfeit()) {
			System.out.println("You forfeited the match!");
			return false;
		    }
		default:
		    errInvalid();
		    continue;
		}
	    }

	    // no more moves after KO
	    if (opponent.isDead()) {
		System.out.println("You won the match!");
		return false;
	    }
	}

	// Opponent's turn
	double x = rand.nextDouble();
	if (x < 0.75)
	    opponent.attack(oneOf(players));
	else
	    opponent.specialAttack(oneOf(players));

	if (allDead(players)) {
	    System.out.println("You lost the match...");
	    return false;
	}

	// after returning, start another round
	return true;
    }

    // randomly choose one that's alive
    static Adventurer oneOf(Adventurer[] a) {
	int i = rand.nextInt(a.length);
	if (!a[i].isDead())
	    return a[i];
	else
	    return oneOf(a);
    }

    static boolean allDead(Adventurer[] ps) {
	// if at least one is alive, return false
	for (Adventurer p: ps) {
	    if (!p.isDead())
		return false;
	}
	return true;
    }

    static Adventurer[] makePlayerArray() {
	System.out.print("How many players do you want [2-4]? ");
	Adventurer[] players;
	int n = in.nextInt();
	in.nextLine(); // discard
	if (n >= 2 && n <= 4) {
	    players = new Adventurer[n];
	}
	else {
	    System.out.println("Invalid. I'll give you 3.");
	    players = new Adventurer[3];
	}
	for (int i = 0; i < players.length; i++) {
	    players[i] = makePlayer(i);
	}
	return players;
    }

    static Adventurer makePlayer(int j) {
	System.out.print("Please enter Player " + (j+1) + "\'s name: ");
	String name = in.nextLine();
	System.out.println();

	// you get three chances
	for(int i = 3; i > 0; i--) {
	    System.out.println("Choose a class (press corresponding key):");
	    System.out.println("A: Warrior");
	    System.out.println("B: Wizard");
	    System.out.println("C: Rogue");
	    System.out.println("D: Martial Artist");
	    System.out.println("W: (random)");
	    switch (key()) {
	    case 'A':
		return new Warrior(name);
	    case 'B':
		return new Wizard(name);
	    case 'C':
		return new Rogue(name);
	    case 'D':
		return new MartialArtist(name);
	    case 'W':
		return wildCard(name);
	    default:
		errInvalid();
	    }
	}
	System.out.println("Fuck it, I'm giving you a random class.");
	return wildCard(name);
    }

    static Adventurer makeOpponent() {
	System.out.print("Please enter your opponent's name: ");
	String name = in.nextLine();
	System.out.println();
	return wildCard(name);
    }

    static Adventurer wildCard(String name) {
	double x = rand.nextDouble();
	if (x < 0.25)
	    return new Warrior(name);
	else if (x < 0.5)
	    return new Wizard(name);
	else if (x < 0.75)
	    return new Rogue(name);
	else
	    return new MartialArtist(name);
    }

    static boolean forfeit() {
	System.out.print("Are you sure you want to give up? (Y/N)");
	if (key() == 'Y')
	    return true;
	return false;
    }

    static void printStats(Adventurer[] players, Adventurer cpu) {
	System.out.println();
	for (int i = 0; i < players.length; i++) {
	    System.out.println("P" + (i+1) + ": " + players[i].getStats());
	}
	System.out.println("     - vs -");
	System.out.println("CPU: " + cpu.getStats());
	System.out.println();
    }

    static void errInvalid() {
	System.out.println("Invalid key input. Please try again.");
	System.out.println();
    }

    static void revive(Adventurer[] party) {
	for (int i = 0; i < party.length; i++) {
	    try {
		// bad@$$ way of resetting
		party[i] = party[i].getClass().getConstructor(String.class)
		    .newInstance(party[i].getName());
	    }
	    catch (ReflectiveOperationException e) {
		// if it fails, do a random class
		party[i] = wildCard(party[i].getName());
	    }
	}
    }

    static void printCopyright() {
	System.out.println("Stuyablo(TM)");
	System.out.println("Copyright (C) 2014 Aidan Fitzgerald");
	System.out.println("For license details, see the file 'LICENSE'.");
	System.out.println();
    }

}
