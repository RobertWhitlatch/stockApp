package ee461l.stockapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

class FavoritesViewHolder extends RecyclerView.ViewHolder {

    private TextView tv;
    private ImageView iv;
    @SuppressWarnings("CanBeFinal")
    private ViewGroup parent;

    public FavoritesViewHolder(View v, ViewGroup parent){
        super(v);
        tv = v.findViewById(R.id.fav_symbol);
        iv = v.findViewById(R.id.fav_logo);
        this.parent = parent;
    }

    public void applyContent(String symbol, String logo){
        tv.setText(symbol);
        Picasso.with(parent.getContext()).load(logo).into(iv);
    }

    public TextView getTextView() {
        return tv;
    }

    public void setTextView(TextView tv) {
        this.tv = tv;
    }

    public ImageView getImageView() {
        return iv;
    }

    public void setImageView(ImageView iv) {
        this.iv = iv;
    }
}
