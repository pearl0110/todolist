package com.example.cjcucsie.todolist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao{

    //讀取全部的資料
    @Query(" select * from " + Expense.TABLE_EXPENSE)
    List<Expense> getAllUsers();

    /*@Query(" select * from " + Expense.TABLE_EXPENSE + " where price >= :price")
    public List<Expense> queryByPrice(int price);*/

    //加入資料
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Expense expense);

    @Delete()
    void delete(Expense expense);
}
