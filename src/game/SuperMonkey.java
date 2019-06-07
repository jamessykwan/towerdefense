package game;

public class SuperMonkey extends Tower {

    public SuperMonkey(int x, int y, String type) {
        super(x, y, type);
        // TODO Auto-generated constructor stub
        super.setCost(2000);
    }

    public SuperMonkey(int i, int j) {
        // TODO Auto-generated constructor stub
        super(i, j, "../resources/Super_Monkey.png");
        setAttackRadius(800);
        super.setCost(2000);
    }

}
