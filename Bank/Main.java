package pack.Bank;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String account = "";
		while(true) {
			System.out.print("계좌번호 입력(6자리 숫자) : ");
			account = scanner.next();
			if(account.length()==6) break;
			System.out.println("계좌번호 입력이 잘못되었습니다. 6자리 계좌를 입력해주세요.\n");
		}
		String num = "";
		int money = 0;
		BasicInfo obj = BasicInfo.getInstance(account);
		
		while (true) {
			System.out.print("\n온라인 뱅킹 메뉴 선택 \n1. 잔액확인 2. 입금 3. 출금 9. 종료 : ");
			num = scanner.next();
			if (num.equals("1")) {
				int res = obj.mtdBalance();
				System.out.printf("%d 계좌잔액 : %d원", account, res);
			} else if (num.equals("2")) {
				System.out.print("입금액 : ");
				money = scanner.nextInt();
				obj = new Deposit();
				obj.mtdMoney(money);
				System.out.println("입금확인!");
			} else if (num.equals("2")) {
				System.out.print("출금액 : ");
				money = scanner.nextInt();
				obj = new Withdraw();
				obj.mtdMoney(money);
				System.out.println("출금완료!");
			} else if (num.equals("9")) {
				System.out.println("\n온라인 뱅킹을 종료합니다.");
				break;
			} else {
				System.out.println("해당하는 번호를 입력해주세요.");
				continue;
			}
		}
		scanner.close();
	}

}
