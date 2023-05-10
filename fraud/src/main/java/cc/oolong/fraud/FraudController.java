package cc.oolong.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fraud-check")
@Slf4j
public class FraudController {
  private final FraudCheckService fraudCheckService;

  @GetMapping("{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
       log.info("fraud check response for customer {}", customerId);
       boolean isFraudster=this.fraudCheckService.isFraudulentCustomer(customerId);
       return new FraudCheckResponse(isFraudster);
    }
}
