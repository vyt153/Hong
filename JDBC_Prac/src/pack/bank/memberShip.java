package pack.bank;

import java.sql.SQLException;

public class memberShip extends Account {
	public memberShip() {
		super();
	}

	@Override
	public void login(String id, String pw, String name, String address) {
		try {
			mtd();
			String sql = "insert into list values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, address);

			int rtnCnt = pstmt.executeUpdate();
			if (rtnCnt == 1) {
				System.out.println("회원가입이 완료되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
