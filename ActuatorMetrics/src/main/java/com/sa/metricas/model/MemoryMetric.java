
package com.sa.metricas.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemoryMetric {

    private String name;
    private String description;
    private String baseUnit;
    private List<Measurement> measurements;
    private List<Tag> availableTags;

    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Measurement {
        private String statistic;
        private double value;
    }
    
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tag {
        private String tag;
        private List<String> values;


    }
}
