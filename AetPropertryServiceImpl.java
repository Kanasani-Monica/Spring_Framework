package com.application.aaiis.establishment.vigilance.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.application.aaiis.establishment.employeedetails.domain.AemEmployee;
import com.application.aaiis.establishment.vigilance.domain.AetPropertyAcquisition;
import com.application.aaiis.establishment.vigilance.domain.AetPropertyDisposal;
import com.application.aaiis.establishment.vigilance.domain.AetPropertyFinance;
import com.application.aaiis.establishment.vigilance.domain.AetPropertyOwner;
import com.application.aaiis.establishment.vigilance.service.AetPropertyService;

@Service("AetPropertyService.java")
public class AetPropertryServiceImpl implements AetPropertyService{
	public String saveAetPropertyAcqisitionForm(HttpServletRequest httpServletRequest){
		String message = "";
		try{
										
			Long empNo = Long.parseLong(httpServletRequest.getSession().getAttribute("usersession").toString());
			AemEmployee employeeObj= AemEmployee.findAemEmployee(empNo);
			String doa = httpServletRequest.getParameter("acqDate");
			String pdoa = httpServletRequest.getParameter("proAcqDate");
			String locationOfProperty = httpServletRequest.getParameter("locationOfProp");
			String propertyDescription = httpServletRequest.getParameter("propDesc");
			String modeOfTransaction = httpServletRequest.getParameter("modeOfTrans");
			String natureOfProperty = httpServletRequest.getParameter("natureOfProp");
			String areaOfProperty = httpServletRequest.getParameter("areaOfProp");
			String applicantShareOfProperty= httpServletRequest.getParameter("prop_share");			
			String valueOfProperty = httpServletRequest.getParameter("valueOfProp");
			if(valueOfProperty == null || valueOfProperty.equals("") ) valueOfProperty = "0";
			String partialShare = httpServletRequest.getParameter("partialShare");
			String ownerShare = httpServletRequest.getParameter("ownerShare");
			String ownSavings = httpServletRequest.getParameter("ownSavings");
			if(ownSavings == null||ownSavings.equals("")) ownSavings = "0";
			String withdrawal = httpServletRequest.getParameter("withdrawal");
			if(withdrawal == null || withdrawal.equals("")) withdrawal= "0";
			String nameOfParty = httpServletRequest.getParameter("nameOfParty");
			String addressOfParty = httpServletRequest.getParameter("addressOfParty");
			String partyFlag = httpServletRequest.getParameter("party_flag");
			String relationDetails = httpServletRequest.getParameter("relationDetails");
			String dealings = httpServletRequest.getParameter("dealings");
			String transacDetails = httpServletRequest.getParameter("transacDetails");
			String gift = httpServletRequest.getParameter("gift");
			String relevantFacts = httpServletRequest.getParameter("relevantFacts");
			
			String[] otherResource = httpServletRequest.getParameterValues("otherResource");
			String[] amount = httpServletRequest.getParameterValues("amount");
			
			String[] name = httpServletRequest.getParameterValues("name");
			String[] relation = httpServletRequest.getParameterValues("relation");
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			AetPropertyAcquisition propObj = new AetPropertyAcquisition();
			propObj.setAcquisitionDate((doa==null || doa.equals("")) ? null : sd.parse(doa));
			propObj.setProbableAcquisitionDate((pdoa==null || pdoa.equals("")) ? null : sd.parse(pdoa));
			propObj.setPropertyAddress(locationOfProperty);
			propObj.setPropertyDesc(propertyDescription);
			propObj.setModeOfTransaction(modeOfTransaction) ;
			propObj.setPropertyNature(natureOfProperty);
			propObj.setAreaOfProperty(areaOfProperty);
			propObj.setShareInProperty(applicantShareOfProperty);
			propObj.setValueOfProperty(Long.parseLong(valueOfProperty));
			propObj.setPartShare(partialShare);
			propObj.setShareDtls(ownerShare);
			propObj.setOwnSaving(Integer.parseInt(ownSavings));
			propObj.setPfAmount(Integer.parseInt(withdrawal));
			propObj.setAcqFrom(nameOfParty);
			propObj.setAcqAddr(addressOfParty);
			propObj.setPartyFlag(partyFlag);
			propObj.setPartyRelation(relationDetails);
			propObj.setOfficialDeal(dealings);
			propObj.setTransactionArranged(transacDetails);
			propObj.setCcsRule(gift);
			propObj.setOtherFacts(relevantFacts);
			propObj.setEmpNo(employeeObj);
			propObj.persist();
			
			
			for(int i=0; i < name.length; i++){
				AetPropertyOwner propOwnerObj = new AetPropertyOwner();
				propOwnerObj.setOwnerName(name[i]);
				propOwnerObj.setRelationShip(relation[i]);
				propOwnerObj.setPropId(propObj);
				propOwnerObj.persist();
			}
			for(int j=0; j < otherResource.length; j++){
				AetPropertyFinance resourceObj = new AetPropertyFinance();
				resourceObj.setFinanceSource(otherResource[j]);
				resourceObj.setFinanceAmount((amount[j]==null || amount[j].equals("")) ? null : Integer.parseInt(amount[j]));
				resourceObj.setPropId(propObj);
				resourceObj.persist();
			}
			message = "Data Saved Successfully.";
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception ::"+e.getMessage());
			message = "Error Saving Data.";	    
		}
		return message;
	}
	
