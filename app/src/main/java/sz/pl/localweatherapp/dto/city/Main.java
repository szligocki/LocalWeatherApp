package sz.pl.localweatherapp.dto.city;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

public class Main implements Parcelable{

    @Getter @Setter
    private double temp;
    @Getter @Setter
    private double pressure;
    @Getter @Setter
    private long humidity;
    @Getter @Setter
    private double temp_min;
    @Getter @Setter
    private double temp_max;

    protected Main(Parcel in) {
        temp = in.readDouble();
        pressure = in.readDouble();
        humidity = in.readLong();
        temp_min = in.readDouble();
        temp_max = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(temp);
        dest.writeDouble(pressure);
        dest.writeLong(humidity);
        dest.writeDouble(temp_min);
        dest.writeDouble(temp_max);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };
}
