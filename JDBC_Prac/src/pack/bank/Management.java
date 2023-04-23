package pack.bank;

import java.util.Scanner;

public class Management {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Account account = Account.getInstance();

		int num = 0;
		while (true) {
			System.out.print("원하시는 서비스 번호를 입력해주세요.(숫자만 입력)\n1. 로그인 2. 회원가입\n번호 선택 : ");
			try {
				num = scanner.nextInt();
				if (num == 1 || num == 2)
					break;
				else
					System.out.println("1 또는 2를 입력해주세요.\n");
			} catch (Exception e) {
				System.out.println("1 또는 2를 입력해주세요.\n");
				scanner = new Scanner(System.in);
			}
		}
		if (num == 2) {
			System.out.print("\n[회원가입]\n1. 아이디 : ");
			String id = scanner.next();
			System.out.print("2. 비밀번호 : ");
			String pw = scanner.next();
			System.out.print("3. 이름 : ");
			String name = scanner.next();
			System.out.print("4. 주소 : ");
			String address = scanner.next();
			account = new memberShip();
			account.login(id, pw, name, address);
		} else if(num == 1) {
			System.out.print("\n[로그인]\n1. 아이디 : ");
			String id = scanner.next();
			System.out.print("2. 비밀번호 : ");
			String pw = scanner.next();
			account = new Login();
			account.login(id, pw);
		}
		scanner.close();
		System.out.println("\n\nEnd");
	}

}
