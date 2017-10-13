var heatMap = new BMap.Map("container");          // 创建地图实例
    var point = new BMap.Point(113.274449,23.142448);
    heatMap.centerAndZoom(point, 13);             // 初始化地图，设置中心点坐标和地图级别
    heatMap.enableScrollWheelZoom(); // 允许滚轮缩放
  
    var points =[
    {"lng":113.278261,"lat":23.141984,"count":50},
    {"lng":113.273332,"lat":23.146532,"count":51},
    {"lng":113.279787,"lat":23.930658,"count":15},
    {"lng":113.278455,"lat":23.140921,"count":40},
    {"lng":113.278843,"lat":23.145516,"count":100},
    {"lng":113.27546,"lat":23.148503,"count":6},
    {"lng":113.273289,"lat":23.149989,"count":18},
    {"lng":113.278162,"lat":23.145051,"count":80},
    {"lng":113.272039,"lat":23.14782,"count":11},
    {"lng":113.27387,"lat":23.147253,"count":7},
    {"lng":113.27773,"lat":23.149426,"count":42},
    {"lng":113.271107,"lat":23.146445,"count":4},
    {"lng":113.277521,"lat":23.147943,"count":27},
    {"lng":113.279812,"lat":23.140836,"count":23},
    {"lng":113.270682,"lat":23.14463,"count":60},
    {"lng":113.275424,"lat":23.144675,"count":8},
    {"lng":113.279242,"lat":23.144509,"count":15},
    {"lng":113.272766,"lat":23.141408,"count":25},
    {"lng":113.271674,"lat":23.144396,"count":21},
    {"lng":113.277268,"lat":23.14267,"count":1},
    {"lng":113.277721,"lat":23.140034,"count":51},
    {"lng":113.272456,"lat":23.14667,"count":7},
    {"lng":113.270432,"lat":23.149114,"count":11},
    {"lng":113.275013,"lat":23.141611,"count":35},
    {"lng":113.278733,"lat":23.931037,"count":22},
    {"lng":113.279336,"lat":23.931134,"count":4},
    {"lng":113.273557,"lat":23.143254,"count":5},
    {"lng":113.278367,"lat":23.14943,"count":3},
    {"lng":113.274312,"lat":23.149621,"count":100},
    {"lng":113.273874,"lat":23.149447,"count":87},
    {"lng":113.274225,"lat":23.143091,"count":32},
    {"lng":113.277801,"lat":23.141854,"count":44},
    {"lng":113.277129,"lat":23.148227,"count":21},
    {"lng":113.276426,"lat":23.142286,"count":80},
    {"lng":113.271597,"lat":23.14948,"count":32},
    {"lng":113.273895,"lat":23.140787,"count":26},
    {"lng":113.273563,"lat":23.141197,"count":17},
    {"lng":113.277982,"lat":23.142547,"count":17},
    {"lng":113.276126,"lat":23.141938,"count":25},
    {"lng":113.27326,"lat":23.145782,"count":100},
    {"lng":113.279239,"lat":23.146759,"count":39},
    {"lng":113.277185,"lat":23.149123,"count":11},
    {"lng":113.277237,"lat":23.147518,"count":9},
    {"lng":113.277784,"lat":23.145754,"count":47},
    {"lng":113.270193,"lat":23.147061,"count":52},
    {"lng":113.272735,"lat":23.145619,"count":100},
    {"lng":113.278495,"lat":23.145958,"count":46},
    {"lng":113.276292,"lat":23.931166,"count":9},
    {"lng":113.279916,"lat":23.144055,"count":8},
    {"lng":113.27189,"lat":23.141308,"count":11},
    {"lng":113.273765,"lat":23.149376,"count":3},
    {"lng":113.278232,"lat":23.140348,"count":50},
    {"lng":113.277554,"lat":23.930511,"count":15},
    {"lng":113.278568,"lat":23.148161,"count":23},
    {"lng":113.273461,"lat":23.146306,"count":3},
    {"lng":113.27232,"lat":23.14161,"count":13},
    {"lng":113.2774,"lat":23.148616,"count":6},
    {"lng":113.274679,"lat":23.145499,"count":21},
    {"lng":113.27171,"lat":23.145738,"count":29},
    {"lng":113.277836,"lat":23.146998,"count":99},
    {"lng":113.270755,"lat":23.148001,"count":10},
    {"lng":113.274077,"lat":23.930655,"count":14},
    {"lng":113.276092,"lat":23.142995,"count":16},
    {"lng":113.27535,"lat":23.931054,"count":15},
    {"lng":113.273022,"lat":23.141895,"count":13},
    {"lng":113.275551,"lat":23.143373,"count":17},
    {"lng":113.271191,"lat":23.146572,"count":1},
    {"lng":113.279612,"lat":23.147119,"count":9},
    {"lng":113.278237,"lat":23.141337,"count":54},
    {"lng":113.273776,"lat":23.141919,"count":26},
    {"lng":113.277694,"lat":23.14536,"count":17},
    {"lng":113.275377,"lat":23.144137,"count":19},
    {"lng":113.277434,"lat":23.144394,"count":43},
    {"lng":113.27588,"lat":23.142622,"count":27},
    {"lng":113.278345,"lat":23.149467,"count":8},
    {"lng":113.276883,"lat":23.147171,"count":3},
    {"lng":113.273877,"lat":23.146659,"count":34},
    {"lng":113.275712,"lat":23.145613,"count":14},
    {"lng":113.279869,"lat":23.931416,"count":12},
    {"lng":113.276956,"lat":23.145377,"count":11},
    {"lng":113.27066,"lat":23.145017,"count":38},
    {"lng":113.276244,"lat":23.140215,"count":91},
    {"lng":113.27929,"lat":23.145908,"count":54},
    {"lng":113.272116,"lat":23.149658,"count":21},
    {"lng":113.2783,"lat":23.145015,"count":15},
    {"lng":113.271969,"lat":23.143527,"count":3},
    {"lng":113.272936,"lat":23.141854,"count":24},
    {"lng":113.27905,"lat":23.149217,"count":12},
    {"lng":113.274579,"lat":23.144987,"count":57},
    {"lng":113.27076,"lat":23.145251,"count":70},
    {"lng":113.275867,"lat":23.148989,"count":8}];
   
    if(!isSupportCanvas()){
    	alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
    }
	//详细的参数,可以查看heatmap.js的文档 https://github.com/pa7/heatmap.js/blob/master/README.md
	//参数说明如下:
	/* visible 热力图是否显示,默认为true
     * opacity 热力的透明度,1-100
     * radius 势力图的每个点的半径大小   
     * gradient  {JSON} 热力图的渐变区间 . gradient如下所示
     *	{
			.2:'rgb(0, 255, 255)',
			.5:'rgb(0, 110, 255)',
			.8:'rgb(100, 0, 255)'
		}
		其中 key 表示插值的位置, 0~1. 
		    value 为颜色值. 
     */
	heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
	heatMap.addOverlay(heatmapOverlay);
	heatmapOverlay.setDataSet({data:points,max:100});
	
    function setGradient(){
     	/*格式如下所示:
		{
	  		0:'rgb(102, 255, 0)',
	 	 	.5:'rgb(255, 170, 0)',
		  	1:'rgb(255, 0, 0)'
		}*/
     	var gradient = {};
     	var colors = document.querySelectorAll("input[type='color']");
     	colors = [].slice.call(colors,0);
     	colors.forEach(function(ele){
			gradient[ele.getAttribute("data-key")] = ele.value; 
     	});
        heatmapOverlay.setOptions({"gradient":gradient});
    }
	//判断浏览区是否支持canvas
    function isSupportCanvas(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    }