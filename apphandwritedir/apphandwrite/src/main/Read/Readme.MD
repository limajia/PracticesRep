Glide是一个快速高效的Android图片加载库，具备性能高，流式API的语法特点.作者bumptech

优势：

        1.可配置度高，自适应度高；

        2.支持多钟数据源，本地，网络，assets gif在Glide是支持的；

        3.高效缓存，支持memory和disk图片缓存，默认使用二级缓存

        4.高效处理Bitmap，使用Bitmap pool复用Bitmap

        5.图片加载过程可以监听

        6.生命周期集成至Glide


Glide现在升级了很多内容，刚开始是架构师由<<<<<请求、管理者、分发>>>>三个对象完成。现在使用的是Builder模式来构造对象，再由Node树形结构进行访问请求bitmap对象。

首先需要RequestOptions收集设置参数，然后通过apply设置到RequestBuilder对象中，从RequestBuilder来看他就是一个Builder对象，使用Builder模式构建参数数据。然后使用into将请求加入到队列中