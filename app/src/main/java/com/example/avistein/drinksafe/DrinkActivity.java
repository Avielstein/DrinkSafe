package com.example.avistein.drinksafe;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;



public class DrinkActivity extends ActionBarActivity {

    Button mdrinkButton;
    TextView mcurrentBAC;
    TextView mtimeLeft;
    TextView mbac;
    TextView mtime;
    public int drink;
    //public int bac;
    //public int goingDown;
    public int tag;
    public int timeKeper;



    LineGraphSeries<DataPoint> series1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);








        mcurrentBAC = (TextView) findViewById(R.id.currentBAC);
        mtimeLeft = (TextView) findViewById(R.id.timeLeft);
        mtime = (TextView) findViewById(R.id.time);
        mbac = (TextView) findViewById(R.id.bac);
        

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        series1 = new LineGraphSeries<DataPoint>(generateData());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(50);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxY(12);
        graph.addSeries(series1);






        mdrinkButton = (Button) findViewById(R.id.drink);
        mdrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drink == 0){
                    series1.resetData(generateData());
                    graph.addSeries(series1);

                }
                else{
                    series1.resetData(generateData());
                }


                drink = drink + 1;
            }
        });


    }





    private DataPoint[] generateData() {
        int count = 50;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {


            //no drinks have been taken / now sober
            if (drink == 0){
                double x = 0;
                double y = 0;
                DataPoint v = new DataPoint(x, y);
                values[i] = v;
            }


            //this is intended to show how bac decreses
            if (timeKeper>10 & drink >0){
                timeKeper = 0;
                drink = drink - 1;
                double x = i;
                double y = drink;
                DataPoint v = new DataPoint(x, y);
                values[i] = v;
            }
            
            else{
                double x = i;
                double y = drink;
                DataPoint v = new DataPoint(x, y);
                values[i] = v;
            }

        }
        return values;
    }


    private final Handler mHandler = new Handler();
    private Runnable mTimer1;



    @Override
    public void onResume() {
        super.onResume();
        mTimer1 = new Runnable() {
            @Override
            public void run() {

                //This loop makes sure that if you havent been drinking for a while
                //that the first click will make the BAC go up
                if(drink >0){
                    //time keeper allows time delays of choice to effect BAC (see gen.data loop)
                    timeKeper = timeKeper + 1;
                }
                series1.resetData(generateData());
                mHandler.postDelayed(this, 300);
            }
        };
        mHandler.postDelayed(mTimer1, 300);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer1);
        super.onPause();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drink, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
