package droidlabs.factogram.adapter;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import droidlabs.factogram.R;
import droidlabs.factogram.dto.FactDTO;

public class FactListAdapter extends RecyclerView.Adapter<FactListAdapter.FactViewHolder>{

    private List<FactDTO> data;

    public FactListAdapter(List<FactDTO> data) {
        this.data = data;
    }

    @Override
    public FactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fact_item, parent, false);

        return new FactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FactViewHolder holder, int position) {
        FactDTO item = data.get(position);
        holder.title.setText(item.getTitle());
        holder.text.setText(item.getText());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class FactViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView title;
        TextView text;

        public FactViewHolder(View itemView) {
            super(itemView);
            cardView =(CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.title);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public void setData(List<FactDTO> data) {
        this.data = data;
    }
}
