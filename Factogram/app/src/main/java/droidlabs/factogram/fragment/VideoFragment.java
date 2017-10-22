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

public class VideoFragment extends AbstractTabFragment{
    private static final int LAYOUT = R.layout.fragment_video;

    public static VideoFragment getInstance(Context context){

        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_video));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rw = (RecyclerView) view.findViewById(R.id.recyleView);
        rw.setLayoutManager(new LinearLayoutManager(context));
        rw.setAdapter(new FactListAdapter(createMocFactListData()));
        return view;
    }

    private List<FactDTO> createMocFactListData() {
        List<FactDTO> data = new ArrayList<>();
        data.add(new FactDTO("Title 1"));
        data.add(new FactDTO("Title 2"));
        data.add(new FactDTO("Title 3"));
        data.add(new FactDTO("Title 4"));


        return data;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
