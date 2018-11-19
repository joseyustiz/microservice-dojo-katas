package com.joseyustiz.msvcdojo.profilesservice;

import com.joseyustiz.msvcdojo.profilesservice.model.Profile;
import com.joseyustiz.msvcdojo.profilesservice.repository.ProfilesRepository;
import com.joseyustiz.msvcdojo.profilesservice.web.ProfilePhotoController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class ProfilesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfilesServiceApplication.class, args);
	}

	@Bean
	public ResourceProcessor<Resource<Profile>> personProcessor() {

		return new ResourceProcessor<Resource<Profile>>() {

			@Override
			public Resource<Profile> process(Resource<Profile> resource) {

				resource.add(linkTo(methodOn(ProfilePhotoController.class).readPhotos(resource.getContent().getKey())).withRel("photos"));
				return resource;
			}
		};
	}
}
