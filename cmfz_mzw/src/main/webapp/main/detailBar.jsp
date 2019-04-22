<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>

<head>
    <meta charset="utf-8">
    <title>ECharts</title>

    <script src="../echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>

<div id="RegBar" style="width: 800px;height:600px;"></div>
<script type="text/javascript">

    var myChart = echarts.init(document.getElementById('RegBar'));
    $.ajax({
        url:"${pageContext.request.contextPath}/bar/regDetails",
        dataType:'json',
        success:function (data) {
            var option = {
                title: {
                    text: '持明法洲用户注册统计图'
                },
                tooltip: {},
                legend: {
                    data:['注册人数']
                },
                xAxis: {
                    data: data.x,
                },
                yAxis: {},
                series: [{
                    name: '注册人数',
                    type: 'bar',
                    data: data.y
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }

    })

</script>
</body>
</html>