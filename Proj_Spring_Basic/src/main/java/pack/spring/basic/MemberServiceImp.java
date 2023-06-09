package pack.spring.basic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService {
	@Autowired
	MemberDao memberDao;

	@Override
	public String create(Map<String, Object> map) {
		int affectRowCnt = this.memberDao.insert(map);
		if(affectRowCnt==1) {
			return map.get("member_id").toString();
		}
		return null;
	}

	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return this.memberDao.selectDetail(map);
	}

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return this.memberDao.selectList(map);
	}

}
