package sz.pl.localweatherapp.dto.city;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Sys implements Parcelable{

    private Long type;
    private Long id;
    private double message;
    private String country;
    private Long sunrise;
    private Long sunset;

    protected Sys(Parcel in) {
        if (in.readByte() == 0) {
            type = null;
        } else {
            type = in.readLong();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        message = in.readDouble();
        country = in.readString();
        if (in.readByte() == 0) {
            sunrise = null;
        } else {
            sunrise = in.readLong();
        }
        if (in.readByte() == 0) {
            sunset = null;
        } else {
            sunset = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (type == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(type);
        }
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeDouble(message);
        dest.writeString(country);
        if (sunrise == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(sunrise);
        }
        if (sunset == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(sunset);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Sys> CREATOR = new Creator<Sys>() {
        @Override
        public Sys createFromParcel(Parcel in) {
            return new Sys(in);
        }

        @Override
        public Sys[] newArray(int size) {
            return new Sys[size];
        }
    };
}
