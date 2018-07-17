package com.example.cjcucsie.todolist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.RoomDatabase;

import java.util.List;

import static com.example.cjcucsie.todolist.Expense.TABLE_EXPENSE;

@Entity(tableName = TABLE_EXPENSE)
public class Expense {
    public static final String TABLE_EXPENSE = "expense";

    public Expense()
    {

    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "list")
    public String list;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id=id;
    }

    public String getList()
    {
        return list;
    }

    public void setList(String list)
    {
        this.list=list;
    }
}