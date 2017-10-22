package droidlabs.factogram.adapter;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import droidlabs.factogram.R;
import droidlabs.factogram.dto.CategoryDTO;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>{

    private List<CategoryDTO> list;

    public CategoryListAdapter(List<CategoryDTO> data) {
        this.list = data;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        CategoryDTO item = list.get(position);
        holder.name.setText(item.getName());
        holder.amount.setText(item.getAmount().toString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView name;
        TextView amount;


        public CategoryViewHolder(View itemView) {
            super(itemView);
            cardView =(CardView) itemView.findViewById(R.id.cardView);
            name = (TextView) itemView.findViewById(R.id.name);
            amount = (TextView) itemView.findViewById(R.id.amount);
        }
    }



    public void setData(List<CategoryDTO> data) {
        this.list = data;
    }
}
