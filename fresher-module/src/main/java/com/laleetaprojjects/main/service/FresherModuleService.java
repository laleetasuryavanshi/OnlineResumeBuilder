package com.laleetaprojjects.main.service;

import com.laleetaprojjects.main.entity.FresherInfo;

public interface FresherModuleService {
	public String saveFresherInfo(FresherInfo freshinfo);
	public String findFresherById(long id);
	public String deleteFresherById(Long id);
	public String findAllFresher();
}
