/*
	©2016 ARPANET.RED
	
	All rights reserved.
	
	* No Warranty * 
		1.1. "As-Is".
			The Software is provided "as is,"
			with all faults, defects and errors, and without
			warranty of any kind.
			
		1.1. No Liability.
			Licensor does not warrant that
			the Software will be free of bugs, errors, viruses
			or other defects, and Licensor shall have no
			liability of any kind for the use of or inability
			to use the software, the software content or any
			associated service.
*/

package red.arpanet.t2k.dao;

import static red.arpanet.t2k.util.LogUtil.e;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import red.arpanet.t2k.dao.model.T2kEnumeratedValue;

public class ValueManager extends BaseDaoManager  {
	
	private static final Logger LOG = Logger.getLogger(ValueManager.class);

	public static List<T2kEnumeratedValue> findRootGroups() {
		List<T2kEnumeratedValue> values = null;
		
		EntityManager em = EMF.createEntityManager();

		TypedQuery<T2kEnumeratedValue> query = em.createNamedQuery("FindRootGroups",T2kEnumeratedValue.class);

		try {			
			values = query.getResultList();
		} catch(Exception e) {
			e(LOG,"Exception encountered accessing root enumerated value groups.",e);
		} finally {
			closeEm(em);
		}
		
		return values;
	}

	public static List<T2kEnumeratedValue> findGroupById(int id) {
		List<T2kEnumeratedValue> values = null;
		
		EntityManager em = EMF.createEntityManager();

		TypedQuery<T2kEnumeratedValue> query = em.createNamedQuery("FindGroupById",T2kEnumeratedValue.class);
		query.setParameter("id", id);

		try {			
			values = query.getResultList();
		} catch(Exception e) {
			e(LOG,"Exception encountered accessing enumerated value groups by id.",e);
		} finally {
			closeEm(em);
		}
		
		return values;
	}
	
	
}
