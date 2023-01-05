package com.common.controller;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.dto.Mail;
import com.common.dto.Member;
import com.common.service.MailService;
import com.common.service.MemberService;

@RestController
public class AjaxController {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MemberService memberservice;
	@Autowired
	private MailService service;

	@Autowired
	public void setMemberService(MemberService memberservice) {
		this.memberservice = memberservice;
	}

	// idCheck
	@PostMapping("/joinus/idCheck/{userid}")
	public ResponseEntity<String> idCheck(@PathVariable("userid") String userid) {
		// (넘어온 DATA)
		System.out.println("userid : " + userid);
		String data = null;
		try {
			System.out.println("idcheck 실행");
			data = memberservice.memberDetail(userid);
			System.out.println("컨트롤러 데이터(Y, N) : " + data);
			return new ResponseEntity<String>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(data, HttpStatus.BAD_REQUEST);
		}
	}

	// 회원가입
	@PostMapping("/joinus/join")
	public ResponseEntity<String> insert(Member member) {
		String check = "N";
		System.out.println("member1 : " + member);
		try {
			System.out.println("insert 실행");
			memberservice.insert(member);

			check = "Y"; // 중복
			System.out.println("가입 성공");

			System.out.println("결과" + check);
			return new ResponseEntity<String>(check, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(check, HttpStatus.BAD_REQUEST);
		}
	}

	// 여러명에게 메일 보내기
	@PostMapping("/joinus/multimail.htm")
	public ResponseEntity<String> multiMail() {
		String data = null;
		try {
			data = "<input type='text' placeholder='이메일' name='email' id='email' class='email'>";
			System.out.println("multiMail 실행");
			return new ResponseEntity<String>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(data, HttpStatus.BAD_REQUEST);
		}
	}

	
	// 개인 or 단체 메일 보내기
	@RequestMapping(value = "/joinus/emailAuth", method = RequestMethod.POST)
	public String emailAuth(@RequestParam(value = "email[]") String[] email) {
		System.out.println("컨트롤러로 넘어온 이메일 파라미터 : " + email.toString());

		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111; // 6자리 난수 생성

		// 메일 본문 템플릿
		String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + "인증 번호는 <" + checkNum + "> 입니다." + "<br>"
				+ "홈페이지로 돌아가서 해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		// MimeMessage 대신 SimpleMailMessage를 사용할 수도 있습니다.
		// 둘의 차이점은 MimeMessage의 경우 멀티파트 데이터를 처리 할 수 있고 SimpleMailMessage는 단순한 텍스트 데이터만
		// 전송이 가능합니다.

		MimeMessage message = mailSender.createMimeMessage(); // MimeMessage 객체를 이용해서 메시지를 구성한 뒤 메일 발송

		try {
			// MimeMessag를 이용해서 파일첨부가 가능하지만 복잡하고 힘들기에 MimeMessageHelper 도움받아 파일 첨부
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");// true는 멀티파트 메세지를 사용하겠다는 의미

			helper.setFrom("hjdo0211@naver.com"); // 보내는 사람 이메일
			// bean에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
			// 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
			// mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");

			helper.setTo(email); // 수신자 이메일
			helper.setSubject("TTAC :: 회원가입 인증메일"); // 제목
			helper.setText(content, true); // 내용. true는 html을 사용하겠다는 의미

			// 아래는 helper안쓰고 그냥 했을 때 ...
			// message.setSubject("스프링으로 메일보내기");
			// message.setText("메일본문 : " + checkNum);
			// message.setRecipients(MimeMessage.RecipientType.TO,
			// InternetAddress.parse(email));
			// message.setRecipients(MimeMessage.RecipientType.TO, email);
			// message.setFrom("hjdo0211@naver.com");

			mailSender.send(message);
			System.out.println("메일 보내기 성공");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return Integer.toString(checkNum);
	}

	// 첨부파일 보내는 메소드
	@RequestMapping(value = "/joinus/fileMail", method = RequestMethod.POST)
	public String writeMail(Mail mail) throws Exception {
		System.out.println("파일첨부 컨트롤러1 : " + mail);
		/* service.sendMultieMessage(mail); */
		
		service.sendMultieMessage(mail);
		
		// 메일
		return null;
	}

}
