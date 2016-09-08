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
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import red.arpanet.t2k.dao.model.T2kEnumeratedValue;
import red.arpanet.t2k.dao.model.character.T2kNationality;
import red.arpanet.t2k.dao.model.character.T2kNativeLanguage;
import red.arpanet.t2k.dao.model.character.T2kSkill;

public class CharacterManager extends BaseDaoManager  {
	
	private static final Logger LOG = Logger.getLogger(CharacterManager.class);

	public static List<T2kNationality> getNationalities() {
		
		List<T2kNationality> nationalities = null;

		EntityManager em = EMF.createEntityManager();

		TypedQuery<T2kNationality> query = em.createNamedQuery("FindAllNationalities",T2kNationality.class);

		try {			
			nationalities = query.getResultList();
		} catch(Exception e) {
			e(LOG,String.format("Exception encountered retrieving nationalities list. (Error: %s - %s)", e.getClass().getSimpleName(), e.getMessage()));
		} finally {
			closeEm(em);
		}

		return nationalities;
	}
	
	public static List<T2kNationality> findNationalitiesByFaction(T2kEnumeratedValue faction) {
		List<T2kNationality> nationalities = null;
		
		EntityManager em = EMF.createEntityManager();

		TypedQuery<T2kNationality> query = em.createNamedQuery("FindNationalitiesByFaction",T2kNationality.class);
		query.setParameter("faction", faction);

		try {			
			nationalities = query.getResultList();
		} catch(Exception e) {
			e(LOG,String.format("Exception encountered accessing nationalities by faction. (Error: %s - %s)", e.getClass().getSimpleName(), e.getMessage()));
		} finally {
			closeEm(em);
		}
		
		return nationalities;
	}
	
	public static List<T2kSkill> findSkillsByNameList(List<String> nameList) {
		List<T2kSkill> skills = null;
		
		EntityManager em = EMF.createEntityManager();

		TypedQuery<T2kSkill> query = em.createNamedQuery("FindSkillByNameList",T2kSkill.class);
		query.setParameter("nameList", nameList);

		try {			
			skills = query.getResultList();
		} catch(Exception e) {
			e(LOG,String.format("Exception encountered finding skills by list of skill names. (Error: %s - %s)", e.getClass().getSimpleName(), e.getMessage()));
		} finally {
			closeEm(em);
		}
		
		return skills;
	}
	
	public static Set<T2kNativeLanguage> findLanguagesByNationalityId(int id) {
		T2kNationality nationality = null;
		Set<T2kNativeLanguage> languages = null;
		
		EntityManager em = EMF.createEntityManager();

		TypedQuery<T2kNationality> query = em.createNamedQuery("FindNationalityById",T2kNationality.class);
		query.setParameter("id", id);

		try {			
			nationality = query.getSingleResult();
			
			if(nationality != null && nationality.getNativeLanguages() != null) {
				languages = nationality.getNativeLanguages();
			}
			
		} catch(Exception e) {
			e(LOG,String.format("Exception encountered finding skills by list of skill names. (Error: %s - %s)", e.getClass().getSimpleName(), e.getMessage()));
		} finally {
			closeEm(em);
		}
		
		return languages;
	}
	
}
