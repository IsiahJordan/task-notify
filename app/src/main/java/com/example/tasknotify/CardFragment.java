package com.example.tasknotify;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Objects;

public class CardFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_CHIP = "categories";
    private static final String ARG_DATE1 = "startDate",  ARG_DATE2 = "endDate";

    private TextView textTitle, textRange;
    private LinearLayout category_container;

    private static String title_arg, start_date_arg, end_date_arg;
    private static String[] categories_arg;


    public CardFragment() {

    }

    public static CardFragment newInstance(String title, String[] categories, String start_date, String end_date) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putStringArray(ARG_CHIP, categories);
        args.putString(ARG_DATE1, start_date);
        args.putString(ARG_DATE2, end_date);
        fragment.setArguments(args);
        return fragment;
    }

    private void replaceText(TextView view, String... texts) {
        if (texts != null) {
            String replace = "";

            int max_length = 20; // this is to monitor text size
            int curr_length = 0;

            for (String text : texts) {
                if (curr_length + text.length() >= max_length) {
                    replace = replace.concat(text.substring(0, max_length - curr_length));

                    // this to add suffix expression
                    replace = replace.substring(0, replace.length() - 3);
                    replace = replace.concat("...");
                    break;
                }

                replace = replace.concat(text);
                replace = replace.concat(" ");
                curr_length = replace.length();
            }

            view.setText(replace);
        }
    }

    private void createChips(String[] chips, LinearLayout container) {
        if (chips != null) {
            for (String chip : chips) {
                TextView view = new TextView(requireActivity().getApplicationContext());

                view.setId(View.generateViewId());
                view.setText(chip);
                view.setTextSize(12);
                view.setTextColor(ContextCompat.getColor(requireActivity(), R.color.text));
                view.setBackgroundResource(R.drawable.tag_background);
                view.setPadding(16, 0, 16, 0);
                view.setGravity(Gravity.CENTER_VERTICAL);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    );

                params.setMarginEnd(16);

                view.setLayoutParams(params);
                container.addView(view);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            title_arg = getArguments().getString(ARG_TITLE);
            categories_arg = getArguments().getStringArray(ARG_CHIP);
            start_date_arg = getArguments().getString(ARG_DATE1);
            end_date_arg = getArguments().getString(ARG_DATE2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textTitle = (TextView) view.findViewById(R.id.card_title);
        textRange = (TextView) view.findViewById(R.id.card_range);
        category_container = (LinearLayout) view.findViewById(R.id.card_chips);

        replaceText(textTitle, title_arg);
        replaceText(textRange, start_date_arg, end_date_arg);
        createChips(categories_arg, category_container);
    }
}