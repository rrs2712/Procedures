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

import java.util.List;

/**
 *
 * Class extending {@link ArrayAdapter<Phase>} to create a custom ListView.
 *
 * @author Raul RS
 * @version 1.0
 */
public class PhaseAdapter extends ArrayAdapter<Phase> {

    // Async image loader
    private ImageLoader imageloader = ImageLoader.getInstance();

    /**
     * Class constructor.
     *
     * @param context - {@link Activity}
     * @param phases - {@link List<Phase>}
     */
    public PhaseAdapter(Activity context, List<Phase> phases) {
        super(context, 0, phases);
        imageloader.init(ImageLoaderConfiguration.createDefault(context));
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
                    R.layout.list_phase, parent, false);
        }

        Phase phase = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.phase_name);
        nameTextView.setText(phase.getName());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.phase_icon);
        imageloader.displayImage(phase.getIcon(), iconView);

        return listItemView;
    }
}
