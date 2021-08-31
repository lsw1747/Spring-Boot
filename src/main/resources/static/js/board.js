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
			contentType: "application/json; charset=utf-8",
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

	},
	delete: function() {
		var result = confirm("한번 삭제하면 되돌릴 수 없습니다. 정말로 삭제할까요?");
		if (result) {
			$.ajax({
				type: "POST",
				url: "/api/board/delete",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(res => {
				alert('삭제가 완료되었습니다.');
				location.href = "/";
			}).fail(err => {
				alert(JSON.stringify(err));
			});

		}
	}
}

index.init();