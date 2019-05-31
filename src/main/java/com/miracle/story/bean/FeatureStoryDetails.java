package com.miracle.story.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.miracle.common.api.bean.StoryID;

@Component
@JsonPropertyOrder({ "id", "stories_ids" })
public class FeatureStoryDetails {
	private int id;
	private List<StoryID> stories_ids;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<StoryID> getStories_ids() {
		return stories_ids;
	}

	public void setStories_ids(List<StoryID> stories_ids) {
		this.stories_ids = stories_ids;
	}

}
