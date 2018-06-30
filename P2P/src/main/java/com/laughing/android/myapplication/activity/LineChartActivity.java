package com.laughing.android.myapplication.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.laughing.android.myapplication.R;

import java.util.ArrayList;
/**
 * 折线图
 */
public class LineChartActivity extends BaseActivity {

    private Typeface mTf;
    private LineChart chart;
    private ImageView mTitle_left;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_line_chart;
    }

    @Override
    protected void initView(View mContextView) {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        mTitle_left = (ImageView) findViewById(R.id.title_left);
        ImageView title_right = (ImageView) findViewById(R.id.title_right);
        title_tv.setText("折线图示例");
        mTitle_left.setVisibility(View.VISIBLE);
        title_right.setVisibility(View.INVISIBLE);
        chart = (LineChart) findViewById(R.id.chart);


    }

    @Override
    protected void initListener() {
        mTitle_left.setOnClickListener(new View.OnClickListener() {//回退按钮
            @Override
            public void onClick(View v) {
                closeCurrentActivity();
            }
        });
    }


    @Override
    protected void initData() {
        drawLineChart();//调用API画图
    }

    ///////////////////////////////////////////////////////////////////////////////////////////


    private void drawLineChart() {
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");//引入assets字体
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        // apply styling
        // holder.chart.setValueTypeface(mTf);
        //绘制图表右下角文字描述信息
        chart.setDescription("折线图demo");
        chart.setDrawGridBackground(true);

        //绘制图表的X轴
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        //绘制图表的Y轴
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        //false:代表值是平均分配的;
        leftAxis.setLabelCount(7, false);

        chart.getAxisRight().setEnabled(false);
//        YAxis rightAxis = chart.getAxisRight();
//        rightAxis.setTypeface(mTf);
//        rightAxis.setLabelCount(5, false);
//        rightAxis.setDrawGridLines(false);

        // set data
        chart.setData(generateDataLine());
        // do not forget to refresh the chart
        // holder.chart.invalidate();
        chart.animateX(750);
    }



    /**
     * generates a random ChartData object with just one DataSet
     * 只生成一个随机的ChartData对象，只有一个DataSet
     *
     * @return
     */
    private LineData generateDataLine() {

        ArrayList<Entry> e1 = new ArrayList<Entry>();
        for (int i = 0; i < 12; i++) {
            e1.add(new Entry((int) (Math.random() * 65) + 40, i));
        }
        LineDataSet d1 = new LineDataSet(e1, "New DataSet 1");
        //指定数据集合绘制时候的属性
        d1.setLineWidth(5f);
        d1.setCircleSize(7.5f);
        d1.setHighLightColor(Color.GREEN);//十字线条颜色
        d1.setValueTextSize(12);//文字大小
        d1.setValueTextColor(Color.RED);//图表圆圈上的文字颜色
        d1.setDrawValues(true);//显示图表圆圈上的文字

        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);

        LineData cd = new LineData(getMonths(), sets);
        return cd;
    }

    private ArrayList<String> getMonths() {
        ArrayList<String> m = new ArrayList<String>();
        m.add("Jan");
        m.add("Feb");
        m.add("Mar");
        m.add("Apr");
        m.add("May");
        m.add("Jun");
        m.add("Jul");
        m.add("Aug");
        m.add("Sep");
        m.add("Okt");
        m.add("Nov");
        m.add("Dec");
        return m;
    }

}
