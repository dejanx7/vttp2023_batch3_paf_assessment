package vttp2023.batch3.assessment.paf.bookings.models;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Landing {

    @NotNull(message = "Country cannot be blank")
    @NotBlank(message = "Country cannot be blank")
    private String country;
    @Max(value = 10, message = "Number cannot be more than 10")
    @Min(value = 1, message = "Number cannot be less than 1")
    private Integer numOfPerson;
    @Max(value = 10000, message = "Price cannot be more than 1000")
    @Min(value = 1, message = "Price cannot be less than 1")
    private double min;
    @Max(value = 10000, message = "Price cannot be more than 1000")
    @Min(value = 1, message = "Price cannot be less than 1")
    private double max;
    
}
