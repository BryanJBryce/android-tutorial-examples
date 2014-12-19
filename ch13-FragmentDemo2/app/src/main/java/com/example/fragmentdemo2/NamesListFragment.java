package com.example.fragmentdemo2;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/* we don't need fragment_names-xml anymore */
public class NamesListFragment extends ListFragment {

    final String[] names = {"Amsterdam", "Brussels", "Paris"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                names);
        setListAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        // ListView can only be accessed here, not in onCreate() 
        super.onViewCreated(view, savedInstanceState);
        ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new
                                                AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent,
                                                                            final View view, int position, long id) {
                                                        if (callback != null) {
                                                            callback.onItemSelected(names[position]);
                                                        }
                                                    }
                                                });

    }

    public interface Callback {
        public void onItemSelected(String id);
    }

    private Callback callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Callback) {
            callback = (Callback) activity;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}