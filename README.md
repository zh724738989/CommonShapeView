# CommonShapeView
Android中需要指定button、TextView、布局（LinearLayout等）边框、圆角等属性时，需要在drawable文件夹中定义shape文件，当需要点击效果的时候，需要去定义select文件。随着版本的迭代，shape、select文件会越来越多，导致drawable越来越臃肿，并且这些文件可能极度相似（也许就一个颜色值、一个圆角角度不一样）。为了解决这样的问题，本人定义了2个自定义控件。

## 使用方法
##### 在项目根目录的build.gradle中设置如下代码：

            allprojects {
               repositories {
                     ...
                     maven { url 'https://jitpack.io' }
                  }
               }

#### 在app目录下的build.gradle中设置如下代码：

            dependencies {
                 implementation 'com.github.zh724738989:CommonShapeView:1.0.1'
            }


## 适用于button、TextView的自定义控件CommonShapeButton，使用如下

            <com.zh.custom_view.commonshapeview.CommonShapeButton
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:layout_margin="10dp"
                android:text="文本样式+填充颜色+指定位置的圆角"
                app:csb_cornerPosition="bottomLeft"
                app:csb_cornerRadius="10dp"
                app:csb_fillColor="#FFB366" />

            <com.zh.custom_view.commonshapeview.CommonShapeButton
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:layout_margin="10dp"
                android:text="文本样式+填充颜色+圆角"
                app:csb_cornerRadius="5dp"
                app:csb_fillColor="#FFB366" />

            <com.zh.custom_view.commonshapeview.CommonShapeButton
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:layout_margin="10dp"
                android:text="文本样式+填充颜色+半圆"
                app:csb_cornerRadius="25dp"
                app:csb_fillColor="#FFB366" />

            <com.zh.custom_view.commonshapeview.CommonShapeButton
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:layout_margin="10dp"
                android:text="文本样式+填充颜色+圆角+边框线"
                app:csb_cornerRadius="5dp"
                app:csb_fillColor="#F4A460"
                app:csb_strokeColor="#e60b30"
                app:csb_strokeWidth="2dp" />

            <com.zh.custom_view.commonshapeview.CommonShapeButton
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:layout_margin="10dp"
                android:text="文本样式+指定方向的渐变色+圆角+边框线"
                app:csb_cornerRadius="5dp"
                app:csb_endColor="#FFF5EE"
                app:csb_orientation="TL_BR"
                app:csb_startColor="#FFB366"
                app:csb_strokeColor="@color/colorAccent"
                app:csb_strokeWidth="2dp" />

            <com.zh.custom_view.commonshapeview.CommonShapeButton
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:layout_margin="10dp"
                android:text="椭圆+文本样式+指定方向的渐变色+圆角+边框线"
                app:csb_cornerRadius="5dp"
                app:csb_endColor="#FFF5EE"
                app:csb_orientation="TL_BR"
                app:csb_shapeMode="oval"
                app:csb_startColor="#FFB366"
                app:csb_strokeColor="@color/colorAccent"
                app:csb_strokeWidth="2dp" />

            <com.zh.custom_view.commonshapeview.CommonShapeButton
                style="@style/CommonShapeButtonStyle"
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:layout_margin="10dp"
                android:text="按钮样式+指定方向的渐变色+圆角+边框线"
                app:csb_activeEnable="true"
                app:csb_cornerRadius="5dp"
                app:csb_endColor="#FFF5EE"
                app:csb_orientation="TR_BL"
                app:csb_startColor="#FFB366"
                app:csb_strokeColor="@color/colorAccent"
                app:csb_strokeWidth="2dp" />

            <com.zh.custom_view.commonshapeview.CommonShapeButton
                style="@style/CommonShapeButtonStyle"
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:layout_margin="10dp"
                android:text="按钮样式+指定方向的渐变色+圆角+边框线+按压水波纹效果"
                app:csb_activeEnable="true"
                app:csb_cornerRadius="5dp"
                app:csb_endColor="#FFF5EE"
                app:csb_orientation="BOTTOM_TOP"
                app:csb_pressedColor="@color/colorPrimaryDark"
                app:csb_startColor="#FFB366"
                app:csb_strokeColor="@color/colorAccent"
                app:csb_strokeWidth="2dp" />

            <com.zh.custom_view.commonshapeview.CommonShapeButton
                style="@style/CommonShapeButtonStyle"
                android:layout_width="match_parent"
                android:layout_height="50dp"
                android:layout_margin="10dp"
                android:drawableLeft="@mipmap/ic_launcher"
                android:text="带图标样式居中"
                app:csb_activeEnable="true"
                app:csb_cornerRadius="5dp"
                app:csb_drawablePosition="left"
                app:csb_endColor="#FFF5EE"
                app:csb_orientation="BOTTOM_TOP"
                app:csb_pressedColor="@color/colorPrimaryDark"
                app:csb_startColor="#FFB366"
                app:csb_strokeColor="@color/colorAccent"
                app:csb_strokeWidth="2dp" />
                
 ### 效果如下：
