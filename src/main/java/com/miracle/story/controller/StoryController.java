package com.miracle.story.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miracle.common.api.bean.Feature;
import com.miracle.common.controller.APIMicroService;
import com.miracle.exception.GatewayServiceException;
import com.miracle.story.bean.FeatureMetadetails;
import com.miracle.story.bean.FeatureStoryDetails;
import com.miracle.story.exception.StoryErrorCode;
import com.miracle.story.exception.StoryException;

@RestController
@RequestMapping(value = "/masterBot/project/features")
public class StoryController extends APIMicroService {
	public static final Logger logger = LoggerFactory.getLogger(StoryController.class);
	String iceScrumURLPrefix = null;

	// API_M4
	// @Override
	@PostMapping("/extractStories")
	public FeatureStoryDetails runService(@RequestBody FeatureMetadetails featureMetadetails) throws Exception {
		FeatureStoryDetails featureStoryDetails = null;
		try {
			iceScrumURLPrefix = getIceScrumURLPrefix();
			String url = buildURLEndpointToGetFeature(featureMetadetails);
			Map<String, String> headerDetails = commonUtil.getHeaderDetails();
			List<MediaType> acceptableMediaTypes = commonUtil.getAcceptableMediaTypes();
			String featureDetails = commonUtil.getDetails(url, headerDetails, acceptableMediaTypes);
			logger.info("Extracted Feature Details :: " + featureDetails);
			Feature extracetdFeatures = new ObjectMapper().readValue(featureDetails, Feature.class);
			featureStoryDetails = new FeatureStoryDetails();
			featureStoryDetails.setId(extracetdFeatures.getId());
			featureStoryDetails.setStories_ids(extracetdFeatures.getStories_ids());
		} catch (StoryException storyException) {
			logger.error("Getting exception in extracting stories from feature, Exception Description :: "
					+ storyException.getMessage(), storyException);
			throw storyException;
		} catch (GatewayServiceException gatewayServiceException) {
			logger.error("Getting exception in extracting stories from feature, Exception Description :: "
					+ gatewayServiceException.getMessage(), gatewayServiceException);
			throw gatewayServiceException;
		} catch (Exception exception) {
			logger.error("Getting exception in extracting stories from feature, Exception Description :: "
					+ exception.getMessage(), exception);
			throw new StoryException(
					"Getting exception in extracting stories from feature, Exception Description :: "
							+ exception.getMessage(),
					exception, StoryErrorCode.STORY_CONTROLLER_UNKNOWN_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return featureStoryDetails;
	}

	/**
	 * 
	 * @param featureMetadetails
	 * @return
	 * @throws StoryException
	 */
	private String buildURLEndpointToGetFeature(FeatureMetadetails featureMetadetails) throws StoryException {
		StringBuilder urlEndpoint = new StringBuilder("");
		int featuredID = featureMetadetails.getId();
		String projectName = featureMetadetails.getProjectName();
		if (projectName != null && !projectName.trim().isEmpty()) {
			if (featuredID > 0) {
				urlEndpoint.append(iceScrumURLPrefix).append(featureMetadetails.getProjectName()).append("/feature/")
						.append(featuredID);
			} else {
				logger.error("Invalid futureId in the request");
				throw new StoryException("Invalid futureId in the request", StoryErrorCode.INVALID_FEATURE_ID);
			}
		} else {
			logger.error("Invalid project Name the request");
			throw new StoryException("Invalid project Name the request\"", StoryErrorCode.INVALID_PROJECT_NAME);
		}
		return urlEndpoint.toString();
	}
}
