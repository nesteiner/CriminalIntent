package com.example.criminalintent.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.criminalintent.R;
import com.example.criminalintent.model.Crime;
import lombok.NonNull;

public class CrimePoliceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView titleTextView;
    TextView dateTextView;
    Button policeButton;
    ImageView solvedImageView;
    Crime crime;

    @Override
    public void onClick(View view) {
        Toast.makeText(itemView.getContext(), crime.getTitle() + "被点击!", Toast.LENGTH_LONG).show();
    }

    public CrimePoliceHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this::onClick);

        titleTextView = itemView.findViewById(R.id.crime_title);
        dateTextView = itemView.findViewById(R.id.crime_date);
        policeButton = itemView.findViewById(R.id.require_police);
        solvedImageView = itemView.findViewById(R.id.crime_solved);
        policeButton.setOnClickListener(view -> {
            Toast.makeText(itemView.getContext(), "该违纪已发给警察去处理!",
                    Toast.LENGTH_SHORT).show();
        });
    }

    public void bind(Crime crime) {
        this.crime = crime;
        titleTextView.setText(crime.getTitle());
        dateTextView.setText(crime.getDate().toString());
        if(crime.isSolved()) {
            policeButton.setEnabled(false);
            solvedImageView.setVisibility(View.INVISIBLE);
        } else {
            policeButton.setEnabled(true);
            solvedImageView.setVisibility(View.VISIBLE);
        }
    }
}
