package com.ntr.krishna.myapplication;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class second extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);
        }

    public void mani(View V)
    {

        DatabaseReference apm;
        apm=FirebaseDatabase.getInstance().getReference().child("Airpollution");
        apm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                float xyz=dataSnapshot.getValue(float.class);
                updateui(xyz);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void updateui(float s)
    { String k= String.valueOf(s);
        TextView sk=(TextView) findViewById(R.id.dsp);
        sk.setText(k);
        TextView sp=(TextView) findViewById(R.id.bb);
        if(s<1000.0)
        {
            sp.setText("Fresh Air");
            sp.setTextColor(Color.parseColor("#008141"));
        }
        if((s>1000.0)&&(s<2000.0)){
            sp.setText("Poor Air");
            sp.setTextColor(Color.parseColor("#005682"));
        }
        if(s>2000.0){
            sp.setText("Worst Air");
            sp.setTextColor(Color.parseColor("#e00707"));
        }
    }
}



