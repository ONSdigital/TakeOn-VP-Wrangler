package uk.gov.ons.validation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationConfig {
    private String questionCode;


    public List<ValidationConfig> getValidationConfiguration() {
        List<ValidationConfig> configuration = new ArrayList<>();
        ValidationConfig config1 = new ValidationConfig();
        config1.setQuestionCode("601");
        configuration.add(config1);
        ValidationConfig config2 = new ValidationConfig();
        config2.setQuestionCode("602");
        configuration.add(config2);
        return configuration;
    }
}
