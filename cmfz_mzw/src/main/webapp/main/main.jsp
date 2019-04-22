<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:xxxxx
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div id="menu" data-options="region:'west',title:'导航菜单',split:true" style="width:220px;" class="easyui-accordion">

</div>
<div  data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(${pageContext.request.contextPath}/main/image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
<script>
    $(function () {

        $.ajax({
            url:'${pageContext.request.contextPath}/menu/selectAll',
            dataType:'json',
            type:'get',
            success:function (data) {
               for (i in data){
                   var c="<div align='center'>"
                   var list = data[i].list
                   for( m in list){
                       var iconCls=list[m].iconCls;
                       console.log(iconCls);
                       var data2 = JSON.stringify(list[m]);
                       console.log(data2);
                       c+="<p><a class='easyui-linkbutton' onclick='addTabs("+data2+")' data-options=\"iconCls:'"+iconCls+"'\">"+list[m].title+"</a></p>";
                   }
                   c+="</div>";
                   $("#menu").accordion('add',{
                       title: data[i].title,
                       content:c,
                       iconCls:data[i].iconCls,
                       selected: false,
                   })
               }
            }
        })
    })
    function addTabs(data) {

        var title = data.title;
        console.log(data.url);
        var tabIsExist = $("#tt").tabs("exists", title);
        if (tabIsExist) {
            $("#tt").tabs("select", title);
        } else {
            $("#tt").tabs("add", {
                title: title,
                iconCls:data.iconCls,
                href: data.url,
                closable: true,
                selected: true,
            })
        }
    }


</script>
</html>