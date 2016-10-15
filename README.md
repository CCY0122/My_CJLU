# My_CJLU
中国计量大学实验预约管理软件
这是一款专门管理计量大学 光电、电子、电信专业的实验课的管理软件。如果你愿意，也可以当做自己的便笺  <br/>
可以方便添加、删除本学期的实验课，方便查看自己已经做了多少实验，修得了多少学时。 <br/>
更有吐槽界面，可以发帖吐槽，帖子可评论，云数据使用的是Bmob <br/><br/><br/>
结构概述：
顶部指示器为自定义view，因此指示器不是常见的下划线，而是可爱的小三角形。支持一屏显示tab数量，多出的tab会超出屏幕，<br/>
在滑动时会根据position自动滑动出后面的tab。<br/>
内容区域为viewpager+fragment . 列表都是使用recyclerView + cardView  <br/>
实验添加窗口用的是dialogFragment ，自己的实验内容保存在本地sqlite ，吐槽、评论内容保存在bmob云端。<br/><br/>

截图
![image](https://github.com/CCY0122/My_CJLU/blob/master/myImage/3.png)
![image](https://github.com/CCY0122/My_CJLU/blob/master/myImage/5.png)
![image](https://github.com/CCY0122/My_CJLU/blob/master/myImage/1.png)
![image](https://github.com/CCY0122/My_CJLU/blob/master/myImage/4.png)
![image](https://github.com/CCY0122/My_CJLU/blob/master/myImage/2.png)
 
