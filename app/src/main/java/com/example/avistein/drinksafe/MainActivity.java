package com.example.avistein.drinksafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity {


    Button mmore_InfoButton;
    Button mget_StartedButton;


    TextView mwelcome_Text;
    TextView mwelcome_Text2;
    TextView mwelcome_Text3;


    int age;
    int weight;
    int height;
    int sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //this gets the information from the spinners
        Spinner ageSpinner=(Spinner) findViewById(R.id.age_spinner);
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Log.d("MyAgeSpinner", parentView.getItemAtPosition(position).toString());
                age = Integer.valueOf((String)parentView.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        Spinner weightSpinner=(Spinner) findViewById(R.id.weight_spinner);
        weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Log.d("MyWeightSpinner", parentView.getItemAtPosition(position).toString());
                weight = Integer.valueOf((String)parentView.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        Spinner heightSpinner=(Spinner) findViewById(R.id.height_spinner);
        heightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Log.d("MyHeightSpinner", parentView.getItemAtPosition(position).toString());
                height = position + 54;
            }



            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //what is going on here???
        Spinner sexSpinner=(Spinner) findViewById(R.id.sex_spinner);
        sexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Log.d("MySexSpinner", parentView.getItemAtPosition(position).toString());
                sex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        mwelcome_Text = (TextView) findViewById(R.id.welcome_Text);
        mwelcome_Text2 = (TextView) findViewById(R.id.welcome_Text2);
        mwelcome_Text3 = (TextView) findViewById(R.id.welcome_Text3);


        mmore_InfoButton = (Button) findViewById(R.id.more_Info);
        mmore_InfoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            //start MoreInfo
                Intent i = new Intent(MainActivity.this, InformationActivity.class);
                startActivityForResult(i, 0);
            }
        });


        mget_StartedButton = (Button) findViewById(R.id.get_Started);
        mget_StartedButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //start MoreInfo
                //push spinner info as instance variable
                //I dont think this is
                Intent i = new Intent(MainActivity.this, DrinkActivity.class);
                i.putExtra("MyAgeSpinner",age); //this pushes the info?
                i.putExtra("MyWeightSpinner",weight); //this pushes the info?
                i.putExtra("MyHeightSpinner",height); //this pushes the info?
                i.putExtra("MySexSpinner", sex); //this pushes the info?
                startActivityForResult(i, 0);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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