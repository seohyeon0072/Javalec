package org.doo;
import java.util.Vector;

import javax.servlet.ServletContext;


public class MemberManager {
	
	// ��� �ʵ忡 �⺻������ ��������
	public static Member getBlankMember(){
		Member member = new Member();
		member.setU_id("");
		member.setU_name("");
		member.setU_nick("");
		member.setU_pw("");
		
		return member;
	}
	
	// application������ ����Ǿ��ִ� ��������Ʈ ��ȸ
	public static Vector<Member> getMemberList(ServletContext application){
		Vector<Member> memberList = (Vector<Member>)application.getAttribute("memberList");
		
		// ���� ����Ʈ�� ���� ���ٸ�
		if(memberList == null){
			// ���ο� ��ü ����
			memberList = new Vector<Member>();
			// application������ ����
			application.setAttribute("memberList", memberList);
		}
		return memberList;
	}
	
	// id�� ��ġ�ϴ� ��ü ã��(ServletContext = application ����)
	public static Member findMember(ServletContext application, String u_id){
		//
		Vector<Member> memberList = getMemberList(application);
		Member member = null;
		int idx = memberList.lastIndexOf(new Member(u_id));
		// id�� ��ġ�ϴ� ��ü�� ���� �Ѵٸ�
		if(idx != -1){
			// �����´�.
			member = memberList.get(idx);
		}
		return member;
	}
	
	// ���� �߰�
	public static boolean addMember(ServletContext application, Member member){
		Vector<Member> memberList = getMemberList(application);
		// �⺻�� false
		boolean flag = false;
		// ���Խ� ���� ���� id�� ��ġ�ϴ� id�� ���ٸ� ���������� �߰�
		if(findMember(application, member.getU_id()) == null){
			memberList.add(member);
			flag = true;
		}
		return flag;
	} 
	
	// ���� ����
	public static boolean deleteMember(ServletContext application, String u_id){
		Vector<Member> memberList = getMemberList(application);
		// id�� ��ġ�ϴ� ��������
		return memberList.remove(findMember(application, u_id));
	}
	
	// ���� ����
	public static boolean updateMember(ServletContext application, Member member){
		Vector<Member> memberList = getMemberList(application);
		int idx = memberList.indexOf(member);
		boolean flag = false;
		// ��ġ�ϴ°� �����Ѵٸ� 
		if(idx != -1){
			// ����
			memberList.set(idx, member);
			flag = true;
		}
		return flag;
	}
}
