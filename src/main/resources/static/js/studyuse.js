//提交一级回复内容
function post(){
    var question_id=$("#question_id").val();
    var type=$("#type").val();
    var content=$("#content").val();

    });
}
//发送评论
function sendComment(){
 $.ajax({
      type: 'POST',
      url: '/comment',
      contentType:'application/json',
      data: JSON.stringify({
        "parent_id":question_id,
        "type":type,
        "content":content
      }),
      success: function(response){
      if(response.code==200){
        $("#comment_div").hide();
       }
      if(response.code==2002){
        var as=confirm(response.message);
        if(as){
            window.open("https://github.com/login/oauth/authorize?client_id=47c368246d423c34df47&redirect_uri=http://localhost:8081/callback&scope=user&state=1");
            window.localStorage.setItem("close",true);
        }
      }
      console.log(response);},
      dataType: 'json'
}
//展开关闭二级评论框
function openOrClose(e){
    var id=e.getAttribute("data-id");
    var second=$("#comment-"+id);
    var temp=e.getAttribute("temp");
    if(temp){
        e.removeAttribute("temp");

        second.removeClass("in");
    }else{
        e.setAttribute("temp","in")
        second.addClass("in");

    }



}
