package droidlabs.factogram.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class ImageFragment extends AbstractTabFragment{
    private static final int LAYOUT = R.layout.fragment_image;

    private List<FactDTO> data;
    FactListAdapter adapter;

    public static ImageFragment getInstance(Context context, List<FactDTO> data){

        Bundle args = new Bundle();
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        fragment.setData(data);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_image));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyleView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new FactListAdapter(data);
        rv.setAdapter(adapter);
        return view;
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
