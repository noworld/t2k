/*
	©2016 ARPANET.RED

	All rights reserved.

 * No Warranty * 
		1.1. "As-Is".
			The Software is provided "as is,"
			with all faults, defects and errors, and without
			warranty of any kind.

		1.2. No Liability.
			Licensor does not warrant that
			the Software will be free of bugs, errors, viruses
			or other defects, and Licensor shall have no
			liability of any kind for the use of or inability
			to use the software, the software content or any
			associated service.
 */

package red.arpanet.t2k.dao;

import static red.arpanet.t2k.util.LogUtil.d;
import static red.arpanet.t2k.util.LogUtil.e;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import red.arpanet.t2k.dao.model.account.T2kAccountStatus;
import red.arpanet.t2k.dao.model.account.T2kInvite;
import red.arpanet.t2k.dao.model.account.T2kRole;
import red.arpanet.t2k.dao.model.account.T2kUser;
import red.arpanet.t2k.util.CopyrightArpanet;
import red.arpanet.t2k.util.HashUtil;
import red.arpanet.t2k.util.TokenUtil;
import red.arpanet.t2k.util.valueobjects.RegistrationInfo;

@CopyrightArpanet
public class UserManager {

	private static final Logger LOG = Logger.getLogger(UserManager.class);

	private static final String PERSISTENCE_UNIT_NAME = "t2k_v1";
	private static final String DEFAULT_ROLE = "newbie";
	private static final String DEFAULT_STATUS = "pending";

	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	public static T2kUser getUserById(String userId) {

		T2kUser user = null;

		EntityManager em = EMF.createEntityManager();

		TypedQuery<T2kUser> query = em.createNamedQuery("FindUserByUserId",T2kUser.class);
		query.setParameter("userId", userId);

		try {			
			user = query.getSingleResult();
		} catch(NonUniqueResultException | NoResultException e) {
			d(LOG,String.format("Exception encountered retrieving user info. (User: %s, Error: %s)", userId, e.getClass().getSimpleName()));
		} finally {
			closeEm(em);
		}

		return user;

	}

	public static T2kInvite getInviteForTokenAndEmail(String token, String email) {

		T2kInvite invite = null;

		EntityManager em = EMF.createEntityManager();

		TypedQuery<T2kInvite> query = em.createNamedQuery("FindInviteByTokenAndEmail",T2kInvite.class);
		query.setParameter("token", token);
		query.setParameter("email", email);

		try {
			invite = query.getSingleResult();
		} catch(NonUniqueResultException | NoResultException e) {
			e(LOG,String.format("Exception encountered retrieving invite info. (E-Mail: %s, Error: %s)",email, e.getClass().getSimpleName()));
		} finally {
			closeEm(em);
		}

		return invite;
	}

	public static T2kInvite createInviteWithNewToken(String email) {

		if(email == null) {
			return null;
		}

		T2kInvite invite = new T2kInvite();

		invite.setEmailAddress(email);
		invite.setInviteToken(TokenUtil.createToken());
		invite.setInviteTimestamp(Timestamp.from(Instant.now()));
		invite.setRegistrationTimestamp(null);

		EntityManager em = EMF.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(invite);
			et.commit();
		} catch(Exception e) {			
			e(LOG,String.format("Exception encountered creating invite. (E-Mail: %s)",email),e);
			invite = null;
			et.rollback();
		} finally {
			closeEm(em);
		}

