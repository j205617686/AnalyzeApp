package com.example.changetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class page3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        Button button=(Button)findViewById(R.id.but1);

        button.setOnClickListener(new Button.OnClickListener() {
                                      String c = "";

                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getApplicationContext(),page1.class);
                                          Bundle bundle = new Bundle();

                                          bundle.putString("page1", "My name is page1");

                                          //將Bundle物件assign給intent
                                          intent.putExtras(bundle);

                                          //intent.addCategory("com.example.changetest");


                                          startActivity(intent);
                                          page3.this.finish();


                                      }
                                  }








        );



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);
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
