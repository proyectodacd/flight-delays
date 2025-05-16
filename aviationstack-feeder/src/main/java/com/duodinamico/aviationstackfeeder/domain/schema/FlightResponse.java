package com.duodinamico.aviationstackfeeder.domain.schema;

import java.util.List;

public class FlightResponse {
    private Pagination pagination;
    private List<FlightDescription> data;

    public FlightResponse(Pagination pagination, List<FlightDescription> data) {
        this.pagination = pagination;
        this.data = data;
    }

    public List<FlightDescription> getData() {
        return data;
    }
}
