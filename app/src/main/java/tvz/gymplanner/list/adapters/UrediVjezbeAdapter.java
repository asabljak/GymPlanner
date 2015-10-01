package tvz.gymplanner.list.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import tvz.gymplanner.exerciseManage.UpdateVjezbaActivity;
import tvz.gymplanner.R;

/**
 * Created by Antun on 29.5.2015..
 */
public class UrediVjezbeAdapter extends ListAdapter {

    public UrediVjezbeAdapter(Context con) {
        super(con, R.drawable.update);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_item, null);
        text=(TextView)v.findViewById(R.id.naziv_vjezbe);
        text.setText(allExercises.get(position).getName());
        image=(ImageView)v.findViewById(R.id.listItemIcon);
        image.setImageResource(imageId);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateVjezbaActivity.class);
                String item = getText(position);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.putExtra("item", item);
                context.startActivity(i);
            }
        });
        final CheckBox cb = (CheckBox)v.findViewById(R.id.checkbox_vjezbe);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb.isChecked()) {
                    selectedExercises.add(allExercises.get(position));
                    checked++;
                }
                else {
                    selectedExercises.remove(allExercises.get(position));
                    checked--;
                }
            }
        });

        return v;
    }
}
