package es.uniovi.asw.business.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class CommandExecutor {

	public Object execute(Command cmd) throws BusinessException{
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		try {

			Object res = cmd.execute();
			
			trx.commit();
			return res;
		}catch (PersistenceException be) {
			if (trx.isActive())
				trx.rollback();
			throw be;
		}catch (BusinessException be) {
			if (trx.isActive())
				trx.rollback();
			throw be;
		} finally {

			if (mapper.isOpen())
				mapper.close();
		}
	}
}
