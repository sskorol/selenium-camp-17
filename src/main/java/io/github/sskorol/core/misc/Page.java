package io.github.sskorol.core.misc;

import static io.github.sskorol.utils.Configuration.CoreConstants.SITE_URL;

public interface Page {

	default void openPage() {
		this.navigateTo(this.getPageUrl());
	}

	default String getPageUrl() {
		return SITE_URL;
	}

	void navigateTo(String url);
}
