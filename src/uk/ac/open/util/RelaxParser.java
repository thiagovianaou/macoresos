package uk.ac.open.util;

import java.util.ArrayList;
import java.util.List;

import uk.ac.open.assertions.AssertionGenerator;
import uk.ac.open.data.DBFactory;
import uk.ac.open.data.models.RelaxStatements;
import uk.ac.open.data.models.RelaxStatementsID;

public class RelaxParser {

	public static void extractRelaxClauses(String requirementCode, String text){
		String[] SHALL_STMs = text.split(Constants.SHALL);
		text = text.replace(SHALL_STMs[0], "");
		SHALL_STMs = text.split(Constants.SHALL);
		
		for(int i=1; i< SHALL_STMs.length; i++){
			List<String> relaxClauses = new ArrayList<String>();
			List<String> relaxParams = new ArrayList<String>();
			String shallClause = "";
			String[] texts = SHALL_STMs[i].split(Constants.WORD_DELIMITER);
			boolean shallStm = true;
			for (int j = 1; j < texts.length; ) {
				String tempRelaxClause = "";
				String tempRelaxParam = "";
				//finding the first SHALL clause
				while(j < texts.length && !isClauseCase(texts[j])){
					if(shallStm){
						shallClause = shallClause+texts[j]+Constants.WORD_DELIMITER;
					}else{
						tempRelaxParam = tempRelaxParam+texts[j]+Constants.WORD_DELIMITER;
					}
					j++;
				}
				if(!shallStm){
					if(!tempRelaxParam.equals("")){
						relaxParams.add(tempRelaxParam);
					}
				}else{
					shallStm = false;
				}
				while(j < texts.length && isClauseCase(texts[j])){
					tempRelaxClause = tempRelaxClause+texts[j]+Constants.WORD_DELIMITER;
					j++;
				}
				if(!tempRelaxClause.equals("")){
					relaxClauses.add(tempRelaxClause);
				}
			}
			
			//insert 
			String RELAX_CLAUSES = "";
			if(relaxClauses.size()>0){
				for (String clause : relaxClauses) {
					RELAX_CLAUSES = RELAX_CLAUSES+clause+Constants.DELIMITER_RELAX_PARAMS;
				}
				RELAX_CLAUSES = RELAX_CLAUSES.substring(0, RELAX_CLAUSES.length()-1);
			}else{
				RELAX_CLAUSES=null;
			}
			
			String RELAX_PARAMS = "";
			if(relaxParams.size()>0){
				for (String param : relaxParams) {
					RELAX_PARAMS = RELAX_PARAMS+param+Constants.DELIMITER_RELAX_PARAMS;
				}
				RELAX_PARAMS = RELAX_PARAMS.replace(Constants.TEXT_CODES,""); 
				RELAX_PARAMS = RELAX_PARAMS.substring(0, RELAX_PARAMS.length()-1);
			}else{
				RELAX_PARAMS = null;
			}
			
			RelaxStatementsID id = new RelaxStatementsID(requirementCode, shallClause);
			RelaxStatements rs = new RelaxStatements(id, RELAX_CLAUSES, RELAX_PARAMS);
			DBFactory.getDB().insertOrUpdate(rs);
			AssertionGenerator.generateAssertion(rs);
		}
	}

	private static boolean isClauseCase(String clause){
		return clause.equals(clause.toUpperCase()) &&
				!clause.equals("") &&
				!Character.isDigit(clause.charAt(0));
	}
}
