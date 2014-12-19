package com.example.fragmentdemo1;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details,
                container, false);
    }

    public void showDetails(String name) {
        TextView textView = (TextView)
                getView().findViewById(R.id.text1);
        textView.setText(name);

        ImageView imageView = (ImageView) getView().findViewById(
                R.id.imageView1);
        imageView.setScaleType(ScaleType.FIT_XY); // stretch image 
        if (name.equals("Amsterdam")) {
            imageView.setImageResource(R.drawable.amsterdam);
        } else if (name.equals("Brussels")) {
            imageView.setImageResource(R.drawable.brussels);
        } else if (name.equals("Paris")) {
            imageView.setImageResource(R.drawable.paris);
        }
    }
}