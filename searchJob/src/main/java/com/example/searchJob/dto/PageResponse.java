package com.example.searchJob.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {
	private long page;
	
	private long size;
	
	private long totalElements;
	
	private T contents;
}
