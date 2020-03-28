package mpip.finki.ukim.mpip_lab2.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import mpip.finki.ukim.mpip_lab2.R;
import mpip.finki.ukim.mpip_lab2.models.OmdbItem;

public class OmdbItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageViewItem;
    private TextView textViewTitle;
    private TextView textViewYear;

    public OmdbItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageViewItem = itemView.findViewById(R.id.viewHolderImageView);
        this.textViewTitle = itemView.findViewById(R.id.viewHolderTitleTextView);
        this.textViewYear = itemView.findViewById(R.id.viewHolderYearTextView);
    }

    public void bind(final OmdbItem entity) {
        Glide.with(itemView.getContext())
                .load(entity.Poster)
                .centerCrop()
                .into(imageViewItem);
        textViewTitle.setText(entity.Title);
        textViewYear.setText(entity.Year);
    }

}
