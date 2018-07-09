package sz.pl.localweatherapp.dto.city;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Coord implements Parcelable {

    private float lon;
    private float lat;

    protected Coord(Parcel in) {
        lon = in.readFloat();
        lat = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(lon);
        dest.writeFloat(lat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Coord> CREATOR = new Creator<Coord>() {
        @Override
        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        @Override
        public Coord[] newArray(int size) {
            return new Coord[size];
        }
    };
}
