package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberUpdateController implements Controller 
{
  @Override
  public String execute(Map<String, Object> model) throws Exception 
  {
	MemberDao memberDao = (MemberDao)model.get("memberDao");
    System.out.println("UpdateCont memberDao : " + memberDao);
    
    if (model.get("member") == null) 
    {
    	System.out.println("UpdateCont : member = null");
    	
    	Integer no = (Integer)model.get("no");
    	System.out.println("UpdateCont : no "+no);
    	Member member = memberDao.selectOne(no);
    	model.put("member", member);
    	return "/member/MemberUpdateForm.jsp";

    } else 
    {
    	System.out.println("UpdateCont : member != null");
    	Member member = (Member)model.get("member");
    	memberDao.update(member);
    	return "redirect:list.do";
    }
  }
}
