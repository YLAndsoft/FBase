package f.b.cview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import f.b.R;

/**
 * @author: FYL
 * @time: 2018/9/27
 * @email:347933430@qq.com
 * @describe: f.b.cview
 */
public class CircleView extends View {

    Paint mPaint,mPaint1;
    private int mColor;
    // 自定义View有四个构造函数
    // 如果View是在Java代码里面new的，则调用第一个构造函数
    public CircleView(Context context) {
        super(context);
        init();
    }
    // 如果View是在.xml里声明的，则调用第二个构造函数
    // 自定义属性是从AttributeSet参数传进来的
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init();
    }
    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleView);

        mColor = ta.getColor(R.styleable.CircleView_bg_color,Color.RED);
        boolean focusable = ta.getBoolean(R.styleable.CircleView_focusable,false);

        //释放资源
        ta.recycle();
        init();
    }


    private void init() {
        //创建画笔
        mPaint = new Paint();

        mPaint1 = new Paint();
        //设置画笔颜色
        mPaint.setColor(Color.RED);
        mPaint1.setColor(mColor);
        //setARGB(int a, int r, int g, int b)

        mPaint1.setTextSize(50);
        //设置颜色过滤
        //setColorFilter(ColorFilter filter)

        //设置画笔宽度
        mPaint.setStrokeWidth(10f);
        mPaint1.setStrokeWidth(2f);
        //设置画笔填充模式
        //画笔样式分三种：
        //1.Paint.Style.STROKE：描边
        //2.Paint.Style.FILL_AND_STROKE：描边并填充
        //3.Paint.Style.FILL：填充
        mPaint.setStyle(Paint.Style.STROKE);

        //设置绘制图形的透明度，设置范围是[0..255]
        mPaint.setAlpha(120);

        //打开抗锯齿
        mPaint.setAntiAlias(true);

        //设置文本仿粗体。注意设置在小字体上效果会非常差。
        //setFakeBoldText (boolean fakeBoldText)

        //设置画笔的隐藏模式。可以是 HINTING_OFF or HINTING_ON之一。
        //setHinting(int mode)

        //设置行间距，默认是0.
        //setLetterSpacing(float letterSpacing)

        // 设置阴影效果
        mPaint.setShadowLayer(10, 3, 3, Color.DKGRAY);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布背景颜色
        canvas.drawColor(Color.WHITE);

        // 获取传入的padding值
        /*final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();*/

        int width = getWidth()-getPaddingLeft()-getPaddingRight();//宽
        int height = getHeight()-getPaddingTop()-getPaddingBottom();//高
        // 设置圆的半径 = 宽,高最小值的2分之1
        int r = (Math.min(width, height)-100)/2;
        //绘制参数: 宽：640>>高：320>>半径：160
        Log.i("绘制参数","宽："+width+">>高："+height+">>半径："+r+"");

        canvas.drawText("傻傻的",width/2,height/2,mPaint1);
//        canvas.drawLines(new float[]{50, 600, 400, 600, 400, 600, 400, 50,400, 50, 50, 50, 50, 50, 50, 600},mPaint1);
//        canvas.drawText("傻傻的",width/2,height/2,mPaint1);
        // 圆心 = 控件的中央,半径 = 宽,高最小值的2分之1
        //cx：圆心的x坐标。cy：圆心的y坐标。radius：圆的半径。paint：绘制时所使用的画笔。
        Path path = new Path();

        canvas.drawCircle(width/2,height/2,r,mPaint);


    }






}
