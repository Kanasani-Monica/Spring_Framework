package com.application.aaiis.establishment.vigilance.service;

import javax.servlet.http.HttpServletRequest;

public interface AetPropertyService {
	public String saveAetPropertyAcqisitionForm(HttpServletRequest httpServletRequest);
	public String updateAetPropertyAcqisitionForm(HttpServletRequest httpServletRequest);
	public String disposeAetPropertyAcqisitionForm(HttpServletRequest httpServletRequest);

}
