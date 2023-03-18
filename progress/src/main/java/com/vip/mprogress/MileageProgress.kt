package com.vip.mprogress

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat


/**
 *AUTHOR:AbnerMing
 *DATE:2023/3/17
 *INTRODUCE:里程进度
 */
class MileageProgress : View {


    private var mColorArray = intArrayOf()//颜色集合

    private var mPaint: Paint? = null//画笔

    //默认的背景颜色
    private var mBackgroundColor = ContextCompat.getColor(context, R.color.text_bfc5d8)

    //告警颜色
    private var mWarnBackgroundColor = ContextCompat.getColor(context, R.color.text_f64749)

    private var mAngleWidth = 10f//默认的角度的宽

    private var mMaxProgress = 100f//默认最大进度

    private var mDefaultProgress = 0f//默认进度为0

    private var mWarnProgress = 0.3f//告警进度

    private var mIsDimProgress = true//模糊进度

    constructor(
        context: Context
    ) : super(context) {
        initData(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        val osa = context.obtainStyledAttributes(attrs, R.styleable.MileageProgress)
        //背景颜色
        mBackgroundColor = osa.getColor(R.styleable.MileageProgress_mp_background, mBackgroundColor)
        //背景颜色
        mBackgroundColor = osa.getColor(R.styleable.MileageProgress_mp_background, mBackgroundColor)
        //角度的宽
        mAngleWidth = osa.getDimension(R.styleable.MileageProgress_mp_angle_width, mAngleWidth)
        //获取定义的颜色数组
        val resourceId = osa.getResourceId(R.styleable.MileageProgress_mp_anamorphism_color, 0)
        if (resourceId != 0) {
            mColorArray = resources.getIntArray(resourceId)
        }

        //模糊进度
        mIsDimProgress = osa.getBoolean(R.styleable.MileageProgress_mp_is_dim_progress, true)

        //最大进度
        mMaxProgress = osa.getFloat(R.styleable.MileageProgress_mp_max_progress, mMaxProgress)

        //默认进度
        mDefaultProgress =
            osa.getFloat(R.styleable.MileageProgress_mp_default_progress, mDefaultProgress)

        //警告进度
        mWarnProgress = osa.getFloat(R.styleable.MileageProgress_mp_warn_progress, mWarnProgress)
        //告警颜色
        mWarnBackgroundColor =
            osa.getColor(R.styleable.MileageProgress_mp_warn_background, mWarnBackgroundColor)
        initData(context)
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:初始化数据
     */
    private fun initData(context: Context) {
        mPaint = Paint()
        mPaint?.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //绘制平行四边形矩形
        canvasParallelogram(canvas!!)

    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:绘制平行四边形矩形
     */
    private fun canvasParallelogram(canvas: Canvas) {
        mPaint!!.color = mBackgroundColor

        //不为空
        if (mColorArray.isEmpty()) {
            mColorArray = intArrayOf(
                ContextCompat.getColor(context, R.color.text_ffa28ceb),
                ContextCompat.getColor(context, R.color.text_ff733bb8)
            )
        }

        val colorArray = IntArray(mColorArray.size + 2)
        mColorArray.forEachIndexed { index, i ->
            colorArray[index] = i
        }

        //倒数第二个为背景颜色
        colorArray[mColorArray.size] = mBackgroundColor
        //设置最后一个颜色为背景颜色
        colorArray[mColorArray.size + 1] = mBackgroundColor

        //进度数组
        val progressArray = FloatArray(colorArray.size)
        progressArray[0] = 0f

        val defaultProgress = mDefaultProgress / mMaxProgress//当前进度

        if (defaultProgress <= mWarnProgress) {
            //如果低于某一个阀门，就标注为告警颜色
            colorArray[0] = mWarnBackgroundColor
            colorArray[1] = mWarnBackgroundColor
        } else {
            colorArray[0] = mColorArray[0]
            colorArray[1] = mColorArray[1]
        }

        progressArray[1] = defaultProgress

        //如果默认进度为0
        if (defaultProgress == 0f) {
            progressArray[2] = defaultProgress
        } else {
            //模糊进度
            if (mIsDimProgress) {
                progressArray[2] = defaultProgress + 0.04f
            } else {
                progressArray[2] = defaultProgress + 0.01f
            }
        }

        val max = mMaxProgress / mMaxProgress//最大进度

        progressArray[3] = max

        val linearShader = LinearGradient(
            0f, 0f, width.toFloat(), height.toFloat(), colorArray,
            progressArray, Shader.TileMode.CLAMP
        )
        mPaint!!.shader = linearShader


        val path = Path()
        path.apply {
            moveTo(0f, height.toFloat())//第一个点的位置
            lineTo(mAngleWidth, 0f)//第二个
            lineTo(width.toFloat(), 0f)//第三个
            lineTo((width - mAngleWidth), height.toFloat())//第四个
            close()
            canvas.drawPath(this, mPaint!!)
        }

    }


    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:改变进度
     */
    fun changeProgress(progress: Float) {
        mDefaultProgress = progress
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var windowHeight = heightMeasureSpec
        if (heightMode == MeasureSpec.AT_MOST) {
            windowHeight = 20//默认的高度
        }
        setMeasuredDimension(widthMeasureSpec, windowHeight)
    }
}