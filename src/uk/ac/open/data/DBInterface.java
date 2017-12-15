package uk.ac.open.data;

import java.util.List;

import org.hibernate.HibernateException;

import uk.ac.open.behavior.statechart.State;
import uk.ac.open.behavior.statechart.Transition;
import uk.ac.open.data.models.Instance;
import uk.ac.open.data.models.RelaxStatements;
import uk.ac.open.data.models.RelaxStatementsID;
import uk.ac.open.data.models.Requirement;

public interface DBInterface {
	
	public void insertOrUpdate(Instance i) throws HibernateException; 
	public Instance getInstance(int id) throws HibernateException;
	public void delete(Instance i) throws HibernateException;
	public List<Instance> getAllInstances() throws HibernateException;
	public List<Instance> getAllInstances(String entityID) throws HibernateException;

	public void insertOrUpdate(State s) throws HibernateException; 
	public State getState(int id) throws HibernateException;
	public State getState(String entityID, String name) throws HibernateException;
	public State getInitialState(String entityID) throws HibernateException;
	public void delete(State s) throws HibernateException;
	public List<State> getAllStates() throws HibernateException;
	public List<State> getAllStates(String entityID) throws HibernateException;
	public boolean existState(State s) throws HibernateException;
	
	public void insertOrUpdate(Transition t) throws HibernateException; 
	public Transition getTransition(int id) throws HibernateException;
	public Transition getTransition(int stateID, String event, String guard) throws HibernateException;
	public void delete(Transition t) throws HibernateException;
	public List<Transition> getAllTransition() throws HibernateException;
	public List<Transition> getAllTransition(int stateID) throws HibernateException;
	public boolean existTransition(Transition t) throws HibernateException;
	
	public void insertOrUpdate(Requirement r) throws HibernateException; 
	public Requirement getRequirement(String  requirementCode) throws HibernateException;
	public void delete(Requirement r) throws HibernateException;
	public List<Requirement> getAllRequirement() throws HibernateException;

	public void insertOrUpdate(RelaxStatements rs) throws HibernateException; 
	public RelaxStatements getRelaxStatements(RelaxStatementsID id) throws HibernateException;
	public void delete(RelaxStatements rs) throws HibernateException;
	public List<RelaxStatements> getAllRelaxStatements() throws HibernateException;
	public List<RelaxStatements> getAllRelaxStatements(String  requirementCode) throws HibernateException;
}
