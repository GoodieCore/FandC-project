package Strategy;

import java.util.List;
import java.util.stream.Collectors;

public class ZScoreNormalizationStrategy implements NormalizationStrategy{
    @Override
    public List<Double> normalize(List<Double> data) {
        double mean = data.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double stdDev = Math.sqrt(data.stream().mapToDouble(d -> (d - mean) * (d - mean)).average().orElse(0));
        return data.stream()
                .map(value -> (value - mean) / stdDev)
                .collect(Collectors.toList());
    }
}
