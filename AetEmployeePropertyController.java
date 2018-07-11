package com.application.aaiis.establishment.vigilance.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.aaiis.establishment.employeedetails.domain.AemEmployee;
import com.application.aaiis.establishment.employeedetails.domain.AemEmployeeFamily;
import com.application.aaiis.establishment.rtf.domain.AetRtfApplication;
import com.application.aaiis.establishment.rtf.service.RtfApplicationService;
import com.application.aaiis.establishment.vigilance.domain.AetPropertyAcquisition;
import com.application.aaiis.establishment.vigilance.domain.AetPropertyFinance;
import com.application.aaiis.establishment.vigilance.domain.AetPropertyOwner;
import com.application.aaiis.establishment.vigilance.service.AetPropertyService;
import com.application.aaiis.mastermaintenance.common.domain.AgmRoleMapping;

@RequestMapping("/aetemployeeproperty")
@Controller
public class AetEmployeePropertyController {
	
	@Autowired	
	private AetPropertyService aetPropertyService;

	
	@RequestMapping(params = "acqform", method = RequestMethod.GET)
    public String createAcqForm(Model uiModel, HttpServletRequest httpServletRequest) {
		
		long empNo = Integer.parseInt(httpServletRequest.getSession().getAttribute("usersession").toString());
		AemEmployee empObj = AemEmployee.findAemEmployee(empNo);
		
		uiModel.addAttribute("empObj",empObj);
        return "aetemployeeproperty/createAcqForm";
    }
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)	
	public String create(Model uiModel, HttpServletRequest httpServletRequest) {
		String msg = aetPropertyService.saveAetPropertyAcqisitionForm(httpServletRequest);				
		uiModel.addAttribute("message", msg);
		return "aetemployeeproperty/createAcqForm";
	}
	
	@RequestMapping(params = "list", method = RequestMethod.GET)
    public String listProperty(Model uiModel, HttpServletRequest httpServletRequest) {
		
		long empNo = Integer.parseInt(httpServletRequest.getSession().getAttribute("usersession").toString());
		AemEmployee empObj = AemEmployee.findAemEmployee(empNo);
		
		List<AetPropertyAcquisition> propList =  AetPropertyAcquisition.findAllAetPropertyAcquisition(empNo);
		
		uiModel.addAttribute("empObj",empObj);
		uiModel.addAttribute("propList",propList);
        return "aetemployeeproperty/listAllProperty";
    }
	
	
	@RequestMapping(value = "/updateForm/{propertyId}",method = RequestMethod.GET)
    public String updateProperty(@PathVariable("propertyId") String propertyId, Model uiModel, HttpServletRequest httpServletRequest) {
		
		long empNo = Integer.parseInt(httpServletRequest.getSession().getAttribute("usersession").toString());
		AemEmployee empObj = AemEmployee.findAemEmployee(empNo);
		Long propId = Long.parseLong(propertyId);
		AetPropertyAcquisition property = AetPropertyAcquisition.findAetPropertyAcquisition(propId);
		System.out.println("Property Id"+propertyId);
		List<AetPropertyOwner> propOwnerList = AetPropertyOwner.findAllAetPropertyOwner(propId);
		List<AetPropertyFinance> propFinanceList = AetPropertyFinance.findAllAetPropertyFinance(propId);
		//List<AetPropertyOwner> propOwnerList = AetPropertyOwner.findAllAetPropertyOwner(propertyId);
		//Long partyId=Long.parseLong(httpServletRequest.getParameter("partyId").toString());
		//AetPropertyOwner owner=AetPropertyOwner.findAetPropertyOwner(partyId);
		uiModel.addAttribute("empObj",empObj);
		uiModel.addAttribute("property",property);
		uiModel.addAttribute("propOwnerList",propOwnerList);
		uiModel.addAttribute("propFinanceList",propFinanceList);
		//uiModel.addAttribute("owner",owner);
        return "aetemployeeproperty/updateProperty";
    }
	@RequestMapping(value = "/update",method = RequestMethod.POST)	
	public String update(Model uiModel, HttpServletRequest httpServletRequest) {
		String msg = aetPropertyService.updateAetPropertyAcqisitionForm(httpServletRequest);				
		uiModel.addAttribute("message", msg);
		return "aetemployeeproperty/updateAcqForm";
	}
	@RequestMapping(value = "/disposeForm/{disposeId}",method = RequestMethod.GET)
	 public String disposeProperty(@PathVariable("disposeId") String disposeId, Model uiModel, HttpServletRequest httpServletRequest) {
		long empNo = Integer.parseInt(httpServletRequest.getSession().getAttribute("usersession").toString());
		AemEmployee empObj = AemEmployee.findAemEmployee(empNo);
		Long propId = Long.parseLong(disposeId);
		AetPropertyAcquisition dispose = AetPropertyAcquisition.findAetPropertyAcquisition(propId);
		uiModel.addAttribute("empObj",empObj);
		uiModel.addAttribute("dispose",dispose);
		 return "aetemployeeproperty/disposeProperty";
	}
	@RequestMapping(value = "/dispose",method = RequestMethod.POST)	
	public String dispose(Model uiModel, HttpServletRequest httpServletRequest) {
		String msg = aetPropertyService.disposeAetPropertyAcqisitionForm(httpServletRequest);				
		uiModel.addAttribute("message", msg);
		return "aetemployeeproperty/disposal";
	}
	@RequestMapping(params = "ipr", method = RequestMethod.GET)
    public String propertyReturnForm(Model uiModel, HttpServletRequest httpServletRequest){
		long empNo = Integer.parseInt(httpServletRequest.getSession().getAttribute("usersession").toString());
		AemEmployee empObj = AemEmployee.findAemEmployee(empNo);
		List<AetPropertyAcquisition> propertyAcquisitionList =  AetPropertyAcquisition.findAllAetPropertyAcquisition(empNo);
		List<AetPropertyOwner> propertyOwnerList = null;
		for(AetPropertyAcquisition propReturn : propertyAcquisitionList)
		{
			propertyOwnerList = AetPropertyOwner.findAllAetPropertyOwner(propReturn.getPropId());
			propReturn.setAetPropertyOwner(propertyOwnerList);
		}
		uiModel.addAttribute("empObj",empObj);
		uiModel.addAttribute("propertyAcquisitionList",propertyAcquisitionList);
		uiModel.addAttribute("propertyOwnerList",propertyOwnerList);
		return "aetemployeeproperty/propertyReturnForm";
	}
}
