var table;
var tableIns;
var h = $(window).height();
$(function () {
    $("#left-bottom").height(h-52);
    //加载部门树形结构
    $.ajax({
        type : "post",
        url : "/user/getDeptTree",
        success : function (result) {
            for (var i in result ){
                if(result[i].parentid === 0){
                    result[i].open = true;
                }
            }
            $.fn.zTree.init($("#treeDemo"), setting, result);
        }
    });

    //初始化加载办公室用户列表
    layui.use(['form','layer','table','laytpl'],function() {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            laytpl = layui.laytpl,
            table = layui.table;

        tableIns = table.render({
            elem: '#userTable',
            url: '/user/getUserByDeptId/2',
            cellMinWidth: 95,
            page: true,
            height: 'full-54',
            limits: [10, 15, 20, 25],
            limit: 20,
            id: "userTable",
            size : 'sm',
            cols : [[
                {type:'checkbox'},
                {field:'usernum',title:'用户编号',width:110,sort:true,align:'center'},
                {field:'username',title:'用户名',width:90,align:'center'},
                {field:'realname',title:'姓名',width:100,align:'center'},
                {field:'sex',title:'性别',width:50,align:'center'},
                {field:'deptname',title:'上级部门',width:110,align:'center'},
                {field:'mail',title:'邮箱',width:140,align:'center',templet:function(data){
                    return '<a class="layui-blue" href="mailto:'+data.mail+'">'+data.mail+'</a>';
                }},
                {field:'state', title:'启用/停用', width:85, templet:"#state",align:'center'},
                {title:'操作',width:180,templet:'#userBar',fixed:"right",align:"center"}
            ]]
        });

        $('.layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //启用或暂停
        form.on('switch(state)', function(data){
            var userid = this.value;
            var state = data.elem.checked;
            var stateMsg;
            if(state){
                stateMsg = '启用';
            }else {
                stateMsg = '停用';
            }
            $.ajax({
                type : 'post',
                url : '/user/updateState',
                data : {userid:userid,state:state},
                success : function (result) {
                    if(result !== null && result === 1){
                        layer.msg("该用户已" + stateMsg);
                    }
                }
            });
        });

        //监听工具条
        table.on('tool(userTable)', function(obj){
            var userid = obj.data.userid;
            var username = obj.data.username;
            if(obj.event === 'resetpsd'){
                $.ajax({
                    type : 'post',
                    url : '/user/resetPassword',
                    data : {userid:userid,username:username},
                    success : function (result) {
                        if(result !== null && result === 1){
                            layer.msg("密码重置成功!");
                        }
                    }
                });
            } else if (obj.event === 'userRole'){
                var index = layui.layer.open({
                    title : "用户角色分配",
                    type : 2,
                    content : "/user/userRolePage/" + userid
                });
                layui.layer.full(index);
                //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
                $(window).on("resize",function(){
                    layui.layer.full(index);
                })
            }
        });

        var $ = layui.$, active = {
            //添加用户
            addUser: function(){
                var index = layui.layer.open({
                    title : "添加用户",
                    type : 2,
                    content : "/user/userAddPage"
                });
                layui.layer.full(index);
                //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
                $(window).on("resize",function(){
                    layui.layer.full(index);
                })
            },
            //修改用户
            updateUser: function(){
                var checkStatus = table.checkStatus('userTable'),
                    data = checkStatus.data;
                if (data.length == 1){
                    var index = layui.layer.open({
                        title : "修改用户",
                        type : 2,
                        content : "/user/userUpdatePage/" + data[0].userid
                    });
                    layui.layer.full(index);
                    //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
                    $(window).on("resize",function(){
                        layui.layer.full(index);
                    })
                } else {
                    layer.msg("请选择一条数据！")
                }
            },
            deleteUser: function () {
                var checkStatus = table.checkStatus('userTable'),
                    data = checkStatus.data,
                    userid = [];
                if (data.length != 0){
                    for (var i in data) {
                        userid.push(data[i].userid);
                    }
                    layer.confirm('确定删除选中的所有用户？', {icon: 3, title: '提示信息'}, function (index) {
                        $.ajax({
                            type : 'post',
                            url : '/user/deleteUser/' + userid,
                            success : function (result) {
                                if (result > 0){
                                    layer.alert('用户删除成功！', {
                                        icon : 1,
                                        skin : 'layer-ext-moon'
                                    });
                                } else {
                                    layer.alert('用户删除失败！', {
                                        icon : 2,
                                        skin : 'layer-ext-moon'
                                    });
                                }
                            }
                        });
                        layer.close(index);
                        tableIns.reload();
                    })
                } else {
                    layer.msg("请选择一条或多条数据")
                }
            }
        };
    })
});

var setting = {
    data : {
        key : {
            name : "deptname"
        },
        simpleData : {
            enable: true,
            idKey: "deptid",
            pIdKey: "parentid"
        }
    },
    callback: {
        onClick: onClick
    }
};

function onClick(event, treeId, treeNode) {
    var deptid = treeNode.deptid;
    tableIns.reload({
        url: '/user/getUserByDeptId/' + deptid
    })
}