package com.common.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.MemberDao;
import com.common.dto.Member;


@Service
public class MemberService {

	// Mybatis 작업
	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	
	// 회원 가입
		public String insert(Member member) {
			String check=null;
			try {
				//동기화/////////////////////////////////////////////////////
				MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
					///////////////////////////////////////////////////////////
				memberdao.insert(member);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return check;
		}

	
	
			//member idChek
			public String memberDetail(String userid) {
				System.out.println("서비스로 오나?");
				String member = "Y";
				String check="Y";
				System.out.println("실행전 체크"+check);
				try {
					System.out.println(userid);
						//동기화/////////////////////////////////////////////////////
					MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
						///////////////////////////////////////////////////////////
					member = memberdao.idCheck(userid);
					System.out.println("member : "+ member);
					
//					if(member) {
//						check="Y";
//						System.out.println("null일 때");
//					}
					if(member != null) {
						check="N"; //중복
						System.out.println("중복");
					} else {
						check="Y";
						System.out.println("null일 때");
					}
//					/if(member.equals(userid))
					
				
					System.out.println("결과"+check);
				} catch (ClassNotFoundException e) {
								e.printStackTrace();
				} catch (SQLException e) {
								e.printStackTrace();
				}
				return check;		
			}
}
