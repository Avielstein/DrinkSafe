package com.example.avistein.drinksafe;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class InformationActivity extends ActionBarActivity {

    TextView minfo_Text1;
    TextView minfo_Text2;
    TextView minfo_Text3;
    TextView minfo_Text4;
    TextView minfo_Text5;
    TextView minfo_Text6;
    TextView minfo_Text7;
    TextView minfo_Text8;
    Button mbackButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        minfo_Text1 = (TextView) findViewById(R.id.info_Text1);
        minfo_Text2 = (TextView) findViewById(R.id.info_Text2);
        minfo_Text3 = (TextView) findViewById(R.id.info_Text3);
        minfo_Text4 = (TextView) findViewById(R.id.info_Text4);
        minfo_Text5 = (TextView) findViewById(R.id.info_Text5);
        minfo_Text6 = (TextView) findViewById(R.id.info_Text6);
        minfo_Text7 = (TextView) findViewById(R.id.info_Text7);
        minfo_Text8 = (TextView) findViewById(R.id.info_Text8);

        mbackButton = (Button) findViewById(R.id.MainActivity);
        mbackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //start MoreInfo
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_information, menu);
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
