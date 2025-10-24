package com.example.tasknotify;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        onCreateFragments(savedInstanceState, new ActionBarFragment(), R.id.fragment_action_bar);

        onCreateFragments(
                savedInstanceState,
                CardFragment.newInstance(
                        "Testing Testing Natin Gaano Kahaba kaya nya",
                        new String[]{"coding", "ongoing", "priority"},
                        "01/10/2025 :",
                        "01/11/2025"),
                R.id.fragment_card);

    }

    private void onCreateFragments(Bundle instance, Fragment fragment, int frag_id) {
        if (instance == null) {

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(frag_id, fragment);
            transaction.commit();
        }
    }
}