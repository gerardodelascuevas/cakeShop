package com.example.demo.utils;

import com.example.demo.application.dto.IExportable;
import com.example.demo.application.dto.StreamOutput;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import com.opencsv.CSVWriter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

/**
 * @author Gerardo De Las Cuevas
 */
public class CSVStreamingOutput <DTO extends IExportable> implements StreamingResponseBody {
    private final List<DTO> exportableData;

    public CSVStreamingOutput(List<DTO> exportableData) {
        this.exportableData = exportableData;
    }

    @Override
    public void writeTo(OutputStream output) {
        try (OutputStreamWriter stream = new OutputStreamWriter(output);
             BufferedWriter bufferedWriter = new BufferedWriter(stream);
             CSVWriter csvWriter = new CSVWriter(bufferedWriter)) {
            csvWriter.writeNext(exportableData.getFirst().createHeader().toArray(new String[0]), false);
            for (DTO data : exportableData) {
                csvWriter.writeNext(data.createRow().toArray(new String[0]), false);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
