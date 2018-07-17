package com.example.cjcucsie.todolist;

import android.app.AlertDialog;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView rv;
    private MyAdapter adapter;
    //private ArrayList<Expense> mData = new ArrayList<>();
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //準備資料，塞50個項目到ArrayList裡
        for (int i=0;i<2;i++)
        {
            Expense expense = new Expense("項目"+i);
            mData.add(expense);
        }
        */
        final ExpenseDatabase db = Room.databaseBuilder(getApplicationContext()
                ,ExpenseDatabase.class,"prodaction").allowMainThreadQueries().build();
        List<Expense> mData = db.expenseDao().getAllUsers();
        ExpenseDao dao =(ExpenseDao) db.expenseDao();

        //連結元件
        rv=(RecyclerView)findViewById(R.id.recycler_view);
        btnAdd=(Button)findViewById(R.id.btnAdd);

        //設置RecyclerView為列表型態
        rv.setLayoutManager(new LinearLayoutManager(this));

        //設置路線
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        //將資料交給adapter
        adapter=new MyAdapter(mData,dao);

        //設置adapter給recycler_view
        rv.setAdapter(adapter);

        /*
        //新增一個項目
        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                adapter.addItem("New Item");
            }
        })
        */

        //增加輸入項目
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override //把View塞進去AlertDialog就會看到顯示，輸入完成後，按下確定就可以新增一個項目並對應處理
            public void onClick(View view) {

                //呈現alertdialog的畫面，裡面的輸入框讓使用者能夠填入，接著透過 LayoutInflater 來產生這個 View 的物件
                final View item = LayoutInflater.from(MainActivity.this).inflate(R.layout.alertdialog,null);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("輸入資料")
                        .setView(item)
                        .setPositiveButton("輸入", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                EditText editText = (EditText)item.findViewById(R.id.edittext1);
                                Expense expense=new Expense();

                                //因為EditString是一串字串，要把字串變成一個資料夾(資料夾在Expense)
                                //之後丟入mData(在MyAdapter，而mDataList)
                                expense.list=editText.getText().toString();
                                adapter.addItem(expense); //是一個印出expense

                                db.expenseDao().insert(expense);
                                startActivity(new Intent(MainActivity.this,MainActivity.class));
                            }
                        })
                        .show(); //呈現對話視窗
            }
        });

    }

}