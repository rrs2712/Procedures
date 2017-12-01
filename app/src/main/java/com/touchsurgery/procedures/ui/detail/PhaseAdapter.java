package com.touchsurgery.procedures.ui.detail;

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
import com.touchsurgery.procedures.data.model.Phase;
import com.touchsurgery.procedures.ui.main.ProcedureAdapter;

import java.util.List;

/**
 * Created by rrs27 on 2017-12-01.
 */

public class PhaseAdapter extends ArrayAdapter<Phase> {

    private static final String LOG_TAG = ProcedureAdapter.class.getSimpleName();
    private ImageLoader imageloader = ImageLoader.getInstance();
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param phases A List of AndroidFlavor objects to display in a list
     */
    public PhaseAdapter(Activity context, List<Phase> phases) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, phases);

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
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_phase, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Phase phase = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.phase_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(phase.getName());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.phase_icon);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
//        iconView.setImageResource(procedure.getImageResourceId());
//        iconView.setImageResource(R.drawable.download);
        imageloader.displayImage(phase.getIcon(), iconView);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
