layui.use(['form','layer'], function () {
    $=layui.jquery;
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    getDept(form);

    form.verify({
        pass : function (value) {
            var userpass = $("#userpass").val();
            if (value != userpass){
                return '两次密码输入不一致';
            }
        }
    });

    form.on('submit(addUser)', function (data) {
        $.ajax({
            url : '/user/addUser',
            type : 'POST',
            data : data.field,
            success : function (result) {
                if (result == 1){
                    top.layer.msg("添加成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    top.layer.msg("添加失败！")
                }
            }
        });
        return false;
    })
});

//获取部门下拉框
function getDept(form) {
    $.ajax({
        url : '/user/getDeptTree',
        type : 'POST',
        success : function (result) {
            var option;
            for (var i = 0; i < result.length; i++){
                if(result[i].deptnum != null){
                    option += "<option value="+result[i].deptid+">"+result[i].deptname+"</option>";
                }
            }
            $("select[name='deptid']").append(option);
            layui.use(['form'], function(){
                form.render();
                form.on('select(deptid)', function(data){
                    console.log(data.value);
                });
            })
        }
    })
}