package com.example.criminalintent.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.criminalintent.R;
import com.example.criminalintent.model.Crime;
import lombok.NonNull;

public class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    Crime crime;
    TextView titleTextView;
    TextView dateTextView;

    public CrimeViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.crime_title);
        dateTextView = itemView.findViewById(R.id.crime_date);
    }

    public void bind(Crime crime) {
        this.crime = crime;
        titleTextView.setText(crime.getTitle());
        dateTextView.setText(crime.getDate().toString());
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), crime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
    }
}
