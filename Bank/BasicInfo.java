package pack.Bank;

public class BasicInfo implements I{
	private static BasicInfo obj = new BasicInfo();
	static String fieldAccount;
	static int balance;
	
	protected BasicInfo() {};
	
	public int mtdBalance() {
		return balance;
	}

	public static BasicInfo getInstance(String account) {
		fieldAccount = account;
		return obj;
	}

	@Override
	public int mtdMoney(int money) {
		return 0;
	}
}