	public String updateAetPropertyAcqisitionForm(HttpServletRequest httpServletRequest){
		String message = "";
		try{
										
			Long empNo = Long.parseLong(httpServletRequest.getSession().getAttribute("usersession").toString());
			AemEmployee employeeObj= AemEmployee.findAemEmployee(empNo);
			
			Long propId = Long.parseLong(httpServletRequest.getParameter("propId"));
			String doa = httpServletRequest.getParameter("acqDate");
			String pdoa = httpServletRequest.getParameter("proAcqDate");
			String locationOfProperty = httpServletRequest.getParameter("locationOfProp");
			String propertyDescription = httpServletRequest.getParameter("propDesc");
			String modeOfTransaction = httpServletRequest.getParameter("modeOfTrans");
			String natureOfProperty = httpServletRequest.getParameter("natureOfProp");
			String areaOfProperty = httpServletRequest.getParameter("areaOfProp");
			String applicantShareOfProperty= httpServletRequest.getParameter("prop_share");			
			String valueOfProperty = httpServletRequest.getParameter("valueOfProp");
			if(valueOfProperty == null || valueOfProperty.equals("") ) valueOfProperty = "0";
			String partialShare = httpServletRequest.getParameter("partialShare");
			String ownerShare = httpServletRequest.getParameter("ownerShare");
			String ownSavings = httpServletRequest.getParameter("ownSavings");
			if(ownSavings == null||ownSavings.equals("")) ownSavings = "0";
			String withdrawal = httpServletRequest.getParameter("withdrawal");
			if(withdrawal == null || withdrawal.equals("")) withdrawal= "0";
			String nameOfParty = httpServletRequest.getParameter("nameOfParty");
			String addressOfParty = httpServletRequest.getParameter("addressOfParty");
			String partyFlag = httpServletRequest.getParameter("party_flag");
			String relationDetails = httpServletRequest.getParameter("relationDetails");
			String dealings = httpServletRequest.getParameter("dealings");
			String transacDetails = httpServletRequest.getParameter("transacDetails");
			String gift = httpServletRequest.getParameter("gift");
			String relevantFacts = httpServletRequest.getParameter("relevantFacts");
			
			String[] otherResource = httpServletRequest.getParameterValues("otherResource");
			String[] amount = httpServletRequest.getParameterValues("amount");
			
			String[] name = httpServletRequest.getParameterValues("name");
			String[] relation = httpServletRequest.getParameterValues("relation");
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			
			AetPropertyAcquisition propObj = AetPropertyAcquisition.findAetPropertyAcquisition(propId);
			if(propObj != null){
				propObj.setAcquisitionDate((doa==null || doa.equals("")) ? null : sd.parse(doa));
				propObj.setProbableAcquisitionDate((pdoa==null || pdoa.equals("")) ? null : sd.parse(pdoa));
				propObj.setPropertyAddress(locationOfProperty);
				propObj.setPropertyDesc(propertyDescription);
				propObj.setModeOfTransaction(modeOfTransaction) ;
				propObj.setPropertyNature(natureOfProperty);
				propObj.setAreaOfProperty(areaOfProperty);
				propObj.setShareInProperty(applicantShareOfProperty);
				propObj.setValueOfProperty(Long.parseLong(valueOfProperty));
				propObj.setPartShare(partialShare);
				propObj.setShareDtls(ownerShare);
				propObj.setOwnSaving(Integer.parseInt(ownSavings));
				propObj.setPfAmount(Integer.parseInt(withdrawal));
				propObj.setAcqFrom(nameOfParty);
				propObj.setAcqAddr(addressOfParty);
				propObj.setPartyFlag(partyFlag);
				propObj.setPartyRelation(relationDetails);
				propObj.setOfficialDeal(dealings);
				propObj.setTransactionArranged(transacDetails);
				propObj.setCcsRule(gift);
				propObj.setOtherFacts(relevantFacts);
				propObj.setEmpNo(employeeObj);
				propObj.merge();			
				
				List <AetPropertyOwner> propOwnerList = AetPropertyOwner.findAllAetPropertyOwner(propId);				
				for(AetPropertyOwner propOwnerObj:propOwnerList){
					propOwnerObj.remove();
				}
				for (int i=0; i < name.length; i++){
					AetPropertyOwner propOwnerObj = new AetPropertyOwner();
					propOwnerObj.setOwnerName(name[i]);
					propOwnerObj.setRelationShip(relation[i]);
					propOwnerObj.setPropId(propObj);
					propOwnerObj.persist();
				}
				List <AetPropertyFinance> PropFinanceList =AetPropertyFinance.findAllAetPropertyFinance(propId) ;
				for(AetPropertyFinance resourceObj:PropFinanceList){
					resourceObj.remove();
				}
				for(int j=0; j < otherResource.length; j++){
					AetPropertyFinance resourceObj = new AetPropertyFinance();
					resourceObj.setFinanceSource(otherResource[j]);
					resourceObj.setFinanceAmount((amount[j]==null || amount[j].equals("")) ? null : Integer.parseInt(amount[j]));
					resourceObj.setPropId(propObj);
					resourceObj.merge();
				}
				message = "Data Updated Successfully.";
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception ::"+e.getMessage());
			message = "Error Updating Data.";	    
		}
		return message;
	}
	public String disposeAetPropertyAcqisitionForm(HttpServletRequest httpServletRequest){
		String message = "";
		try{
			Long empNo = Long.parseLong(httpServletRequest.getSession().getAttribute("usersession").toString());
			AemEmployee employeeObj= AemEmployee.findAemEmployee(empNo);
			Long propId = Long.parseLong(httpServletRequest.getParameter("propId"));
			
			String pdod = httpServletRequest.getParameter("proDisDate");
			String modeOfTransaction = httpServletRequest.getParameter("modeOfTrans");
			String sellingPrice = httpServletRequest.getParameter("sellingPrice");
			if(sellingPrice == null||sellingPrice.equals("")) sellingPrice = "0";
			String sanction = httpServletRequest.getParameter("sanction");
			String nameOfParty = httpServletRequest.getParameter("nameOfParty");
			String addressOfParty = httpServletRequest.getParameter("addressOfParty");
			String refund = httpServletRequest.getParameter("refund");
			String partyFlag = httpServletRequest.getParameter("party_flag");
			String relationDetails = httpServletRequest.getParameter("relationDetails");
			String dealings = httpServletRequest.getParameter("dealings");
			String transacDetails = httpServletRequest.getParameter("transacDetails");
			String relevantFacts = httpServletRequest.getParameter("relevantFacts");
			
			
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			
			AetPropertyDisposal disposeObj = new AetPropertyDisposal();
			disposeObj.setModeOfTransaction(modeOfTransaction) ;
			disposeObj.setSellPrice(Integer.parseInt(sellingPrice));
			disposeObj.setAcqFlag(sanction);
			disposeObj.setDisposalTo(nameOfParty);
			disposeObj.setDisposalAddress(addressOfParty);
			disposeObj.setHbaFlag(refund);
			disposeObj.setPartyFlag(partyFlag);
			disposeObj.setPartyRelation(relationDetails);
			disposeObj.setOfficialDeal(dealings);
			disposeObj.setTransactionArranged(transacDetails);
			disposeObj.setOtherFacts(relevantFacts);
			
			
			AetPropertyAcquisition propObj = AetPropertyAcquisition.findAetPropertyAcquisition(propId);
			if(propObj != null){
			propObj.setDisposalDate((pdod==null || pdod.equals("")) ? null : sd.parse(pdod));
			propObj.setEmpNo(employeeObj);
			propObj.merge();
			
			message = "Data Disposed Successfully.";
		}
		}
		
	 catch(Exception e) {
				e.printStackTrace();
				System.out.println("Exception ::"+e.getMessage());
				message = "Error Disposing Data.";	    
			}
			return message;
	}
}

