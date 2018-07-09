package sz.pl.localweatherapp.dto.city;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Weather implements Parcelable {

    private Long id;
    private String main;
    private String description;
    private String icon;

    protected Weather(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        main = in.readString();
        description = in.readString();
        icon = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(main);
        dest.writeString(description);
        dest.writeString(icon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
}
