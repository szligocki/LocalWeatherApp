package sz.pl.localweatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sz.pl.localweatherapp.db.City;
import sz.pl.localweatherapp.service.NotifyDetailActivityUiElementChange;
import sz.pl.localweatherapp.service.impl.RestServiceImpl;

public class DetailActivity extends AppCompatActivity implements NotifyDetailActivityUiElementChange {

    @BindView(R.id.city_field)
    TextView cityField;
    @BindView(R.id.updated_field)
    TextView updatedField;
    @BindView(R.id.weather_icon)
    ImageView weatherIcon;
    @BindView(R.id.current_temperature_field)
    TextView currentTemperatureField;
    @BindView(R.id.details_field)
    TextView detailsField;


    /**
     * Instance of the rest management service
     */
    private RestServiceImpl restService = new RestServiceImpl();

    /**
     * The onCreate method initializes the list
     *
     * @param savedInstanceState {@link Bundle}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        restService.getWeatherInformation(getIntent().getLongExtra(City.KEY_CITY_ID, 0), this);
    }

    @Override
    public void setCityField(String cityField) {
        this.cityField.setText(cityField);
    }

    @Override
    public void setUpdateField(String updateField) {
        this.updatedField.setText(updateField);
    }

    @Override
    public void setWeatherIcon(int weatherIcon) {
        this.weatherIcon.setImageResource(weatherIcon);
    }

    @Override
    public void setCurrentTemperatureField(String currentTemperatureField) {
        this.currentTemperatureField.setText(currentTemperatureField);
    }

    @Override
    public void setDetailField(String detailsField) {
        this.detailsField.setText(detailsField);
    }
}
