package com.dianping.zebra.admin.datasource;

import java.util.HashMap;
import java.util.Map;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.DataObject;
import org.unidal.dal.jdbc.QueryDef;
import org.unidal.dal.jdbc.engine.DefaultQueryContext;
import org.unidal.dal.jdbc.engine.QueryContext;
import org.unidal.dal.jdbc.entity.EntityInfo;
import org.unidal.dal.jdbc.entity.EntityInfoManager;
import org.unidal.dal.jdbc.transaction.TransactionManager;
import org.unidal.lookup.annotation.Inject;

public class TransactionManagerWrapper {
	@Inject
	private EntityInfoManager m_entityManager;

	@Inject
	private TransactionManager m_transactionManager;

	public static final String HINT_QUERY_TYPE = "QUERY_TYPE";

	public <T extends DataObject> void startTransaction(QueryDef query, T proto) throws DalException {
		QueryContext ctx = createContext(query, proto);

		m_transactionManager.startTransaction(ctx);
	}

	public <T extends DataObject> void commitTransaction(QueryDef query, T proto) throws DalException {
		QueryContext ctx = createContext(query, proto);

		m_transactionManager.commitTransaction(ctx);
	}

	public <T extends DataObject> void rollbackTransaction(QueryDef query, T proto) throws DalException {
		QueryContext ctx = createContext(query, proto);

		m_transactionManager.rollbackTransaction(ctx);
	}

	protected <T extends DataObject> QueryContext createContext(QueryDef query, T proto) {
		QueryContext ctx = new DefaultQueryContext();
		EntityInfo enityInfo = m_entityManager.getEntityInfo(query.getEntityClass());
		Map<String, Object> queryHints = getQueryHints(query, proto);

		ctx.setQuery(query);
		ctx.setProto(proto);
		ctx.setEntityInfo(enityInfo);
		ctx.setQueryHints(queryHints);

		return ctx;
	}

	protected Map<String, Object> getQueryHints(QueryDef query, DataObject proto) {
		Map<String, Object> hints = proto.getQueryHints();

		if (hints == null) {
			hints = new HashMap<String, Object>(3);
		}

		hints.put(HINT_QUERY_TYPE, query.getType());

		return hints;
	}

}
