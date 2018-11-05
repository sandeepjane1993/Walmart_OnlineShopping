package com.example.sande.walmart_onlineshopping.ui.accountbase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sande.walmart_onlineshopping.R;

public class TechnologiesFragment extends Fragment {

    TextView tv_technologies;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technologies,container,false);

        tv_technologies =view.findViewById(R.id.tv_technologies);

        tv_technologies.setText("Splash screen" + "\n" +
                "Material designing" + "\n" +
                "Scroll View" + "\n" +
                "Horizontal RecyclerView" + "\n" +
                "Support Fragments" + "\n" +
                "Volley" + "\n" +
                "Single SignOn" + "\n" +
                "View Flipper" + "\n" +
                "Bottom Navigation Bar" + "\n" +
                "SQLLite Database" + "\n" +
                "MVP" + "\n" +
                "Location Services" + "\n" +
                "PayPal Paymentgate" + "\n" +
                "View pager" + "\n" +
                "Kanban Board(Trello)" + "\n" +
                "Sdlc - Agile");

        return view;
    }
}
