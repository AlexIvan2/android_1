package alex.com.android_1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GridViewAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private List data;

    public GridViewAdapter(Context context, int layoutResourceId, List data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.car_item, null, false);
        }

        CatalogViewHolder catalogViewHolder = (CatalogViewHolder) convertView.getTag();
        if (catalogViewHolder == null) {
            catalogViewHolder = new CatalogViewHolder(convertView);

            convertView.setTag(catalogViewHolder);
        }

        CarObject carObject = (CarObject) data.get(position);
        PhotoItem photoItem = (PhotoItem) data.get(position);
        Picasso.get().load(photoItem.getImgUrl()).into(catalogViewHolder.itemViewImage);
//        .setImageDrawable(carObject.getImage());
//        catalogViewHolder.itemViewName.setText(carObject.getName());
        return convertView;
    }
}
