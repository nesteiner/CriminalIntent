package com.example.criminalintent.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.criminalintent.R;
import com.example.criminalintent.activity.CrimeActivity;
import com.example.criminalintent.activity.CrimeListActivity;
import com.example.criminalintent.model.Crime;
import com.example.criminalintent.util.CrimeSelectedCallback;
import lombok.NonNull;

public class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    Crime crime;
    TextView titleTextView;
    TextView dateTextView;
    ImageView solvedImageView;
    CrimeSelectedCallback callback;
    public CrimeViewHolder(@NonNull View itemView, CrimeSelectedCallback callback) {
        super(itemView);
        this.callback = callback;
        itemView.setOnClickListener(this::onClick);
        titleTextView = itemView.findViewById(R.id.crime_title);
        dateTextView = itemView.findViewById(R.id.crime_date);
        solvedImageView = itemView.findViewById(R.id.crime_solved);
    }

    public void bind(Crime crime) {
        this.crime = crime;
        titleTextView.setText(crime.getTitle());
        dateTextView.setText(crime.getDate().toString());

        if(crime.isSolved()) {
            solvedImageView.setVisibility(View.GONE);
        } else {
            solvedImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        callback.onCrimeSelected(crime.getId());
//        Intent intent = CrimeActivity.newIntent(itemView.getContext(), crime.getId());
//        itemView.getContext().startActivity(intent);
    }
}
