package sz.pl.localweatherapp.dto.city;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import lombok.Data;
import sz.pl.localweatherapp.R;

@Data
public class WeatherMapDto implements Parcelable {

    private Coord coord;
    private ArrayList<Weather> weather;
    private String base;
    private Main main;
    private Long visibility;
    private Wind wind;
    private Clouds clouds;
    private float dt;
    private Sys sys;
    private float id;
    private String name;
    private Long cod;

    private WeatherMapDto(Parcel in) {
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

    public Weather getWeather() {
        if (weather != null && !weather.isEmpty())
            return weather.get(0);
        return null;
    }

    public int getIcon() {
        switch (getWeather().getIcon()) {
            case "01d":
                return R.drawable.ic_01d;
            case "01n":
                return R.drawable.ic_01n;
            case "02d":
                return R.drawable.ic_02d;
            case "02n":
                return R.drawable.ic_02n;
            case "03d":
                return R.drawable.ic_03d;
            case "03n":
                return R.drawable.ic_03n;
            case "04d":
                return R.drawable.ic_4d;
            case "04n":
                return R.drawable.ic_4n;
            case "09d":
                return R.drawable.ic_9d;
            case "09n":
                return R.drawable.ic_9n;
            case "10d":
                return R.drawable.ic_10d;
            case "10n":
                return R.drawable.ic_10n;
            case "11d":
                return R.drawable.ic_11d;
            case "11n":
                return R.drawable.ic_11n;
            case "13d":
                return R.drawable.ic_13d;
            case "13n":
                return R.drawable.ic_13n;
            case "50d":
                return R.drawable.ic_50d;
            case "50n":
                return R.drawable.ic_50n;
            default:
                return R.drawable.ic_01d;
        }
    }
}
