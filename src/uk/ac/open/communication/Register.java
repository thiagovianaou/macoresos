package uk.ac.open.communication;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import uk.ac.open.behavior.statechart.State;
import uk.ac.open.behavior.statechart.Transition;
import uk.ac.open.data.DBFactory;
import uk.ac.open.data.DBInterface;
import uk.ac.open.data.models.Instance;
import uk.ac.open.data.models.Requirement;
import uk.ac.open.util.Constants;
import uk.ac.open.util.RelaxParser;
import uk.ac.open.util.TextParser;

public class Register {

	public static void register(String entityID, int instanceCode,
			String fileRelax, String fileStateChart,
			String fileMonitoringElements, String fileStatesRequirementMap)
			throws IOException {

		loadIntance(entityID, instanceCode);

		if (fileRelax != null) {
			loadRelaxRequirements(entityID, fileRelax);
		}

		if (fileStateChart != null) {
			loadStateChart(fileStateChart, entityID);
		}

		if (fileMonitoringElements != null) {
			// loadMonitoringElements(fileStateChart);
		}

		if (fileStatesRequirementMap != null) {
			// loadStatesRequirementMap(fileStateChart);
		}
		
	}

	private static void loadIntance(String entityID, int instanceCode) {
		DBInterface db = DBFactory.getDB();
		Instance i = new Instance(entityID, instanceCode);
		db.insertOrUpdate(i);
	}

	private static void loadRelaxRequirements(String entityID,
			String fileRelax) throws FileNotFoundException, IOException {
		FileInputStream relax = new FileInputStream(fileRelax);
		InputStreamReader reader = new InputStreamReader(relax);
		BufferedReader br = new BufferedReader(reader);
		String line = br.readLine();

		DBInterface db = DBFactory.getDB();

		while (!line.contains(Constants.BEGIN_RELAX)) {
			line = br.readLine();
		}

		line = br.readLine();
		String requirementCode="";
		while (line != null) {
			line = TextParser.processTextCodes(line);
			if (!line.equals("") && !line.equals(Constants.WORD_DELIMITER)) {
				if(line.contains(Constants.REQUIREMENT_SPLIT)){
					requirementCode = line.replace(Constants.REQUIREMENT_SPLIT, "");
					Requirement r = new Requirement(requirementCode, entityID, 0, 0);
					db.insertOrUpdate(r);
				}else{
					RelaxParser.extractRelaxClauses(requirementCode, line);
					requirementCode="";
				}
			}
			line = br.readLine();
		}

		br.close();
	}

	private static void loadStateChart(String fileStateChart, String entityID)
			throws IOException {
		FileInputStream stateChart = new FileInputStream(fileStateChart);
		InputStreamReader reader = new InputStreamReader(stateChart);
		BufferedReader br = new BufferedReader(reader);
		String line = br.readLine();

		DBInterface db = DBFactory.getDB();

		while (line != null) {
			String[] fields = line.split(Constants.DELIMITER_STATECHART_FILE);
			if (fields[0].equals(Constants.STATE)) {
				line = br.readLine();
				fields = line.split(Constants.DELIMITER_STATECHART_FILE);
				int id = db.getAllStates().size() + 1;
				int type = State.getType(fields[1]);
				db.insertOrUpdate(new State(id, entityID, fields[0], type));
			} else if (fields[0].equals(Constants.TRANSITION)) {
				line = br.readLine();
				fields = line.split(Constants.DELIMITER_STATECHART_FILE);
				int id = db.getAllTransition().size() + 1;
				State s = db.getState(entityID, fields[0]);
				db.insertOrUpdate(new Transition(id, s.getID(), fields[1],
						fields[2], fields[3], fields[4]));
			}
			line = br.readLine();
		}
		br.close();
	}

}
