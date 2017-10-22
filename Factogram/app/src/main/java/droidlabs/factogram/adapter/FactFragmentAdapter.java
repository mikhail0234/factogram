package droidlabs.factogram.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import droidlabs.factogram.dto.CategoryDTO;
import droidlabs.factogram.dto.FactDTO;
import droidlabs.factogram.fragment.AbstractTabFragment;
import droidlabs.factogram.fragment.CategoryFragment;
import droidlabs.factogram.fragment.FactListFragment;
import droidlabs.factogram.fragment.ImageFragment;

/**
 * Created by Misha on 01.06.2016.
 */
public class FactFragmentAdapter  extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    private List<FactDTO> data;
    FactListFragment factListFragment;

    public FactFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.data = new ArrayList<>();
        //factListFragment = FactListFragment.getInstance(context, data);
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
        tabs.put(0, factListFragment = FactListFragment.getInstance(context, data));
    }

    public void setData(List<FactDTO> data){
        this.data = data;
        factListFragment.refreshData(data);
    }

}
