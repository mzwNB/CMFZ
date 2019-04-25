<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>

<head>
    <meta charset="utf-8">
    <title>ECharts</title>

    <script src="../echarts/echarts.min.js"></script>
    <script src="../echarts/china.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>
<div align="center">
    <div id="province" style="width: 800px;height:600px;">
    </div>
</div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('province'));
     $.ajax({
         url:"${pageContext.request.contextPath}/bar/provinceDetails",
         dataType:'json',
         success:function (data) {
             var option = {
                 title : {
                     text: '持明法洲用户全国分布图',
                     subtext: '纯属虚构',
                     left: 'center'
                 },
                 tooltip : {
                     trigger: 'item'
                 },
                 legend: {
                     orient: 'vertical',
                     left: 'left',
                     data:['男','女']
                 },
                 visualMap: {
                     min: 0,
                     max: 20,
                     left: 'left',
                     top: 'bottom',
                     text:['多','少'],           // 文本，默认为数值文本
                     calculable : true
                 },
                 toolbox: {
                     show: true,
                     orient : 'vertical',
                     left: 'right',
                     top: 'center',
                     feature : {
                         mark : {show: true},
                         dataView : {show: true, readOnly: false},
                         restore : {show: true},
                         saveAsImage : {show: true}
                     }
                 },
                 series : [
                     {
                         name: '男',
                         type: 'map',
                         mapType: 'china',
                         roam: false,
                         label: {
                             normal: {
                                 show: false
                             },
                             emphasis: {
                                 show: true
                             }
                         },
                         data:data.male,//男 全国分布


                     },
                     {
                         name: '女',
                         type: 'map',
                         mapType: 'china',
                         label: {
                             normal: {
                                 show: false
                             },
                             emphasis: {
                                 show: true
                             }
                         },
                         data:data.famale,

                     }
                 ]
             };


             // 使用刚指定的配置项和数据显示图表。
             myChart.setOption(option);
         }
     })
    var goEasy = new GoEasy({
        appkey: "BC-07dde9196acf4107ab17c1745697110f"
    });
    goEasy.subscribe({
        channel: "mzw1",
        onMessage: function (message) {
            var content = JSON.parse(message.content);

            var option = {
                title: {
                    text: '持明法洲用户全国分布图',
                    subtext: '纯属虚构',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['男', '女']
                },
                visualMap: {
                    min: 0,
                    max: 20,
                    left: 'left',
                    top: 'bottom',
                    text: ['多', '少'],           // 文本，默认为数值文本
                    calculable: true
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: [
                    {
                        name: '男',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.male,//男 全国分布


                    },
                    {
                        name: '女',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.famale,

                    }
                ]
            };

            myChart.setOption(option);
        }
    });

</script>
</body>
</html>