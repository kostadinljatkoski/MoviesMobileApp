package mpip.finki.ukim.mpip_lab2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mpip.finki.ukim.mpip_lab2.MovieDetailsActivity;
import mpip.finki.ukim.mpip_lab2.R;
import mpip.finki.ukim.mpip_lab2.models.OmdbItem;
import mpip.finki.ukim.mpip_lab2.viewholders.OmdbItemViewHolder;

import java.util.List;

public class OmdbListAdapter extends RecyclerView.Adapter<OmdbItemViewHolder> {

    private List<OmdbItem> data;
    private Context context;

    public OmdbListAdapter(Context context, List<OmdbItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public OmdbItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.omdb_item, parent, false);
        return new OmdbItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OmdbItemViewHolder holder, int position) {
        final OmdbItem entity = data.get(position);
        holder.bind(entity);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("imdbID", entity.imdbID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<OmdbItem> newData) {
        data = newData;
        notifyDataSetChanged();
    }
}
