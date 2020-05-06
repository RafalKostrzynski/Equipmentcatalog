package pl.kostrzynski.equipmentcatalog.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ModelAndView;
import pl.kostrzynski.equipmentcatalog.api.EquipmentApiAll;
import pl.kostrzynski.equipmentcatalog.api.EquipmentApiAvailable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan(basePackageClasses = {EquipmentApiAll.class, EquipmentApiAvailable.class})
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@Configuration
public class SwaggerConfig {
    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String title = "Equipment API";
    private static final String description = "Equipment API for the justfit app project";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(SWAGGER_API_VERSION)
                .build();
    }

    @Bean
    public Docket equipmentApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("")
                .ignoredParameterTypes()
                .select()
                .paths(PathSelectors.regex("/justfit.equipment.*"))
                .build();
    }
}
