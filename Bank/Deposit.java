package pack.Bank;

public class Deposit extends BasicInfo {
	public Deposit() {
		super();
	}

	@Override
	public int mtdMoney(int money) {
		return balance += money;
	}
}
