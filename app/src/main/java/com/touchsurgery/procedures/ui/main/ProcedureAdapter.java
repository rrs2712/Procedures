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

import java.util.List;

/**
 *
 * Class extending {@link ArrayAdapter<Procedure>} to create a customs ListView.
 *
 * @author Raul RS
 * @version 1.0
 */
public class ProcedureAdapter extends ArrayAdapter<Procedure> {

    // Async image loader
    private ImageLoader imageloader = ImageLoader.getInstance();

    /**
     * Class constructor.
     *
     * @param context - {@link Activity}
     * @param procedures - {@link List<Procedure>}
     */
    public ProcedureAdapter(Activity context, List<Procedure> procedures) {
        super(context, 0, procedures);
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
                    R.layout.list_item, parent, false);
        }

        Procedure procedure = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.procedure_name);
        nameTextView.setText(procedure.getName());

        TextView numberTextView = (TextView) listItemView.findViewById(R.id.procedure_id);
        numberTextView.setText(procedure.getId());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        imageloader.displayImage(procedure.getIcon(), iconView);

        return listItemView;
    }

}