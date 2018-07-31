package com.cringe.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Events");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ModelClass, ReViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelClass, ReViewHolder>(
                ModelClass.class,
                R.layout.design_row,
                ReViewHolder.class,
                ref) {

            protected void populateViewHolder(ReViewHolder viewHolder, ModelClass model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(), model.getImage());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ReViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ReViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/vitieeemtts/"));
                    Intent browserChooserIntent = Intent.createChooser(browserIntent, "Choose a browser of your choice");
                    v.getContext().startActivity(browserChooserIntent);
                }
            });
        }


        public void setName(String name) {
            TextView post_title = mView.findViewById(R.id.titleText);
            post_title.setText(name);
        }

        public void setDesc(String desc) {
            TextView post_desc = mView.findViewById(R.id.titleDesc);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = mView.findViewById(R.id.imageView);
            Picasso.with(ctx).load(image).into(post_image);
        }

    }
}
