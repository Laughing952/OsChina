package com.laughing.android.myapplication.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.laughing.android.myapplication.R;

import java.util.ArrayList;

/**
 * 饼状图
 */
public class PieChartActivity extends BaseActivity {

    private ImageView mTitle_left;
    private PieChart chart;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pie_chart;
    }

    @Override
    protected void initView(View mContextView) {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        mTitle_left = (ImageView) findViewById(R.id.title_left);
        ImageView title_right = (ImageView) findViewById(R.id.title_right);
        title_tv.setText("饼状图示例");
        mTitle_left.setVisibility(View.VISIBLE);
        title_right.setVisibility(View.INVISIBLE);
        chart = (PieChart) findViewById(R.id.chart);

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

        drawPieChart();
    }

    private void drawPieChart() {

        // apply styling
        chart.setDescription("饼图示例");
        //绘制最内层的圆的半径
        chart.setHoleRadius(45f);
        //绘制包裹最内层圆的外层圆的半径
        chart.setTransparentCircleRadius(57f);
        chart.setCenterText("资产分配");
        chart.setCenterTextSize(14f);
        //是否适用百分比显示值
        chart.setUsePercentValues(true);//用百分比显示各部分

        PieData mChartData = generateDataPie();
        mChartData.setValueFormatter(new PercentFormatter());//用百分比显示各部分
        mChartData.setValueTextSize(14f);
        mChartData.setValueTextColor(Color.RED);
        // set data
        chart.setData(mChartData);

        //饼图指示器绘制
        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setYEntrySpace(0f);//文字间隙
        l.setYOffset(10f);//文字Y偏移

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        chart.animateXY(900, 900);
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new Entry((int) (Math.random() * 70) + 30, i));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        //绘制图表数据集合的时候指定属性
        d.setSliceSpace(10f);//图表中间间隙
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData cd = new PieData(getQuarters(), d);
        return cd;
    }

    private ArrayList<String> getQuarters() {

        ArrayList<String> q = new ArrayList<String>();
        q.add("1st Quarter");
        q.add("2nd Quarter");
        q.add("3rd Quarter");
        q.add("4th Quarter");

        return q;
    }

}
