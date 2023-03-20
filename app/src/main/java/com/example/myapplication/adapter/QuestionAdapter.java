package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.QuestionDetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.QuestionModel;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{
    private Context context;
    private List<QuestionModel> questionList;

    public QuestionAdapter(Context context, List<QuestionModel> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.quizTitle.setText(questionList.get(position).getTitle());
        holder.op1.setText(questionList.get(position).getOption1());
        holder.op2.setText(questionList.get(position).getOption2());
        holder.op3.setText(questionList.get(position).getOption3());
        holder.op4.setText(questionList.get(position).getOption4());
        holder.questionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuestionDetailActivity.class);
                intent.putExtra("quizTitle", questionList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("op1", questionList.get(holder.getAdapterPosition()).getOption1());
                intent.putExtra("op2", questionList.get(holder.getAdapterPosition()).getOption2());
                intent.putExtra("op3", questionList.get(holder.getAdapterPosition()).getOption3());
                intent.putExtra("op4", questionList.get(holder.getAdapterPosition()).getOption4());
                intent.putExtra("solution", questionList.get(holder.getAdapterPosition()).getSolution());
                intent.putExtra("course", questionList.get(holder.getAdapterPosition()).getCourse());
                intent.putExtra("key", questionList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("courseId", questionList.get(holder.getAdapterPosition()).getCourseId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView quizTitle, op1, op2, op3, op4, solution;
        CardView questionCard;


        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            quizTitle = itemView.findViewById(R.id.txt_titlequiz);
            op1 = itemView.findViewById(R.id.txt_op1);
            op2 = itemView.findViewById(R.id.txt_op2);
            op3 = itemView.findViewById(R.id.txt_op3);
            op4 = itemView.findViewById(R.id.txt_op4);
            solution = itemView.findViewById(R.id.txt_solution);
            questionCard = itemView.findViewById(R.id.card_question);
        }

    }


}
