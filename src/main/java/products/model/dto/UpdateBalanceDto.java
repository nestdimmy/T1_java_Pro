package products.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import products.model.BalanceOperation;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBalanceDto {
    private BigDecimal amount;
    private BalanceOperation operation;
}
