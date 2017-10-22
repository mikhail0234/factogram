package droidlabs.factogram.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import droidlabs.factogram.CategoryActivity;
import droidlabs.factogram.MainActivity;
import droidlabs.factogram.R;
import droidlabs.factogram.adapter.CategoryListAdapter;
import droidlabs.factogram.dto.CategoryDTO;




public class CategoryFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_category;

    private List<CategoryDTO> data;
    CategoryListAdapter adapter;
    CardView cardView;

    public static CategoryFragment getInstance(Context context, List<CategoryDTO> data){

        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        fragment.setData(data);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_category));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        cardView = (CardView) view.findViewById(R.id.cardView);

        /*cardView.setOnClickListener(new View.OnClickListener() {

            public Context context;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(this.context, CategoryActivity.class);
                startActivity(intent);
            }
        });
        */

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyleView);
        rv.setLayoutManager(new LinearLayoutManager(context));



        adapter = new CategoryListAdapter(createMocFactListData());
        rv.setAdapter(adapter);
        return view;
    }





    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<CategoryDTO> data){
        this.data = data;
    }

    public void refreshData(List<CategoryDTO> data){
        this.setData(data);
        //adapter.notifyDataSetChanged();
    }

    private List<CategoryDTO> createMocFactListData() {
        List<CategoryDTO> data = new ArrayList<>();
        data.add(new CategoryDTO("Статистика", 5));
        data.add(new CategoryDTO("Человек", 4));
        data.add(new CategoryDTO("Отношения", 3));
        data.add(new CategoryDTO("Страны", 3));
        data.add(new CategoryDTO("Автомобили", 1));
        data.add(new CategoryDTO("Праздники", 1));
        data.add(new CategoryDTO("Природа", 1));
        data.add(new CategoryDTO("Спорт", 1));
        data.add(new CategoryDTO("Топ", 1));

        data.add(new CategoryDTO("Еда", 0));
        data.add(new CategoryDTO("Животные", 0));
        data.add(new CategoryDTO("Здоровье", 0));
        data.add(new CategoryDTO("Знаменитости", 0));
        data.add(new CategoryDTO("Игры", 0));
        data.add(new CategoryDTO("Интересное", 0));
        data.add(new CategoryDTO("Интернет", 0));
        data.add(new CategoryDTO("Лайфхаки", 0));
        data.add(new CategoryDTO("Мировые рекорды", 0));
        data.add(new CategoryDTO("Наука", 0));
        data.add(new CategoryDTO("Путешествия", 0));
        data.add(new CategoryDTO("Разное", 0));
        data.add(new CategoryDTO("Смешное", 0));
        data.add(new CategoryDTO("Странное", 0));
        data.add(new CategoryDTO("Техника", 0));

        return data;
    }






}

