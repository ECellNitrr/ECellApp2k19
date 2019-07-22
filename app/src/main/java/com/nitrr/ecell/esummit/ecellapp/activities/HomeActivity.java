package com.nitrr.ecell.esummit.ecellapp.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.HomeRVAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.CustomHamburgerDialog;
import com.nitrr.ecell.esummit.ecellapp.misc.MySnapHelper;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.HomeRVData;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private HomeRVAdapter adapter;
    private List<HomeRVData> homeRVDataList = new ArrayList<>();

    private ImageView bgCircle1, bgCircle2, bgCircle3;

    private int distance = 0, offset;
    private float displacement = 0;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.home_recycler);
        bgCircle1 = findViewById(R.id.homebg_circle1);
        bgCircle2 = findViewById(R.id.homebg_circle2);
        bgCircle3 = findViewById(R.id.homebg_circle3);

        recyclerView = findViewById(R.id.home_recycler);
        recyclerView.hasFixedSize();
        adapter = new HomeRVAdapter(this, homeRVDataList);
        setUpRV();

        initializeList("E Summit", R.drawable.ic_esummit, this.getString(R.string.color_esummit), v -> {
            Intent intent = new Intent(HomeActivity.this, ESummitActivity.class);
            startActivity(intent);
        });

        initializeList("Events", R.drawable.ic_events, this.getString(R.string.color_events), v -> {
            Intent intent = new Intent(HomeActivity.this, EventActivity.class);
            startActivity(intent);
        });

        initializeList("BQuiz", R.drawable.ic_event_bq, this.getString(R.string.color_bquiz), v -> {
            AlertDialog dialog = null;
            dialog = Utils.showDialog(this, null, true, "Sorry for the Inconvenience",
                    "BQuiz will be online soon, Please checkout later",
                    null, null, "Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
        });

        initializeList("Sponsors", R.drawable.ic_hand_shake, this.getString(R.string.color_spons), v -> {
            Intent intent = new Intent(HomeActivity.this, SponsorsActivity.class);
            startActivity(intent);
        });

        ImageButton hamburger_button = findViewById(R.id.hamburgerButton);
        hamburger_button.setOnClickListener((View view) -> new CustomHamburgerDialog().with(HomeActivity.this).build());
        recyclerView = findViewById(R.id.home_recycler);
        recyclerView.hasFixedSize();

        setUpRV();
    }


    public void setUpRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setPadding(100, 0, 100, 0);

        SnapHelper snapHelper = new MySnapHelper();
        if (recyclerView.getOnFlingListener() == null)
            snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                distance = recyclerView.computeHorizontalScrollRange();
                offset = recyclerView.computeHorizontalScrollOffset();
                if (offset < distance / 4) {
                    displacement = (float) offset / (distance / 4);
                    setColor(147, 223, 204, 241, 140, 120, displacement);

                } else if (offset < distance / 2) {
                    displacement = (float) (offset - (distance / 4)) / (distance / 4);
                    setColor(241, 140, 120, 123, 193, 227, displacement);
                } else if (offset < (distance * 3 / 4)) {
                    displacement = (float) (offset - (distance / 2)) / (distance / 4);
                    setColor(123, 193, 227, 248, 212, 130, displacement);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    void setColor(int IR, int IG, int IB, int FR, int FG, int FB, float pos) {
        bgCircle1.setColorFilter(color(IR, IG, IB, FR, FG, FB, pos));
        bgCircle2.setColorFilter(color(IR, IG, IB, FR, FG, FB, pos));
        bgCircle3.setColorFilter(color(IR, IG, IB, FR, FG, FB, pos));
    }

    int color(int IR, int IG, int IB, int FR, int FG, int FB, float pos) {
        return Color.rgb(colorValue(IR, FR, pos), colorValue(IG, FG, pos), colorValue(IB, FB, pos));
    }

    int colorValue(int Initial, int Final, float pos) {
        return (int) (Final * pos + Initial * (1 - pos));
    }

    public void initializeList(String name, int cardImage, String color, View.OnClickListener listener) {
        HomeRVData data = new HomeRVData(name, color, cardImage, listener);
        homeRVDataList.add(data);
    }
}