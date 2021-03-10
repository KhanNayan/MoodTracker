package com.example.moodTracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class rvadapter extends RecyclerView.Adapter<rvadapter.myviewholder> {

    private Context mCtx;
    private List<model> quesList;
    private int next = 0;
    int limit =1;
    RecyclerView recyclerView;

    public rvadapter(Context mCtx, List<model> quesList) {
        this.mCtx = mCtx;
        this.quesList = quesList;
        recyclerView = ((Activity) this.mCtx).findViewById(R.id.rv);
    }
    rvadapter(){
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    @NonNull
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.single_view,parent,false);
        return new myviewholder(view);
    }

    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        model model = quesList.get(position);
        holder.tv_ques1.setText(model.q1);
        if (TextUtils.isEmpty(holder.tv_ques1.getText().toString()))
            holder.tv_ques1.setVisibility(View.GONE);
        else
            holder.tv_ques1.setVisibility(View.VISIBLE);

        holder.tv_ques2.setText(model.q2);
        if (TextUtils.isEmpty(holder.tv_ques2.getText().toString()))
            holder.tv_ques2.setVisibility(View.GONE);
        else
            holder.tv_ques2.setVisibility(View.VISIBLE);

        holder.tv_ques3.setText(model.q3);
        if (TextUtils.isEmpty(holder.tv_ques3.getText().toString()))
            holder.tv_ques3.setVisibility(View.GONE);
        else
            holder.tv_ques3.setVisibility(View.VISIBLE);

        holder.btn_ans1.setText(model.btn_ans1);
        if (TextUtils.isEmpty(holder.btn_ans1.getText().toString()))
           holder.btn_ans1.setVisibility(View.GONE);
        else
            holder.btn_ans1.setVisibility(View.VISIBLE);
            holder.btn_ans1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.tv_reply.setText(model.btn_ans1);
                    holder.tv_reply.setVisibility(View.VISIBLE);
                    limit += 1;
                    hideReplyButtons(holder);
                    if(position == quesList.size()-1){
                        holder.btn_sign.setVisibility(View.VISIBLE);
                    }
                }
            });


        holder.btn_ans2.setText(model.btn_ans2);
        if (TextUtils.isEmpty(holder.btn_ans2.getText().toString()))
            holder.btn_ans2.setVisibility(View.GONE);
        else
            holder.btn_ans2.setVisibility(View.VISIBLE);
            holder.btn_ans2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.tv_reply.setText(model.btn_ans2);
                    holder.tv_reply.setVisibility(View.VISIBLE);
                    hideReplyButtons(holder);
                    limit += 1;
                    if(position == quesList.size()-1){
                        holder.btn_sign.setVisibility(View.VISIBLE);
                    }
                }
            });

        holder.btn_ans3.setText(model.btn_ans3);
        if (TextUtils.isEmpty(holder.btn_ans3.getText().toString()))
            holder.btn_ans3.setVisibility(View.GONE);
        else
            holder.btn_ans3.setVisibility(View.VISIBLE);
            holder.btn_ans3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.tv_reply.setText(model.btn_ans3);
                    holder.tv_reply.setVisibility(View.VISIBLE);
                    hideReplyButtons(holder);
                    limit += 1;
                    if(position == quesList.size()-1){
                        holder.btn_sign.setVisibility(View.VISIBLE);
                    }
                }
            });

        holder.btn_ans4.setText(model.btn_ans4);
        if (TextUtils.isEmpty(holder.btn_ans4.getText().toString()))
            holder.btn_ans4.setVisibility(View.GONE);
        else
            holder.btn_ans4.setVisibility(View.VISIBLE);
            holder.btn_ans4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.tv_reply.setText(model.btn_ans4);
                    holder.tv_reply.setVisibility(View.VISIBLE);
                    hideReplyButtons(holder);
                    limit += 1;
                    if(position == quesList.size()-1){
                        holder.btn_sign.setVisibility(View.VISIBLE);
                    }
                }
            });

        holder.btn_txt.setText(model.btn_txt);
        if (TextUtils.isEmpty(holder.btn_txt.getText().toString()))
            holder.btn_txt.setVisibility(View.GONE);
        else
            holder.btn_txt.setVisibility(View.VISIBLE);

        holder.btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCtx.startActivity(new Intent(mCtx,LoginAuthActivity.class));
            }
        });

        // Automatic scrolling to bottom
        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());

    }

    private void hideReplyButtons(myviewholder holder) {
       holder.answer_layout.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        if(quesList.size()>limit) {
            return limit;
        }else{
            return quesList.size();
        }
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView tv_ques1, tv_ques2, tv_ques3, tv_reply;
        TextView btn_txt, btn_ans1, btn_ans2, btn_ans3, btn_ans4;
        Button btn_sign;
        LinearLayout answer_layout;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            tv_ques1 = itemView.findViewById(R.id.tv_ques1);
            tv_ques2 = itemView.findViewById(R.id.tv_ques2);
            tv_ques3 = itemView.findViewById(R.id.tv_ques3);
            tv_reply = itemView.findViewById(R.id.tv_reply);
            btn_txt = itemView.findViewById(R.id.btn_txt);
            btn_ans1 = itemView.findViewById(R.id.btn_ans1);
            btn_ans2 = itemView.findViewById(R.id.btn_ans2);
            btn_ans3 = itemView.findViewById(R.id.btn_ans3);
            btn_ans4 = itemView.findViewById(R.id.btn_ans4);
            btn_sign = itemView.findViewById(R.id.signIn);
            answer_layout = itemView.findViewById(R.id.answer_selection_layout);
        }
    }
}
