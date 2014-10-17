package com.dianping.zebra.group.filter.visitor;

import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.Visitable;
import com.foundationdb.sql.parser.Visitor;

/**
 * Created by Dozer on 10/8/14.
 */
public class MergeSqlVisitor implements Visitor {
    @Override
    public Visitable visit(Visitable node) throws StandardException {
        return null;
    }

    @Override
    public boolean visitChildrenFirst(Visitable node) {
        return false;
    }

    @Override
    public boolean stopTraversal() {
        return false;
    }

    @Override
    public boolean skipChildren(Visitable node) throws StandardException {
        return false;
    }
}
