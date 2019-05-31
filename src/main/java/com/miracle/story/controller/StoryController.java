package com.miracle.story.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miracle.feature.response.FeatureResponse;

@RestController
public class StoryController {

	@GetMapping(value = "/storyIds")
	public ResponseEntity<FeatureResponse> buildEffort(@RequestParam(value = "featureId") int featureId,
			@RequestParam(value = "projectName") String projectName) {
		// Invoke IceScrum to retrieve Feature details related to feature id
		// Extract story ids related to feature
		FeatureResponse response = new FeatureResponse();
		// send List of story ids specific to Feature using FeatureResponse
		return new ResponseEntity<FeatureResponse>(response, HttpStatus.OK);
	}

}
