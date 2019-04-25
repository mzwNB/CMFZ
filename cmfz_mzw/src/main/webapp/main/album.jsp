<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>


<table id="albumTreegrid">

</table>
<div id="albumTableBtns">
    <a id="selelctAlbum" class="easyui-linkbutton" data-options="iconCls:'icon-add'">查看专辑详情</a>
    <a id="addAlbum" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">添加专辑</a>
    <a id="addChapter" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">添加章节</a>
    <a id="download" class="easyui-linkbutton" data-options="iconCls:'icon-save'">下载音频</a>
    <a id="excelDownload" class="easyui-linkbutton" data-options="iconCls:'icon-save'">导出专辑表</a>
</div>
<div id="albumInfo">
    <p>专辑名称：&nbsp; <span id="albumTitle"></span></p>
    <p>专辑评分：&nbsp; <span id="albumScore"></span>分</p>
    <p>专辑章节：&nbsp; <span id="albumAmount"></span></p>
    <p>专辑作者：&nbsp; <span id="albumAuthor"></span></p>
    <p>播音员：&nbsp; <span id="albumBoardCast"></span></p>
    <p>出版时间：&nbsp; <span id="albumTime"></span></p>
    <p>描述 ：&nbsp;<span id="albumBrief"></span>
    <p>专辑封面 <img id="albumImg" width="150px" height="200px"/></p>
</div>

<div id="addAlbumDialog">
    <form id="addAblumForm" method="post" enctype="multipart/form-data">
        <p>专辑名称：&nbsp; <input id="AddalbumTitle" type="text" name="title"></input></p>
        <p>专辑评分：&nbsp; 1<input value="1" type="radio" name="score"/>&nbsp;
            2<input value="2" type="radio" name="score"/>&nbsp;
            3<input value="3" type="radio" name="score"/>&nbsp;
            4<input value="4" type="radio" name="score"/>&nbsp;
            5<input value="5" type="radio" name="score"/>&nbsp;
            分</p>
        <input id="AddalbumAmount" type="hidden" value="0" name="amount">
        <p>专辑作者：&nbsp; <input id="AddalbumAuthor" type="text" name="author"></input></p>
        <p>播音员：&nbsp; <input id="AddalbumBoardCast" type="text" name="boardcast"></input></p>
        <p>出版时间：&nbsp; <input id="AddalbumTime" name="publishTime" style="width:150px"></p>
        <p>描述 ：&nbsp;<textarea id="AddalbumBrief" name="brief"></textarea></p>
        <p>选择专辑图片 ：&nbsp; <input id="addAlbumImg" type="button" style="width:150px" name="imgFile"></p>
    </form>
</div>

<div id="addChapterDialog">
    <form id="addChapterForm" method="post" enctype="multipart/form-data">
    <p>章节名称：&nbsp;<input id="addChaptertitle" type="text" name="title"></p>
    <p>出版时间: &nbsp;<input id="addChpterDate" name="publishDate"></p>
     <p>所属专辑: &nbsp; <input  id="ablumSelector" name="albumId" style="width:200px;" ></p>
     <p>文件上传：&nbsp;<input id="addChapterFile" name="chapterFile" type="text"></p>
    </form>

</div>

