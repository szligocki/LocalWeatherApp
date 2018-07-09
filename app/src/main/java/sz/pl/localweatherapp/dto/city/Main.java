package sz.pl.localweatherapp.dto.city;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Main implements Parcelable{

    private double temp;
    private double pressure;
    private long humidity;
    private double temp_min;
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
