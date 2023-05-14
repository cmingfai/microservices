package cc.oolong.apigateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ApiGatewayConfig {
    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(
                DefaultAddressResolverGroup.INSTANCE);
    }
}
