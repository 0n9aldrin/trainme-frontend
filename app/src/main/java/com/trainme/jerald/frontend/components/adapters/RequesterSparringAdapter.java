package com.trainme.jerald.frontend.components.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.dependencies.response.model.RequesterSparring;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RequesterSparringAdapter extends RecyclerView.Adapter<RequesterSparringAdapter.MyViewHolder> {
    private List<RequesterSparring> listData;
    private LayoutInflater layoutInflater;
    Context mContext;
    private OnClickListener onClickItem;
    public RequesterSparringAdapter(Context context, List<RequesterSparring> listData, OnClickListener onClickItem) {
        mContext = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.onClickItem = onClickItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_requester,
                parent, false);
        return new RequesterSparringAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RequesterSparring data = listData.get(position);
        holder.name.setText(data.getName());
        holder.phoneNumber.setText("Phone Number: "+data.getPhoneNumber());
        holder.status.setText("Status: "+data.getStatus());
        holder.notes.setText("Notes: "+data.getNotes());
        holder.level.setText("Level: "+ data.getLevel());
        holder.gender.setText("Gender: "+ data.getGender());
        holder.age.setText("Age: "+ this.getAge(data.getBirthdate())+" years old");
        holder.utr.setText("UTR: "+data.getUtr());
        holder.cardView.setOnClickListener(v -> onClickItem.OnClickItem(data));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phoneNumber;
        TextView status;
        TextView notes;
        TextView level;
        TextView gender;
        TextView age;
        TextView utr;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            name = itemView.findViewById(R.id.name);
            level = itemView.findViewById(R.id.level);
            gender = itemView.findViewById(R.id.gender);
            age = itemView.findViewById(R.id.age);
            utr = itemView.findViewById(R.id.utr);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            status = itemView.findViewById(R.id.email);
            notes = itemView.findViewById(R.id.notes);
        }
    }

    public interface OnClickListener {
        void OnClickItem(RequesterSparring data);
    }

    public int getAge(String birthdate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        int age = 0;
        try {
            dateOfBirth = sdf.parse(birthdate);
            Calendar today = Calendar.getInstance();
            Calendar birthDate = Calendar.getInstance();
            birthDate.setTime(dateOfBirth);
            if (birthDate.after(today)) {
                throw new IllegalArgumentException("You don't exist yet");
            }
            int todayYear = today.get(Calendar.YEAR);
            int birthDateYear = birthDate.get(Calendar.YEAR);
            int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
            int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
            int todayMonth = today.get(Calendar.MONTH);
            int birthDateMonth = birthDate.get(Calendar.MONTH);
            int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
            int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
            age = todayYear - birthDateYear;

            // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
            if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)){
                age--;

                // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
            } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)){
                age--;
            }
        } catch (ParseException e) {
            Log.e("Con","Convert age error");
            e.printStackTrace();
        }


        return age;
    }
}