		return invite;
	}

	public static boolean registerNewUser(RegistrationInfo registration) {

		boolean success = false;

		T2kUser newUser = new T2kUser();

		byte[] salt, passwordHash;

		salt = HashUtil.generateSalt();
		passwordHash = HashUtil.hashPassword(registration.getPassword(), salt);

		newUser.setEmailAddress(registration.getEmail());
		newUser.setFirstName(registration.getFirstName());
		newUser.setLastName(registration.getLastName());	
		newUser.setUserId(registration.getUserId());
		newUser.setSalt(salt);
		newUser.setPassword(passwordHash);				
		newUser.setCreatedTimestamp(Timestamp.from(Instant.now()));
		newUser.setLastUpdateTimestamp(Timestamp.from(Instant.now()));

		EntityManager em = EMF.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {

			et.begin();			

			T2kInvite invite = getInviteForTokenAndEmail(registration.getInviteToken(), registration.getEmail());

			if(invite != null) {

				T2kAccountStatus status = getAccountStatusByName(DEFAULT_STATUS);
				T2kRole  role = getRoleByName(DEFAULT_ROLE);
				newUser.setAccountStatus(status);
				List<T2kRole> roles = new ArrayList<T2kRole>(1);
				roles.add(role);
				newUser.setRoles(roles);
				em.persist(newUser);
				invite.setRegistrationTimestamp(Timestamp.from(Instant.now()));			
				em.merge(invite);								
			}

			et.commit();
			
			success = true;

		} catch(Exception e) {			
			e(LOG,String.format("Exception encountered registering user. (E-Mail: %s)",registration.getEmail()),e);
			et.rollback();
		} finally {
			closeEm(em);		
		}

		return success;
	}

	public static T2kAccountStatus getAccountStatusByName(String name) {

		if(name == null) {
			return null;
		}

		name = name.toLowerCase();

		T2kAccountStatus status = null;

		EntityManager em = EMF.createEntityManager();

		TypedQuery<T2kAccountStatus> query = em.createNamedQuery("FindAccountStatusByName",T2kAccountStatus.class);
		query.setParameter("statusName", name);

		try {			
			status = query.getSingleResult();
		} catch(NonUniqueResultException | NoResultException e) {
			e(LOG,String.format("Exception encountered retrieving account status. (Status Name: %s, Error: %s)", name, e.getClass().getSimpleName()));
		} finally {
			closeEm(em);
		}

		return status;

	}

	public static T2kRole getRoleByName(String name) {

		if(name == null) {
			return null;
		}

		name = name.toLowerCase();

		T2kRole role = null;

		EntityManager em = EMF.createEntityManager();

		TypedQuery<T2kRole> query = em.createNamedQuery("FindRoleByName",T2kRole.class);
		query.setParameter("roleName", name);

		try {			
			role = query.getSingleResult();
		} catch(NonUniqueResultException | NoResultException e) {
			e(LOG,String.format("Exception encountered retrieving account status. (Role Name: %s, Error: %s)", name, e.getClass().getSimpleName()));
		} finally {
			closeEm(em);
		}

		return role;

	}

	public static void updateLastLogin(String userId) {

		EntityManager em = EMF.createEntityManager();
		EntityTransaction et = em.getTransaction();
		T2kUser user = null;		

		try {			

			TypedQuery<T2kUser> query = em.createNamedQuery("FindUserByUserId",T2kUser.class);
			query.setParameter("userId", userId);

			et.begin();

			user = query.getSingleResult();

			if(user != null) {

				user.setLastLoginTimestamp(Timestamp.from(Instant.now()));
				em.merge(user);

				et.commit();
			}

		} catch(NonUniqueResultException | NoResultException e) {			
			e(LOG,String.format("Exception encountered trying to update last login.  (User: %s, Error: %s)", userId, e.getClass().getSimpleName()));
			et.rollback();
		} finally {
			closeEm(em);
		}

	}
	
	public static T2kUser updateUserWithToken(String email) {

		if(email == null) {
			return null;
		}
		
		EntityManager em = EMF.createEntityManager();	
		EntityTransaction et = em.getTransaction();
		T2kUser user = null;
		
		try {
			TypedQuery<T2kUser> query = em.createNamedQuery("FindUserByEmailAddress",T2kUser.class);
			query.setParameter("email", email);
			user = query.getSingleResult();
			
			if(user == null) {
				return null;
			}
			
			user.setRecoveryToken(TokenUtil.createToken());

			et.begin();
			user = em.merge(user);
			et.commit();
			
		} catch(NonUniqueResultException | NoResultException e) {
			d(LOG,String.format("E-mail address not found for password recovery. (E-mail: %s, Error: %s)", email, e.getClass().getSimpleName()));
		} catch(Exception e) {			
			e(LOG,String.format("Exception encountered creating account recovery token for user. (E-Mail: %s)",email),e);
			user = null;
			et.rollback();
		} finally {
			closeEm(em);
		}

		return user;
	}
	
	private static void closeEm(EntityManager em) {
		if(em != null && em.isOpen()) {
			em.close();
		}
	}

}
