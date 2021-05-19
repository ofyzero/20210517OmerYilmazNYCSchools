package com.example.a20210517_omeryilmaz_nycschools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder> {

    ArrayList<String> name;
    ArrayList<String> math;
    ArrayList<String> reading;
    ArrayList<String> writing;

    Context context;


    public NameAdapter(Context ct, ArrayList<String> name , ArrayList<String> math , ArrayList<String> reading ,ArrayList<String> writing){
        this.context = ct;
        this.name = name;
        this.math = math;
        this.reading = reading;
        this.writing = writing;


    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_cell,parent,false);

        return new NameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NameAdapter.NameViewHolder holder, int position) {
        holder.text.setText(name.get(position));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details.class);

                // check for school has SAT details
                if (math.get(position) != "-1") {


                    intent.putExtra("math", math.get(position));
                    intent.putExtra("reading", reading.get(position));
                    intent.putExtra("writing", writing.get(position));
                }

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder{

        TextView text;
        ConstraintLayout mainLayout ;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.textView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
