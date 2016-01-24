package com.example.changetest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class page2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = (Button) findViewById(R.id.button2);
        EditText e2=(EditText)findViewById(R.id.editText2);

        Bundle bundle1 =this.getIntent().getExtras();

  e2.setText(bundle1.getString("page1"));

//        e2.setText(getIntent().getData().toString());
        button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent();

                Bundle bundle = new Bundle();

                bundle.putString("page1","My name is page1");

                //將Bundle物件assign給intent
                intent.putExtras(bundle);




                intent.setClass(page2.this,page3.class);
                startActivity(intent);
                page2.this.finish();



            }
        });


    }


}
