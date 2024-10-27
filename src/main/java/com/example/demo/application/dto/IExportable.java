package com.example.demo.application.dto;

import java.util.List;

/**
 * @author Gerardo De Las Cuevas
 */
public interface IExportable {
    List<String> createHeader();
    List<String> createRow();
}
