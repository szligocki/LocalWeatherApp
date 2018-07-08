package sz.pl.localweatherapp.dto.city;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class WeatherMapDto implements Parcelable{

    @Getter @Setter
    private Coord coord;
    @Getter @Setter
    private List<Weather> weather;
    @Getter @Setter
    private String base;
    @Getter @Setter
    private Main main;
    @Getter @Setter
    private Long visibility;
    @Getter @Setter
    private Wind wind;
    @Getter @Setter
    private Clouds clouds;
    @Getter @Setter
    private float dt;
    @Getter @Setter
    private Sys sys;
    @Getter @Setter
    private float id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Long cod;

    protected WeatherMapDto(Parcel in) {
        coord = in.readParcelable(Coord.class.getClassLoader());
        weather = in.createTypedArrayList(Weather.CREATOR);
        base = in.readString();
        main = in.readParcelable(Main.class.getClassLoader());
        if (in.readByte() == 0) {
            visibility = null;
        } else {
            visibility = in.readLong();
        }
        wind = in.readParcelable(Wind.class.getClassLoader());
        clouds = in.readParcelable(Clouds.class.getClassLoader());
        dt = in.readFloat();
        sys = in.readParcelable(Sys.class.getClassLoader());
        id = in.readFloat();
        name = in.readString();
        if (in.readByte() == 0) {
            cod = null;
        } else {
            cod = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(coord, flags);
        dest.writeTypedList(weather);
        dest.writeString(base);
        dest.writeParcelable(main, flags);
        if (visibility == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(visibility);
        }
        dest.writeParcelable(wind, flags);
        dest.writeParcelable(clouds, flags);
        dest.writeFloat(dt);
        dest.writeParcelable(sys, flags);
        dest.writeFloat(id);
        dest.writeString(name);
        if (cod == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(cod);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeatherMapDto> CREATOR = new Creator<WeatherMapDto>() {
        @Override
        public WeatherMapDto createFromParcel(Parcel in) {
            return new WeatherMapDto(in);
        }

        @Override
        public WeatherMapDto[] newArray(int size) {
            return new WeatherMapDto[size];
        }
    };
}
