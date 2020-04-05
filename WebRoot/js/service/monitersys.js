$(function () {
    $(document).ready(function () {
    	changeLang_js();
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
        $('#container').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function () {
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function () {
                            var x = (new Date()).getTime(); // current time
                             var y ;

                            jQuery.ajax({
            					type : 'POST',
            				    url : ctx + '/onlineUserList.do',
            				    dataType : 'json',
            				    success : function(data) {
            				    	y = (data.userCount - 0);
        							series.addPoint( [ x, y ], true, true);
            				    }
            				});			
                        }, 6000);
                    }
                }
            },
            title: {
                text: $.i18n.prop('sysonlineuserwath')
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 
                    	$.i18n.prop('usernum')
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/><b>用户数</b><br/>' +
                        Highcharts.numberFormat(this.y, 0);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 
                	$.i18n.prop('time'),
                data: (function () {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
                    for (i = -12; i <= 0; i++) {
                        data.push({
                            x: time + i * 6000,
                            y: 0
                        });
                    }
                    return data;
                }())
            }]
        });
    });
});