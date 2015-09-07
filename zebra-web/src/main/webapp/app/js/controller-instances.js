zebraWeb.controller('instances', function($rootScope, $scope,$location) {
	$scope.zones = [ "南汇", "宝山", "桂桥" ];
	$scope.bus = [ "交易前台", "交易后台", "平台产品", "电影" ];
	$scope.datas = [ {
		name : "rdsj43h97v9hbka102a2",
		status : "运行中",
		type : "读写实例",
		version : "MySQL5.6",
		zone : "宝山",
		bu : "交易前台",
		dba : "苗发平"
	}, {
		name : "edsj43h97v9hbka102a2",
		status : "运行中",
		type : "只读实例",
		version : "MySQL5.6",
		zone : "桂桥",
		bu : "交易后台",
		dba : "苗发平"
	}, {
		name : "gdsj43h97v9hbka102a2",
		status : "下线",
		type : "只读实例",
		version : "MySQL5.1",
		zone : "南汇",
		bu : "电影",
		dba : "苗发平"
	} ];
	
	$scope.manager = function(name){
		$location.path("/database-info").search({id:name});
	}
});