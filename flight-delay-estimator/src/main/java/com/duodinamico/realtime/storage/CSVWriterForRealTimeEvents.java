package com.duodinamico.realtime.storage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriterForRealTimeEvents {

    public void saveToDatamartPartition(String filepath, String json) {
        File archivo = new File(filepath);
        boolean existe = archivo.exists();
        try (FileWriter fw = new FileWriter(archivo, true)) {
            if (!existe) fw.write("referenceDate,json\n");
            String id = getDateID(json);
            String jsonEscapado = "\"" + json.replace("\"", "\"\"") + "\"";
            fw.write(id + "," + jsonEscapado + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDateID(String json){
        JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
        String ts = jsonObj.has("ts") ? jsonObj.get("ts").getAsString() : "";
        String dateId = ts.length() >= 10 ? ts.substring(0, 10).replace("-", "") : "";
        return dateId;
    }
}
