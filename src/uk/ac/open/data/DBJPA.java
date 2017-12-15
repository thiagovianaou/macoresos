package uk.ac.open.data;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import uk.ac.open.behavior.statechart.State;
import uk.ac.open.behavior.statechart.Transition;
import uk.ac.open.data.models.Instance;
import uk.ac.open.data.models.RelaxStatements;
import uk.ac.open.data.models.RelaxStatementsID;
import uk.ac.open.data.models.Requirement;
import uk.ac.open.util.db.HibernateConnectionUtil;

public class DBJPA implements DBInterface {

	@Override
	public void insertOrUpdate(Instance i) throws HibernateException {
		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			session.saveOrUpdate(i);
			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Instance getInstance(int id) throws HibernateException {
		Instance i = null;

		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			i = (Instance) session.get(Instance.class, id);

			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return i;

	}

	@Override
	public void delete(Instance i) throws HibernateException {
		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			session.delete(i);
			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Instance> getAllInstances() throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from Instance");

		@SuppressWarnings("unchecked")
		List<Instance> us = q.list();

		return us;
	}

	@Override
	public List<Instance> getAllInstances(String entityID)
			throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from Instance WHERE entityID = '"+entityID+"' ");

		@SuppressWarnings("unchecked")
		List<Instance> us = q.list();

		return us;
	}

	@Override
	public void insertOrUpdate(State s) throws HibernateException {
		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			if(!existState(s)){
				session.saveOrUpdate(s);
				session.flush();
				t.commit();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public State getState(int id) throws HibernateException {
		State i = null;

		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			i = (State) session.get(State.class, id);

			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return i;
	}

	@Override
	public State getState(String entityID, String name)
			throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from State WHERE entityID = '"+entityID+"' AND name = '"+name+"' ");

		@SuppressWarnings("unchecked")
		List<State> us = q.list();

		return us.get(0);
	}

	@Override
	public State getInitialState(String entityID) throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from State WHERE entityID = '"+entityID+"' AND type=0 ");

		@SuppressWarnings("unchecked")
		List<State> us = q.list();

		return us.get(0);
	}

	@Override
	public void delete(State s) throws HibernateException {
		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			session.delete(s);
			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<State> getAllStates() throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from State");

		@SuppressWarnings("unchecked")
		List<State> us = q.list();

		return us;
	}

	@Override
	public List<State> getAllStates(String entityID)
			throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from State WHERE entityID = '"+entityID+"' ");

		@SuppressWarnings("unchecked")
		List<State> us = q.list();

		return us;
	}

	@Override
	public void insertOrUpdate(Transition tr) throws HibernateException {
		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();
			if(!existTransition(tr)){
				session.saveOrUpdate(tr);
				session.flush();
				t.commit();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Transition getTransition(int id) throws HibernateException {
		Transition i = null;

		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			i = (Transition) session.get(Transition.class, id);

			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return i;
	}

	@Override
	public Transition getTransition(int stateID, String event, String guard)
			throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from Transition WHERE stateID="+stateID+" AND event = '"+event+"' and guard = '"+guard+"' ");

		@SuppressWarnings("unchecked")
		List<Transition> us = q.list();

		return us.get(0);
	}

	@Override
	public void delete(Transition tr) throws HibernateException {
		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			session.delete(tr);
			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Transition> getAllTransition() throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from Transition");

		@SuppressWarnings("unchecked")
		List<Transition> us = q.list();

		return us;
	}

	@Override
	public List<Transition> getAllTransition(int stateID)
			throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from Transition WHERE stateID="+stateID);

		@SuppressWarnings("unchecked")
		List<Transition> us = q.list();

		return us;
	}

	@Override
	public void insertOrUpdate(Requirement r) throws HibernateException {
		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			session.saveOrUpdate(r);
			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Requirement getRequirement(String requirementCode)
			throws HibernateException {
		Requirement r = null;

		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			r = (Requirement) session.get(Requirement.class, requirementCode);

			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return r;
	}

	@Override
	public void delete(Requirement r) throws HibernateException {
		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			session.delete(r);
			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Requirement> getAllRequirement() throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from Requirement");

		@SuppressWarnings("unchecked")
		List<Requirement> us = q.list();

		return us;
	}

	@Override
	public void insertOrUpdate(RelaxStatements rs) throws HibernateException {
		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			session.saveOrUpdate(rs);
			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RelaxStatements getRelaxStatements(RelaxStatementsID id)
			throws HibernateException {
		RelaxStatements rs = null;

		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			rs = (RelaxStatements) session.get(RelaxStatements.class, id);

			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return rs;
	}

	@Override
	public void delete(RelaxStatements rs) throws HibernateException {
		try {
			Session session = HibernateConnectionUtil.getSession();
			Transaction t = session.beginTransaction();

			session.delete(rs);
			session.flush();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RelaxStatements> getAllRelaxStatements()
			throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from RelaxStatements");

		@SuppressWarnings("unchecked")
		List<RelaxStatements> us = q.list();

		return us;
	}

	@Override
	public List<RelaxStatements> getAllRelaxStatements(String requirementCode)
			throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from RelaxStatements WHERE requirementCode='"+requirementCode+"' ");

		@SuppressWarnings("unchecked")
		List<RelaxStatements> us = q.list();

		return us;
	}

	@Override
	public boolean existState(State s) throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from State WHERE entityID = '"+s.getEntityID()+"' AND name = '"+s.getName()+"' ");

		@SuppressWarnings("unchecked")
		List<State> us = q.list();

		return us.size() > 0;
	}

	@Override
	public boolean existTransition(Transition t) throws HibernateException {
		Session session = HibernateConnectionUtil.getSession();
		Query q = session.createQuery("from Transition WHERE stateID="+t.getStateID()+" AND event = '"+t.getEvent()+"' and guard = '"+t.getGuard()+"' ");

		@SuppressWarnings("unchecked")
		List<Transition> us = q.list();

		return us.size() > 0;
	}
}
