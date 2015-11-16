package com.easein;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ricardo on 19/11/2015.
 */
public class EspacioAccesibleAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<EspacioAccesible> items;

    public EspacioAccesibleAdapter(Activity activity, ArrayList<EspacioAccesible> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getPosicion();
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View vi = contentView;

        if (contentView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.list_item_layout, null);
        }

        EspacioAccesible item = items.get(position);

        ImageView image = (ImageView) vi.findViewById(R.id.imagen);
        int imageResource = activity.getResources().getIdentifier(item.getImagen(), null, activity.getPackageName());
        image.setImageDrawable(activity.getResources().getDrawable(imageResource));

        TextView nombre = (TextView) vi.findViewById(R.id.espacioAccesible);
        nombre.setText(item.getTipoEspacio());

        TextView tipo = (TextView) vi.findViewById(R.id.descripcion);
        tipo.setText(item.getDescripcion());

        return vi;
    }
}
