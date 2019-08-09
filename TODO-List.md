- ShareElement实现转场动画，这种动画能用于Activity之间的转场，别的页面切换可以用吗？
- ViewPage产品推荐栏位
- Banner图展示
- 圆头像
- 至少使用一个网络框架
- 明确MVC、MVP、MVVM框架





## 资源
### 历史上的今天（聚合数据免费接口）
一级地址：

http://v.juhe.cn/todayOnhistory/

二级地址：

事件列表 queryEvent.php

    GET请求
        date：查询日期，形如"5/12";
        key: 聚合数据key,写死"b98878f54d1d8bcd27465baef40a3863".

事件详情 queryDetail.php

    GET请求
        e_id：事件ID，从事件列表中拿到要查看详情单对应事件ID;
        key： （参照第一个接口）.