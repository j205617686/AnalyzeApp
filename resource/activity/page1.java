package com.example.changetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ryg.dynamicload.DLBasePluginActivity;
import com.ryg.dynamicload.DLBasePluginFragmentActivity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class page1 extends Activity implements View.OnClickListener {

    Button button;
    EditText e2;
    Button btnplay;
    Button btclose;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnplay = (Button) findViewById(R.id.btplay);
        btclose = (Button) findViewById(R.id.btclose);
        button = (Button) findViewById(R.id.button1);
        e2 = (EditText) findViewById(R.id.editText1);



        btnplay.setOnClickListener(this);
        btclose.setOnClickListener(this);





        Bundle bundle1 = this.getIntent().getExtras();


        Class x = page1.class;
        Constructor[] constructors = x.getDeclaredConstructors();
        Field[] fields = x.getDeclaredFields();
        Method[] methods = x.getDeclaredMethods();

        Class[] classes = x.getDeclaredClasses();

        System.out.println("Classes:");
        for (Class cls : classes) {
            System.out.println(cls);
        }


        System.out.println("Constructor:");
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println("Field:");
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("Method:");
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("----------------------------------");

        String sClassName = "android.app.Activity";
        try {
            Class classToInvestigate = Class.forName(sClassName);

            Annotation[] aAnnotations = classToInvestigate.getDeclaredAnnotations();
            for (Annotation a : aAnnotations) {
                System.out.println(a.toString());
            }

        } catch (ClassNotFoundException e) {
            // Class not found!
        } catch (Exception e) {
            // Handle unknown exception!
        }


        //page1.button = (Button) findViewById(R.id.button1);


        button.setOnClickListener(new Button.OnClickListener() {
            String c = "";

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putString("page1", "My name is page1");

                //將Bundle物件assign給intent
                intent.putExtras(bundle);
                intent.setAction("myapp.action.test1");
                intent.addCategory("cate1");
                //intent.addCategory("cate2");

                startActivity(intent);
                page1.this.finish();


            }
        });


        try {
            System.out.println("============================================");
            Class x1 = Class.forName("com.example.changetest.page1$1");

            Constructor[] constructors1 = x1.getDeclaredConstructors();
            Field[] fields1 = x1.getDeclaredFields();
            Method[] methods1 = x1.getDeclaredMethods();
            Class[] classes1 = x1.getDeclaredClasses();

            System.out.println("Classes:");
            for (Class cls : classes1) {
                System.out.println(cls);
            }

            System.out.println("Constructor:");
            for (Constructor constructor : constructors1) {
                System.out.println(constructor);
            }
            System.out.println("Field:");
            for (Field field : fields1) {
                System.out.println(field);
            }
            System.out.println("Method:");
            for (Method method : methods1) {
                System.out.println(method);
            }


            System.out.println();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(getApplicationContext(), MusicService.class);
        switch (v.getId()) {
            case R.id.btplay:
                startService(intent);
                break;
            case R.id.btclose:
                stopService(intent);
        }


    }









}
