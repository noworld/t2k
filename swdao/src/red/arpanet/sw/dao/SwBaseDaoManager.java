/*
	�2016 ARPANET.RED
	
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

package red.arpanet.sw.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class SwBaseDaoManager {

	public static final String PERSISTENCE_UNIT_NAME = "sw_v1";

	protected static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	protected static void closeEm(EntityManager em) {
		if(em != null && em.isOpen()) {
			em.close();
		}
	}
	
}
