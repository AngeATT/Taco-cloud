package angeATT.Tacocloud.service;

import angeATT.Tacocloud.domains.Ingredient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class RestIngredientService implements IngredientService{
    private RestTemplate restTemplate;

    public RestIngredientService(String accessToken){
        this.restTemplate = new RestTemplate();
        if (accessToken!= null ){
            this.restTemplate.getInterceptors().add(getBearerTokenInterceptor(accessToken));
        }
    }
    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken){
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().add("Authorization","Bearer "+ accessToken);
                return execution.execute(request,body);
            }
        };
    }
    @Override
    public Iterable<Ingredient> findAll(){
        return Arrays.asList(
                restTemplate.getForObject("http://127.0.0.1:8080/api/ingredients", Ingredient[].class)
        );
    }
    @Override
    public Ingredient addIngredient(Ingredient ingredient){
       return restTemplate.postForObject("http://127.0.0.1:8080/api/ingredients",ingredient,Ingredient.class);
    }

   @Bean
   @RequestScope
    public IngredientService ingredientService(OAuth2AuthorizedClientService clientService){ // récupérer le token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String accesToken = null;
        if ( authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class) ){
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            if (Objects.equals(clientRegistrationId, "taco-admin-client")){
                OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, oAuth2AuthenticationToken.getName() );
                accesToken = client.getAccessToken().getTokenValue();
            }
        }
        return new RestIngredientService(accesToken);

   }
}
