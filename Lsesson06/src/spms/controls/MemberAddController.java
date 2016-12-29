package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

// 의존 객체 주입을 위해 인스턴스 변수와 셋터 메서드 추가
//- 또한 의존 객체를 꺼내는 기존 코드 변경
public class MemberAddController implements Controller, DataBinding {
  MemberDao memberDao;
  
  public MemberAddController setMemberDao(MemberDao memberDao) {
    this.memberDao = memberDao;
    return this;
  }
  
  @Override
  public String execute(Map<String, Object> model) throws Exception {
    if (model.get("member") == null) { // 입력폼을 요청할 때
      return "/member/MemberForm.jsp";
      
    } else { // 회원 등록을 요청할 때
      Member member = (Member)model.get("member");
      memberDao.insert(member);
      
      return "redirect:list.do";
    }
  }

	@Override
	public Object[] getDataBinders() {//String[]{"",""}이럴거면 일케하지 근데 올라가다보면 오브젝트통일 짝수 번째 는 string /홀수 번째 는 class로 형변환
		return new Object[]{"member",spms.vo.Member.class};
					//ㄴ 객체의 member클래스라는 정보를 주고,....? 클래스이름하고 변수명을 넘기면 된다
					//클래스 자체를 넘겨주면 된다 클래스를 저장할수있는 클래스 타입이 있다. 
	}	
		/*
		return new Member[1];
	}				//ㄴ 결국 Object로 받을수 잇지만 사용할때 코드를 통일 할 수 없다. 형변환 해줘야하니까
	*/ 
}
