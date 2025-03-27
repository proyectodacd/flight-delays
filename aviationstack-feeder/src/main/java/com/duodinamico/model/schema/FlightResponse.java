package com.duodinamico.model.schema;
import com.duodinamico.model.Flight;

import java.util.List;

public class FlightResponse {
    private Pagination pagination;
    private List<Flight> data;

    public FlightResponse(Pagination pagination, List<Flight> data) {
        this.pagination = pagination;
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public List<Flight> getData() {
        return data;
    }
}
