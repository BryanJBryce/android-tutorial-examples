package com.example.fragmentdemo2;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    int imageId;
    String name;

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("name")) {
            name = getArguments().getString("name");
        }
        if (getArguments().containsKey("imageId")) {
            imageId = getArguments().getInt("imageId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_details, container, false);
        TextView textView = (TextView)
                rootView.findViewById(R.id.text1);
        textView.setText(name);

        ImageView imageView = (ImageView) rootView.findViewById(
                R.id.imageView1);
        imageView.setScaleType(ScaleType.FIT_XY); //stretch image 
        imageView.setImageResource(imageId);
        return rootView;
    }
}