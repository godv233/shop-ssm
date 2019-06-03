var E3MALL = {
	checkLogin : function(){
		var _ticket = $.cookie("JSESSIONID");
//		if(!_ticket){
//			return ;
//		}
		$.ajax({
			url : "http://localhost/user/session.action",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到GODV购物网！<a href=\"http://localhost/user/logout.action\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	E3MALL.checkLogin();
});