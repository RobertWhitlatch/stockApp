package ee461l.stockapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TextViewHolder extends RecyclerView.ViewHolder {

    private TextView tv;

    public TextViewHolder(View v) {
        super(v);
        tv = v.findViewById(R.id.text_holder);
    }

    public TextView getTextView() {
        return tv;
    }

    public void setTextView(TextView tv) {
        this.tv = tv;
    }

    public void applyText(String text){
        tv.setText(text);
    }

}
