package com.arcesi.micoroserviceecommerce;

import java.time.Instant;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arcesi.micoroserviceecommerce.entities.Category;
import com.arcesi.micoroserviceecommerce.entities.Product;
import com.arcesi.micoroserviceecommerce.repositories.CategoryRepository;
import com.arcesi.micoroserviceecommerce.repositories.ProductRepository;

@SpringBootApplication
public class MicroServiceEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceEcommerceApplication.class, args);
	}

	@Bean
    CommandLineRunner run(final CategoryRepository categoryRepository, final ProductRepository productRepository) {
     return args->{
    	
    	 Category ordinateur=Category.builder()
    			.createdAt(Instant.now())
    			.codeUniqueCategory(UUID.randomUUID().toString())
    			.isActive(Boolean.FALSE)
    			.libelleCategory("ORDINATEUR")
    			.descriptionCategory("La description des ordinateurs")
    			.build(); 
      //sava Category
    	categoryRepository.save(ordinateur);
    	
    	 Category telephone=Category.builder()
     			.createdAt(Instant.now())
     			
     			.codeUniqueCategory(UUID.randomUUID().toString())
     			.isActive(Boolean.FALSE)
     			.libelleCategory("TELEPHONE")
     			.descriptionCategory("La description des téléphones")
     			.build(); 
    	 
    	 categoryRepository.save(telephone);
    	 
    	 Category livre=Category.builder()
     			.createdAt(Instant.now())
     			.codeUniqueCategory(UUID.randomUUID().toString())
     			
     			.isActive(Boolean.FALSE)
     			.libelleCategory("LIVRE")
     			.descriptionCategory("La description des livres")
     			.build(); 
     
    	 categoryRepository.save(livre);
    	 
    	 for(int i=0;i<300;i++) {
    		 Product product=Product.builder()
    				 .createdAt(Instant.now())
    				 .isActive(Boolean.TRUE)
    				 .codeUniqueProduct(UUID.randomUUID().toString())
    				 .designation("HP N°:"+i+"SN")
    				 .description("Ordinateur portable hp de qualité 1")
    				 .prixUnitaire(1500)
    				 .quantiteStock(i+i+300)
    				 .isDisponible(Boolean.TRUE)
    				 .isPromotion(Boolean.TRUE)
    				 .imageProduct("ordianteur"+i+"jpeg")
    				 .build();
    		 product.setCategory(ordinateur);
    		 productRepository.save(product);
    		 
    		 
    		 Product productTelephone=Product.builder()
    				 .createdAt(Instant.now())
    				 .isActive(Boolean.TRUE)
    				 .codeUniqueProduct(UUID.randomUUID().toString())
    				 .designation("SAMUSUNG GALAXY"+i+"PRO")
    				 .description("Téléphonone portable originale qualité 1")
    				 .prixUnitaire(i+300+10)
    				 .quantiteStock(i+i+300)
    				 .isDisponible(Boolean.TRUE)
    				 .isPromotion(Boolean.TRUE)
    				 .imageProduct("samsunggalaxy"+i+"jpeg")
    				 .build();
    		 productTelephone.setCategory(telephone);
    		 productRepository.save(productTelephone);
    		 
    		 
    		 Product productLivre=Product.builder()
    				 .createdAt(Instant.now())
    				
    				 .isActive(Boolean.TRUE)
    				 .codeUniqueProduct(UUID.randomUUID().toString())
    				 .designation("LIVRE CUISINE FRANÇAISE"+i+"PRO")
    				 .description("Livre de la cuisine française ")
    				 .prixUnitaire(i+30+15)
    				 .quantiteStock(i+i+300)
    				 .isDisponible(Boolean.TRUE)
    				 .isPromotion(Boolean.TRUE)
    				 .imageProduct("livre"+i+"jpeg")
    				 .build();
    		 productLivre.setCategory(telephone);
    		 productRepository.save(productLivre);
    	 }
     };
    }
	 
}
