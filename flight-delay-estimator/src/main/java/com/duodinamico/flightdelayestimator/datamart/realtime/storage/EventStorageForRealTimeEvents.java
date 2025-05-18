package com.duodinamico.flightdelayestimator.datamart.realtime.storage;

public class EventStorageForRealTimeEvents {

    private final String datamartPartitionForRawFlightData;
    private final String datamartPartitionForRawWeatherData;
    private final CSVWriterForRealTimeEvents csvWriterForRealTimeEvents;

    public EventStorageForRealTimeEvents(String datamartPartitionForRawFlightData, String datamartPartitionForRawWeatherData) {
        this.datamartPartitionForRawFlightData = datamartPartitionForRawFlightData;
        this.datamartPartitionForRawWeatherData = datamartPartitionForRawWeatherData;
        this.csvWriterForRealTimeEvents = new CSVWriterForRealTimeEvents();
    }

    public void saveRealTimeEventToDatamart(String json, String topic) {
        String path = topic.equals("Flights") ? this.datamartPartitionForRawFlightData : this.datamartPartitionForRawWeatherData ;
        this.csvWriterForRealTimeEvents.saveToDatamartPartition(path, json);
    }
}
