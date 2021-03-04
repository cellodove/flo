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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class LyricFragment extends Fragment {
    private FragmentFullLyricBinding binding;
    public List<String> lyricsList = new ArrayList<>();
    private List<String> lyrics;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_full_lyric,container,false);
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        if (bundle != null){
            binding.musicTitle.setText(bundle.getString("musicTitle"));
            binding.albumName.setText(bundle.getString("albumName"));
            binding.signer.setText(bundle.getString("signer"));

            lyricsList = Arrays.asList(bundle.getString("lyric").split("\n"));
            lyrics = new ArrayList<>();
            for (int i=0; i<lyricsList.size(); i++){
                lyrics.add(lyricsList.get(i).substring(11));
                System.out.println("프래그먼트가사 "+lyrics.get(i));
            }
            /*binding.lyricList.setText(lyrics.toString());*/

            System.out.println("번들보자 "+bundle.getString("musicTitle"));
            System.out.println("번들보자 "+bundle.getString("albumName"));
            System.out.println("번들보자 "+bundle.getString("signer"));
            System.out.println("번들보자 "+bundle.getString("lyric"));
        }else {
            System.out.println("bundel is null");
        }


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
