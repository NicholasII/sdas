# sdas
<b>the station of data staistic system to guangzhou mobile</b><br>
<small>基于r语言和大数据等技术实现对移动工单的验证，通过海量数据的分析显示对未来时刻的预警</small><br>
<small>本系统作为上述算法验证的演示系统</small><br>
<h1>introduce</h1>
1.工单验证<br>
2、预警<br>


<h2>项目进展</h2>
2017/10/16 为小区内各类工单添加了时间查询，修改因为恢复库不能正常显示的历史健康曲线<br>
2017/10/28 小区模型修改，后台数据使用t_normal_model_detail_info<br>
2017/10/30 添加小区异常指标预警和任务调度<br>
2017/10/31 controller、service、dao、dto都基于相应的基类，在基类中写了大量的通用方法，减少的代码量<br>
2017/11/6 前端大量代码优化，生成通用的工具类<br>
2017/11/7 添加用户管理，登录验证和权限管理<br> 
2017/11/9 增加登陆状态和验证码<br>
2017/11/13 添加数据导入模块的日志，后期计划做一个独立的日志模块


<h2>技术选型</h2>
1. spring4<br>
2. mybatis3.4.5<br>
3. log4j<br>
4. poi<br>
5. pagehelper<br>
6. echarts<br>
7. bootsrap.table.notify,validator..<br>
8. hplus jqgrid<br>
9. ......

