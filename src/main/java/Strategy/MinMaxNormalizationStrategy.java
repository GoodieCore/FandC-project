package Strategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MinMaxNormalizationStrategy implements NormalizationStrategy {
    @Override
    public List<Double> normalize(List<Double> data) {
        double min = Collections.min(data);
        double max = Collections.max(data);
        return data.stream()
                .map(value -> (value - min) / (max - min))
                .collect(Collectors.toList());
    }
}
