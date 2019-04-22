<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>


<table id="bannerTable">

</table>
<div id="bannerTableBtns">
    <a id="add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
    <a id="remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    <a id="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
    <a id="save" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    <a id="exportExcel" class="easyui-linkbutton" data-options="iconCls:'icon-save'">导出excel</a>
</div>
<div id="addbannerDialog">
<form id="addbanner" method="post" enctype="multipart/form-data">
    <div>
        <label for="title">图片标题:</label>
        <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
    </div>
    播放 &nbsp;<input type="radio" name="status" value="1"> &nbsp; 不播放&nbsp;<input name="status" type="radio" value="0">
    </br>
    选择图片<input id="file" type="button" style="width:150px" name="imgFile">
    选择时间<input id="date" name="createTime" style="width:150px">
</form>
</div>

<div id="updatebannerDialog">
    <form id="update" method="post" enctype="multipart/form-data">
        <input id="updateId" type="hidden" name="id">
        <div>

            <input id="updatetitle" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        播放 &nbsp;<input id="a" type="radio" name="status" value="1"> &nbsp; 不播放&nbsp;<input id="b"name="status" type="radio" value="0">
        </br>
        选择图片<input id="updatefile" type="button" style="width:150px" name="imgFile"><br>
        选择时间<input id="updatedate" name="createTime" style="width:150px">
    </form>
</div>

</html>
<script>
    $(function () {
      /*  $('#addbanner').form('submit',{
            url:'${pageContext.request.contextPath}/banner/insert',
            success:function(data){
                alert(data)
            }
        });*/
        $('#addbannerDialog').dialog({
            title: '添加轮播图',
            width: 400,
            height: 200,
            closed: true,
            modal: true,
            buttons:[{
                text:'添加',
                handler:function(){
                    $('#addbanner').form('submit',{
                        url:'${pageContext.request.contextPath}/banner/insert',
                        dataType:'json',
                        success:function(data){
                            data =JSON.parse(data);
                            if(data.flag) {
                                $("#bannerTable").datagrid('load');
                                $("#addbannerDialog").dialog("close")
                                $("#addbanner").form('clear');
                            }else{
                                alert("添加失败");
                            }

                        }
                    });
                }
            },{
                text:'关闭',
                handler:function(){
                  $("#addbannerDialog").dialog("close")
                }
            }]

        })
 /*=====================修改==========================*/
        $('#updatebannerDialog').dialog({
            title: '修改轮播图信息',
            width: 400,
            height: 200,
            closed: true,
            modal: true,
            buttons:[{
                text:'修改',
                handler:function(){
                    $('#update').form('submit',{
                        url:'${pageContext.request.contextPath}/banner/update',
                        dataType:'json',
                        success:function(data){
                            data =JSON.parse(data);
                            if(data.flag) {
                                $("#bannerTable").datagrid('load');
                                $("#updatebannerDialog").dialog("close")
                            }else{
                                alert("修改失败");
                            }

                        }
                    });
                }
            },{
                text:'关闭',
                handler:function(){
                    $("#updatebannerDialog").dialog("closed")
                }
            }]

        })
        /*=====================修改结束==========================*/

        $('#date').datetimebox({
            required: true,
            showSeconds: true
        });

        $('#file').filebox({
            buttonText: '选择文件',
            buttonAlign: 'left'
        })
        $('#updatedate').datetimebox({
            required: true,
            showSeconds: true
        });

        $('#updatefile').filebox({
            buttonText: '选择文件',
            buttonAlign: 'left'
        })

        $("#bannerTable").edatagrid({
            title: '轮播图管理',
            method:'get',
            fit: true,
            fitColumns: true,
            onDblClickRow:function(index,row){
                console.log(row.id);
                $("#updateId").val(row.id);
                $("#updatetitle").val(row.title);
                if(row.status==1){
                    $("#a").prop("checked",true);
                }else{
                    $("#b").prop("checked",true);
                }
                $("#updatedate").datetimebox('setValue',row.createTime);
                $("#updatebannerDialog").dialog('open');
            },
            pagination: true,
            destroyUrl:'${pageContext.request.contextPath}/banner/delete',
            toolbar:'#bannerTableBtns',
            url: '${pageContext.request.contextPath}/banner/selectAll',
            type: 'get',
            columns: [[
                {field: 'id', title: '编号', width: 80},
                {field: 'title', title: '标题', width: 100, sortable: true,editor: {
                        type: 'text',
                        options: {required: true}
                    }},
                {field: 'createTime', title: '时间', width: 80, sortable: true},
                {
                    field: 'status', title: '状态', width: 80, sortable: true, formatter: function (value, row, index) {
                        if (row.status == 1) {
                            return "播放";
                        } else {
                            return "不播放";
                        }
                    }
                }
            ]],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>标题: ' + rowData.title + '</p>' +
                    '<p>图片路径: ' + rowData.imgPath + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        })
        $("#add").click(function () {
            $('#addbannerDialog').dialog('open');

        })
         $("#update").click(function () {



         })




        $("#remove").click(function () {
            $('#bannerTable').edatagrid('destroyRow');
            $('bannerTable').edatagrid('load');

        })
        $("#exportExcel").click(function () {
            window.location.href="${pageContext.request.contextPath}/banner/exportExcel";
        })
    })

</script>