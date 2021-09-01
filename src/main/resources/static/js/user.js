var _username;
var _password;
var _email;
let index = {
	init: function() {
		$('#btn-save').on("click", () => {
			_username = $("#username").val();
			_password = $("#password").val();
			_email = $("#email").val();
			if (_username != "" && _password != "" && _email != "") {
				this.save();
			} else {
				alert('입력되지 않은 정보가 있습니다.');
			}
		});
		$('#btn-update').on("click", () => {
			_password = $("#password").val();
			_email = $("#email").val();
			if (_username != "" && _password != "" && _email != "") {
				this.update();
			} else {
				alert('입력되지 않은 정보가 있습니다.');
			}
		});
	},

	save: function() {
		let data = {
			username: _username,
			password: _password,
			email: _email
		};

		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			async: false,
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지
			dataType: "json" // 응답이 json 형태로 오면 -> javascript
		}).done(response => {
			// 성공시
			console.log(response);
			alert('회원가입이 완료되었습니다1.');
			location.href = "/";
		}).fail(err => {
			// 실패시
			alert(JSON.stringify(err));
		}); //ajax통신을 이용해 3개의 데이터를 json으로 parsing해 insert
	},
	update: function() {

		let data = {
			id: $('#id').val(),
			password: _password,
			email: _email
		};

		$.ajax({
			type: "PUT",
			async: false,
			url: "/auth/update",
			data: JSON.stringify(data),
			contentType: "application/json; charset=UTF-8",
			dataType: "json"
		}).done(res => {
			alert("회원 정보 수정이 완료되었습니다. ");
		}).fail(err => {
			alert(JSON.stringify(err));
		});
	}
}

index.init();