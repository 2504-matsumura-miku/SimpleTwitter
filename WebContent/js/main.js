$(function() {
	$('#deleteBtn').on('click', function() {
		if (!confirm('このつぶやきを削除しますか？')) {
			// キャンセルの場合
			return false;
		} else {
			// OKの場合はそのまま
		}
	});
});