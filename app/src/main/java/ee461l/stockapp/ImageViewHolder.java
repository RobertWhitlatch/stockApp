package ee461l.stockapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageViewHolder extends RecyclerView.ViewHolder {

    private ImageView iv;
    private ViewGroup parent;


    public ImageViewHolder(View v, ViewGroup parent) {
        super(v);
        this.iv = v.findViewById(R.id.image_holder);
        this.parent = parent;
    }

    public ImageView getImageView() {
        return iv;
    }

    public void setImageView(ImageView iv) {
        this.iv = iv;
    }

    public void applyImage(String url){
        Picasso.with(parent.getContext()).load(url).into(iv);
    }

}
