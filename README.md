# CommonShapeView
Android中需要指定button、TextView、布局（LinearLayout等）边框、圆角等属性时，需要在drawable文件夹中定义shape文件，当需要点击效果的时候，需要去定义select文件。随着版本的迭代，shape、select文件会越来越多，导致drawable越来越臃肿，并且这些文件可能极度相似（也许就一个颜色值、一个圆角角度不一样）。为了解决这样的问题，本人定义了2个自定义控件。


适用于button、TextView的自定义控件CommonShapeButton，使用如下

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
                
    效果如下：
          
      ![image](http://github.com/zh724738989/CommonShapeView/raw/master/QQ20180827-170057.png)
     