</html>
<script>
    $(function () {


        $('#AddalbumTime').datebox({
            required: true,
        });
        $('#addChpterDate').datebox({
            required: true,
        });

        $('#addAlbumImg').filebox({
            required: true,
            buttonText: '选择文件',
            buttonAlign: 'left'
        })
        $('#addChapterFile').filebox({
            buttonText: '选择文件',
            buttonAlign: 'left'
        })

        $('#addAlbumDialog').dialog({
            title: '添加专辑',
            width: 400,
            height: 600,
            closed: true,
            modal: true,
            buttons: [{
                text: '添加',
                handler: function () {
                    $('#addAblumForm').form('submit', {
                        url: '${pageContext.request.contextPath}/album/insert',
                        dataType: 'json',
                        success: function (data) {
                            data = JSON.parse(data);
                            if (data.flag) {
                                $("#albumTreegrid").treegrid('load');
                                $("#addAlbumDialog").dialog("close")
                                $("#addAblumForm").form('clear');
                            } else {
                                alert("添加失败");
                            }

                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#addAlbumDialog").dialog("close")
                }
            }]

        })
        $('#addChapterDialog').dialog({
            title: '添加章节',
            width: 400,
            height: 600,
            closed: true,
            modal: true,
            buttons: [{
                text: '添加',
                handler: function () {
                    $('#addChapterForm').form('submit', {
                        url: '${pageContext.request.contextPath}/chapter/insert',
                        dataType: 'json',
                        success: function (data) {
                            data = JSON.parse(data);
                            if (data.flag) {
                                $("#albumTreegrid").treegrid('reload');
                                $("#addChapterDialog").dialog("close");
                                $("#addAblumForm").form('clear');
                            } else {
                                alert("添加失败");
                            }

                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#addChapterDialog").dialog("close")
                }
            }]

        })

        $('#albumInfo').dialog({
            title: '查看专辑详情',
            width: 400,
            height: 700,
            closed: true,
            modal: true,
            buttons: [{
                text: '关闭',
                handler: function () {
                    $("#albumInfo").dialog("close");
                }
            }]

        })

        $('#albumTreegrid').treegrid({
            url: '${pageContext.request.contextPath}/album/selectAll',
            method: 'get',
            idField: 'id',
            treeField: 'title',
            fit: true,
            fitColumns: true,
            toolbar: "#albumTableBtns",
            columns: [[
                {field: 'title', title: '章节名称', width: 100,},
                {field: 'size', title: '文件大小', width: 80},
                {field: 'chapterPath', title: '文件路径', width: 80},
                {field: 'duration', title: '时长', width: 80},
            ]]
        });

        $("#selelctAlbum").click(function () {
            var ele = $('#albumTreegrid').treegrid('getSelected');
            if (ele == null) {
                alert("请先选中一个专辑");
                return false;
            }
            var id;
            if (ele.albumId == null) {
                id = ele.id;
            } else {
                id = ele.albumId;
            }
            console.log(id);
            $.ajax({
                type: "get",
                url: '${pageContext.request.contextPath}/album/selectOne',
                data: 'id=' + id,
                dataType: "json",
                success: function (data) {
                    if (data.flag) {
                        $("#albumTitle").html(data.Album.title);
                        $("#albumTime").html(data.Album.publishTime);
                        $("#albumScore").html(data.Album.score);
                        $("#albumAmount").html(data.Album.amount);
                        $("#albumAuthor").html(data.Album.author);
                        $("#albumBoardCast").html(data.Album.boardcast);
                        $("#albumBrief").html(data.Album.brief);
                        $("#albumImg").prop("src", "${pageContext.request.contextPath}" + data.Album.imgPath);

                        $("#albumInfo").dialog("open");
                    } else {
                        alert("查询失败,该专辑可能被删除");
                    }
                }
            })


        })
        $("#addAlbum").click(function () {
            $("#addAlbumDialog").dialog("open");
        })
        $('#addChapter').click(function () {
            $('#addChapterDialog').dialog('open');
            $('#ablumSelector').combobox({
                url:'${pageContext.request.contextPath}/album/selectAll',
                valueField:'id',
                textField:'title',
                panelHeight:"auto"
            });

        })
        $('#download').click(function () {
            var chapter = $('#albumTreegrid').treegrid('getSelected');
            if(chapter.albumId!=null){
                var chapterid = chapter.id;
                window.location.href="${pageContext.request.contextPath}/chapter/download?id="+chapterid;
            }

        })
        $("#excelDownload").click(function () {
            window.location.href = "${pageContext.request.contextPath}/album/downloadAblum";
        })
    })
</script>
