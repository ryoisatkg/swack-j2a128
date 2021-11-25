/**
 *チャットログの最新（最下）までスクロール
 */
$(window).on('load', function() {
  $("#logArea").scrollTop($("#logArea")[0].scrollHeight)
});