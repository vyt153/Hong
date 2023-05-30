package pack.bank;

import java.sql.SQLException;

public class Login extends Account {
	public Login() {
		super();
	}

	@Override
	public void login(String id, String pw) {
		try {
			mtd();
			stmt = conn.createStatement();
			String sql = "select * from list where id = '" + id + "' and pw = '" + pw + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("name");
				String dbId = rs.getString("id");
				String dbPw = rs.getString("pw");
				if ((dbId.equals(id) && dbPw.equals(pw))) {
					System.out.println(name + " 님께서 로그인 하셨습니다.");
				}
			} else {
				System.out.println("일치하는 회원정보가 없습니다.");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}
}
