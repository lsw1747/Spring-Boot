let index ={
    init : function(){
        $('#btn-save').on("click", ()=>{
            this.save();
        });
    },

    save : function(){
        let data = {
            username : $("#username").val(),
            password : $("#password").val(),
            email : $("#email").val(),
        };

        
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProc",
            data : JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지
            dataType: "json" // 응답이 json 형태로 오면 -> javascript
        }).done(function(response){
            // 성공시
            alert('회원가입이 완료되었습니다.');
            console.log(response);
            location.href = "/";
        }).fail(function(err){
            // 실패시
            alert(JSON.stringify(err));
        }); //ajax통신을 이용해 3개의 데이터를 json으로 parsing해 insert
    },
}

index.init();