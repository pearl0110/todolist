package com.example.cjcucsie.todolist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Expense> mData;
    ExpenseDao dao;
    MyAdapter(List<Expense> data,ExpenseDao dao)
    {
        mData=data;
        this.dao=dao;
    }

    //建立ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder
    {
        //宣告元件
        private TextView txtItem;
        private Button btnRemove;


        List<Expense> mData = dao.getAllUsers();

        ViewHolder (View itemView)
        {
            super(itemView);
            txtItem = (TextView) itemView.findViewById(R.id.txtItem);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);

            //移除項目，getAdapterPosition為點擊的項目位置
            btnRemove.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){

                    Expense expense=mData.get(getAdapterPosition());
                    removeItem(getAdapterPosition());
                    dao.delete(expense);
                }
            });
        }
    }

    @Override //連結項目布局檔List_item
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override //設置TxtItem要顯示內容
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.txtItem.setText(mData.get(position).getList());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //為了顯示效果，固定新增在位置1。若要新增在其他地方可以更改數字
    public void addItem(Expense text){
        mData.add(0,text);
        notifyItemInserted(1);
    }

    //刪除項目
    public void removeItem(int position){
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
