package com.example.avistein.drinksafe;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;



public class DrinkActivity extends ActionBarActivity {

    Button mdrinkButton;
    Button mbackButton;
    TextView mcurrentBAC;
    TextView mtimeLeft;
    TextView mbac;
    TextView mtime;
    double drink;
    public int tag;
    public int timeKeper;
    int age;
    int weight;
    int height;
    int sex;
    int minutes = 80;
    double r; //is widmarks factor




    LineGraphSeries<DataPoint> series1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Gets the spinner info
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            age = extras.getInt("MyAgeSpinner");
            weight = extras.getInt("MyWeightSpinner");
            height = extras.getInt("MyHeightSpinner");
            sex = extras.getInt("MySexSpinner");
        }

        if(sex == 0){
            //Female
            r = 0.50766 + 0.11165*height - weight*(0.001612+(0.0031/(height^2)))-weight*(1/(0.62115-3.1665*height));
        }
        else{
            //Male
            r = 0.62544 + 0.1366*height - weight*(0.00189+(0.002452/(height^2)))-weight*(1/(0.57986-2.545*height-0.2255*age));
        }


        //this test the information coming in

        //display in short period of time
        /*
        Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(r),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
        toast.show();*/


        mcurrentBAC = (TextView) findViewById(R.id.currentBAC);
        mtimeLeft = (TextView) findViewById(R.id.timeLeft);
        mtime = (TextView) findViewById(R.id.time);
        mbac = (TextView) findViewById(R.id.bac);
        

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        series1 = new LineGraphSeries<DataPoint>(generateData());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(minutes);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxY(50);
        graph.addSeries(series1);
        series1.setColor(Color.rgb(254,116,112));


        graph.getViewport().setScrollable(true);
        graph.setHorizontalScrollBarEnabled(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(10);

        graph.getViewport().setScalable(true);
        graph.getViewport().setYAxisBoundsManual(true);

//        graph.getGridLabelRenderer().setNumVerticalLabels/setNumHorizontalLabels;






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

        mbackButton = (Button) findViewById(R.id.MainActivity);
        mbackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //start MoreInfo
                finish();
            }
        });


    }





    private DataPoint[] generateData() {

        DataPoint[] values = new DataPoint[minutes];
        for (int i=0; i<minutes; i++) {


            //no drinks have been taken / now sober
            if ((drink == 0) || ((.05*i)>=drink)){
                double x = i;
                double y = 0;
                DataPoint v = new DataPoint(x, y);
                values[i] = v;
            }


            //this is intended to show how bac decreses
            /*else if (timeKeper>10 & drink >0){
                timeKeper = 0;
                drink = drink - 1;

                //same as what is in the else  ~V~V~
                double x = i;
                double y = (1000*drink)/(r*weight)-(.05*x); //need absorbtion and elemnation rate to make it work
                                                    //also how do I keep it above 0?
                DataPoint v = new DataPoint(x, y);
                values[i] = v;
            }*/
            
            else{
                double x = i;
                double y =(1000*drink)/(r*weight)-(.05*x); //need absorbtion and elemnation rate to make it work
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
