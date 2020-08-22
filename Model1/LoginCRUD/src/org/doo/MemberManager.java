package org.doo;
import java.util.Vector;

import javax.servlet.ServletContext;


public class MemberManager {
	
	// 모든 필드에 기본값으로 공백지정
	public static Member getBlankMember(){
		Member member = new Member();
		member.setU_id("");
		member.setU_name("");
		member.setU_nick("");
		member.setU_pw("");
		
		return member;
	}
	
	// application영역에 저장되어있는 유저리스트 조회
	public static Vector<Member> getMemberList(ServletContext application){
		Vector<Member> memberList = (Vector<Member>)application.getAttribute("memberList");
		
		// 만약 리스트에 값이 없다면
		if(memberList == null){
			// 새로운 객체 생성
			memberList = new Vector<Member>();
			// application영역에 저장
			application.setAttribute("memberList", memberList);
		}
		return memberList;
	}
	
	// id와 일치하는 객체 찾기(ServletContext = application 영역)
	public static Member findMember(ServletContext application, String u_id){
		//
		Vector<Member> memberList = getMemberList(application);
		Member member = null;
		int idx = memberList.lastIndexOf(new Member(u_id));
		// id와 일치하는 객체가 존재 한다면
		if(idx != -1){
			// 가져온다.
			member = memberList.get(idx);
		}
		return member;
	}
	
	// 유저 추가
	public static boolean addMember(ServletContext application, Member member){
		Vector<Member> memberList = getMemberList(application);
		// 기본값 false
		boolean flag = false;
		// 가입시 내가 적은 id와 일치하는 id가 없다면 정상적으로 추가
		if(findMember(application, member.getU_id()) == null){
			memberList.add(member);
			flag = true;
		}
		return flag;
	} 
	
	// 유저 삭제
	public static boolean deleteMember(ServletContext application, String u_id){
		Vector<Member> memberList = getMemberList(application);
		// id와 일치하는 유저삭제
		return memberList.remove(findMember(application, u_id));
	}
	
	// 유저 수정
	public static boolean updateMember(ServletContext application, Member member){
		Vector<Member> memberList = getMemberList(application);
		int idx = memberList.indexOf(member);
		boolean flag = false;
		// 일치하는게 존재한다면 
		if(idx != -1){
			// 수정
			memberList.set(idx, member);
			flag = true;
		}
		return flag;
	}
}
