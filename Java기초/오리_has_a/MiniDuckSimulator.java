interface FlyBehavior { 
	public void fly();
}

class FlyWithRocket implements FlyBehavior {
	@Override
	public void fly() {
		System.out.println("น฿ป็~~~");
	}
}
class FlyWithWings implements FlyBehavior {
	public void fly() {
		System.out.println("I'm flying!!");
	}
}
class FlyNoWay implements FlyBehavior {
	public void fly() {
		System.out.println("I can't fly");
	}
}

interface QuackBehavior {
	public void quack();
}
class Quack implements QuackBehavior {
	public void quack() {
		System.out.println("Quack");
	}
}
class Squeak implements QuackBehavior {
	public void quack() {
		System.out.println("Squeak");
	}
}
class MuteQuack implements QuackBehavior {
	public void quack() {
		System.out.println("<< Silence >>");
	}
}

abstract class Duck {
	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;
	
	public FlyBehavior getFlyBehavior () {
		return flyBehavior;
	} 
	public QuackBehavior getQuackBehavior() {
		return quackBehavior;
	}

	public void setFlyBehavior (FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	} 
	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}
 
	public abstract void display();
 
	public void performFly() {
		flyBehavior.fly();
	}
 
	public void performQuack() {
		quackBehavior.quack();
	}
 
	public void swim() {
		System.out.println("All ducks float, even decoys!");
	}
}

class MallardDuck extends Duck {
	public MallardDuck() { 
		setQuackBehavior(new Quack());
        setFlyBehavior(new FlyWithWings());
	}

	@Override 
	public void display() {
		System.out.println("I'm a real Mallard duck");
	}
}

class RedHeadDuck extends Duck {
 
	public RedHeadDuck() {
		setQuackBehavior(new Quack());
       	setFlyBehavior(new FlyWithWings());
	}
 
	public void display() {
		System.out.println("I'm a real Red Headed duck");
	}
}

class RubberDuck extends Duck {
 
	public RubberDuck() {
		setFlyBehavior(new FlyNoWay());
		setQuackBehavior(new Squeak());
	}
 
	public void display() {
		System.out.println("I'm a rubber duckie");
	}
}
class DecoyDuck extends Duck {
	public DecoyDuck() {
		setFlyBehavior(new FlyNoWay());
		setQuackBehavior(new MuteQuack());
	}
	public void display() {
		System.out.println("I'm a duck Decoy");
	}
}

class MiniDuckSimulator {
	public static void main(String[] args) {
 		MallardDuck m = new MallardDuck();
		m.performFly();
		m.setFlyBehavior(new FlyWithRocket());
		m.performFly();
	}
}
