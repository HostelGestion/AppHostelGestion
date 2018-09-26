package org.ifes.isis.tesis.applib.generic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by router on 31/12/17.
 */

public class GenericListAdapter extends ArrayAdapter<GenericItem> {
    Context context;
    int myResourceId;
    GenericItem[] listItem=null;

    public GenericListAdapter(@NonNull Context context, int resource,  GenericItem[] items) {
        super(context, resource);
        this.myResourceId=resource;
        this.context = context;
        this.listItem = items;
    }

    public View getView(int pos, View convertView, ViewGroup viewGroup)
    {
        View row = convertView;
     return null;
    }



}
