package com.chakaseptember.forecast.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chakaseptember.forecast.R;
import com.chakaseptember.forecast.data.response.ForecastResponse;
import com.chakaseptember.forecast.data.response.List;

/**
 * Created by Chaka on 21/03/2017.
 */

public class ForecastAdapter  extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    ForecastResponse forecastResponse;
    Context context;

    public ForecastAdapter(Context context, ForecastResponse forecastResponse) {
        this.forecastResponse = forecastResponse;
        this.context = context;
    }

    public void setForecastResponse(ForecastResponse forecastResponse){
        this.forecastResponse = forecastResponse;
        notifyDataSetChanged();
    }

    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List list = forecastResponse.getList().get(position);
        holder.weather.setText(list.getWeather().get(0).getDescription());
        holder.temp.setText(String.format(context.getString(R.string.temperature),+list.getMain().getTemp()));
        holder.time.setText(list.getDtString());


    }

    @Override
    public int getItemCount() {
        if(forecastResponse!=null)
        return forecastResponse.getList().size();

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView weather;
        public TextView temp;
        public TextView time;

        public ViewHolder(View v) {
            super(v);
            weather = (TextView) v.findViewById(R.id.item_forecast_weather_tv);
            temp = (TextView) v.findViewById(R.id.item_forecast_temp_tv);
            time = (TextView) v.findViewById(R.id.item_forecast_time_tv);
        }
    }
}
