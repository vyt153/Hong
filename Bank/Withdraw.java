package pack.Bank;

public class Withdraw extends BasicInfo {
	public Withdraw() {
		super();
	}

	@Override
	public int mtdMoney(int money) {
		return balance -= money;
	}
}
