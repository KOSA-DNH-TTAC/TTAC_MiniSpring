<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>filetable</title>
<style>
#container {
	width: 100%;
	height: 100%;
	position: relative;
}

/* #recipient {
	width: 550px;
	margin: 0 auto;
	border: 1px solid #dadada;
	padding: 24px;
	border-radius: 5px;
} */

.dpn {
	display: none !important;
}

.join_wrap {
	width: 550px;
	margin: 200 auto;
	border: 1px solid #dadada;
	padding: 24px;
	border-radius: 5px;
	/* margin-top: 20px; */
}

.join_title {
	width: 100%;
	height: 35px;
	font-size: 19px;
	font-weight: bold;
	padding-left: 10px;
	padding: 0 0 10px 10px;
}

.join_box {
	width: 100%;
}

.join_box input {
	width: 100%;
	height: 50px;
	border: 1px solid #dadada;
	padding: 17px;
	border-radius: 5px;
	margin-top: 20px;
}

.join_box span {
	display: inline-block;
	color: red;
	font-size: 12px;
	padding-left: 1px;
	padding-top: 8px;
}

.email_auth {
	width: 100%;
	margin-top: 10px;
}

.email_auth input {
	display: inline-block;
	width: calc(100%);
	vertical-align: top;
	margin: 10px 0px 0px 0px;
}

.email_auth_btn {
	display: inline-block;
	width: 115px;
	height: 50px;
	padding: 17px 0 15px;
	border-radius: 6px;
	border: solid 1px rgba(0, 0, 0, .15);
	background-color: #03c75a;
	color: white;
	font-size: 17px;
	font-weight: bold;
	line-height: 1;
}

.join_btn {
	display: block;
	width: 100%;
	padding: 17px 0 15px;
	border-radius: 6px;
	border: solid 1px rgba(0, 0, 0, .15);
	background-color: #03c75a;
	color: white;
	font-size: 19px;
	font-weight: bold;
	margin-top: 20px;
}
</style>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
	$(document).ready(function() {

		$(".email_auth_btn").click(function() {
			var a =[];
			email = $('.email').val();
			$("input[name=email]").each(function(index, item){
	              //a.push($(item).val());
	              let txt = "";
	              txt =( $(item).val());
	              console.log("?????? ?????? ?????? : "+txt)
	              a.push(txt)
	              //console.log($(item).val());
	        });
			 console.log("a?????? : "+ a);
			 console.log("a?????? 0 : "+ a[0]);
			 console.log("a?????? 1 : "+ a[1])
			if (email == '') {
				swal("Check!", " ???????????? ????????? ?????????.", "warning");
				//alert("???????????? ??????????????????.");
				return false;
			}

			$.ajax({
				type : "POST",
				url : "emailAuth/",
				data : {
					"email" : a
				}, 
				success : function(data) {
					swal("Check!", "????????? ?????????????????????.", "success");
					//alert("??????????????? ?????????????????????.");
					email_auth_cd = data;
					 /* location.href="/qwe/index.htm"; */
				},
				error : function(data) {
					swal("Check!", "?????? ????????? ??????????????????.", "error");
					//alert("?????? ????????? ??????????????????.");
				}
			});
		});

		
		$('#recipient').click(function() {
				$('#id_ck').append("<input type='text' placeholder='?????????' name='email' id='email'' class='email'>");		//?????? ?????? ??? ??????
				return false;
		});
				
		//????????? END
	});
</script>
</head>
<body>
	<div id="container" class="container">
		<div class="content">
			<div class="join_wrap">
				<form id="join_frm">
					<div class="join_title">??????????????? ?????? ?????????</div><div id="b"></div>
					<div class="join_box">
						<!-- <input type="text" placeholder="?????????" name="userid" id="userid" style="margin: 0;">
					 	<span id="id_ck" ></span> 
							<input type="text" placeholder="??????" name="name" id="name">
							 <span id="nickname_ck" class="dpn">?????? ???????????? ??????????????????.</span>
							 <input type="password" placeholder="????????????" name="pwd" id="password"> 
							 <input type="password" placeholder="????????????  ??????" id="password_ck"> -->
						<div class="email_auth">
							<input type="text" placeholder="?????????" name="email" id="email" class="email">
							<!-- <input type="text" placeholder="?????????" name="email" id="email" class="email"> -->
							<!-- <button type="button" id="email_auth_btn" class="email_auth_btn">???????????? ??????</button> -->
							<div id="id_ck" ></div> 
							<button id="recipient" style="width:30px; height:30px; margin-left:250px; margin-top:20px;">+</button><br>
							<button type="button" id="email_auth_btn" class="email_auth_btn" style="margin-left:210px; margin-top:20px;">???????????? ??????</button>
						</div>
						<!-- <input type="text" placeholder="???????????? ??????" id="email_auth_key"> -->
					</div>
					<!-- <button type="button" id="join" class="join_btn">????????????</button> -->
				</form>
			</div>
		</div>
	</div>
</body>
</html>