package uk.ac.open.assertions;

import java.util.HashMap;

import uk.ac.open.assertions.FBTL.FBTLAssertion;
import uk.ac.open.data.models.RelaxStatements;
import uk.ac.open.util.Constants;

public class AssertionGenerator {
	public static void generateAssertion(RelaxStatements rs){
		if(rs.getRelaxStatement()!=null){
			String[] stms = rs.getRelaxStatement().split(Constants.DELIMITER_RELAX_PARAMS);
			String[] params = rs.getRelaxParams().split(Constants.DELIMITER_RELAX_PARAMS);
	
			String assertion = "";
			for (int i = 0; i < stms.length; i++) {
				assertion = assertion + stms[i] + Constants.OFunc +params[i] + Constants.CFunc;
			}
			assertion = assertion + Constants.AG + Constants.OFunc + rs.getId().getShallClause()+Constants.CFunc;;
			
			FBTLAssertion a = translateFBTLAssertion(assertion);
			System.out.println(a.getAssertion());
			//AssertionMonitor.updateMonitor() - running
		}
	}
	
	private static FBTLAssertion translateFBTLAssertion(String relaxSttm){
		relaxSttm = relaxSttm.replace(Constants.SHALL, Constants.AG);
		relaxSttm = relaxSttm.replace(Constants.MAY, Constants.AG);
		relaxSttm = relaxSttm.replace(Constants.EVENTUALLY, Constants.AF);
		relaxSttm = relaxSttm.replace(Constants.UNTIL, Constants.U);
		relaxSttm = relaxSttm.replace(Constants.AEAPB, Constants.AXLessThanD);
		relaxSttm = relaxSttm.replace(Constants.AEAPA, Constants.AXMoreThanD);
		relaxSttm = relaxSttm.replace(Constants.ACAPT, Constants.AFd);
		relaxSttm = relaxSttm.replace(Constants.AMAP, Constants.AF1);
		relaxSttm = relaxSttm.replace(Constants.AFAP, Constants.AF0);
		
		return new FBTLAssertion(relaxSttm);
	}
	
	private static HashMap<String, String> FBTLSttms;
	static{
		FBTLSttms = new HashMap<String, String>();
		FBTLSttms.put(Constants.SHALL, Constants.AG);
		FBTLSttms.put(Constants.MAY, Constants.AG);
		FBTLSttms.put(Constants.EVENTUALLY, Constants.AF);
		FBTLSttms.put(Constants.UNTIL, Constants.U);
		FBTLSttms.put(Constants.AEAPB, Constants.AXLessThanD);
		FBTLSttms.put(Constants.AEAPA, Constants.AXMoreThanD);
		FBTLSttms.put(Constants.ACAPT, Constants.AFd);
		FBTLSttms.put(Constants.AMAP, Constants.AF1);
		FBTLSttms.put(Constants.AFAP, Constants.AF0);
	}
}
