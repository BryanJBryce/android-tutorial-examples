package com.example.fragmentdemo2;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity
        implements NamesListFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public void onItemSelected(String value) {

        Bundle arguments = new Bundle();
        arguments.putString("name", value);
        if (value.equals("Amsterdam")) {
            arguments.putInt("imageId", R.drawable.amsterdam);
        } else if (value.equals("Brussels")) {
            arguments.putInt("imageId", R.drawable.brussels);
        } else if (value.equals("Paris")) {
            arguments.putInt("imageId", R.drawable.paris);
        }
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(arguments);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                R.id.details_container, fragment);
        fragmentTransaction.commit();
    }
}