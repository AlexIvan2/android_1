package alex.com.android_1.catalog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import alex.com.android_1.catalog.CatalogViewHolder;
import alex.com.android_1.interfaces.PhotoItem;

public class ViewAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private List data;

    public ViewAdapter(Context context, int layoutResourceId, List data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public List getData() {
        return data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, null, false);
        }

        CatalogViewHolder catalogViewHolder = (CatalogViewHolder) convertView.getTag();
        if (catalogViewHolder == null) {
            catalogViewHolder = new CatalogViewHolder(convertView);

            convertView.setTag(catalogViewHolder);
        }

        PhotoItem photoItem = (PhotoItem) data.get(position);
//        Picasso.get().load(photoItem.getImgUrl()).into(catalogViewHolder.itemViewImage);
        Glide.with(convertView).load(photoItem.getImgUrl()).into(catalogViewHolder.itemViewImage);
        catalogViewHolder.itemViewName.setText(photoItem.getUserName());
        catalogViewHolder.itemViewLocation.setText(photoItem.getLocation());
//        .setImageDrawable(carObject.getImage());
//        catalogViewHolder.itemViewName.setText(carObject.getName());
        return convertView;
    }
}
