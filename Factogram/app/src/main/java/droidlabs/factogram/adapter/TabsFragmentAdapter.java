package droidlabs.factogram.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import droidlabs.factogram.dto.CategoryDTO;
import droidlabs.factogram.dto.FactDTO;
import droidlabs.factogram.fragment.AbstractTabFragment;
import droidlabs.factogram.fragment.CategoryFragment;
import droidlabs.factogram.fragment.ImageFragment;
import droidlabs.factogram.fragment.VideoFragment;

public class TabsFragmentAdapter extends FragmentPagerAdapter {
    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    private List<FactDTO> data;
    private List<CategoryDTO> dataCategory;
    ImageFragment imageFragment;
    CategoryFragment categoryFragment;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.data = new ArrayList<>();
        this.dataCategory = new ArrayList<>();
        initTabMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabMap(Context context) {
        tabs = new HashMap<>();
        tabs.put(0, imageFragment = ImageFragment.getInstance(context, data));
        //tabs.put(1, VideoFragment.getInstance(context));
        tabs.put(1, categoryFragment = CategoryFragment.getInstance(context, dataCategory));
    }

    public void setData(List<FactDTO> data){
        this.data = data;
        imageFragment.refreshData(data);
    }

    public void setDataCategory(List<CategoryDTO> data){
        this.dataCategory = data;
        categoryFragment.refreshData(dataCategory);
    }

}
