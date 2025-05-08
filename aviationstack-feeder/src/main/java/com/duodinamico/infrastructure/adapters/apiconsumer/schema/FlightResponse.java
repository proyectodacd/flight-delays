package com.duodinamico.infrastructure.adapters.apiconsumer.schema;

import java.util.List;

public class FlightResponse {
    private Pagination pagination;
    private List<Flight> data;

    public FlightResponse(Pagination pagination, List<Flight> data) {
        this.pagination = pagination;
        this.data = data;
    }

    public List<Flight> getData() {
        return data;
    }
}
