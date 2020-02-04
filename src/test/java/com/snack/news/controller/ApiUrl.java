package com.snack.news.controller;

import static com.snack.news.controller.ApiUrl.Domain.TOPIC;

public class ApiUrl {

	private final static String ADMIN = "/admin";
	private final static String API = "/api";

	private String url;
	private boolean hasDomain;

	private ApiUrl() {
	}

	public static ApiUrl builder() {
		return new ApiUrl();
	}

	public ApiUrl create(Domain domain) {
		hasDomain = true;
		url = ADMIN + API + domain.value;
		return this;
	}

	public ApiUrl update(Domain domain) {
		hasDomain = true;
		url = ADMIN + API + domain.value;
		return this;
	}

	public ApiUrl delete(Domain domain) {
		hasDomain = true;
		url = ADMIN + API + domain.value;
		return this;
	}


	public ApiUrl get(Domain domain) {
		hasDomain = true;
		url = API + domain.value;
		return this;
	}

	public ApiUrl getFromAdmin(Domain domain) {
		hasDomain = true;
		url = ADMIN + API + domain.value;
		return this;
	}

	public ApiUrl list(int page) {
		if(!hasDomain) throw new IllegalArgumentException();
		url += ("/" + page);
		return this;
	}

	public ApiUrl list() {
		if(!hasDomain) throw new IllegalArgumentException();
		return this;
	}

	public ApiUrl id(long id) {
		url += ("/" + id);
		return this;
	}

	public ApiUrl query(String query) {
		url += ("/" + query);
		return this;
	}

	public String build() {
		return url;
	}

	enum Domain {
		NEWS("/news"), PICKS("/picks"), CATEGORY("/category"), TOPIC("/topic"), TAG("/tag");
		String value;

		Domain(String value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		System.out.println(ApiUrl.builder().create(TOPIC).build());
	}
}