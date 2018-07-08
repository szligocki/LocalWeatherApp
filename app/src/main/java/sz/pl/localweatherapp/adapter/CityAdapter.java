package sz.pl.localweatherapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import sz.pl.localweatherapp.R;
import sz.pl.localweatherapp.db.City;

public class CityAdapter extends CursorAdapter {

    private LayoutInflater layoutInflater;

    public CityAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder();
        holder.cityName = view.findViewById(R.id.cityName);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.cityName.setText(cursor.getString(cursor.getColumnIndex(City.KEY_NAME)));
    }

    static class ViewHolder {
        TextView cityName;
    }
}
