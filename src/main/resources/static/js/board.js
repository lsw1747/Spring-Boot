let ajax_content_type = "application/json; charset=utf-8";

let index = {
	init: function() {
		$('#btn-save').on("click", () => {
			this.save();
		});
		$('#btn-update').on("click", () => {
			this.update();
		});
		$('#btn-delete').on("click", () => {
			this.delete();
		});
		$('#btn-reply').on("click", () => {
			this.reply();
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};


		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: ajax_content_type,
			dataType: "json"
		}).done(response => {
			alert('글쓰기가 완료되었습니다.');
			console.log(response);
			location.href = "/";
		}).fail(err => {
			alert(JSON.stringify(err));
		});
	},
	update: function() {
		
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type: "PUT",
			url: "/api/board/update/"+id,
			data: JSON.stringify(data),
			contentType: ajax_content_type,
			dataType: "json"
		}).done(response => {
			alert('글 수정이 완료되었습니다..');
			console.log(response);
			location.href = "/board/"+id;
		}).fail(err => {
			alert(JSON.stringify(err));
		});
	},
	delete: function() {
		let result = confirm("한번 삭제하면 되돌릴 수 없습니다. 정말로 삭제할까요?");
		let id = $("#id").text();
		if (result) {
			$.ajax({
				type: "DELETE",
				url: "/api/board/delete/"+id,
				dataType:"json"
			}).done(res => {
				alert('삭제가 완료되었습니다.');
				location.href = "/";
			}).fail(err => {
				alert(JSON.stringify(err));
			});

		}
	},
	reply: ()=>{
		let id = $("#id").text(); //게시글 번호
		let data = {
			content :  $("#reply--content").val(),
			userId : $("#userid").val()
		}
		$.ajax({
			type: "POST",
			url: "/api/reply/"+id,
			data: JSON.stringify(data),
			contentType: ajax_content_type,
			dataType: "json"
		}).done(res=>{
			console.log(res);
			location.href="/board/"+id;
		}).fail(err=>{
			alert("에러 내용 : "+JSON.stringify(err))
		})
	}
}

index.init();