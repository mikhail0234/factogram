package droidlabs.factogram.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import droidlabs.factogram.R;
import droidlabs.factogram.adapter.FactListAdapter;
import droidlabs.factogram.dto.FactDTO;

/**
 * Created by Misha on 01.06.2016.
 */
public class FactListFragment extends AbstractTabFragment{
    private static final int LAYOUT = R.layout.fragment_facts;

    private List<FactDTO> data;
    FactListAdapter adapter;

    public static FactListFragment getInstance(Context context, List<FactDTO> data){

        Bundle args = new Bundle();
        FactListFragment fragment = new FactListFragment();
        fragment.setArguments(args);
        fragment.setData(data);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_image));
        //super.onCreateView(LAYOUT, )

        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyleView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new FactListAdapter(data);
        rv.setAdapter(adapter);
        return super.view;
    }

    private List<FactDTO> createMocFactListData() {
        List<FactDTO> data = new ArrayList<>();
        data.add(new FactDTO("Title 1"));
        data.add(new FactDTO("Title 2"));
        data.add(new FactDTO("Title 3"));
        data.add(new FactDTO("Title 4"));
        data.add(new FactDTO("Title 1"));
        data.add(new FactDTO("Title 2"));
        data.add(new FactDTO("Title 3"));
        data.add(new FactDTO("Title 4"));

        data.add(new FactDTO("Title 1"));
        data.add(new FactDTO("Title 2"));
        data.add(new FactDTO("Title 3"));
        data.add(new FactDTO("Title 4"));

        data.add(new FactDTO("Title 1"));
        data.add(new FactDTO("Title 2"));
        data.add(new FactDTO("Title 3"));
        data.add(new FactDTO("Title 4"));



        return data;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<FactDTO> data){
        this.data = data;
    }

    public void refreshData(List<FactDTO> data){
        this.setData(data);
        //adapter.notifyDataSetChanged();
    }






}
