package com.snack.news.controller;

import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.dto.RequestQueryDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.PicksService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/picks")
public class PicksController {

	private final PicksService picksService;

	@GetMapping
	public WrappedResponse<List<Pick>> getPicksList(@ModelAttribute @Valid RequestQueryDto pickDto) {
		ListCursorResult<Pick> picksList = picksService.getPickList(pickDto);
		return WrappedResponse.ok(picksList);
	}
}
