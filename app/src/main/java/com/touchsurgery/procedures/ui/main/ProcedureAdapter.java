package com.touchsurgery.procedures.ui.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.touchsurgery.procedures.R;
import com.touchsurgery.procedures.data.model.Procedure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rrs27 on 2017-11-30.
 */

public class ProcedureAdapter extends ArrayAdapter<Procedure> {

    private static final String LOG_TAG = ProcedureAdapter.class.getSimpleName();
    private ImageLoader imageloader = ImageLoader.getInstance();
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param procedures A List of AndroidFlavor objects to display in a list
     */
    public ProcedureAdapter(Activity context, ArrayList<Procedure> procedures) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, procedures);
    }

    public ProcedureAdapter(Activity context, List<Procedure> procedures) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, procedures);

        imageloader.init(ImageLoaderConfiguration.createDefault(context));
/*        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.download)
                .showImageOnFail(R.drawable.download)
                .resetViewBeforeLoading(true).cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .cacheInMemory(true)
                .displayer(new FadeInBitmapDisplayer(300)).build();*/
    }

    /**
     * Provides a View for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item View.
     * @param convertView The recycled View to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing View is being reused, otherwise inflate the View
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Procedure procedure = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.procedure_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(procedure.getName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.procedure_id);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(procedure.getId());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
//        iconView.setImageResource(procedure.getImageResourceId());
//        iconView.setImageResource(R.drawable.download);
        imageloader.displayImage(procedure.getIcon(), iconView);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }



}