![image](https://raw.githubusercontent.com/zh724738989/CommonShapeView/0.0.2/QQ20180827-170057%402x.png)
    

### 属性说明
app:csb_cornerPosition="bottomLeft|bottomRight"     指定圆角位置bottomLeft、bottomRight、topLeft、topRight;若不指定则4个角都是圆角。  
app:csb_cornerRadius="10dp"       圆角度数（圆角度数增大，可设置半圆）  
app:csb_fillColor="#FFB366"       控件背景颜色   
app:csb_strokeColor="#e60b30"     边框颜色  
app:csb_strokeWidth="2dp"         边框宽度   
app:csb_orientation="TL_BR"       渐变方向（共8种方向，自行查看提示），若设置渐变色，就不要设置csb_fillColor   
app:csb_endColor="#FFF5EE"        渐变色结束颜色   
pp:csb_startColor="#FFB366"       渐变色开始颜色    
app:csb_shapeMode="oval"          控件类型（矩形（rectangle）、椭圆形(oval)、线形(line)、环形(ring)）
style="@style/CommonShapeButtonStyle"   若要让控件类似于button，需要设置此代码    
app:csb_activeEnable="true"       是否开启点击效果，true 开启；false  不开启；需要设置类似button    
app:csb_pressedColor="@color/colorPrimaryDark"  点击按钮，按下效果的颜色（默认5.1以上开启水波纹，5.1以下点击变色）   
app:csb_drawablePosition="left"    图片位置（通过android:drawableLeft等设置图片）   



## 适用于LinearLayout等布局的自定义控件CommonLinearLayout，使用如下

            <com.zh.custom_view.commonshapeview.CommonLinearLayout
                android:id="@+id/couponView"
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:orientation="vertical"
                android:padding="10dp"
                android:layout_margin="20dp"
                app:cv_dash_line_color="@android:color/white"
                app:cv_dash_line_gap="10dp"
                app:cv_dash_line_height="2dp"
                app:cv_dash_line_length="10dp"
                app:cv_dash_semicircle_margin_left="80dp"
                app:cv_dash_semicircle_margin_top="30dp"
                app:cv_dash_semicircle_num_x="2"
                app:cv_dash_semicircle_num_y="1"
                app:cv_frame_corner_radius="6dp"
                app:cv_frame_stroke_color="@color/colorPrimaryDark"
                app:cv_frame_stroke_width="0.5dp"
                app:cv_is_dash_line_bottom="true"
                app:cv_is_dash_line_left="true"
                app:cv_is_dash_line_right="true"
                app:cv_is_dash_line_top="true"
                app:cv_is_semicircle_bottom="true"
                app:cv_is_semicircle_left="true"
                app:cv_is_semicircle_right="true"
                app:cv_is_semicircle_top="true"
                app:cv_semicircle_stoken_color="#ffffff"
                app:cv_semicircle_color="#000000"
                app:cv_semicircle_gap="50dp"
                app:cv_semicircle_radius="20dp"
                app:cv_view_background_color="@color/colorAccent" />

 ### 效果如下：
![image](https://raw.githubusercontent.com/zh724738989/CommonShapeView/0.0.2/QQ20180827-170355%402x.png)   


### 属性说明   

app:cv_is_dash_line_bottom="true"  底部显示虚线    
app:cv_is_dash_line_left="true"    左边显示虚线    
app:cv_is_dash_line_right="true"   右边显示虚线    
app:cv_is_dash_line_top="true"     顶部显示虚线    
app:cv_dash_line_colo    虚线颜色    
app:cv_dash_line_gap     虚线间距   
app:cv_dash_line_height="2dp"   虚线高度   
app:cv_dash_line_length="10dp"  一截虚线的长度   
app:cv_is_semicircle_bottom="true"  底部显示半圆    
app:cv_is_semicircle_left="true"    左边显示半圆     
app:cv_is_semicircle_right="true"   右边显示半圆     
app:cv_is_semicircle_top="true"     顶部显示半圆     
app:cv_dash_semicircle_margin_left="80dp"     顶部和底部第一个半圆距离左边的距离    
app:cv_dash_semicircle_margin_top="30dp"      左边和右边第一个半圆距离顶部的距离     
app:cv_dash_semicircle_num_x="2"              顶部和底部的半圆个数    
app:cv_dash_semicircle_num_y="1"              左边和右边半圆个数    
app:cv_semicircle_stoken_color="#ffffff"      半圆边框颜色    
app:cv_semicircle_color="#000000"    半圆背景色    
app:cv_semicircle_gap="50dp"   半圆间距     
app:cv_semicircle_radius="20dp"   半圆半径    
app:cv_frame_corner_radius="6dp"  控件边框圆角半径    
app:cv_frame_stroke_color="@color/colorPrimaryDark"   控件边框颜色    
app:cv_frame_stroke_width="0.5dp"  控件边框宽度    
app:cv_view_background_color="@color/colorAccent"   控件背景颜色   


