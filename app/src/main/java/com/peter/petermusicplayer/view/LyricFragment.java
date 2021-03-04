package com.peter.petermusicplayer.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.peter.petermusicplayer.R;
import com.peter.petermusicplayer.databinding.FragmentFullLyricBinding;

import static android.widget.Toast.LENGTH_SHORT;

public class LyricFragment extends Fragment {
    private FragmentFullLyricBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_full_lyric,container,false);
        View root = binding.getRoot();


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"lyric fragment back home", LENGTH_SHORT).show();
                getActivity().getFragmentManager()
                        .beginTransaction()
                        .remove(LyricFragment.this)
                        .hashCode();
                FrameLayout frameLayout = getActivity().findViewById(R.id.fullLyric);
                frameLayout.setVisibility(View.GONE);
            }
        });

        return root;
    }
}
